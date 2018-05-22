import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

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
public class AnagramSolver {

	private Set<String> keyset;
	private TreeMap<String, LetterInventory> storage = new TreeMap<String, LetterInventory>();
	
	public AnagramSolver(List<String> dictionary){
		//precondition for checking the dictionary
		if(dictionary==null){
			throw new IllegalStateException("Dictionary is null");
		}
		//set up the map put the words into a tree map;
		for(String words : dictionary){
			storage.put(words, new LetterInventory(words));
		}
	}
	
	public List<List<String>> getAnagrams(String phrase, int maxWords){
		//check the precondition
		if(phrase == null || maxWords<0){
			throw new IllegalStateException("Wrong!");
		}
		
		//make map key into set so I can be easier to reach those keys
		keyset=storage.keySet();
		//then transform words from set to a ArrayList
		ArrayList<String> availableWords = new ArrayList<String>();
		for(String words: keyset){
			availableWords.add(words);
		}
		//set up the result type
		List<List<String>> result = new ArrayList<List<String>>();
		List<String> list = new ArrayList<String>(); 
		LetterInventory inputPhrase = new LetterInventory(phrase);
		//for the special case max==0 then there is no limit
		if(maxWords==0){
			maxWords=-1;
		}
		//call recursive method
		found(inputPhrase, maxWords, result, list, availableWords,0);
		//sort
		Collections.sort(result, new AnagramComparator());
		return result;
	}
	
	private void found(LetterInventory phrase, int max, List<List<String>> result, List<String> list, 
			ArrayList<String> available, int set){
		//base case when where is no letter inventory left
		if(phrase.isEmpty()){
			result.add(list);
		}
		//The recursive will stop when no letter inventory left or there already have max word in list
		// or max==-1 then there is no limitation for the num of words
		if(!phrase.isEmpty() && (max>0 || max<=-1)){
			//for every time recursive there are different matching words left
			//smaller the range to efficiency
			ArrayList<String> match= new ArrayList<String>();
			for(String words: available){
				if(phrase.subtract(storage.get(words))!=null||(list.size()!=0 && words==list.get(list.size()-1))){
					match.add(words);	
				}
			}
			//To more efficiency the next words in the list always after the pre words in matching words list
			if(list.size()!=0){
				//must be include itself cuz "dog" anagram can be "dog"
				set = match.indexOf(list.get(list.size()-1));
			}
			for(int i=set ;i<match.size();i++){
				LetterInventory newWord = phrase.subtract(storage.get(match.get(i)));
				//for sometimes itself will not fit the matching words, so add condition for that
				if(newWord!=null){
					//use the helper method to tracking the word list
					//so it can be easier to backtracking
					List<String> temp =tracking(list);
					temp.add(match.get(i));
					found(newWord, max-1,result,temp, match,0);
				}
			}
		}
	}
	
	//backtracking helping method
	private List<String> tracking(List<String> input){
		List<String> result= new ArrayList<String>();
		for(String element: input){
			result.add(element);
		}
		return result;
	}
	
	//Comparator
	private static class AnagramComparator implements Comparator<List<String>> {
        public int compare(List<String> a1, List<String> a2) {
            // code for compare
        	if(a1.size()!=a2.size()){
        		return a1.size()-a2.size();
        	}
        	else{
        		int i=0;
        		int compare =0;
        		// for the some length list compare
				while(i<a1.size()&& compare==0){
					compare=a1.get(i).compareTo(a2.get(i));
					i++;
				}
				return compare;
        	}
        }
    } // end of AnagramComparator
}

