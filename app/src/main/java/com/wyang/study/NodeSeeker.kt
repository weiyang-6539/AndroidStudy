package com.wyang.study

/**
 * @author Yang
 * @since 2022/11/9 17:45
 * @desc
 */
class NodeSeeker(node: TreeNode) {

    companion object {
        fun newInstance(node: TreeNode): NodeSeeker {
            return NodeSeeker(node)
        }

    }

    private var currentFilter = NodeFilter()
    private var firstFilter = currentFilter

    init {
        doFilter { _, function ->
            function(node)
        }
    }

    fun children(): NodeSeeker {
        return doFilter { node, function ->
            node.children.forEach {
                function(it)
            }
        }
    }

    fun hasAttribute(key: String): NodeSeeker {
        return match { it.containsKey(key) }
    }

    fun match(predicate: (node: TreeNode) -> Boolean): NodeSeeker {
        return doFilter { node, function ->
            if (predicate(node)) {
                function(node)
            }
        }
    }

    fun descendants(): NodeSeeker {
        return doFilter { node, function ->
            val temp = arrayListOf<((node: TreeNode) -> Unit)>()
            val visitor: ((node: TreeNode) -> Unit) = {
                function(node)
                node.children.forEach {
                    temp[0](it)
                }
            }
            temp.add { visitor }
        }
    }

    fun doFilter(filter: ((node: TreeNode, (node: TreeNode) -> Unit) -> Unit)): NodeSeeker {
        val nodeFilter = NodeFilter()
        nodeFilter.filter = filter
        currentFilter.visitor = {
            nodeFilter.filter(it, nodeFilter.visitor)
        }
        currentFilter = nodeFilter
        return this
    }

    fun execute(visitor: (node: TreeNode) -> Unit) {
        currentFilter.visitor = visitor
        firstFilter.visitor(null!!)
    }

    fun result(): TreeNode {
        val nodes = mutableListOf<TreeNode?>(null)
        execute {
            nodes[0] = it
        }
        return nodes[0]!!
    }

    fun results(): MutableList<TreeNode> {
        val list = mutableListOf<TreeNode>()
        execute { list.add(it) }
        return list
    }

    class NodeFilter {
        lateinit var visitor: ((node: TreeNode) -> Unit)
        lateinit var filter: ((node: TreeNode, (node: TreeNode) -> Unit) -> Unit)
    }
}