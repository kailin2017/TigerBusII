package com.kailin.bus.ui.fragment.subway

import com.kailin.architecture_model.architecture.ArchitectureFragment
import com.kailin.bus.R
import com.kailin.bus.databinding.SubwayFragmentBinding

class SubwayFragment : ArchitectureFragment<SubwayFragmentBinding, SubwayViewModel>() {

    override val layoutRes: Int
        get() = R.layout.subway_fragment
    override val viewModelClass: Class<SubwayViewModel>
        get() = SubwayViewModel::class.java

    override fun initBindings() {

    }
}
