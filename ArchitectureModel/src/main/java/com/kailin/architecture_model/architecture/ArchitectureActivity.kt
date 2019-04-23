package com.kailin.architecture_model.architecture

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi

import com.kailin.architecture_model.R
import com.kailin.architecture_model.architecture.interfaces.ArchitectureInterface
import com.kailin.architecture_model.architecture.interfaces.OnRequestPermissionsResult
import com.kailin.architecture_model.architecture.interfaces.PermissionInterface
import com.kailin.architecture_model.architecture.interfaces.ReceiverInterface
import com.kailin.architecture_model.receiver.ShutdownReceiver
import com.kailin.architecture_model.util.CheckVersionUtil
import com.kailin.architecture_model.util.LoggerUtil

import java.util.ArrayList
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

abstract class ArchitectureActivity<B : ViewDataBinding, VM : ArchitectureViewModel> : AppCompatActivity(), OnRequestPermissionsResult, PermissionInterface, ReceiverInterface, ArchitectureInterface<VM> {

    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected lateinit var context: Context
    private val broadcastReceivers = ArrayList<BroadcastReceiver>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val clazz = viewModelClass
        if (clazz != null) {
            viewModel = ViewModelProviders.of(this).get(viewModelClass)
            lifecycle.addObserver(viewModel)
            viewModel.throwableLiveData.observe(this, Observer<Throwable> { this.onRxThrowable(it) })
            viewModel.showLoading.observe(this, Observer<Boolean> { this.onShowLoading(it) })
        }
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
        context = this
        registerReceiver(ShutdownReceiver(), ShutdownReceiver.ACTION_SHUTOWN)
        initBindings()
    }

    override fun onDestroy() {
        super.onDestroy()
        for (broadcastReceiver in broadcastReceivers) {
            unRegisterReceiver(broadcastReceiver)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun checkPermission(requestCode: Int, vararg permissions: String) {
        if (CheckVersionUtil.getInstance().isBelowM)
            return
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permissions, 0)
                return
            }
        }
        onPermissionGranted(requestCode, permissions as Array<String>, intArrayOf())
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (grantResult in grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                onPermissionDenied(requestCode, permissions, grantResults)
                return
            }
        }
        onPermissionGranted(requestCode, permissions, grantResults)
    }

    override fun onPermissionGranted(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {}

    override fun onPermissionDenied(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {}

    override fun registerReceiver(receiver: BroadcastReceiver, vararg actions: String) {
        val intentFilter = IntentFilter()
        for (action in actions) {
            intentFilter.addAction(action)
        }
        registerReceiver(receiver, intentFilter)
        broadcastReceivers.add(receiver)
    }

    override fun unRegisterReceiver(receiver: BroadcastReceiver) {
        super.unregisterReceiver(receiver)
        broadcastReceivers.remove(receiver)
    }

    override fun onRxThrowable(throwable: Throwable) {
        LoggerUtil.getInstance().e(throwable, throwable.message!!)
        val alertDialog = AlertDialog.Builder(context)
                .setMessage(throwable.message)
                .setPositiveButton(R.string.button_ok) { d, i -> }
                .create()
        alertDialog.show()
    }

    override fun onShowLoading(isLoading: Boolean?) {

    }
}
