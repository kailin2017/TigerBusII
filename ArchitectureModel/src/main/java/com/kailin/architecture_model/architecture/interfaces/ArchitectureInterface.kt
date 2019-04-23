package com.kailin.architecture_model.architecture.interfaces

import androidx.lifecycle.ViewModel

interface ArchitectureInterface<VM : ViewModel> {

    val layoutRes: Int

    val viewModelClass: Class<VM>

    fun initBindings()

    fun onRxThrowable(throwable: Throwable)

    fun onShowLoading(isLoading: Boolean?)

}
