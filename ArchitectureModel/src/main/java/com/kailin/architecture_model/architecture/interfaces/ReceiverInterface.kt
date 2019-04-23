package com.kailin.architecture_model.architecture.interfaces

import android.content.BroadcastReceiver

interface ReceiverInterface {

    fun registerReceiver(receiver: BroadcastReceiver, vararg actions: String)

    fun unRegisterReceiver(receiver: BroadcastReceiver)
}
