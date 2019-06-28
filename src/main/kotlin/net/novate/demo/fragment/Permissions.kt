package net.novate.demo.fragment

import java.util.concurrent.atomic.AtomicInteger

object Permissions {

    private val coder = AtomicInteger()
    private val requests: MutableMap<Int, ((Boolean) -> Unit)> = HashMap()

    fun requestPermission(fragment: Fragment, permission: String, callback: ((Boolean) -> Unit)) {
        val code = coder.getAndIncrement()
        requests[code] = callback
        fragment.requestPermission(code, permission)
    }

    fun onRequestPermission(requestCode: Int, permission: String, granted: Boolean) {
        requests[requestCode]?.invoke(granted)
        requests.remove(requestCode)
    }
}