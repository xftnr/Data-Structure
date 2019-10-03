import java.util.ArrayList;

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
 *  
*/

public class NameRecord {
	private String name;
	private ArrayList<Integer> rank;
	private int baseDecade;
	private int numOfDecade;
	
	//constructor
	public NameRecord(int base,int numOfD, String line){
		rank=new ArrayList<Integer>();
		baseDecade=base;
		numOfDecade=numOfD;
		//split the string into a array
	    String[] parsedData = line.split("\\s+");
	    name= parsedData[0];
	    for(int i=1; i <parsedData.length; i++){
	    	Integer ranks = new Integer(parsedData[i]);
			//replace the 0 to 1001 for unranked decade
	    	if(ranks==0){
	    		ranks=1001;
	    	}
	    	rank.add(ranks);
	    	}
		}		

	// get name
	public String getName(){
		return name;
	}
	// the decade
	public int decade(){
		return baseDecade;
	}
	// the rank
	// careful the rank change back to 0
	public int getRanks(int decade){
		int num = 0;
		if (rank.get((decade-baseDecade)/10)==1001){
			num=0;
		}
		else
			num= rank.get((decade-baseDecade)/10);
		return num;
	}
	
	// the best decade index
	public int bestDecade(){
		int bestRank=1001;
		int index=0;
		for (int i=0;i<numOfDecade;i++){
			if(rank.get(i)<=bestRank){
				bestRank=rank.get(i);
				index=i;
			}
		}
		return index*10+baseDecade;
	}
	
	// the number of the decades this name has been ranked in the top 1000
	public int numDecadeRanked(){
		int count=0;
		for (int i=0;i<rank.size();i++){
			if(rank.get(i)!=1001){
				count++;
			}
		}
		return count;
	}
	
	// Does the name has been ranked every decade
	public boolean rankedEveryDecade(){
		return numDecadeRanked()==numOfDecade;
	}
	
	// Does the name has been ranked only one decade
	public boolean rankedOnlyDecade(){
		return numDecadeRanked()==1;
	}
	
	// Does the name get popular every decade
	public boolean rankedBetter(){
		boolean result=true;
		for (int i=rank.size()-1;i>0;i--){
			if(!(rank.get(i)<rank.get(i-1))){
				result=false;
			}
		}
		return result;
	}
	
	// Does the name get less popular every decade
	public boolean rankedWorse(){
		boolean result=true;
		for (int i=0;i<rank.size()-1;i++){
			if(!(rank.get(i)<rank.get(i+1))){
				result= false;
			}
		}
		return result;
	}
	
	//toString
	public String toString(){
		String result="";
		result += name+"\n";
		for (int i=0;i<numOfDecade;i++){
			result += baseDecade+i*10+": "+getRanks(i*10 + baseDecade)+"\n";
		}
		return result;
	}
}
