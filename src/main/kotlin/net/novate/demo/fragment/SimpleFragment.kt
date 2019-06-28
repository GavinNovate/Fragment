package net.novate.demo.fragment

class SimpleFragment(tag: String) : Fragment(tag) {

    override fun onCreate() {
        println("SimpleFragment:${this.hashCode()} onCreate")
        Permissions.requestPermission(this, "互联网权限") { fragment: Fragment, granted: Boolean ->
            println("SimpleFragment:${fragment.hashCode()} Granted=$granted")
        }
    }

    override fun onDestroy() {

    }

    override fun onRequestPermissionResult(requestCode: Int, permission: String, granted: Boolean) {
        println("SimpleFragment:${this.hashCode()} onRequestPermissionResult")
        Permissions.onRequestPermission(this, requestCode, permission, granted)
    }
}