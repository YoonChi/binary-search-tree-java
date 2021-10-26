/*
Class that implements a binary min-heap.  A binary min-heap is a complete binary
tree with the property that every node is <= any of its children.

Like in BinarySearchTree earlier, the <E extends Comparable<E>> in the type
parameter limits the type of E to classes that implement the Comparable interface.
This makes sense for a heap since every node has to be comparable to other nodes.
*/
import java.util.ArrayList;

public class MinHeap<E extends Comparable<E>> {
    // This array list stores the data in the min-heap.
    // Given a node at index n of the array list:
    //  * The parent node is at index (n - 1)/2 (using integer division)
    //  * The left child node is at index (2n + 1)
    //  * The right child node is at index (2n + 2)
    private ArrayList<E> data = new ArrayList<>();

    // Adds the newValue to the min heap, percolating it up the heap as needed
    public void add(E newValue) {
        // Add the newValue to the end of the array list
        data.add(newValue);

        // Percolate the newValue up the heap
        int nodeIndex = data.size() - 1;	// The just-added element is at the last index of the array list

        while (nodeIndex > 0) {
            // Compute the index of the parent node
            int parentIndex = (nodeIndex - 1) / 2;

            // Compare the newly added node vs. its parent
            if (data.get(nodeIndex).compareTo(data.get(parentIndex)) >= 0)	// New node is >= the parent - min-heap property is satisfied, so we're done
                return;
            else {
                // Swap the new node with its parent
                E temp = data.get(nodeIndex);
                data.set(nodeIndex, data.get(parentIndex));
                data.set(parentIndex, temp);

                // Move up the heap
                nodeIndex = parentIndex;
            }
        }
    }


    // removes and returns the minimum element from the heap
    public E remove() {

        if (!data.isEmpty()) {
            // Remove min node
            E popped = data.get(0);

//            System.out.println(popped);

            // 1. Delete the node and replace the deleted node
            data.set(0,data.get(data.size() - 1));
            // 3. Decrease the heap size by 1
            data.remove(data.size() - 1);

            // 4. Heapify (fix the heap
                /* if the heap property holds true, then you are done
                     else if replacement node <= its parents, then swap them, and repeat
                     step 3
                     else
                     swap replacement node with the smallest child node, and repeat step 3
                */
            int nodeIndex = 0;
            int absSize;
            if (data.size() % 2 == 0) {
                absSize = data.size() - 2;
            }
            else {
                absSize = data.size() - 1;
            }
            for (int i = 0; i < absSize; i++) {
                int leftChild = ((2 * i) + 1);
                int rightChild = ((2 * i) + 2);

                absSize--;

                if ((data.get(leftChild).compareTo(data.get(rightChild))) < 0) { // if left child is smaller than right child
                    // switch left child and nodeIndex
                    E temp = data.get(leftChild);
                    data.set(leftChild, data.get(nodeIndex));
                    data.set(nodeIndex, temp);
                    nodeIndex++;
                }
                else {
                    // switch right child and nodeIndex
                    E temp = data.get(rightChild);
                    data.set(rightChild, data.get(nodeIndex));
                    data.set(nodeIndex, temp);
                    nodeIndex++;
                }
            }
            return popped;
        }
        return null;
    }

    // This toString method just calls the toString of ArrayList
    public String toString() {
        // attempted...failed..
        int rows = data.size() / 2;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < i; j++) {
                int parent =  (j - 1)/2;
                int leftChild = ((2 * j) + 1);
                int rightChild = ((2 * j) + 2);
                System.out.println(data.get(parent) + " " + data.get(leftChild) + " " + data.get(rightChild));
            }

//            2
//            5 7
//            8 9 10
        }


        return data.toString();
    }


    public static void main(String[] args) {
        MinHeap<Integer> testHeap = new MinHeap<>();

        int[] stuff = {2, 5, 7, 8, 10, 9};
        for (int x : stuff) {
            testHeap.add(x);
        }

       // Original list
       System.out.println("Original list");
       System.out.println(testHeap);

       for (int x : stuff) {
           System.out.println("Popped: " + testHeap.remove());
           System.out.println(testHeap);
       }

//         System.out.println(testHeap);

    }
}