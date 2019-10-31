package com.wyang.study.ui.util;

import com.wyang.study.utils.NodeSeeker;
import com.wyang.study.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weiyang on 2019-09-22.
 */
public class TreeHelper {
    private TreeNode rootNode;
    private Map<String, TreeNode> areaMap;//区的map,提升解析村级别效率过慢问题

    public TreeHelper() {
        rootNode = new TreeNode(null);
        areaMap = new HashMap<>();
    }

    public void putAreaNode(TreeNode node) {
        areaMap.put(node.getAttribute("code"), node);
    }

    public TreeNode getAreaNode(String code) {
        return areaMap.get(code);
    }

    public NodeSeeker rootSeeker() {
        return NodeSeeker.newInstance(rootNode);
    }

    public NodeSeeker provincesSeeker() {
        return rootSeeker().children();
    }

    public NodeSeeker areaSeeker() {
        return provincesSeeker().children();
    }

    public NodeSeeker citiesSeeker() {
        return areaSeeker().children();
    }

    public NodeSeeker streetsSeeker() {
        return citiesSeeker().children();
    }

    public TreeNode getProvince(String code) {
        return rootSeeker()
                .children()
                .attribute("code", code)
                .firstResult();
    }

    public TreeNode getCity(String code) {
        return provincesSeeker()
                .children()
                .attribute("code", code)
                .firstResult();
    }

    public TreeNode getArea(String code) {
        return citiesSeeker()
                .children()
                .attribute("code", code)
                .firstResult();
    }

    public TreeNode getStreet(String code) {
        return areaSeeker()
                .children()
                .attribute("code", code)
                .firstResult();
    }

}
