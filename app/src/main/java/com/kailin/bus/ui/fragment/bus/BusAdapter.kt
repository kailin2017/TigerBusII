package com.kailin.bus.ui.fragment.bus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kailin.bus.MyApplication
import com.kailin.bus.data.bus.route.BusRoute
import com.kailin.bus.databinding.BusFragmentItemBinding
import com.kailin.utillibrary.widget.GeneralDiffUtil
import java.util.*

class BusAdapter(private val onClickListener: View.OnClickListener) : RecyclerView.Adapter<BusViewHolder>() {

    private val layoutInflater = LayoutInflater.from(MyApplication.instance)
    private val busRoutes = ArrayList<BusRoute>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusViewHolder
            = BusViewHolder(BusFragmentItemBinding.inflate(layoutInflater, parent, false), onClickListener)


    override fun onBindViewHolder(holder: BusViewHolder, position: Int) {
        holder.bind(busRoutes[position])
    }

    override fun getItemCount(): Int = busRoutes.size

    fun setData(newBusRoute: List<BusRoute>) {
        GeneralDiffUtil.instance.diff(this, busRoutes, newBusRoute)
        busRoutes.clear()
        busRoutes.addAll(newBusRoute)
        notifyDataSetChanged()
    }
}


class BusViewHolder(private val binding: BusFragmentItemBinding, onClickListener: View.OnClickListener) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.onClickListener = onClickListener
    }

    fun bind(busRoute: BusRoute) {
        binding.busRoute = busRoute
        binding.executePendingBindings()
    }
}
