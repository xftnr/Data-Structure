package a10;
import java.util.Iterator;
import java.util.LinkedList;

public class PriorityQueue<E extends Comparable<E>> {
	//instance variables of the class
	//Linked List can make the add method big O smaller
	private LinkedList<E> container;
	
	//constructor, initialize the instance variables
	public PriorityQueue(){
		container = new LinkedList<E>();
	}
	
	//add method
	public void add (E input){
		//check the precondition
		if(input == null){
			throw new IllegalStateException("The input elements can not be null.");
		}
		//index for the position
		int index =0;
		Iterator<E> it = container.iterator();
		//find the position from the list sorted by frequency first then by value
		while(it.hasNext() && it.next().compareTo(input)<=0){
			index++;
		}
		container.add(index, input);
	}
	
	//peak method
	public E peak(){
		return container.getFirst();
	}
	
	//poll method
	public E poll(){
		//check the precondition
		if(container.size()==0){
			throw new IllegalStateException("lol");
		}
		return container.removeFirst();
	}

	//size method
	public int size(){
		return container.size();
	}
}
