package com.kailin.architecture_model.architecture.interfaces

interface OnRequestPermissionsResult {

    fun onPermissionGranted(requestCode: Int, permissions: Array<String>, grantResults: IntArray)

    fun onPermissionDenied(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
}
