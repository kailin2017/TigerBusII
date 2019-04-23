package com.kailin.bus.ui.main

import android.Manifest
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.kailin.bus.R
import com.kailin.bus.base.BaseActivity
import com.kailin.bus.databinding.MainActivityBinding

class MainActivity : BaseActivity<MainActivityBinding, MainViewModel>() {

    override val layoutRes: Int
        get() = R.layout.main_activity
    override val viewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    override fun initBindings() {
        val hostFragment = supportFragmentManager.findFragmentById(R.id.navigationFragment) as NavHostFragment?
        NavigationUI.setupWithNavController(binding.bottomNavigationView, hostFragment!!.navController)
    }

    override fun onResume() {
        super.onResume()
        checkPermission(0, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    override fun onPermissionGranted(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        viewModel.getBusVersion()
    }

    override fun onPermissionDenied(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        finish()
    }
}
