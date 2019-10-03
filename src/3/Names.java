/*  Student information for assignment:
*
 *  On my honor, <PENGDI XIA>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID:px353
 *  email address:xiapengdi@yahoo.com
 *  Grader name:51925 
 *  Unique section number:Gilbert Maldonado
 *  Number of slip days I am using:1
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
* A collection of NameRecords. 
* Stores NameRecord objects and provides methods to select
* NameRecords based on various criteria.
*/
//private class compare implements Comparator
class comp implements Comparator<NameRecord>{
	public int compare (NameRecord name1, NameRecord name2){
		return name1.getName().compareTo(name2.getName());
	}
}

public class Names {
	private ArrayList<NameRecord> records;
	//two valuables needed in NameSurfer
	public int numOfDecade;
	public int baseDecade;
	// constructor read the file
	public Names (Scanner fileScanner){
		records = new ArrayList<NameRecord>();
		String line="";
		//get the basic info first
		baseDecade = fileScanner.nextInt();
		numOfDecade = fileScanner.nextInt();
		while( fileScanner.hasNextLine() ){
		    line = fileScanner.nextLine();
		    //take out the incomplete data for the data base
		    String[] parsedData = line.split("\\s+");
		    if( parsedData.length == numOfDecade+1){
		    	NameRecord newName = new NameRecord(baseDecade, numOfDecade, line);
		    	records.add(newName);
		    }
		}
		Collections.sort(records,new comp());

	}
		//rewrite the compare method
	
   /**
    * Returns an ArrayList of NameRecord objects that contain a 
    * given substring, ignoring case.  The names must be in sorted order based 
    * on name.
    * @param partialName != null, partialName.length() > 0
    * @return an ArrayList of NameRecords whose names contains
    * partialName. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
   
   public ArrayList<NameRecord> getMatches(String partialName) {
	   ArrayList<NameRecord> result = new ArrayList<NameRecord>();
       for(int i =0; i < records.size();i++){
    	   if(records.get(i).getName().toLowerCase().contains(partialName.toLowerCase())){
    		   result.add(records.get(i));
    	   }
       }
       return result;  
   }

   /**
    * Returns an ArrayList of Strings of names that have been ranked in the
    * top 1000 or better for every decade. The Strings  must be in sorted 
    * order based on name. 
    * @return A list of the names that have been ranked in the top
    * 1000 or better in every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
   
   public ArrayList<String> rankedEveryDecade() {
	   ArrayList<String> result = new ArrayList<String>();
       for(int i =0; i < records.size();i++){
    	   if(records.get(i).rankedEveryDecade()){
    		   result.add(records.get(i).getName());
    	   }
       }
       return result;
   }

   /**
    * Returns an ArrayList of Strings of names that have been ranked in the 
    * top 1000 or better in exactly one decade. The Strings must be in sorted 
    * order based on name. 
    * @return A list of the names that have been ranked in the top
    * 1000 or better in exactly one decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
   public ArrayList<String> rankedOnlyOneDecade() {
	   ArrayList<String> result = new ArrayList<String>();
       for(int i =0; i < records.size();i++){
    	   if(records.get(i).rankedOnlyDecade()){
    		   result.add(records.get(i).getName());
    	   }
       }
       return result;
   }

   /**
    * Returns an ArrayList of Strings of names that have been getting more
    * popular every decade. The Strings  must be in sorted order based on name.
    * @return A list of the names that have been getting more popular in
    * every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
   public ArrayList<String> alwaysMorePopular() {
	   ArrayList<String> result = new ArrayList<String>();
       for(int i =0; i < records.size();i++){
    	   if(records.get(i).rankedBetter()){
    		   result.add(records.get(i).getName());
    	   }
       }
       return result;
   }

   /**
    * Returns an ArrayList of Strings of names that have been getting less
    * popular every decade. The Strings  must be in sorted order based on name.
    * @return A list of the names that have been getting less popular in
    * every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
   public ArrayList<String> alwaysLessPopular() {
	   ArrayList<String> result = new ArrayList<String>();
       for(int i =0; i < records.size();i++){
    	   if(records.get(i).rankedWorse()){
    		   result.add(records.get(i).getName());
    	   }
       }
       return result;
   }
   
   /**
    * Return the NameRecord in this Names object that matches the given String.
    * <br>
    * <tt>pre: name != null</tt>
    * @param name The name to search for.
    * @return The name record with the given name or null if no NameRecord in this Names
    * object contains the given name.
    */
   public NameRecord getName(String name) {
       if(name == null)
           throw new IllegalArgumentException("The parameter name cannot be null");
       int count=0;
		int index=-1;
		for(int i =0; i < records.size();i++){
	    	   if(name.equalsIgnoreCase(records.get(i).getName())){
	    		   count++;
	    		   index=i;
	    	   }
	       }
	       if (count==0){
	    	   return null;
	       }
	       else return records.get(index);
   }
   
   
   //My design is that you can search the any names by the decade and rank.
   public ArrayList<String> rankList(int decade, int rank){
	   ArrayList<String> result = new ArrayList<String>();
	   for (int i=0; i <records.size(); i++){
		   if(records.get(i).getRanks(decade)==rank){
			   result.add(records.get(i).getName());
		   }
	   }
	   return result;
   }
}
