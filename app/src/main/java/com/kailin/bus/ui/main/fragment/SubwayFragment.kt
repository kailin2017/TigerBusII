package com.kailin.bus.ui.main.fragment

import com.kailin.architecture_model.architecture.ArchitectureFragment
import com.kailin.bus.R
import com.kailin.bus.databinding.MainSubwayFragmentBinding

class SubwayFragment : ArchitectureFragment<MainSubwayFragmentBinding, SubwayViewModel>() {

    override val layoutRes: Int
        get() = R.layout.main_subway_fragment
    override val viewModelClass: Class<SubwayViewModel>
        get() = SubwayViewModel::class.java

    override fun initBindings() {

    }
}
