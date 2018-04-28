
/*
 * Daniel McFadden (16280010)
 * CT2019: OOP Data Structures and Algorithms
 * Dr. Naill Madden
 * Exam Review
 */
public class BinaryTree<T> implements BinaryTreeInterface<T>, java.io.Serializable {
	private static final long serialVersionUID = -1932834476575953631L;
	private BinaryNodeInterface<T> root;

	public BinaryTree() {
		root = null;
	}

	public BinaryTree(T rootData) {
		root = new BinaryNode<T>(rootData);
	}

	public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		privateSetTree(rootData, leftTree, rightTree);
	}
	public void setTree(T rootData) {
		root = new BinaryNode<T>(rootData);
	}
	
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		privateSetTree(rootData, (BinaryTree<T>)leftTree, (BinaryTree<T>)rightTree);
	}
	
	private void privateSetTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		root = new BinaryNode<T>(rootData);

		if ((leftTree != null) && !leftTree.isEmpty()) {
			root.setLeftChild(leftTree.root);
		}

		if ((rightTree != null) && !rightTree.isEmpty()) {
			if (rightTree != leftTree) {
				root.setRightChild(rightTree.root);
			} else {
				root.setRightChild(rightTree.root.copy());
			}
		}

		if ((leftTree != null) && (leftTree != this)) {
			leftTree.clear();
		} 

		if ((rightTree != null) && (rightTree != this)) {
			rightTree.clear();
		}
	}

	private BinaryNode<T> copyNodes() {
		return (BinaryNode<T>)root.copy();
	}
	
	public T getRootData() {
		T rootData = null;

		if (root != null) {
			rootData = root.getData();
		}

		return rootData;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void clear() {
		root = null;
	}
	
	protected void setRootData(T rootData) {
		root.setData(rootData);
	}
	
	protected void setRootNode(BinaryNodeInterface<T> rootNode) {
		root = rootNode;
	}
	
	protected BinaryNodeInterface<T> getRootNode() {
		return root;
	}
	
	public int getHeight() {
		return root.getHeight();
	}
	
	public int getNumberOfNodes() {
		return root.getNumberOfNodes();
	}
	
	public void inorderTraverse() {
		inorderTraverse(root);
	}

	private void inorderTraverse(BinaryNodeInterface<T> node) {
		
		if (node != null) {
			inorderTraverse(node.getLeftChild());
			System.out.println(node.getData());
			inorderTraverse(node.getRightChild());
		}
	}

}