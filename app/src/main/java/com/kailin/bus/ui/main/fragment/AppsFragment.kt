package com.kailin.bus.ui.main.fragment

import com.kailin.bus.R
import com.kailin.bus.databinding.MainAppsFragmentBinding
import com.kailin.architecture_model.architecture.ArchitectureFragment

class AppsFragment : ArchitectureFragment<MainAppsFragmentBinding, AppsViewModel>() {

    override val layoutRes: Int
        get() = R.layout.main_apps_fragment
    override val viewModelClass: Class<AppsViewModel>
        get() = AppsViewModel::class.java

    override fun initBindings() {

    }
}
