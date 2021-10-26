/*
This class represents a single node in a binary search tree (BST).  Each node
contains data and references to the left and right children of the node.

The <E extends Comparable<E>> in the type parameter limits the type of E to classes
 that implement the Comparable interface.  This makes sense for a BST since every
 node has to be comparable to other nodes.
*/
public class BSTNode<E extends Comparable<E>> {
    private E data;
    private BSTNode<E> left, right;

    // Constructor
    public BSTNode(E data, BSTNode<E> left, BSTNode<E> right) {
        setData(data);
        setLeft(left);
        setRight(right);
    }

    // Setter methods
    public void setData(E data) {
        this.data = data;
    }

    public void setLeft(BSTNode<E> left) {
        this.left = left;
    }

    public void setRight(BSTNode<E> right) {
        this.right = right;
    }

    // Getter methods
    public E getData() {
        return data;
    }

    public BSTNode<E> getLeft() {
        return left;
    }

    public BSTNode<E> getRight() {
        return right;
    }
}
