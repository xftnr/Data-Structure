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
import java.util.ArrayList;

/**
 * A simple implementation of an ISet. 
 * Elements are not in any particular order.
 * Students are to implement methods that 
 * were not implemented in AbstractSet and override
 * methods that can be done more efficiently. 
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {
	//The instance variable
	private ArrayList<E> myCon;
	
	//constructor, set up the ArrayList myCon
	public UnsortedSet(){
		myCon = new ArrayList<E>();
	}
	
	/**
     * Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise.
     */
	//O(N)
    public boolean add(E item){
    	//check the precondition
    	if(item == null)
			throw new IllegalArgumentException("Fail precondition: item != null");
    	//if already contain the item then not add
    	if(!(this.contains(item))){
    		myCon.add(item);
    		return true;
    	}
    	return false;
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
    //O(N^2)
    public ISet<E> difference(ISet<E> otherSet){
    	//check the precondition
    	if(otherSet == null)
			throw new IllegalArgumentException("Fail precondition: otherSet != null");
    	ISet<E> result = new UnsortedSet<E>();
    	Iterator<E> iterator = this.iterator();
    	//check every elements if not in other set then it is differences
    	while (iterator.hasNext()){
    		E temp = iterator.next();
    		if(!otherSet.contains(temp))
    			result.add(temp);
    	}
    	return result;
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
    //O(N^2)
    public ISet<E> intersection(ISet<E> otherSet){
    	//check precondition
    	if(otherSet==null){
    		throw new IllegalStateException("intersection otherSet is null");
    	}
    	//looking for the elements both sets contains
    	ISet<E> result=new UnsortedSet<E>();
    	Iterator<E> iterator = otherSet.iterator();
    	while (iterator.hasNext()){
    		E temp = iterator.next();
    		if(this.contains(temp))
    			result.add(temp);
    	}
    	return result;
    }
    
    
	/**
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     */
    // O(1)
    public Iterator<E> iterator(){
    	return myCon.iterator();
    } 
     
	/**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
    //O(1)
    public int size(){
    	return myCon.size();
    }
    
    /**
     * Determine if item is in this set. 
     * <br>pre: item != null
     * @param item element whose presence is being tested. Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    //O(N)
    public boolean contains(E item){
    	//check the precondition
    	if(item == null)
			throw new IllegalArgumentException("Fail precondition: item != null");
    	//exist a element in this set same with the item
    	Iterator<E> iterator = this.iterator();
    	while (iterator.hasNext()){
    		if(iterator.next() == item)
    			return true;
    	}
    	return false;
    }
    
}
