package com.kailin.bus.ui.main.fragment

import android.view.LayoutInflater
import android.view.ViewGroup

import com.kailin.bus.MyApplication
import com.kailin.bus.data.bus.route.BusRoute
import com.kailin.bus.databinding.MainBusItemBinding

import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView
import com.kailin.utillibrary.widget.GeneralDiffUtil

class BusAdapter : RecyclerView.Adapter<BusAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(MyApplication.instance)
    private val busRoutes = ArrayList<BusRoute>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MainBusItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(busRoutes[position])
    }

    override fun getItemCount(): Int {
        return busRoutes.size
    }

    fun setData(newBusRoute: List<BusRoute>) {
        GeneralDiffUtil.instance.diff(this, busRoutes, newBusRoute)
        busRoutes.clear()
        busRoutes.addAll(newBusRoute)
        notifyDataSetChanged()
    }

    class ViewHolder(private val mainBusItemBinding: MainBusItemBinding) : RecyclerView.ViewHolder(mainBusItemBinding.root) {

        fun bind(busRoute: BusRoute) {
            mainBusItemBinding.busRoute = busRoute
            mainBusItemBinding.executePendingBindings()
        }
    }
}
