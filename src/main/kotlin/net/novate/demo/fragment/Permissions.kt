package net.novate.demo.fragment

import java.util.concurrent.atomic.AtomicInteger

object Permissions {

    private val coder = AtomicInteger()
    private val requests: MutableMap<Int, ((Fragment, Boolean) -> Unit)> = HashMap()
    private val fragments: MutableMap<String, Fragment> = HashMap()

    fun requestPermission(fragment: Fragment, permission: String, callback: ((Fragment, Boolean) -> Unit)) {
        val code = coder.getAndIncrement()
        fragments[fragment.tag] = fragment
        fragment.requestPermission(code, permission)
        requests[code] = callback
    }

    fun onRequestPermission(fragment: Fragment, requestCode: Int, permission: String, granted: Boolean) {
        requests[requestCode]?.invoke(fragments[fragment.tag]!!, granted)
        requests.remove(requestCode)
    }
}