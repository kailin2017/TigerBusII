package com.kailin.bus.ui.fragment.bus

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.kailin.architecture_model.architecture.ArchitectureFragment
import com.kailin.bus.R
import com.kailin.bus.data.bus.route.BusRoute
import com.kailin.bus.databinding.BusFragmentBinding

class BusFragment : ArchitectureFragment<BusFragmentBinding, BusViewModel>(), SearchView.OnQueryTextListener {

    private lateinit var searchView: SearchView
    private lateinit var busAdapter: BusAdapter

    override val layoutRes: Int
        get() = R.layout.bus_fragment
    override val viewModelClass: Class<BusViewModel>
        get() = BusViewModel::class.java

    override fun initBindings() {
        setSupportActionBar(binding.toolbar)
        busAdapter = BusAdapter(View.OnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(BusRoute.BUS_ROUTE, it.tag as BusRoute)
            Navigation.findNavController(it)
                    .navigate(R.id.action_busFragment_to_busInfoFragment,bundle)
        })
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = busAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.search, menu)
        searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.setIconifiedByDefault(true)
        searchView.setOnQueryTextListener(this)
        viewModel.busRoutesObserver(this, Observer { this.busRoutesOnChanged(it) })
    }

    private fun busRoutesOnChanged(busRoutes: List<BusRoute>) {
        busAdapter.setData(busRoutes)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.queryBus(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.queryBus(newText)
        return true
    }
}
