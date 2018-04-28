import java.util.Iterator;

/*
 * Daniel McFadden (16280010)
 * CT2019: OOP Data Structures and Algorithms
 * Dr. Naill Madden
 * Exam Review
 */
public class BinarySearchTreeR<T extends Comparable<? super T>> extends BinaryTree<T> implements SearchTreeInterface<T>, java.io.Serializable {
    
    public BinarySearchTreeR() {
        super();
    }
    
    public BinarySearchTreeR(T rootEntry) {
        super();
        setRootNode(new BinaryNode<T>(rootEntry));
    }
    
    public void setTree(T rootData) {
        throw new UnsupportedOperationException();
    }
    
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
        throw new UnsupportedOperationException();
    }
    
    public T getEntry(T entry) {
        return findEntry(getRootNode(), entry);
    }
    
    private T findEntry(BinaryNodeInterface<T> rootNode, T entry) {
        T result = null;
        
        if (rootNode != null) {
            T rootEntry = rootNode.getData();
            
            // -1 less than, 0 equal to, 1 greater than
            if (entry.equals(rootEntry)) {
                result = rootEntry;
            } else if(entry.compareTo(rootEntry) < 0) {
                result = findEntry(rootNode.getLeftChild(), entry);
            } else {
                result = findEntry(rootNode.getRightChild(), entry);
            }
            
        }
        
        return result;
    }
    
    public boolean contains(T entry) {
        return getEntry(entry) != null;
    }
    
    public T add(T newEntry) {
        T result = null;
        
        if (isEmpty()) {
            setRootNode(new BinaryNode<T>(newEntry));
        } else {
            result = addEntry(getRootNode(), newEntry);
        }
        
        return result;
    }
    
    private T addEntry(BinaryNodeInterface<T> rootNode, T newEntry) {
        assert rootNode != null;
        T result = null;
        int comparison = newEntry.compareTo(rootNode.getData());
        
        if (comparison == 0) {
            result = rootNode.getData();
            rootNode.setData(newEntry);
        } else if (comparison < 0) {
            if (rootNode.hasLeftChild()) {
                result = addEntry(rootNode.getLeftChild(), newEntry);
            } else {
                rootNode.setLeftChild(new BinaryNode<T>(newEntry));
            }
        } else {
            assert comparison > 0;
            
            if (rootNode.hasRightChild()) {
                result = addEntry(rootNode.getRightChild(), newEntry);
            } else {
                rootNode.setRightChild(new BinaryNode<T>(newEntry));
            }
        }
        
        return result;
    }
    
    public T remove(T entry) {
        ReturnObject oldEntry = new ReturnObject(null);
        BinaryNodeInterface<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
        setRootNode(newRoot);
        
        return oldEntry.get();
    }
    
    private BinaryNodeInterface<T> removeEntry(BinaryNodeInterface<T> rootNode, T entry, ReturnObject oldEntry) {
        if (rootNode != null) {
            T rootData = rootNode.getData();
            int comparison = entry.compareTo(rootData);
            
            if (comparison == 0) {
                oldEntry.set(rootData);
                rootNode = removeFromRoot(rootNode);
            } else if (comparison < 0) {
                BinaryNodeInterface<T> leftChild = rootNode.getLeftChild();
                BinaryNodeInterface<T> subtreeRoot = removeEntry(leftChild, entry, oldEntry);
                rootNode.setLeftChild(subtreeRoot);
            } else {
                BinaryNodeInterface<T> rightChild = rootNode.getRightChild();
                rootNode.setRightChild(removeEntry(rightChild, entry, oldEntry));
            }
        }
        
        return rootNode;
    }
    
    private BinaryNodeInterface<T> removeFromRoot(BinaryNodeInterface<T> rootNode) {
        // Case 1: rootNode has two children
        if (rootNode.hasLeftChild() && rootNode.hasRightChild()) {
            // find node with largest entry in left subtree
            BinaryNodeInterface<T> leftSubtreeRoot = rootNode.getLeftChild();
            BinaryNodeInterface<T> largestNode = findLargest(leftSubtreeRoot);
            
            // replace entry in root
            rootNode.setData(largestNode.getData());
            
            // remove node with largest entry in left subtree
            rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
        }
            
        // Case 2: rootNode has at most one child
        else if (rootNode.hasRightChild()) {
            rootNode = rootNode.getRightChild();
        } else {
            rootNode = rootNode.getLeftChild();
        }
        
        return rootNode;
    }
    
    private BinaryNodeInterface<T> findLargest(BinaryNodeInterface<T> rootNode) {
        if (rootNode.hasRightChild()) {
            rootNode = findLargest(rootNode.getRightChild());
        }
        
        return rootNode;
    }
    
    private BinaryNodeInterface<T> removeLargest(BinaryNodeInterface<T> rootNode) {
        if (rootNode.hasRightChild()) {
            BinaryNodeInterface<T> rightChild = rootNode.getRightChild();
            BinaryNodeInterface<T> root = removeLargest(rightChild);
            rootNode.setRightChild(root);
        } else {
            rootNode = rootNode.getLeftChild();
        }
        
        return rootNode;
    }
    
    @SuppressWarnings("serial")
	private class ReturnObject implements java.io.Serializable {
        private T item;
        
        private ReturnObject(T entry) {
            item = entry;
        }
        
        public T get() {
            return item;
        }
        
        public void set(T entry) {
            item = entry;
        }
    }
}