package com.kailin.architecture_model.architecture.interfaces

interface PermissionInterface {

    fun checkPermission(requestCode: Int, vararg permissions: String)
}
