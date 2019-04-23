package com.kailin.architecture_model.architecture.interfaces

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

interface FragmentInterface {

    @get:IdRes
    val containerViewId: Int

    fun replaceFragment(fragment: Fragment)

    fun replaceFragment(fragment: Fragment, TAG: String)

    fun replaceFragment(@IdRes layoutId: Int, fragment: Fragment, TAG: String)

    fun addFragment(fragment: Fragment)

    fun addFragment(fragment: Fragment, TAG: String)

    fun addFragment(@IdRes layoutId: Int, fragment: Fragment, TAG: String)

    fun popFragment()
}
