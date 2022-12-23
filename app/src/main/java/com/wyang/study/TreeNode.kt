package com.wyang.study

/**
 * @author Yang
 * @since 2022/11/9 16:41
 * @desc
 */
class TreeNode(val parent: TreeNode?) : HashMap<String, Any>() {
    val children = mutableListOf<TreeNode>()

    init {
        parent?.addChild(this)
    }

    fun addChild(node: TreeNode) {
        children.add(node)
    }

    fun removeChild(node: TreeNode) {
        children.remove(node)
    }

    fun childCount(): Int {
        return children.size
    }

    fun <T> getAttr(key: String): T {
        return get(key) as T
    }
}