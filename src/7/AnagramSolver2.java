import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

/*  Student information for assignment:
*
*  On my honor, <PEIJIE YANG>, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  Name: Peijie Yang
*  email address:loarek1996@hotmail.com
*  UTEID:py2554
*  Section 5 digit ID: 51970
*  Grader name:GILBERT MALDONADO
*  Number of slip days I am using:0
*/


public class AnagramSolver2 {
	//
	private ArrayList<String> originalDictionary;
	private HashMap<String, LetterInventory> inventoryDict;
	
	//constructor for collecting given data into a arrayList, and a hashMap
	public AnagramSolver2(List<String> data){
		originalDictionary = new ArrayList<String>();
		inventoryDict = new HashMap<String, LetterInventory>();
		//add every element in list into arraylist and hashmap
		for(int i = 0; i < data.size(); i++){
			originalDictionary.add(data.get(i));
			inventoryDict.put(data.get(i), new LetterInventory(data.get(i)));
		}
	}
	// method that find anagram and return a List<List<String>> 
	// pre: target!=null and maxNum >=0
	// post: return a List<List<String>> contains all correct anagram 
	public List<List<String>>getAnagrams (String target, int maxNum){
		//check precondition
		if(target == null || maxNum < 0 ){
			throw new IllegalArgumentException("Failed precondition");
		}
		ArrayList<List<String>> result = new ArrayList<List<String>> ();
		//collect information from a new LetterInventory given a target string
		LetterInventory currentInventory = new LetterInventory (target);
		//create a new arraylist, and a new hashmap to store a simplified version of inventory 
		ArrayList<String> newDictionary = new ArrayList<>();
		HashMap<String, LetterInventory> newInventoryDict = new HashMap<String, LetterInventory>();
		//simplify inventory used in recursion step. only add inventory that currentInventory can substract;
		for(int i = 0; i < originalDictionary.size(); i++){
			if(currentInventory.subtract(inventoryDict.get(originalDictionary.get(i))) != null){
				newDictionary.add(originalDictionary.get(i));
				newInventoryDict.put(originalDictionary.get(i), inventoryDict.get(originalDictionary.get(i)));
			}
		}
		//use a helpmethod to add list<String> into result
		helpMethod(currentInventory, newDictionary, newInventoryDict, maxNum, new ArrayList<String>(), result, 0, 0);
		// use anagramcomparator() to sort result
		Collections.sort(result, new AnagramComparator());
		return result;
	}
	// helpMethod to add list of anagram to result 
	//parameter currentAnagram is used to store each Strings into a single list
	// int num is the max num of String in each list of string. 
	// int index indicate position in newDictionary
	// int count counts number of Strings in each list. 
	private void helpMethod(LetterInventory currentInventory, ArrayList<String> newDictionary, 
								HashMap<String, LetterInventory> newInventoryDict, int num,ArrayList<String> currentAnagram,
								ArrayList<List<String>> result, int count, int index){
		//Base case: when currentInventory.size is 0, meaning we completely use everycharacter of target string
		//then we check number of elements, if count<= num or num == 0(no num limit), we add those String 
		//into a list, and add list into arraylist
		if(currentInventory.size()==0){
			if(num == 0 || count <= num){
				//create a new arraylist to store Strings 
				ArrayList<String> anagramStore = new ArrayList<>();
				for(String currentString : currentAnagram) {
					anagramStore.add(currentString);
				}
				result.add(anagramStore);
				//initialize arraylist
				anagramStore = null;	
			}
		}
		//recursion step: 
		else{
			// when count does not meet the max-1, we can do recursion step
			if(count+1<= num || num == 0){
				// get each string in dictionary, and try if they can form anagram give a target 
				for(int i = index; i< newDictionary.size();i++){
					String str = newDictionary.get(i);
					//currentInventory substracted by the inventory of the string
					LetterInventory newInventory = currentInventory.subtract(newInventoryDict.get(str));
					//if newinventory can do sbustract, we can add that string into currentAnagram
					if(newInventory != null) {
						currentAnagram.add(str);
						count++;
						helpMethod(newInventory, newDictionary, newInventoryDict, num, currentAnagram, result, count, i);
						//recursivebacktracking
						count--;
						currentAnagram.remove(currentAnagram.size()-1);
					}
				}
			}
		}
	}
	
	
	private static class AnagramComparator implements Comparator<List<String>> {
		public int compare(List<String> list1, List<String> list2){
			//first compare length of two list, if length of list1 > length of list2, list1 should be in the front 
			if(list1.size() != list2.size()){
				return list1.size() - list2.size();
			}
			// if they have same length, then compare their content. 
			else {
				Iterator<String> sIterator1 = list1.iterator();
				Iterator<String> sIterator2 = list2.iterator();
				//compare every element of them 
				while(sIterator1.hasNext() && sIterator2.hasNext()){
					String word1 = sIterator1.next();
					String word2 = sIterator2.next();
					if(!(word1.equals(word2))) {
						return word1.compareTo(word2);
					}
				}
			}
			return 0;
		}
	}
	

} 

