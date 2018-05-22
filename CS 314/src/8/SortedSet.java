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
 * In this implementation of the ISet interface the elements in the Set are 
 * maintained in ascending order.
 * 
 * The data type for E must be a type that implements Comparable.
 * 
 * Students are to implement methods that were not implemented in AbstractSet 
 * and override methods that can be done more efficiently. An ArrayList must 
 * be used as the internal storage container. For methods involving two set, 
 * if that method can be done more efficiently if the other set is also a 
 * SortedSet do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {
	//instance variable
	private ArrayList<E> myCon;
    private ArrayList<E> sortHelper;
    private int number;
    /**
     * create an empty SortedSet
     */
    //constructor
    public SortedSet() {
    	myCon = new ArrayList<E>();
    }

    /**
     * create a SortedSet out of an unsorted set. <br>
     * @param other != null
     */
    //constructor with mergesort
    public SortedSet(ISet<E> other) {
    	//precondition
    	if(other == null)
   			throw new IllegalArgumentException("Fail precondition: other != null");
       	//set up the myCon
    	Iterator<E> iterator = other.iterator();
       	myCon = new ArrayList<E>();
       	while(iterator.hasNext()){
       		myCon.add(iterator.next());
       	}
       	number = myCon.size();
       	this.sortHelper = new ArrayList<E>();
       	//add a help arraylist for recursion
       	for(int i = 0; i < myCon.size(); i++){
       		sortHelper.add(myCon.get(i));
       	}
       	//start to sort
       	mergeSort(0, number-1);
    }

    //recurion part
    private void mergeSort(int low, int high){
    	//no base case but condition is kind of base case
       	if(low < high) {
       		//separate the elements
       		int middle = (low + high)/2;
       		// split the list from middle, forming two part, and split another time, until cannot be seperated anymore, forming several
       		// small lists with length of 2
       		mergeSort(low, middle);
       		mergeSort(middle+1, high);
       		//when it backtracking sort the set
       		merge(sortHelper, low, middle+1, high);
       	}
    }
    
    //sort part, sort each small list, and by backtracking, merge them together
    private void merge(ArrayList<E> sortHelper, int low, int middle, int high){
    	//set up the local variable
    	int leftEnd = middle -1 ;
       	int k = low;
       	int num = high - low +1;
       	//add the elements from low to high
       	while(low <= leftEnd && middle <= high){
       		if(myCon.get(low).compareTo(myCon.get(middle)) <= 0){
       			sortHelper.set(k++, myCon.get(low++));
       		}
       		else{
       			sortHelper.set(k++, myCon.get(middle++));
       			
       		}
       	}
       	//the left low index("smaller" element) to add at the end
       	while(low <=leftEnd){
       		sortHelper.set(k++, myCon.get(low++));
       	}
       	//the left high index("bigger" element) to add at the end 
       	while(middle <=high){
       		sortHelper.set(k++, myCon.get(middle++));
       	}
       	//copy back to the myCon ArrayList
       	for(int i = 0; i < num; i++, high--){
       		myCon.set(high, sortHelper.get(high));
       	}
    }

    
    
    /**
     * Return the smallest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the smallest element in this SortedSet.
     */
    //O(1)
    public E min() {
    	//the first element
    	return myCon.get(0);
    }

    /**
     * Return the largest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the largest element in this SortedSet.
     */
    //O(1)
    public E max() {
    	//the last element
    	return myCon.get(myCon.size()-1);
    }

	@Override
	/**
     * Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise.
     */
	//O(N)
	public boolean add(E item){
		//check precondition
		if(item == null)
   			throw new IllegalArgumentException("Fail precondition: item != null");
		int index=0;
		//when item is "big" than the element in set then compare with next
		while(index<myCon.size() && item.compareTo(myCon.get(index))>0){
			index++;
		}
		//when the item is same with one of element in set return false
		if(index<myCon.size() && item.compareTo(myCon.get(index))==0){
			return false;
		}
		//add the the element when the next element is "bigger" than item
		//Because the add is actually insert ,the tail will move one step after
		//so the whole process is O(N)
		myCon.add(index, item);
		return true;
	}
	
	/**
     * A union operation. Add all items of otherSet that are not already present in this set
     * to this set.
     * @param otherSet != null
     * @return true if this set changed as a result of this operation, false otherwise.
     */
	//O(N)
	public boolean addAll(ISet<E> otherSet){
		//check the precondition
		if(otherSet == null)
			throw new IllegalArgumentException("Fail precondition: otherSet != null");
		//the local variable
		ArrayList<E> result = new ArrayList<E>();
		Iterator<E> otherIterator = otherSet.iterator();
		E other=null;
		int index=0;
		//other set while loop
		while(otherIterator.hasNext()){
			other = otherIterator.next();
			//this set while loop
			//when the element is "smaller" than the other set element
			while(index<myCon.size() && myCon.get(index).compareTo(other)<=0){
				//then add the unequal element into result
				if(myCon.get(index)!=other){
					result.add(myCon.get(index));
					System.out.println(result.toString());
				}
				index++;
			}
			//add the other elements when other elements "smaller"
			result.add(other);
		}
		//although there are two while loop, the inside loop will only go through one times
		//the actual O is O(M+N)=O(N)
		//if this set has elements left
		while(index<myCon.size()){
			//add them at the end of the result because the set already sorted
			result.add(myCon.get(index));
			index++;
		}
		//change the current set
		myCon =result;
		//when it contain all then allAll will change anything
		//here the big O is O(N)
		return this.containsAll(otherSet);
		//the total big O is O(N)
	}

	@Override
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
	//O(N)
	public ISet<E> difference(ISet<E> otherSet) {
		//check the precondition
		if(otherSet == null)
			throw new IllegalArgumentException("Fail precondition: otherSet != null");
		//the difference is basically same with add all
		//there will not add the elements from others which is different
		ISet<E> result = new SortedSet<E>();
		Iterator<E> otherIterator = otherSet.iterator();
		E other=null;
		int index=0;
		while(otherIterator.hasNext()){
			other = otherIterator.next();
			while(index<myCon.size() && myCon.get(index).compareTo(other)<=0){
				if(myCon.get(index)!=other){
					result.add(myCon.get(index));
				}
				index++;
			}
			//will not all into result the other elements
		}
		while(index<myCon.size()){
			result.add(myCon.get(index));
			index++;
		}
		return result;
	}
	
	/**
     * Determine if all of the elements of otherSet are in this set.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet, 
     * false otherwise.
     */
	//O(N)
	public boolean containsAll(ISet<E> otherSet){
		//check the precondition
		if(otherSet == null)
			throw new IllegalArgumentException("Fail precondition: otherSet != null");
		//when the this set size is smaller than other set
		//there is no way this set can contain all other set elements
		if(myCon.size()<otherSet.size()){
			return false;
		}
		else{
			Iterator<E> otherIterator = otherSet.iterator();
			E other=null;
			int index=0;
			int count=0;
			//like the differences and add all check the elements by going though the set only one time
			while(otherIterator.hasNext()){
				other = otherIterator.next();
				while(index<myCon.size() && myCon.get(index).compareTo(other)<=0){
					//count the times when there is same elements
					if(myCon.get(index)==other){
						count++;
					}
					index++;
				}
			}
			//when the times is same with the other set size
			//it means this set contain all elements for other set
			if(count==otherSet.size()){
				return true;
			}
		}
		return false;
	}

	@Override
	/**
     * create a new set that is the intersection of this set and otherSet.
     * <br>pre: otherSet != null<br>
     * <br>post: returns a set that is the intersection of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     */
	//O(N)
	public ISet<E> intersection(ISet<E> otherSet){
		//check the precondition
		if(otherSet == null)
			throw new IllegalArgumentException("Fail precondition: otherSet != null");
		//like the differences check the elements by going though the set only one time
		ISet<E> result = new SortedSet<E>();
		Iterator<E> otherIterator = otherSet.iterator();
		E other=null;
		int i=0;
		while(otherIterator.hasNext()){
			other = otherIterator.next();
			while(i<myCon.size() && myCon.get(i).compareTo(other)<=0){
				//the different part from differences
				//here when element same with other set element then add into result
				if(myCon.get(i)==other){
					result.add(other);
				}
				i++;
			}
		}
		return result;
	}
	
	@Override
	/**
     * Determine if item is in this set. 
     * <br>pre: item != null
     * @param item element whose presence is being tested. Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
	//O(logN)
	public boolean contains(E item) {
		//check precondition
		if(item == null)
		   	throw new IllegalArgumentException("Fail precondition: item != null");
		//binary search to find the item
		int high = myCon.size()-1; 
		int low = 0;
		while(low <= high){
			int mid = (high + low)/2;
			if(item.compareTo(myCon.get(mid)) == 0){
				return true;
			}
			else if(item.compareTo(myCon.get(mid)) < 0){
				high = mid-1;
			}
			else{
				low = mid+1;
			}
		}
		//if not found return false
		return false;
	}
	
	@Override
	/**
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     */
	//O(1)
	public Iterator<E> iterator() {
		return myCon.iterator();
	}

	@Override
	/**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
	//O(1)
	public int size() {
		return myCon.size();
	}

	/**
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * <br>pre: none
     * @param other the object to compare to this set 
     * @return true if other is a Set and has the same elements as this set
     */
	//O(N)
	public boolean equals(Object other){
		//check the precondition and check the type of the other
		if( other == null || !(other instanceof ISet<?>) ){
    		return false;
		}
		ISet<?> temp = (ISet<?>) other;
		//when the size different, the set is different
		if(this.size() != temp.size())
			return false;
		//when the size is same
    	if(this.size()==temp.size()){
        	Iterator<E> thisIterator = this.iterator();
        	Iterator<?> otherIterator = ((ISet<?>)other).iterator();
        	//check every position elements
    		while(thisIterator.hasNext()){
    			E thisObject = thisIterator.next();
    			if(!(otherIterator.next() == (thisObject))){
    				return false;	
    			}
    		}
    	}
    	return true;
	}
}
