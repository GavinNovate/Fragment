package net.novate.demo.fragment

@Suppress("MemberVisibilityCanBePrivate")
object Framework {

    private val fragments: MutableMap<String, Fragment> = HashMap()

    // requestCode , fragmentTag,permission
    private val requests: MutableMap<Int, Pair<String, String>> = HashMap()

    @JvmStatic
    fun main(args: Array<String>) {
        // 创建 Fragment
        createFragment("SimpleFragment")

        // 回收 Fragment
        destroyFragment("SimpleFragment")

        // 用户授权
        requests.keys.forEach { requestCode ->
            val tag = requests[requestCode]?.first
            val permission = requests[requestCode]?.second
            if (tag != null && permission != null) {
                if (fragments[tag] == null) {
                    // 重新创建
                    createFragment(tag)
                }
                fragments[tag]?.onRequestPermissionResult(requestCode, permission, true)
            }
        }
    }

    fun createFragment(tag: String) {
        val fragment = SimpleFragment(tag)
        fragment.onCreate()
        fragments[tag] = fragment
    }

    fun destroyFragment(tag: String) {
        val fragment = fragments.remove(tag)
        fragment?.onDestroy()
    }

    fun requestPermission(requestCode: Int, tag: String, permission: String) {
        requests[requestCode] = Pair(tag, permission)
    }
}