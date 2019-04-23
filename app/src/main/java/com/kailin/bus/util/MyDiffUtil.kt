package com.kailin.bus.util

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.atomic.AtomicReference

class MyDiffUtil {

    fun <T> setDiff(adapter: RecyclerView.Adapter<*>, oldData: List<T>, newData: List<T>) {
        setDiff(adapter, MyDiffCallBack(oldData, newData))
    }

    fun <T> setDiff(adapter: RecyclerView.Adapter<*>, callBack: MyDiffCallBack<T>) {
        val diffResult = DiffUtil.calculateDiff(callBack, true)
        diffResult.dispatchUpdatesTo(adapter)
    }

    class MyDiffCallBack<T> constructor(private val oldData: List<T>, private val newData: List<T>) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldData.size
        }

        override fun getNewListSize(): Int {
            return newData.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldT = oldData[oldItemPosition]
            val newT = newData[newItemPosition]
            return oldT == newT
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return areItemsTheSame(oldItemPosition, newItemPosition)
        }
    }

    companion object {

        private val reference = AtomicReference<MyDiffUtil>()

        val instance: MyDiffUtil
            get() {
                while (true) {
                    var instance: MyDiffUtil? = reference.get()
                    if (instance != null)
                        return instance

                    instance = MyDiffUtil()
                    if (reference.compareAndSet(null, instance))
                        return instance
                }
            }
    }
}
