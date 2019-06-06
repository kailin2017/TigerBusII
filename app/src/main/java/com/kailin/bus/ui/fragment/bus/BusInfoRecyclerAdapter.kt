package com.kailin.bus.ui.fragment.bus

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kailin.bus.MyApplication
import com.kailin.bus.R
import com.kailin.bus.data.bus.display.BusDisplayStopOfRoute
import com.kailin.bus.data.bus.estimate.BusN1EstimateTime
import com.kailin.bus.data.bus.display.Stop
import com.kailin.bus.data.bus.estimate.BusStopStatus
import com.kailin.bus.databinding.BusInfoFragmentItemBinding

class BusInfoRecyclerAdapter(private val busDisplayStopOfRoute: List<BusDisplayStopOfRoute>) : RecyclerView.Adapter<BusInfoRecyclerViewHolder>() {

    private val inflater = LayoutInflater.from(MyApplication.instance)
    private val estimates = ArrayList<BusN1EstimateTime>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusInfoRecyclerViewHolder
            = BusInfoRecyclerViewHolder(BusInfoFragmentItemBinding.inflate(inflater, parent, false))

    override fun getItemCount(): Int = 0

    override fun onBindViewHolder(holder: BusInfoRecyclerViewHolder, position: Int) {
//        val stop = busDisplayStopOfRoute.stops?.get(position)
//        val estimate = estimateTimeMap[stop]
//        holder.bind(stop, estimate)
    }

    fun setData(newEstimateTimeMap: HashMap<Stop, BusN1EstimateTime>) {
//        estimateTimeMap.clear()
//        estimateTimeMap.putAll(estimateTimeMap)
    }
}

class BusInfoRecyclerViewHolder(val binding: BusInfoFragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context

    fun bind(stop: Stop?, estimateTime: BusN1EstimateTime?) {
        binding.stop = stop

        if (estimateTime == null)
            return
        binding.estimate.text = when (estimateTime.stopStatus) {
            BusStopStatus.OK -> {
                val min = estimateTime.estimateTime!! % 60
                when {
                    min == null -> context.getString(estimateTime.stopStatus!!.stringRes)
                    min > 0 -> context.getString(R.string.min, min)
                    else -> context.getString(R.string.real_time)
                }
            }
            else -> context.getString(estimateTime.stopStatus!!.stringRes)
        }
        binding.executePendingBindings()
    }
}