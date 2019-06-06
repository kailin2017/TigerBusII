package com.kailin.bus.ui.fragment.bus

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kailin.bus.MyApplication
import com.kailin.bus.databinding.BusInfoFragmentPager2Binding

class BusInfoPager2Adapter(private val adapters: ArrayList<BusInfoRecyclerAdapter>) : RecyclerView.Adapter<BusInfoPager2ViewHolder>() {

    private val inflater = LayoutInflater.from(MyApplication.instance)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusInfoPager2ViewHolder
            = BusInfoPager2ViewHolder(BusInfoFragmentPager2Binding.inflate(inflater))

    override fun getItemCount(): Int = adapters.size

    override fun onBindViewHolder(holder: BusInfoPager2ViewHolder, position: Int) {
        holder.setAdapter(adapters[position])
    }
}

class BusInfoPager2ViewHolder(private val binding: BusInfoFragmentPager2Binding) : RecyclerView.ViewHolder(binding.root) {

    fun setAdapter(adapter: BusInfoRecyclerAdapter){
        binding.recyclerView.adapter = adapter
    }
}
