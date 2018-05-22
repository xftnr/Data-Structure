
/* CS 314 STUDENTS: FILL IN THIS HEADER AND THEN COPY AND PASTE IT TO YOUR
 * LetterInventory.java AND AnagramSolver.java CLASSES.
 *
 * Student information for assignment:
 *
 *  On my honor, <PENGDI XIA>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID:px353
 *  email address:xiapengdi@yahoo.com
 *  Grader name:Gilbert Maldonado
 *  Number of slip days I am using:1
 */


public class LetterInventory {
	private int[] inventory=new int[26];
	private int size;
	//constructors
	public LetterInventory(String input){
		//check the precondition which input can not be null
		if(input==null){
			throw new IllegalStateException("Input string is null");
		}
		//ignore the case
		input=input.toLowerCase();
		//put the letter into inventory
		for(int i=0;i<input.length();i++){
			if ('a' <= input.charAt(i) && input.charAt(i) <= 'z'){
				inventory[input.charAt(i)-'a']++;
				size++;
			}
		}
	}
	
	//my own constructor to efficiency the subtract 
	private LetterInventory(int[] input, int newSize){
		inventory = input;
		size = newSize;
	}
	
	//pre:given char
	//return the frequency of the letter
	public int get(char inputChar){
		//check the precondition which input char is a English letter
		if(inputChar<'A'||inputChar>'z'){
			throw new IllegalStateException("Input char is not a English letter.");
		}
		//to ignore the case
		String result=inputChar+"";
		return inventory[result.toLowerCase().charAt(0) -'a'];
	}	
	
	//return the total numbers of letters
	public int size(){
		return size;
	}
	
	//return true if size is 0
	public boolean isEmpty(){
		return size==0;
	}
	
	//return the a string include all letters in alphabetical order
	public String toString(){
		String result="";
		//alphabetical order
		for(int i=0;i<26;i++){
			//repeat times
			for(int j=0; j<inventory[i];j++){
				result +=(char) (i+'a');
			}
		}
		return result;
	}
	
	//pre: parameter will not be null
	//post: calling object and parameter will not be changed
	//return a new LetterInvertory(add)
	public LetterInventory add(LetterInventory otherOne){
		//check the precondition
		if(otherOne==null){
			throw new IllegalStateException("Input other LetterInventory is null");
		}
		LetterInventory result = new LetterInventory(this.toString()+otherOne.toString());
		return result;
	}
	
	//pre: parameter will not be null
	//post: calling object and parameter will not be changed
	//return a new LetterInvertory(subtract)
	public LetterInventory subtract(LetterInventory otherOne){
		//check the precondition
		if(otherOne==null){
			throw new IllegalStateException("Input other LetterInventory is null");
		}
		
		int[] newfreq = new int[26];
		int newSize =0;
		//subtract each letter frequency and add into size 
		for(int i=0;i<26;i++){
			newfreq[i] = this.inventory[i] - otherOne.inventory[i];
			if(newfreq[i]<0){
				return null;
			}
			newSize +=newfreq[i];
		}
		//use own constructor to build a new letter inventory
		LetterInventory result = new LetterInventory(newfreq, newSize);
		return result;
	}
	
	//return true if they have same letter frequency
	//rerride the Object not letterInventory
	public boolean equals(Object otherOne){
		//return false if they are different type or null
		if(otherOne==null|| !(otherOne instanceof LetterInventory)){
			return false;
		}
		boolean result=true;
		//check the every letter frequency
		for(int i=0;i<26;i++){
			if(((LetterInventory)this).get((char)(i+'a'))!=((LetterInventory)otherOne).get((char)(i+'a'))){
				result=false;
			}
		}
		return result;
	}
}
