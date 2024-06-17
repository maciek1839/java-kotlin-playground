package com.showmeyourcode.playground.java.code.exercise.bst;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * Write Binary Search Tree (BST) implementation.
 * 1. Define a node class.
 * 2. Implement 'insert' method.
 * 3. Implement search function returns boolean.
 * 4. Implement find min/max values.
 * 5. Implement removing a node.
 * 6. Implement calculating number of nodes and leaf nodes.
 * A node with two empty subtrees is called a leaf.
 * 7. Implement calculating tree height.
 * The height of a node in a binary tree is the largest number of edges in a path from a leaf node to a target node.
 * 8. Implement Breadth-first search (BFS) traversal.
 * Breadth-first search is a tree traversal algorithm that explores nodes level by level.
 * 9. Implement Depth-first search (DFS)  preorder, inorder, postorder traversals.
 * Depth-first search is another tree traversal algorithm that goes deep into a tree exploring for nodes branch by branch.
 *
 * <br>
 * <br>
 * More about traversing: <a href="https://medium.com/@Roshan-jha/a-comprehensive-guide-to-binary-tree-traversal-in-java-74c86ee23725">A Comprehensive Guide to Binary Tree Traversal in Java</a>
 * <br>
 * More about data structures: <a href="https://gitlab.com/ShowMeYourCodeYouTube/kotlin-java-data-structures">Data structures in Kotlin and Java</a>
 */
@Slf4j
public class BinarySearchTreeNoDuplicatesExercise {
    private BinarySearchTreeNoDuplicatesExercise() {
    }

    public static void main(String[] args) {
        var root = new NodeNoDuplicates(10);
        root.insert(new NodeNoDuplicates(5));
        root.insert(new NodeNoDuplicates(15));
        root.insert(new NodeNoDuplicates(3));
        root.insert(new NodeNoDuplicates(7));

        log.info("\nSearch 5 in BST: {}", root.search(new NodeNoDuplicates(5)));
        log.info("Search 12 in BST: {}", root.search(new NodeNoDuplicates(12)));

        log.info("Minimum value in BST: {}", root.findMin());
        log.info("Maximum value in BST: {}", root.findMax());
    }
}

/**
 * An implementation without duplicates.
 */
class NodeNoDuplicates implements Comparable<NodeNoDuplicates> {
    NodeNoDuplicates left;
    NodeNoDuplicates right;
    final int value;

    NodeNoDuplicates(int value) {
        this.value = value;
    }

    // =======================
    // standard operations
    // =======================

    public void insert(NodeNoDuplicates n) {
        if (compareTo(n) > 0) {
            if (left == null) {
                left = n;
            } else {
                left.insert(n);
            }
        } else {
            // this implementation doesn't allow duplicates (which also removes a problem of removing duplicates nodes)
            // Reference: https://stackoverflow.com/questions/68207641/which-side-do-we-place-a-node-if-it-is-equal-to-the-parent-in-a-binary-search-tr
            if (compareTo(n) < 0) {
                if (right == null) {
                    right = n;
                } else {
                    right.insert(n);
                }
            }
        }
    }

    public boolean search(NodeNoDuplicates n) {
        if (compareTo(n) == 0) {
            return true;
        } else if (compareTo(n) > 0) {
            if (left != null) {
                return left.search(n);
            } else {
                return false;
            }
        } else {
            if (right != null) {
                return right.search(n);
            } else {
                return false;
            }
        }
    }

    public int findMin() {
        if (left == null) {
            return value;
        } else {
            return left.findMin();
        }
    }

    public int findMax() {
        if (right == null) {
            return value;
        } else {
            return right.findMax();
        }
    }

    public NodeNoDuplicates remove(NodeNoDuplicates n) {
        if (compareTo(n) == 0) {
            return removeRoot();
        } else if (compareTo(n) > 0) {
            if (left != null) {
                left = left.remove(n);
            }
        } else {
            if (right != null) {
                right = right.remove(n);
            }
        }
        return this;
    }

    private NodeNoDuplicates removeRoot() {
        if (left == null && right == null) {
            return null;
        }
        if (left == null) {
            return right;
        } else {
            // How about duplicates? Let's assume we have a node with the same value as root.
            // Answer: one common approach is to only allow a single node for each value (i.e. key) in the BST.
            // For multiset semantics (to keep track of how many times a key is present) it is sufficient to store a counter at each node.
            // This approach is simple and quite efficient.
            // Reference: https://stackoverflow.com/questions/68207641/which-side-do-we-place-a-node-if-it-is-equal-to-the-parent-in-a-binary-search-tr
            // it might happen that we have node with the same value so the rest should go to the right as this is our rule of inserting
            NodeNoDuplicates greatestNode = findGreatestNode(left);
            left = left.remove(greatestNode);
            NodeNoDuplicates newRoot = new NodeNoDuplicates(greatestNode.value);
            newRoot.right = right;
            newRoot.left = left;
            return newRoot;
        }
    }

    private NodeNoDuplicates findGreatestNode(NodeNoDuplicates node) {
        if (node.right == null) {
            return node;
        } else {
            return findGreatestNode(node.right);
        }
    }

    // =======================
    // other operations
    // =======================

    public int countNodes() {
        return 1 + (left != null ? left.countNodes() : 0) + (right != null ? right.countNodes() : 0);
    }

    /**
     * A node with two empty subtrees is called a leaf.
     *
     * @return tree's leaf nodes
     */
    public int countLeafNodes() {
        if (left == null && right == null) {
            return 1;
        } else {
            return (left != null ? left.countLeafNodes() : 0) + (right != null ? right.countLeafNodes() : 0);
        }
    }

    /**
     * The height of a node in a binary tree is the largest number of edges in a path from a leaf node to a target node.
     *
     * @return tree's height
     */
    public int height() {
        if (left == null && right == null) {
            return 0;
        }
        return 1 + Math.max(left != null ? left.height() : 0, right != null ? right.height() : 0);
    }

    // =======================
    // traversal
    // =======================

    public List<Integer> bfs() {
        var result = new ArrayList<Integer>();
        Queue<NodeNoDuplicates> nodesToVisit = new LinkedList<>();
        nodesToVisit.add(this);

        while (!nodesToVisit.isEmpty()) {
            var currNode = nodesToVisit.poll();
            result.add(currNode.value);
            if (currNode.left != null) {
                nodesToVisit.add(currNode.left);
            }
            if (currNode.right != null) {
                nodesToVisit.add(currNode.right);
            }
        }

        return result;
    }

    public List<Integer> dfsPreorder(){
        var result = new ArrayList<Integer>();

        dfsPreorder(result, this);

        return result;
    }

    private void dfsPreorder(List<Integer> resultOrder, NodeNoDuplicates nodeToVisit){
        resultOrder.add(nodeToVisit.value);
        if(nodeToVisit.left!= null) {
            dfsPreorder(resultOrder, nodeToVisit.left);
        }
        if(nodeToVisit.right!= null) {
            dfsPreorder(resultOrder, nodeToVisit.right);
        }
    }

    public List<Integer> dfsInorder(){
        var result = new ArrayList<Integer>();
        if(left != null){
            result.addAll(left.dfsInorder());
        }
        result.add(value);
        if(right != null){
            result.addAll(right.dfsInorder());
        }
        return result;
    }

    public List<Integer> dfsPostorder(){
        var result = new ArrayList<Integer>();
        if(left != null){
            result.addAll(left.dfsPostorder());
        }
        if(right != null){
            result.addAll(right.dfsPostorder());
        }
        result.add(value);
        return result;
    }

    @Override
    public int compareTo(@NotNull NodeNoDuplicates o) {
        return Integer.compare(value, o.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeNoDuplicates node = (NodeNoDuplicates) o;
        return value == node.value && Objects.equals(left, node.left) && Objects.equals(right, node.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, value);
    }
}
