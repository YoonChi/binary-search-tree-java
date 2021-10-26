
import java.lang.Math;

public class BinarySearchTree<E extends Comparable<E>> {
 //    The <E extends Comparable<E>> in the type parameter limits the type of E to classes
 // that implement the Comparable interface.  This makes sense for a BST since every
 // node has to be comparable to other nodes.

    // Maintains the root (first node) of the tree
    private BSTNode<E> root;

    // Adds the newValue to the BST
    // This method also prevents duplicate values from being added to the BST.
    public void add(E newValue) {
        // Create a new BSTNode that contains the newValue
        // Assign newNode a new BSTNode that contains newValue and null values for both left and right children
        BSTNode<E> newNode = new BSTNode<>(newValue, null, null);

        // If tree is empty, the newNode should become the root
        if (root == null)
            root = newNode;
        else {
            BSTNode<E> currentNode = root; // create current Node and assign it to root
            while (currentNode != null) {
                // Compare the newValue vs. the data in the currentNode and store result into compareResult
                int compareResult = newValue.compareTo(currentNode.getData()); // call compareTo with newValue versus the data in currentNode

                // newValue is "less than" the current node - go left
                if (compareResult < 0) {
                    // If there is no left child for currentNode, make newNode
                    //  the left child of currentNode
                    if (currentNode.getLeft() == null) {
                        currentNode.setLeft(newNode);
                        currentNode = null;
                    }
                    // If there *is* a left child for currentNode, just move
                    //  currentNode down the left subtree
                    else
                        currentNode = currentNode.getLeft();
                }
                // newValue is "greater than" the current node - go right
                else if (compareResult > 0) {
                    // If there is no right child for currentNode, make newNode
                    //  the right child of currentNode
                    if (currentNode.getRight() == null) {
                        currentNode.setRight(newNode);
                        currentNode = null;
                    }
                    // If there *is* a right child for currentNode, just move
                    //  currentNode down the right subtree
                    else
                        currentNode = currentNode.getRight();
                }

                // newValue is "equal to" the current node - exit the loop without adding newValue
                else
                    currentNode = null;
            }
        }
    }

    // Searches the BST for someValue
    // Returns true if found, false if not
    public boolean find(E someValue) {
        BSTNode<E> cur = root;
        while (cur != null) {
            // Compare the someValue vs. the data in the current node
            int compareResult = someValue.compareTo(cur.getData());

            if (compareResult == 0)        // someValue is "equal to" cur.getData() - found!
                return true;
            else if (compareResult < 0)    // someValue is "less than" cur.getData() - move left
                cur = cur.getLeft();
            else                        // someValue is "greater than" cur.getData() - move right
                cur = cur.getRight();
        }

        // The only way to exit the while loop and get to this point is for
        //  the return statement inside the loop to never get run -- i.e.,
        //  someValue never matches with any of the nodes' data.
        return false;
    }

    // Gets things started with the recursive version of inOrderTraversal
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }
    // Recursive method that performs an in-order traversal of the tree
    //  starting from the node "where"
    private void inOrderTraversal(BSTNode<E> where) {
        if (where != null) {    // Implied base case - do nothing if where is null
            inOrderTraversal(where.getLeft());
            System.out.println(where.getData());
            inOrderTraversal(where.getRight());
        }
    }

    // post order traversal starts from the root
    private void postOrderTraversal() { postOrderTraversal(root); }
    // Recursive method that performs a post-order traversal of the tree
    // starting from the node where
    private void postOrderTraversal(BSTNode<E> where) {
        if (where != null) { // implied base case - do nothing if where is null
            postOrderTraversal(where.getLeft());
            postOrderTraversal(where.getRight());
            System.out.println(where.getData());
        }
    }

    private E deleteSuccessor(BSTNode<E> where) {
        System.out.println("Replace deleted node with its successor: ");
        if (where != null) {
            BSTNode<E> suc = where;

            // if "where" node has a right sub tree, then it has a successor
            if (where.getRight() != null) {

                suc = suc.getRight(); // go right once

                int i = 0; // use this counter to determine when to shift "parent" node down to the right

                BSTNode<E> parent = where; // set parent as "where" node for now

//                System.out.println("'Where' node: " + parent.getData());

                while (suc.getLeft() != null) {
                    suc =suc.getLeft(); // keep going left until reach successor

                    i++;

                    if (i > 1) {
                        parent = parent.getLeft(); // shift parent node down left
//                        System.out.println("Next parent of successor is :" + parent.getData());
                    }
                    else {
                        parent = parent.getRight(); // shift parent node down once
//                        System.out.println("First parent of successor is :" + parent.getData());
                    }
                }

                if (suc.getRight() != null) { // if suc only has one child, then assign child to parent node
                    BSTNode<E> nextNode = suc.getRight();
                    if (parent.getRight() != null && parent.getRight().getData() == suc.getData()) {
                        parent.setRight(nextNode);
                    }
                    else {
                        parent.setLeft(nextNode);
                    }
                }
                else { // if successor is a leaf node
                    if (parent.getRight() != null && parent.getRight().getData() == suc.getData())
                            parent.setRight(null);
                    else
                        parent.setLeft(null);
                }

                System.out.println("Deleted (successor) node: " + suc.getData());
                System.out.println("Parent node of successor node is: " + parent.getData());

                return suc.getData();
            }
            else return null;
        }
        return null;
    }

    private E deletePredecessor(BSTNode<E> where) {
        System.out.println("Replace deleted node with its predecessor: ");

        if (where != null) {
            BSTNode<E> pre = where;

            if (where.getLeft() != null) {  // if "where" has a left sub tree, then it has a predecessor
                pre = pre.getLeft(); // go left once // 32

                int i = 0; // use this counter to determine when to shift "parent" node down to the right

                BSTNode<E> parent = where; // set parent as "where" node for now
//                System.out.println("'Where' node: " + parent.getData());
                while (pre.getRight() != null) {

                    pre = pre.getRight(); // keep going right until you find "where's" predecessor

                    i++;

                    if (i > 1) {
                        parent = parent.getRight(); // shift parent node down right
//                        System.out.println("Next parent of predecessor is :" + parent.getData());
                    }
                    else {
                        parent = parent.getLeft();
//                        System.out.println("First parent of predecessor is :" + parent.getData());
                    }
                }

                if (pre.getLeft() != null) {  // if pre only has one child, then assign pre's child to parent node
                    BSTNode<E> nextNode = pre.getLeft();

                    if (parent.getRight() != null && parent.getRight().getData() == pre.getData()) {
                        parent.setRight(nextNode);
                    }
                    else {
                        parent.setLeft(nextNode);
                    }
                }
                else {  // if pre has no children, then set parent's (left/right) child to null
                    if (parent.getRight() != null && parent.getRight().getData() == pre.getData()) {
                        parent.setRight(null);
                    }
                    else
                        parent.setLeft(null);
                }

                System.out.println("Deleted (predecessor) node: " + pre.getData());
                System.out.println("Parent node of predecessor node is: " + parent.getData());

                return pre.getData();

            } else
                return null;
        }
        else
            return null;
    }

    public E remove(E item) {
        int rand = 0;

        System.out.println("Item to delete: " + item);
        // check if item exists
        if (find(item)) {
            BSTNode<E> cur = root;
            BSTNode<E> par = root;

                // find the item and its parent node
                while (cur != null) {
                    // Compare the item vs. the data in the current node
                    int compareResult = item.compareTo(cur.getData());

                    if (compareResult == 0) {      // found match
                        break;
                    }
                    else if (compareResult < 0) {  // item is "less than" cur.getData() - move left
                        par = cur;

                        cur = cur.getLeft();

                        System.out.println("Went left. Current: " + cur.getData() + " " + "Parent: " + par.getData());

                    } else {  // item is "greater than" cur.getData() - move right
                        par = cur;

                        cur = cur.getRight();

                        System.out.println("Went right. Current: " + cur.getData() + " Parent: " + par.getData());

                    }
                }

            assert par != null;
            System.out.println("Parent node: " + par.getData());

            System.out.println("Current node: " + cur.getData());

//                 if remove node has two children
                if (cur.getRight() !=  null && cur.getLeft() != null) {
                    System.out.println("Item has two children");
                    rand = (int) (Math.random() * 10 + 1);
                    if (rand > 5) {
//                         go left
                        E replaceValue = deletePredecessor(cur);
//                        inOrderTraversal();
                        cur.setData(replaceValue);
                    }
                    else {
//                        // go right
                        E replaceValue = deleteSuccessor(cur);
//                        inOrderTraversal();
                        cur.setData(replaceValue);
                    }
                }
                else if (cur.getRight() != null && cur.getLeft() == null) { // remove node with only right child
                    BSTNode<E> setNextNode = cur.getRight();
                    int compareItem = item.compareTo(root.getData());
                    if (compareItem < 0) {
                        par.setLeft(setNextNode);
                    }
                    else
                        par.setRight(setNextNode);
                }
                else if (cur.getLeft() != null && cur.getRight() == null) { // remove node with only left child
                    BSTNode<E> setNextNode = cur.getLeft();
                    int compareItem = item.compareTo(root.getData());

                    if (compareItem < 0) {
                        par.setLeft(setNextNode);
                    }
                    else
                        par.setRight(setNextNode);
                }
                else { // remove node is a leaf node
                    if (par.getLeft().getData() == cur.getData()) {
                        par.setLeft(null);
                    }
                    else if (par.getRight().getData() == cur.getData()) {
                        par.setRight(null);
                    }
                }
            return item;
        }
        else
            return null;
    }

    // This version of toString() overrides the method inherited from Object.
    // It gets things started with the recursive toString, defined below.
    public String toString() {
        return toString(root, "");
    }

    // Recursive toString that takes a parameter for where to start from
    private String toString(BSTNode<E> where, String prefix) {
        if (where == null)
            return prefix + "null";
        else
            // Include where's data, and recursively call toString for where's left and right subtrees
            return prefix + where.getData() + "\n" + toString(where.getLeft(), prefix + "-") + "\n" + toString(where.getRight(), prefix + "-");
    }


    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Question #4: Testing Remove Method
        int[] data = {30, 20, 25, 10, 21, 26, 15, 40, 35, 50, 45};
        for (int i : data)
            bst.add(i);
        System.out.println(bst);

        System.out.println("Removing a node with no children");
        System.out.println(bst);
        bst.remove(21);
        bst.inOrderTraversal();
        System.out.println(bst);

        System.out.println("Removing a node with only a left child");
        System.out.println(bst);
        bst.remove(50);
        bst.inOrderTraversal();
        System.out.println(bst);

        System.out.println("Removing a node with only a right child");
        System.out.println(bst);
        bst.remove(10);
        bst.inOrderTraversal();
        System.out.println(bst);

        System.out.println("Removing a node with two children");
        System.out.println(bst);
        bst.remove(20);
        bst.inOrderTraversal();
        System.out.println(bst);

    }
}