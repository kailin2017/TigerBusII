package com.kailin.bus.ui.main.fragment

import com.kailin.bus.R
import com.kailin.bus.databinding.MainHomeFragmentBinding
import com.kailin.architecture_model.architecture.ArchitectureFragment

class HomeFragment : ArchitectureFragment<MainHomeFragmentBinding, HomeViewModel>() {

    override val layoutRes: Int
        get() = R.layout.main_home_fragment
    override val viewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun initBindings() {

    }
}
