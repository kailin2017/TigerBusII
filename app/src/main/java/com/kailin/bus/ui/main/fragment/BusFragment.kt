package com.kailin.bus.ui.main.fragment

import android.view.Menu
import android.view.MenuInflater

import com.kailin.bus.R
import com.kailin.bus.data.bus.route.BusRoute
import com.kailin.bus.databinding.MainBusFragmentBinding
import com.kailin.architecture_model.architecture.ArchitectureFragment

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

class BusFragment : ArchitectureFragment<MainBusFragmentBinding, BusViewModel>(), SearchView.OnQueryTextListener {

    private lateinit var searchView: SearchView
    private val busAdapter = BusAdapter()

    override val layoutRes: Int
        get() = R.layout.main_bus_fragment
    override val viewModelClass: Class<BusViewModel>
        get() = BusViewModel::class.java

    override fun initBindings() {
        setSupportActionBar(binding.toolbar)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = busAdapter
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
