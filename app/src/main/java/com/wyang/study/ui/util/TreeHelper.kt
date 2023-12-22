package com.wyang.study.ui.util

import android.text.TextUtils
import com.wyang.study.NodeSeeker
import com.wyang.study.TreeNode

class TreeHelper {
    private val rootNode by lazy {
        TreeNode(null)
    }
    private val areaMap by lazy {
        HashMap<String, TreeNode>()
    }

    fun putAreaNode(node: TreeNode) {
        areaMap[node["code"] as String] = node
    }

    fun getAreaNode(code: String?): TreeNode? {
        return areaMap[code]
    }

    fun rootSeeker(): NodeSeeker {
        return NodeSeeker.newInstance(rootNode)
    }

    fun provincesSeeker(): NodeSeeker {
        return rootSeeker().children()
    }

    private fun areaSeeker(): NodeSeeker {
        return provincesSeeker().children()
    }

    private fun citiesSeeker(): NodeSeeker {
        return areaSeeker().children()
    }

    fun streetsSeeker(): NodeSeeker {
        return citiesSeeker().children()
    }

    fun getProvince(code: String?): TreeNode {
        return rootSeeker()
            .children()
            .match {
                return@match TextUtils.equals(it["code"] as String, code)
            }
            .firstResult()
    }

    fun getCity(code: String?): TreeNode {
        return provincesSeeker()
            .children()
            .match {
                return@match TextUtils.equals(it["code"] as String, code)
            }
            .firstResult()
    }

    fun getArea(code: String?): TreeNode {
        return citiesSeeker()
            .children()
            .match {
                return@match TextUtils.equals(it["code"] as String, code)
            }
            .firstResult()
    }

    fun getStreet(code: String?): TreeNode {
        return areaSeeker()
            .children()
            .match {
                return@match TextUtils.equals(it["code"] as String, code)
            }
            .firstResult()
    }
}