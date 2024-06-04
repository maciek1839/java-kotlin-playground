package com.showmeyourcode.playground.java.code.exercise;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class BinarySearchTreeExercise {
    private TreeNode root;

    public BinarySearchTreeExercise() {
        this.root = null;
    }

    public void insert(int val) {
        root = insertNode(root, val);
    }

    private TreeNode insertNode(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            root.left = insertNode(root.left, val);
        } else if (val > root.val) {
            root.right = insertNode(root.right, val);
        }

        return root;
    }

    public boolean search(int val) {
        return searchNode(root, val);
    }

    private boolean searchNode(TreeNode root, int val) {
        if (root == null) {
            return false;
        }

        if (val == root.val) {
            return true;
        } else if (val < root.val) {
            return searchNode(root.left, val);
        } else {
            return searchNode(root.right, val);
        }
    }

    public int findMin() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMinNode(root).val;
    }

    private TreeNode findMinNode(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public int findMax() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return findMaxNode(root).val;
    }

    private TreeNode findMaxNode(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public void delete(int val) {
        root = deleteNode(root, val);
    }

    private TreeNode deleteNode(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (val < root.val) {
            root.left = deleteNode(root.left, val);
        } else if (val > root.val) {
            root.right = deleteNode(root.right, val);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            TreeNode minRight = findMinNode(root.right);
            root.val = minRight.val;
            root.right = deleteNode(root.right, minRight.val);
        }

        return root;
    }

    public void inorderTraversal() {
        inorder(root);
    }

    private void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }

    public void preorderTraversal() {
        preorder(root);
    }

    private void preorder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTreeExercise bst = new BinarySearchTreeExercise();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(3);
        bst.insert(7);

        System.out.println("Inorder traversal of BST:");
        bst.inorderTraversal();

        System.out.println("\nPreorder traversal of BST:");
        bst.preorderTraversal();

        System.out.println("\nSearch 5 in BST: " + bst.search(5));
        System.out.println("Search 12 in BST: " + bst.search(12));

        System.out.println("Minimum value in BST: " + bst.findMin());
        System.out.println("Maximum value in BST: " + bst.findMax());

        bst.delete(5);
        System.out.println("\nInorder traversal after deleting 5:");
        bst.inorderTraversal();
    }
}
