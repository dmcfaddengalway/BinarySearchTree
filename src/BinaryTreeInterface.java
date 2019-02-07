
/*
 * Daniel McFadden (16280010)
 * CT2019: OOP Data Structures and Algorithms
 * Dr. Naill Madden
 * Exam Review
 */
public interface BinaryTreeInterface<T> extends TreeInterface<T> {
	
  public void setTree(T rootData);
  public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree);
  
}