/*  Student information for assignment:
 *
 *  On my honor, <PENGDI XIA>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID:px353
 *  email address:xiapengdi@yahoo.com
 *  Grader name:51925 
 *  Unique section number:Gilbert Maldonado
 *  Number of slip days I am using:0
 */

import java.util.Iterator;
import javafx.scene.Node;

public class LinkedList<E> implements IList<E> {
    // CS314 student. Add you instance variables here.
	private DoubleListNode<E> head;
	private DoubleListNode<E> tail;
	private DoubleListNode<E> temp;
	private int size;
    // You decide what instance variables to use.
    // Must adhere to assignment requirements. No ArrayLists or Java LinkedLists.

	

	//constructor for initialize instance variables, O(1)
	public LinkedList(){
		head = new DoubleListNode<E>();
		tail = new DoubleListNode<E>();
		size=0;
		head.setPrev(tail);
		tail.setNext(head);
	}
	
	//helper method(redundancy): position, O(N)
	private DoubleListNode<E> positionNode(int pos){
		DoubleListNode<E> result = head;
		int index=0;
		while(index!=pos){
			result = result.getNext();
			index++;
		}
		return result;
	}
	//helper method(redundancy): set insert, O(1)
	private void setup (DoubleListNode<E> insert, DoubleListNode<E> tail){
		tail.getPrev().setNext(insert);
		insert.setPrev(tail.getPrev());
		insert.setNext(tail);
		tail.setPrev(insert);
	}
		
	//override for all LinkedList method
	/**
     * Add an item to the end of this list.
     * <br>pre: item != null
     * <br>post: size() = old size() + 1, get(size() - 1) = item
     * @param item the data to be added to the end of this list, item != null
     */
	//make it as a cycle, O(1)
	public void add(E item) {
		if(item == null){
			throw new IllegalStateException("Parameter error.");
		}
		temp = new DoubleListNode<E>();
		if(size==0){
			head.setData(item);
			//set the tail always be null. the last element.next = tail and tail.next = head
			head.setPrev(tail);
			head.setNext(tail);
			tail.setNext(head);
			tail.setPrev(head);
			
		}else{
			temp.setData(item);
			setup(temp, tail);
		}
		size++;
	}

	/**
     * Insert an item at a specified position in the list.
     * <br>pre: 0 <= pos <= size(), item != null
     * <br>post: size() = old size() + 1, get(pos) = item, all elements in
     * the list with a positon >= pos have a position = old position + 1
     * @param pos the position to insert the data at in the list
     * @param item the data to add to the list, item != null
    */
	//O(N)
	public void insert(int pos, E item) {
		if(pos<0||pos>size||item== null){
			throw new IllegalStateException("Parameter error.");
		}
		if(pos==0){
			addFirst(item);
		}
		else if(pos==size){
			add(item);
		}
		else if(pos<size){
			temp = new DoubleListNode<E>();
			DoubleListNode<E> tempPos = positionNode(pos);
			temp.setData(item);
			setup(temp, tempPos);
			size++;
		}
	}

	/**
     * Change the data at the specified position in the list.
     * the old data at that position is returned.
     * <br>pre: 0 <= pos < size(), item != null
     * <br>post: get(pos) = item, return the
     * old get(pos)
     * @param pos the position in the list to overwrite  
     * @param item the new item that will overwrite the old item, item != null
     * @return the old data at the specified position
     */
	//O(N) still questions
	public E set(int pos, E item) {
		if(pos<0||pos>=size||item== null){
			throw new IllegalStateException("Parameter error.");
		}
		E result = positionNode(pos).getData();
		positionNode(pos).setData(item);
		return result;
	}

	/**
     * Get an element from the list.
     * <br>pre: 0 <= pos < size()
     * <br>post: return the item at pos
     * @param pos specifies which element to get
     * @return the element at the specified position in the list
     */
	//O(N)
	public E get(int pos) {
		if(pos<0 || pos>=size){
			throw new IllegalStateException("Parameter error.");
		}
		return positionNode(pos).getData();
	}

	/**
     * Remove an element in the list based on position.
     * <br>pre: 0 <= pos < size()
     * <br>post: size() = old size() - 1, all elements of
     * list with a positon > pos have a position = old position - 1
     * @param pos the position of the element to remove from the list
     * @return the data at position pos
     */
	//O(N)
	public E remove(int pos) {
		if(pos<0 || pos>=size){
			throw new IllegalStateException("Parameter error.");
		}
		DoubleListNode<E> tempPos = positionNode(pos);
		if(pos==0){
			removeFirst();
		}
		else if(pos==size-1){
			removeLast();
		}
		else{
		tempPos.getPrev().setNext(tempPos.getNext());
		tempPos.getNext().setPrev(tempPos.getPrev());
		size--;
		}
		
		return tempPos.getData();
	}
	
	/**
     * Remove the first occurrence of obj in this list.
     * Return <tt>true</tt> if this list changed as a result of this call, <tt>false</tt> otherwise.
     * <br>pre: obj != null
     * <br>post: if obj is in this list the first occurrence has been removed and size() = old size() - 1. 
     * If obj is not present the list is not altered in any way.
     * @param obj The item to remove from this list. obj != null
     * @return Return <tt>true</tt> if this list changed as a result of this call, <tt>false</tt> otherwise.
     */
	//O(N)
	public boolean remove(E obj) {
		if(obj == null){
			throw new IllegalStateException("Parameter error.");
		}
		temp = head;
		boolean result = false;
		for(int i=0; i<size ; i++){
			if(temp.getData().equals(obj)){
				result= true;
				temp.getPrev().setNext(temp.getNext());
				temp.getNext().setPrev(temp.getPrev());
				if(i==0){
					head = temp.getNext();
				}
				size--;
			}
			temp = temp.getNext();
		}
		return result;
	}

	/**
     * Return a sublist of elements in this list from <tt>start</tt> inclusive to <tt>stop</tt> exclusive.
     * This list is not changed as a result of this call.
     * <br>pre: <tt>0 <= start <= size(), start <= stop <= size()</tt>
     * <br>post: return a list whose size is stop - start and contains the elements at positions start through stop - 1 in this list.
     * @param start index of the first element of the sublist.
     * @param stop stop - 1 is the index of the last element of the sublist.
     * @return a list with <tt>stop - start</tt> elements, The elements are from positions <tt>start</tt> inclusive to
     * <tt>stop</tt> exclusive in this list. If start == stop an empty list is returned.
     */
	//O(N)
	public IList<E> getSubList(int start, int stop) {
		if(start<0||start>stop||stop>size){
			throw new IllegalStateException("Parameter error.");
		}
		IList<E> result = new LinkedList<E>();
		head = positionNode(start);
		temp = head;
		for(int i=start;i<stop;i++){
			result.add(temp.getData());
			temp = temp.getNext();
		}
		//rebuil the list
		temp = positionNode(stop-1);
		temp.setNext(tail);
		tail.setPrev(temp);
		head.setPrev(tail);
		size=stop-start;
		return result;
	}

	/**
     * Return the size of this list. In other words the number of elements in this list.
     * <br>pre: none
     * <br>post: return the number of items in this list
     * @return the number of items in this list
     */
	//O(1)
	public int size() {
		return size;
	}

	/**
     * Find the position of an element in the list.
     * <br>pre: item != null
     * <br>post: return the index of the first element equal to item
     * or -1 if item is not present
     * @param item the element to search for in the list. item != null
     * @return return the index of the first element equal to item or a -1 if item is not present
     */
	//same with above to find the item, O(1)
	public int indexOf(E item) {
		if(item==null){
			throw new IllegalStateException("Parameter error.");
		}
		return indexOf(item, 0);
	}

	/**
     * find the position of an element in the list starting at a specified position.
     * <br>pre: 0 <= pos < size(), item != null
     * <br>post: return the index of the first element equal to item starting at pos
     * or -1 if item is not present from position pos onward
     * @param item the element to search for in the list. Item != null
     * @param pos the position in the list to start searching from
     * @return starting from the specified position return the index of the first element equal to item or a -1 if item is not present between pos and the end of the list
     */
	//O(N)
	public int indexOf(E item, int pos) {
		if(pos<0||pos>=size||item== null){
			throw new IllegalStateException("Parameter error.");
		}
		boolean pointer = true;
		temp = positionNode(pos);
		int result=-1;
		int index=pos;
		//find the first index
		while(pointer && temp.getData()!=null){
			if(temp.getData().equals(item)){
				pointer=false;
				result=index;
			}
			index++;
			temp = temp.getNext();
		}
		return result;
	}

	/**
     * return the list to an empty state.
     * <br>pre: none
     * <br>post: size() = 0
     */
	//O(1)
	public void makeEmpty() {
		head.setData(null);
		tail.setData(null);
		head.setNext(tail);
		tail.setPrev(head);
		size = 0;
	}

	//rewrite the iterator
	@Override
	public Iterator<E> iterator() {
		
		return new Iterator<E>(){
			DoubleListNode<E> temp = head;
			DoubleListNode<E> temp2;
			boolean removable = false;
			
			//O(1)
			public boolean hasNext() {
				boolean result=true;
				if(temp.getData()==null){
					result=false;
				}
				return result;
			}

			//O(1)
			public E next() {
				if(!hasNext()){
					throw new IllegalStateException("Illegal iterator next.");
				}
				E result=(E) temp.getData();
				removable=true;
				temp2 = temp;
				temp = temp.getNext();
				//index++;
				return result;
			}
			
			//O(1)
			public void remove(){
				if(!removable){
					throw new IllegalStateException("Illegal iterator remove.");
				}
				removable=false;
				temp2.getPrev().setNext(temp2.getNext());
				temp2.getNext().setPrev(temp2.getPrev());
				head=temp;
				head.setPrev(tail);
				size--;
			}
			
		};
	}

	/**
     * Remove all elements in this list from <tt>start</tt> inclusive to <tt>stop</tt> exclusive.
     * <br>pre: <tt>0 <= start < size(), start <= stop <= size()</tt>
     * <br>post: <tt>size() = old size() - (stop - start)</tt>
     * @param start position at beginning of range of elements to be removed
     * @param stop stop - 1 is the position at the end of the range of elements to be removed
     */
	public void removeRange(int start, int stop) {
		if(start<0||start>stop||stop>size||start==size){
			throw new IllegalStateException("Parameter error.");
		}
		// when start==stop list will not change
		if(start!=stop){
			DoubleListNode<E> temp2 = positionNode(start);
			temp= positionNode(stop);
			temp2.getPrev().setNext(temp);
			temp.setPrev(temp2.getPrev());
			if(start==0){
				head=temp;
			}
			size=size-(stop-start);
		}
		
	}

	/**
     * Return a String version of this list enclosed in
     * square brackets, []. Elements are in
     * are in order based on position in the 
     * list with the first element
     * first. Adjacent elements are separated by comma's
     * @return a String representation of this IList
     */
	//O(N)
    public String toString(){
    	StringBuilder sb = new StringBuilder();
    	temp = head;
    	sb.append("[");
    	if(size!=0){
    		sb.append(temp.getData());
    	}
    	//build up the body
    	for(int i=0; i<size-1; i++){
    		temp = temp.getNext();
    		sb.append(", "+temp.getData());
    	}
    	sb.append("]");
    	String result = sb+"";
    	return result;
    }
    /**
     * Determine if this IList is equal to other. Two
     * ILists are equal if they contain the same elements
     * in the same order.
     * @return true if this IList is equal to other, false otherwise
     */
    //O(N)
    public boolean equals(Object other){
    	boolean result=false;
    	if(!(other instanceof IList)){
    		throw new IllegalStateException();
    	}
    	//check size
    	else if(this.size==((IList<E>) other).size()){
    		Iterator thisOne =this.iterator();
        	Iterator otherOne =((IList<E>) other).iterator();
    		result = true;
    		//check element
    		while(thisOne.hasNext()&&result){
    			if(!thisOne.next().equals(otherOne.next())){
    				result=false;
    			}
    		}
    	}
    	return result;
    }
    
    /**
     * add item to the front of the list.
     * <br>pre: item != null
     * <br>post: size() = old size() + 1, get(0) = item
     * @param item the data to add to the front of this list
     */
	//O(1)
    public void addFirst(E item){
    	if(item==null){
    		throw new IllegalStateException("Parameter error");
    	}
    	temp = new DoubleListNode<E>();
    	if (size==0){
    		add(item); 
    	}else{
    		temp.setData(item);
    		temp.setNext(head);
    		temp.setPrev(tail);
    		head.setPrev(temp);
    		tail.setNext(temp);
    		head = temp;
    		size++;
    	}
    }

    /**
     * add item to the end of the list.
     * <br>pre: item != null
     * <br>post: size() = old size() + 1, get(size() -1) = item
     * @param item the data to add to the end of this list
     */
    //O(1)
    public void addLast(E item){
    	add(item);
    }


    /**
     * remove and return the first element of this list.
     * <br>pre: size() > 0
     * <br>post: size() = old size() - 1
     * @return the old first element of this list
     */
    //O(1)
    public E removeFirst(){
    	if(size <= 0){
    		throw new IllegalStateException("Nothing can remove.");
    	}
    	temp = head;
    	head = head.getNext();
    	head.setPrev(tail);
    	tail.setNext(head);
    	size--;
        return temp.getData();
    }


    /**
     * remove and return the last element of this list.
     * <br>pre: size() > 0
     * <br>post: size() = old size() - 1
     * @return the old last element of this list
     */
    //O(1)
    public E removeLast(){
    	if(size <= 0){
    		throw new IllegalStateException("Nothing can remove.");
    	}
    	temp = tail.getPrev();
    	//size == 1
    	if(size==1){
    		temp.setData(null);
    	}else{
    		temp.getPrev().setNext(tail);
    		tail.setPrev(temp.getPrev());
    	}
    	size--;
        return temp.getData();
    }
}