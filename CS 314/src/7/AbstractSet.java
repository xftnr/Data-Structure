/*  Student information for assignment:
 *
 *  On <OUR> honor, <Pengdi Xia> and <Peijie Yang), this programming assignment is <OUR> own work
 *  and <WE> have not provided this code to any other student.
 *
 *  Number of slip days used:1
 *
 *  Student 1 (Student whose turnin account is being used)
 *  UTEID:px353	
 *  email address:xiapengdi@yahoo.com
 *  Grader name:Gilbert Maldonado
 *  Section number:51970
 *  
 *  Student 2 
 *  UTEID:py2554
 *  email address:loarek1996@hotmail.com
 *  Grader name:Gilbert Maldonado
 *  Section number:51970
 *  
 */

import java.util.Iterator;

/**
 * Students are to complete this class. 
 * Students should implement as many methods
 * as they can using the Iterator from the iterator 
 * method and the other methods. 
 *
 */
public abstract class AbstractSet<E> implements ISet<E> {

    /* NO INSTANCE VARIABLES ALLOWED.
     * NO DIRECT REFERENCE TO UnsortedSet OR SortedSet ALLOWED.
     * (In other words the data types UnsortedSet and SortedSet
     * will not appear any where in this class.)
     * Also no direct references to ArrayList or other Java Collections.
     */
     
	/**
     * Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise.
     */  
	//There is no way we can add the item by only using the iterator
	public abstract boolean add(E item);
	
	/**
     * A union operation. Add all items of otherSet that are not already present in this set
     * to this set.
     * @param otherSet != null
     * @return true if this set changed as a result of this operation, false otherwise.
     */
	//For the unsorted addAll
	//O(N^2)(since the add method in unsorted set is O(N), and here has another while loop)
	public boolean addAll(ISet<E> otherSet){
		// check precondition
		if(otherSet == null)
			throw new IllegalArgumentException("Fail precondition: item != null");		
		//add every elements in other set into this set
		int presize = this.size();
		Iterator<E> iterator = otherSet.iterator();
		while (iterator.hasNext()){
			this.add(iterator.next());
		}
		//if the size of the this set mean some elements not contains in this set
		return this.size() != presize;
	}
	
	/**
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     */
	//O(N)
	//Simply remove every element by using iterator for both set.
	public void clear(){
		Iterator<E> iterator = this.iterator();
		while(iterator.hasNext()){
			iterator.next();
			iterator.remove();
		}
	} 
	
	/**
     * Determine if item is in this set. 
     * <br>pre: item != null
     * @param item element whose presence is being tested. Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
	//Different algorithm for two type sort
    public abstract boolean contains(E item);

    
	/**
     * Determine if all of the elements of otherSet are in this set.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet, 
     * false otherwise.
     */
    //For the unsorted containAll
  	//O(N^2)(since the contains method in unsorted set is O(N), and here has another while loop)
    public boolean containsAll(ISet<E> otherSet){
    	//check the precondition
    	if(otherSet == null)
			throw new IllegalArgumentException("Fail precondition: item != null");
    	//check contain all by the every elements in this set contained by other set
    	Iterator<E> iterator = otherSet.iterator();
    	while (iterator.hasNext()){
    		if(!this.contains(iterator.next()))
    			return false;
    	}
    	return true;
    }
    

	/**
     * Create a new set that is the difference of this set and otherSet. Return an ISet of 
     * elements that are in this Set but not in otherSet. Also called
     * the relative complement. 
     * <br>Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z] then
     * A.difference(B) would return an ISet with elements [X, Y] while
     * B.difference(A) would return an ISet with elements [W]. 
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the difference of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     */
    //Different algorithm for two type sort
    public abstract ISet<E> difference(ISet<E> otherSet);
    
    
    

    /**
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * <br>pre: none
     * @param other the object to compare to this set 
     * @return true if other is a Set and has the same elements as this set
     */
    //For the unsorted equals
  	//O(N^2)
    public boolean equals(Object other){
    	//check the precondition and check the type of the other
    	if( other == null || !(other instanceof ISet<?>)){
    		return false;
    	}
    	//set will be equal only when the size is equal
    	if(this.size()==((ISet<?>) other).size()){
        	Iterator<E> thisIterator = this.iterator();
        	int count =0;
        	//check the every elements in other with every elements in this set
    		while(thisIterator.hasNext()){
    			E temp = thisIterator.next();
        		Iterator<?> otherIterator = ((ISet<?>)other).iterator();
    			while(otherIterator.hasNext()){
    				if(otherIterator.next().equals(temp)){
    				count++;
    				}
    			}
    		}
    		if(count==this.size()){
    		return true;
    		}
    	}
    	return false;
    }
    	
    
	/**
     * create a new set that is the intersection of this set and otherSet.
     * <br>pre: otherSet != null<br>
     * <br>post: returns a set that is the intersection of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     */
    //Different algorithm for two type sort
    public abstract ISet<E> intersection(ISet<E> otherSet);
    
    
	/**
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     */
    //Different algorithm for two type sort
    public abstract Iterator<E> iterator();
    
	/**
     * Remove the specified item from this set if it is present.
     * pre: item != null
     * @param item the item to remove from the set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise
     */
    //O(N)
  	//Simply remove the element by using iterator for both set.
    public boolean remove(E item){
    	//check the precondition
    	if(item == null)
			throw new IllegalArgumentException("Fail precondition: item != null");	
    	//use the iterator to find the target
    	Iterator<E> iterator = this.iterator();
    	while (iterator.hasNext()){
    		if(iterator.next()==item){
    			iterator.remove();
    			return true;
    		}
    	}
		return false;
    }
    
    
	/**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
    //Since we can use Arraylist.size() in both set class, which O(1)
    public abstract int size();
    
    
	/**
     * Create a new set that is the union of this set and otherSet.
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     */
	//O(N^2) for both set method
    public ISet<E> union(ISet<E> otherSet){
    	//check the precondition
    	if (otherSet == null)
            throw new IllegalArgumentException("Union otherSet is null");
        //Find the differences elements between this and other Set
    	ISet<E> result = this.difference(otherSet);
    	//And add all the common elements
        result.addAll(otherSet);
        return result;
    }
    
    //toString method is given
    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (this.size() > 0)
            result.setLength(result.length() - seperator.length());

        result.append(")");
        return result.toString();
    }
}
