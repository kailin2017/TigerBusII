package com.kailin.bus.base

import com.kailin.architecture_model.architecture.ArchitectureActivity
import com.kailin.architecture_model.architecture.ArchitectureViewModel

import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding, VM : ArchitectureViewModel> : ArchitectureActivity<B, VM>()
