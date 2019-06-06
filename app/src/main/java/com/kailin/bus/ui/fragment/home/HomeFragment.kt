package com.kailin.bus.ui.fragment.home

import com.kailin.architecture_model.architecture.ArchitectureFragment
import com.kailin.bus.R
import com.kailin.bus.databinding.HomeFragmentBinding

class HomeFragment : ArchitectureFragment<HomeFragmentBinding, HomeViewModel>() {

    override val layoutRes: Int
        get() = R.layout.home_fragment
    override val viewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun initBindings() {

    }
}
