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

// add imports as necessary

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * Manages the details of EvilHangman. This class keeps
 * tracks of the possible words from a dictionary during
 * rounds of hangman, based on guesses so far.
 *
 */
public class HangmanManager {

    // instance vars
	// AWords can not be altered after the constructor. Because the game might be played again.
	private ArrayList<String> AWords;
	private ArrayList<String> gameplay;
	private TreeMap<String, Integer> output;
	private ArrayList<Character> guesseslist;
	private int numofguesses;
	private int rightguesses;
	private TreeMap<String, ArrayList<String>> makeguesswithlist;
	private String guesses;
	private HangmanDifficulty difficulty;
	private int wordlength;
	private String lastpattern;
	private boolean debug;

    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * pre: words != null, words.size() > 0
     * @param words A set with the words for this instance of Hangman.
     * @param debugOn true if we should print out debugging to System.out.
     */
    public HangmanManager(Set<String> words, boolean debugOn) {
    	AWords = new ArrayList<String>();
    	for(String word : words){
    		AWords.add(word);
    	}
    	debug = debugOn;
    }

    /**
     * Create a new HangmanManager from the provided set of words and phrases. 
     * Debugging is off.
     * pre: words != null, words.size() > 0
     * @param words A set with the words for this instance of Hangman.
     */
    public HangmanManager(Set<String> words) {
    	AWords = new ArrayList<String>();
    	for(String word : words){
    		AWords.add(word);
    	}
    }


    /**
     * Get the number of words in this HangmanManager of the given length.
     * pre: none
     * @param length The given length to check.
     * @return the number of words in the original Dictionary with the given length
     */
    public int numWords(int length){
        int count=0;
        for (int i=0; i<AWords.size();i++){
        	if (AWords.get(i).length()==length){
        		count++;
        	}
        }
    	return count;
    }


    /**
     * Get for a new round of Hangman. Think of a round as a complete game of Hangman.
     * @param wordLen the length of the word to pick this time. numWords(wordLen) > 0
     * @param numGuesses the number of wrong guesses before the player loses the round. numGuesses >= 1
     * @param diff The difficulty for this round.
     */
    public void prepForRound(int wordLen, int numGuesses, HangmanDifficulty diff) {
    	// initialize instance vars and set up
    	gameplay=new ArrayList<String>();
    	guesseslist = new ArrayList<Character>();
    	output =new TreeMap<String, Integer>();
    	rightguesses =0;
    	String temps="";
    	numofguesses = numGuesses;
    	difficulty = diff;
    	wordlength = wordLen;
    	lastpattern = "";
    	//take the wordlen length word
    	Iterator<String> check = AWords.iterator();
        for(int i=0; i<AWords.size();i++){
        	temps= check.next();
        	if(temps.length()==wordLen){
        		gameplay.add(temps);
        	}
        }
        
    }


    /**
     * The number of words still possible (live) based on the guesses so far. Guesses will eliminate possible words.
     * @return the number of words that are still possibilities based on the original dictionary and the guesses so far.
     */
    public int numWordsCurrent() {
        return gameplay.size();
    }


    /**
     * Get the number of wrong guesses the user has left in this round (game) of Hangman.
     * @return the number of wrong guesses the user has left in this round (game) of Hangman.
     */
    public int getGuessesLeft() {
        return numofguesses+rightguesses-guesseslist.size();
    }


    /**
     * Return a String that contains the letters the user has guessed so far during this round.
     * The String is in alphabetical order. The String is in the form [let1, let2, let3, ... letN].
     * For example [a, c, e, s, t, z]
     * @return a String that contains the letters the user has guessed so far during this round.
     */
    public String getGuessesMade() {
    	Collections.sort(guesseslist);
        return guesseslist.toString();
    }


    /**
     * Check the status of a character.
     * @param guess The characater to check.
     * @return true if guess has been used or guessed this round of Hangman, false otherwise.
     */
    public boolean alreadyGuessed(char guess) {
    	boolean result = false;
    	if(guesseslist.contains(guess)){
    		result=true;
    	}
        return result;
    }


    /**
     * Get the current pattern. The pattern contains '-''s for unrevealed (or guessed)
     * characters and the actual character for "correctly guessed" characters.
     * @return the current pattern.
     */
    public String getPattern() {  	
    	//set up local var
    	ArrayList<String> result= new ArrayList<String>();
    	String finalresult ="";	
    	difficult(result);
    	//set up turn
    	if(result.size()==0){
    		StringBuilder SB = new StringBuilder(); 
    		for(int i=0; i<wordlength;i++){
    			SB.append('-');
    		}
    		finalresult = SB+"";
    	}
    	//situation 1
    	if(result.size()==1){
    		finalresult = result.get(0);
    	}
    	//situation 2
    	else{
    		boolean set= true;
    		for(String pattern3 : result){
        		if(!pattern3.contains(guesses)){
        			finalresult = pattern3;
        			set =false;
        		}
        	}
    		//situation 3
    		if(set){
    			for(int i=0;i<result.size();i++){
    	    		for(int j=i+1; j<result.size();j++){
    	    			if(result.get(i).compareTo(result.get(j))>0){
    	    				finalresult = result.get(j);
    	    			}
    	    			else
    	    				finalresult = result.get(i);
    	    		}
    	    	}
    		}
    	}
    	lastpattern = finalresult;
        return finalresult;
    }

    // The difficulty helper method used in getpattern
    public void difficult (ArrayList<String> patternlist){
    	int longest =0;
    	Set<String> key	= output.keySet();
    	int secondlong =0;
    	//for the situation when the only word left then we don`t have to consider the difficulty
    	if (!(key.size()==1 && output.get(key.iterator().next())==1)){
    		//find the longest list
    		for(String pattern : key){
    			if(output.get(pattern)>longest){
    				longest=output.get(pattern);
    			}
    		}
    		//find the second longest list
    		for(String pattern2 : key){
    			if(output.get(pattern2)>secondlong && output.get(pattern2)<longest){
    				secondlong=output.get(pattern2);
    			}
    		}
    		//3 kind of difficulty
    		if(difficulty==HangmanDifficulty.EASY){
    			//1 hardest 1 second
    			if(guesseslist.size()%2==1){
    				hardest(key, patternlist, longest);
    			}
    			else{
    				secondhardest(key, patternlist, secondlong);
    			}
    		}
    		else if(difficulty==HangmanDifficulty.MEDIUM){
    			//3 hardest 1 second
    			if(guesseslist.size()%4!=0){
    				hardest(key, patternlist, longest);
    			}
    			else
    				secondhardest(key, patternlist, secondlong);
    		}
    		else if(difficulty==HangmanDifficulty.HARD){
    			//always hardest
    			hardest(key, patternlist, longest);
    		}
    	}
    	else
    		patternlist.add(output.keySet().iterator().next());
    }
    
    //(helper method) redundancy to find second longest list
    public void secondhardest(Set<String> key, ArrayList<String> patternlist, int seondlong){
    	for(String pattern : key){
    		if(output.get(pattern)==seondlong){
    			patternlist.add(pattern);
    		}
    	}
    }
    
    //(helper method) redundancy to find longest list
    public void hardest(Set<String> key, ArrayList<String> patternlist, int longest){
    	for(String pattern : key){
    		if(output.get(pattern)==longest){
    			patternlist.add(pattern);
    		}
    	}
    }
    
    // pre: !alreadyGuessed(ch)
    // post: return a tree map with the resulting patterns and the number of
    // words in each of the new patterns.
    // the return value is for testing and debugging purposes
    /**
     * Update the game status (pattern, wrong guesses, word list), based on the give
     * guess.
     * @param guess pre: !alreadyGuessed(ch), the current guessed character
     * @return return a tree map with the resulting patterns and the number of
     * words in each of the new patterns.
     * The return value is for testing and debugging purposes.
     */
    public TreeMap<String, Integer> makeGuess(char guess) {
    	if(alreadyGuessed(guess)){
    		throw new IllegalStateException("The guess already made.");
    	}
    	//initialize the output map and makeguesswithlist map
    	output = new TreeMap<String, Integer>();
    	makeguesswithlist = new TreeMap<String, ArrayList<String>>();
    	//set add the guesseslist and record the this turn guess
    	guesseslist.add(guess);
    	guesses = guess+"";
    	//set the local var
    	String temp="";
    	for(String words : gameplay){
    		// build the string for each word
    		StringBuilder sb = new StringBuilder();
    		for(int j=0;j<words.length();j++){
    			if(words.charAt(j)==guess){
    				sb.append(guess);
    			}
    			else
    				sb.append(lastpattern.charAt(j));
    		}
    		//change sb to string
    		temp = sb+"";
    		//make the treemap, build up pattern families
    		if(!makeguesswithlist.containsKey(temp)){
    			makeguesswithlist.put(temp, new ArrayList<String>());
    		}
    		makeguesswithlist.get(temp).add(words);
    		output.put(temp, makeguesswithlist.get(temp).size());
    	}
    	// count the right guesses
    	if(getPattern().contains(guess+"")){
    		rightguesses++;
    	}
    	// change the active words list
    	gameplay=makeguesswithlist.get(getPattern());
        return output;
    }

    /**
     * Return the secret word this HangmanManager finally ended up picking for this round.
     * If there are multiple possible words left one is selected at random.
     * <br> pre: numWordsCurrent() > 0
     * @return return the secret word the manager picked.
     */
    public String getSecretWord() {
    	if(gameplay.size()==0){
    		throw new IllegalStateException("There is no final words.");
    	}
    	Random rand = new Random();
    	int index = rand.nextInt(gameplay.size());
        return gameplay.get(index);
    }
}