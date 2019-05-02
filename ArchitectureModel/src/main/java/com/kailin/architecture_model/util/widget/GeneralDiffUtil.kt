package com.kailin.utillibrary.widget

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.atomic.AtomicReference

class GeneralDiffUtil private constructor() {

    fun <T> diff(adapter: RecyclerView.Adapter<*>, oldData: List<T>, newData: List<T>) {
        val diffResult = DiffUtil.calculateDiff(
                MyDiffCallBack(oldData, newData), true
        )
        diffResult.dispatchUpdatesTo(adapter)
    }

    private class MyDiffCallBack<T>(private val oldData: List<T>, private val newData: List<T>) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldData.size
        }

        override fun getNewListSize(): Int {
            return newData.size
        }

        override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            return oldData[oldPosition] == newData[newPosition]
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            return oldData[oldPosition] == newData[newPosition]
        }

        override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
            return null
        }
    }

    companion object {

        private val reference = AtomicReference<GeneralDiffUtil>()

        val instance: GeneralDiffUtil
            get() {
                while (true) {
                    var instance: GeneralDiffUtil? = reference.get()
                    if (instance != null)
                        return instance

                    instance = GeneralDiffUtil()
                    if (reference.compareAndSet(null, instance))
                        return instance
                }
            }
    }
}
