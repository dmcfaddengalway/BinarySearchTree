import java.util.Iterator;

/*
 * Daniel McFadden (16280010)
 * CT2019: OOP Data Structures and Algorithms
 * Dr. Naill Madden
 * Exam Review
 */
public interface SearchTreeInterface<T extends Comparable<? super T>> extends TreeInterface<T> {
	
  public boolean contains(T entry);
  public T getEntry(T entry);
  public T add(T newEntry);
  public T remove(T entry);
  
}