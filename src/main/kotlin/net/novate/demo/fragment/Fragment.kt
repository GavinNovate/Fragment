package net.novate.demo.fragment

abstract class Fragment(val tag: String) {

    abstract fun onCreate()

    abstract fun onDestroy()

    fun requestPermission(requestCode: Int, permission: String) {
        Framework.requestPermission(requestCode, tag, permission)
    }

    abstract fun onRequestPermissionResult(requestCode: Int, permission: String, granted: Boolean)
}