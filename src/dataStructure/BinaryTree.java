package dataStructure;

import dataStructure.basic.TreeNode;

import java.util.*;

public class BinaryTree {
    // 初始化
    public static Queue<Integer> treeElement = new LinkedList<>();

    static {
        treeElement.add(1);
        treeElement.add(2);
        treeElement.add(5);
        treeElement.add(7);
        treeElement.add(11);
    }

    public static void main(String[] args) {
        TreeNode binaryTree = buildTree(treeElement);
        System.out.println("Build successfully");
        inOderTravel(binaryTree);
    }

    // 构建二叉树
    public static TreeNode buildTree(Queue<Integer> treeElement) {
        if (treeElement.isEmpty()) return null;
        TreeNode root = new TreeNode(treeElement.poll());
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            TreeNode current = nodeQueue.poll();
            if (!treeElement.isEmpty()) {
                TreeNode left = new TreeNode(treeElement.poll());
                current.left = left;
                nodeQueue.add(left);
            }
            if (!treeElement.isEmpty()) {
                var right = new TreeNode(treeElement.poll());
                current.right = right;
                nodeQueue.add(right);
            }
        }
        return root;
    }

    // 二叉树的中序遍历
    // 递归实现
    private static void inOderTravel(TreeNode binaryTree) {
        if (binaryTree == null) return;

        inOderTravel(binaryTree.left);
        System.out.println(binaryTree.value);
        inOderTravel(binaryTree.right);
    }
    // <<========================================

    // 平衡二叉树  递归判定 -------------------------------
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return false;
        } else {
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int height(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return Math.max(height(node.left), height(node.right)) + 1;
        }
    }
    // -------------------------------
}
