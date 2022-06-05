package com.wyang.study.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by weiyang on 2017/11/27.
 */
public class NodeSeeker {

    public interface Visitor {//访问器

        void visitNode(TreeNode treeNode);
    }

    public interface Filter {//筛选器

        void filter(TreeNode treeNode, Visitor visitor);
    }

    public interface Predicate {//判断节点是否符合条件

        boolean match(TreeNode treeNode);
    }

    public static NodeSeeker newInstance(TreeNode node) {
        return new NodeSeeker(node);
    }

    public static NodeSeeker newInstance(List<TreeNode> nodes) {
        return new NodeSeeker(nodes);
    }

    private NodeSeeker(TreeNode treeNode) {
        currentFilter = new NodeFilter();
        firstFilter = currentFilter;
        doFilter((node, visitor) -> {
            if (treeNode != null) {
                visitor.visitNode(treeNode);
            }
        });
    }

    private NodeSeeker(List<TreeNode> nodes) {
        currentFilter = new NodeFilter();
        firstFilter = currentFilter;
        doFilter((treeNode, visitor) -> {
            for (TreeNode node : nodes) {
                visitor.visitNode(node);
            }
        });
    }

    public NodeSeeker children() {
        return doFilter((node, visitor) -> {
            for (TreeNode treeNode : node.getChildren()) {
                visitor.visitNode(treeNode);
            }
        });
    }

    public NodeSeeker hasAttribute(String key) {
        return matchPredicate(treeNode -> treeNode.attributes.containsKey(key));
    }

    public NodeSeeker attribute(String key, String value) {
        return matchPredicate(treeNode -> TextUtils.equals(treeNode.getAttribute(key), value));
    }

    public NodeSeeker attribute(String key, int value) {
        return matchPredicate(treeNode -> (int) treeNode.getAttribute(key) == value);
    }

    public NodeSeeker descendants() {
        return doFilter((node, visitor) -> {
            Visitor[] temp = {null};
            temp[0] = (tt) -> {
                visitor.visitNode(tt);
                for (TreeNode ttChild : tt.getChildren())
                    temp[0].visitNode(ttChild);
            };
            temp[0].visitNode(node);
        });
    }

    public NodeSeeker matchPredicate(Predicate predicate) {
        return doFilter((treeNode, visitor) -> {
            if (predicate == null || predicate.match(treeNode)) {
                visitor.visitNode(treeNode);
            }
        });
    }

    public NodeSeeker leaf() {
        return descendants().matchPredicate(treeNode -> treeNode.size() == 0);
    }

    public NodeSeeker parent() {
        return doFilter((treeNode, visitor) -> {
            if (treeNode.parent != null) {
                visitor.visitNode(treeNode.parent);
            }
        });
    }

    public NodeSeeker unique() {
        Set<TreeNode> set = new HashSet<>();
        return doFilter((treeNode, visitor) -> {
            if (!set.contains(treeNode)) {
                set.add(treeNode);
                visitor.visitNode(treeNode);
            }
        });
    }

    private void execute(Visitor visitor) {
        currentFilter.nextVisitor = visitor;
        firstFilter.nextVisitor.visitNode(null);
    }

    public TreeNode firstResult() {
        final TreeNode[] treeNode = {null};
        try {
            execute((node) -> {
                treeNode[0] = node;
                throw new BreakException();
            });
        } catch (BreakException ignored) {
        }
        return treeNode[0];
    }

    public List<TreeNode> results() {
        List<TreeNode> list = new ArrayList<>();
        execute(list::add);
        return list;
    }

    private NodeFilter currentFilter;
    private final NodeFilter firstFilter;

    private static class NodeFilter {
        Visitor nextVisitor;
        Filter filter;
    }

    private NodeSeeker doFilter(Filter filter) {
        NodeFilter nodeFilter = new NodeFilter();
        nodeFilter.filter = filter;
        currentFilter.nextVisitor = node -> nodeFilter.filter.filter(node, nodeFilter.nextVisitor);
        currentFilter = nodeFilter;
        return this;
    }

    /**
     * 强制终止异常
     */
    private static class BreakException extends RuntimeException {

    }
}
