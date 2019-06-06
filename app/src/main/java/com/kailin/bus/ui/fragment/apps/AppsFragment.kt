package com.kailin.bus.ui.fragment.apps

import com.kailin.architecture_model.architecture.ArchitectureFragment
import com.kailin.bus.R
import com.kailin.bus.databinding.AppsFragmentBinding

class AppsFragment : ArchitectureFragment<AppsFragmentBinding, AppsViewModel>() {

    override val layoutRes: Int
        get() = R.layout.apps_fragment
    override val viewModelClass: Class<AppsViewModel>
        get() = AppsViewModel::class.java

    override fun initBindings() {

    }
}
