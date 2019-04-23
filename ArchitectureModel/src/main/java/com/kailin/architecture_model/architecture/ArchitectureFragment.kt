package com.kailin.architecture_model.architecture

import android.content.BroadcastReceiver
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kailin.architecture_model.R
import com.kailin.architecture_model.architecture.interfaces.ArchitectureInterface
import com.kailin.architecture_model.architecture.interfaces.FragmentInterface
import com.kailin.architecture_model.architecture.interfaces.OnRequestPermissionsResult
import com.kailin.architecture_model.architecture.interfaces.PermissionInterface
import com.kailin.architecture_model.architecture.interfaces.ReceiverInterface
import com.kailin.architecture_model.util.CheckVersionUtil
import com.kailin.architecture_model.util.LoggerUtil
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

abstract class ArchitectureFragment<B : ViewDataBinding, VM : ArchitectureViewModel> : Fragment(), OnRequestPermissionsResult, PermissionInterface, ReceiverInterface, ArchitectureInterface<VM> {

    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected lateinit var fragmentInterface: FragmentInterface
    protected lateinit var receiverInterface: ReceiverInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val clazz = viewModelClass
        if (clazz != null) {
            viewModel = ViewModelProviders.of(this).get(viewModelClass)
            lifecycle.addObserver(viewModel)
            viewModel.throwableLiveData.observe(this, Observer<Throwable> { this.onRxThrowable(it) })
            viewModel.showLoading.observe(this, Observer<Boolean> { this.onShowLoading(it) })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (context is FragmentInterface) {
            fragmentInterface = (context as FragmentInterface?)!!
        }
        if (context is ReceiverInterface) {
            receiverInterface = (context as ReceiverInterface?)!!
        }
        initBindings()
    }

    fun setSupportActionBar(toolbar: Toolbar) {
        if (activity is AppCompatActivity) {
            setHasOptionsMenu(true)
            val appCompatActivity = activity as AppCompatActivity?
            appCompatActivity!!.setSupportActionBar(toolbar)
        }
    }


    override fun checkPermission(requestCode: Int, vararg permissions: String) {
        if (CheckVersionUtil.getInstance().isBelowM)
            return
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(context!!, permission) == PackageManager.PERMISSION_DENIED) {
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
        receiverInterface.registerReceiver(receiver, *actions)
    }

    override fun unRegisterReceiver(receiver: BroadcastReceiver) {
        receiverInterface.unRegisterReceiver(receiver)
    }

    override fun onRxThrowable(throwable: Throwable) {
        LoggerUtil.getInstance().e(throwable, throwable.message!!)
        val alertDialog = AlertDialog.Builder(context!!)
                .setMessage(throwable.message)
                .setPositiveButton(R.string.button_ok) { d, i -> }
                .create()
        alertDialog.show()
    }

    override fun onShowLoading(isLoading: Boolean?) {

    }
}
