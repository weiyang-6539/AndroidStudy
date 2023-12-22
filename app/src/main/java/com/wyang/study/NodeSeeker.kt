package com.wyang.study

import android.util.Log

typealias Visitor = (node: TreeNode?) -> Unit
typealias Predicate = (node: TreeNode) -> Boolean
typealias Filter = (node: TreeNode, visitor: Visitor) -> Unit

open class NodeSeeker(node: TreeNode?) {
    companion object {
        fun newInstance(node: TreeNode): NodeSeeker {
            return NodeSeeker(node)
        }
    }

    private var currentFilter = NodeFilter()
    private var firstFilter = currentFilter

    init {
        doFilter { _, function ->
            node?.let {
                function.invoke(it)
            }
        }
    }

    open fun children() = doFilter { node, function ->
        node.children.forEach {
            function.invoke(it)
        }
    }

    open fun match(predicate: Predicate) = doFilter { node, function ->
        if (predicate(node)) {
            function.invoke(node)
        }
    }

    fun descendants() = doFilter { node, function ->
        var visitor: Visitor? = null
        visitor = { tt ->
            function.invoke(tt)
            node.children.forEach {
                visitor?.invoke(it)
            }
        }
        visitor.invoke(node)
    }

    private fun doFilter(filter: Filter): NodeSeeker {
        val nodeFilter = NodeFilter()
        nodeFilter.filter = filter
        currentFilter.visitor = {
            nodeFilter.filter(it!!, nodeFilter.visitor)
        }
        currentFilter = nodeFilter
        return this
    }

    private fun execute(visitor: Visitor) {
        currentFilter.visitor = visitor
        firstFilter.visitor(null)
    }

    fun firstResult(): TreeNode {
        val nodes = mutableListOf<TreeNode>()
        execute {
            Log.e("node111111111111111", it.toString())
            it?.let {
                nodes[0] = it
            }
        }
        Log.e("node111111111111111", nodes.toString())

        return nodes[0]
    }

    fun results(): MutableList<TreeNode> {
        val list = mutableListOf<TreeNode>()
        execute {
            it?.let {
                list.add(it)
            }
        }
        return list
    }

    class NodeFilter {
        lateinit var visitor: Visitor
        lateinit var filter: Filter
    }
}