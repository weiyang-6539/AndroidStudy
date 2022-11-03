package com.wyang.study.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weiyang on 2017/11/27.
 */
public class TreeNode {
    TreeNode parent;
    List<TreeNode> children = new ArrayList<>();
    Map<String, Object> attributes;

    public TreeNode(TreeNode parent) {
        attributes = new HashMap<>();
        this.parent = parent;
        if (parent != null)
            parent.add(this);
    }

    public TreeNode getParent() {
        return parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void add(TreeNode node) {
        children.add(node);
    }

    public void remove(TreeNode node) {
        children.remove(node);
    }

    public int size() {
        return children.size();
    }

    public void setAttribute(String key, Object obj) {
        attributes.put(key, obj);
    }

    public <T extends Object> T getAttribute(String key) {
        return (T) attributes.get(key);
    }
}
