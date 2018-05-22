package ass1;


//  CodeCamp.java - CS314 Assignment 1

/*  Student information for assignment:
 * 
 * replace <PENGDI XIA> with your name.
 *
 *  On my honor, <PENGDI XIA>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name:Pengdi Xia
 *  email address:xiapengdi@yahoo.com
 *  UTEID:px353
 *  Section 5 digit ID:51925 
 *  Grader name: Gilbert Maldonado
 *  Number of slip days used on this assignment:0
 */


import java.util.Random;

public class CodeCamp {
  
    /**
     * Determine the Hamming distance between two arrays of ints. 
     * Neither the parameter <tt>aList</tt> or
     * <tt>bList</tt> are altered as a result of this method.<br>
     * @param aList != null, aList.length == bList.length
     * @param bList != null, bList.length == aList.length
     * @return the Hamming Distance between the two arrays of ints.
     */    
    public static int hammingDistance(int[] aList, int[] bList){
        // check preconditions
    	int result = 0;
        if (aList == null || bList == null || aList.length != bList.length) 
            throw new IllegalArgumentException("Violation of precondition: " +
            		"hammingDistance. neither parameter may equal null, arrays" +
            		" must be equal length.");
        /*CS314 STUDENTS: INSERT YOUR CODE HERE*/
        for (int i=0; i < aList.length; i++){
        	if (aList[i]!=bList[i]){
        		result ++;
        	}
        }
        return result; //must change
    }
    
    
    /**
     * Determine if one array of ints is a permutation of another.
     * Neither the parameter <tt>listA</tt> or 
     * the parameter <tt>listB</tt> are altered as a result of this method.<br>
     * @param listA != null
     * @param listB != null
     * @return <tt>true</tt> if listB is a permutation of listA, 
     * <tt>false</tt> otherwise
     * 
    */
    public static boolean isPermutation(int[] listA, int[] listB) {
        // check preconditions
    	boolean result = true;
        if (listA == null || listB == null) 
            throw new IllegalArgumentException("Violation of precondition: " +
            		"isPermutation. neither parameter may equal null.");
        /*CS314 STUDENTS: INSERT YOUR CODE HERE*/
        if (listA.length != listB.length){
        	result = false;
        }
        else{
        	int[] position = new int[listA.length];
        	for (int i=0 ; i< listA.length; i ++){
        		for (int j=0 ; j< listA.length; j++){
        			if(listA[i]==listB[j] && position[j]== 0){
        				position[j]++;
        			}
        		}
        	}
        	for (int i =0 ; i< position.length; i++){
        		if (position[i]!=1){
        			result = false;
        		}
        	}
        }
        return result; //must change
    }
    
    
    /**
     * Determine the index of the String that 
     * has the largest number of vowels. 
     * Vowels are defined as <tt>'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 
     * 'U', and 'u'</tt>.
     * The parameter <tt>list</tt> is not altered as a result of this method.
     * <p>pre: <tt>list != null</tt>, <tt>list.length > 0</tt>, there is an least 1 non null element in list
     * <p>post: return the index of the non-null element in list that has the 
     * largest number of characters that are vowels.
     * If there is a tie return the index closest to zero. 
     * The empty String, "", has zero vowels.
     * It is possible for the maximum number of vowels to be 0.<br>
     * @param list the array to check
     * @return the index of the non-null element in list that has 
     * the largest number of vowels.
     */
    public static int mostVowels(String[] list) {
        // check preconditions
    	int result = -1;
    	char c=' ';
    	int max =0;
    	int temp;
        if (list == null || list.length == 0 || !atLeastOneNonNull(list))  
            throw new IllegalArgumentException("Violation of precondition: " +
            		"mostVowels. parameter may not equal null and must contain " +
            		"at least one none null value.");
        // CS314 STUDENTS: ADD YOUR CODE HERE
        //  You can use all methods from the String class and native arrays.
        for (int i=0; i<list.length; i++){
        	temp = 0;
        	if (list[i]!= null){
        		for(int j=0; j < list[i].length(); j++){
        			c= list[i].toLowerCase().charAt(j);
        			if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'){
        				temp++;
        			}
        		}
        	}
        	if (result==-1&&list[i]!=null){
        		result=i;
        	}
        	if (temp > max){
        		max = temp;
        		result=i;
        	}
        }
        return result; //must change
    }
    

    
    /**
     * Perform an experiment simulating the birthday problem.
     * Pick random birthdays for the given number of people. 
     * Return the number of pairs of people that share the
     * same birthday.<br>
     * @param numPeople The number of people in the experiment.
     * This value must be > 0
     * @param numDaysInYear The number of days in the year for this experiement.
     * This value must be > 0
     * @return The number of pairs of people that share a birthday 
     * after running the simulation.
     */
    public static int sharedBirthdays(int numPeople, int numDaysInYear) {
        // check preconditions
    	int result = 0;
        if (numPeople <= 0 || numDaysInYear <= 0)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"sharedBirthdays. both parameters must be greater than 0. " +
                    "numPeople: " + numPeople + 
                    ", numDaysInYear: " + numDaysInYear);     
        //CS314 STUDENTS: ADD YOUR CODE HERE
        Random r = new Random();
        
        int[] dayslist = new int[numPeople];
        int[] countshares = new int[numDaysInYear];
        for(int i=0;i< numPeople ;i++){
        	dayslist[i] = r.nextInt(numDaysInYear);
        	countshares[dayslist[i]]++;
        }
        for (int i =0; i<numDaysInYear; i++){
        	if (countshares[i]>1){
        		for(int j=countshares[i]-1; j>0; j--){
        			result +=j;
        		}
        	}
        }
        return result; //must change
    }
    
    
    /**
     * Determine if the chess board represented by board is a safe set up.
     * <p>pre: board != null, board.length > 0, board is a square matrix.
     * (In other words all rows in board have board.length columns.),
     * all elements of board == 'q' or '.'. 'q's represent queens, '.'s
     * represent open spaces.<br>
     * <p>post: return true if the configuration of board is safe,
     * that is no queen can attack any other queen on the board.
     * false otherwise.
     * the parameter <tt>board</tt> is not altered as a result of 
     * this method.<br>
     * @param board the chessboard
     * @return true if the configuration of board is safe,
     * that is no queen can attack any other queen on the board.
     * false otherwise.
    */
    public static boolean queensAreSafe(char[][] board) {
        char[] validChars = {'q', '.'};
        // check preconditions
        if (board == null || board.length == 0 || !isSquare(board) 
                || !onlyContains(board, validChars))
            throw new IllegalArgumentException("Violation of precondition: " +
            		"queensAreSafe. The board may not be null, must be square, " +
            		"and may only contain 'q's and '.'s"); 
      //CS314 STUDENTS: ADD YOUR CODE HERE
        int set = 0;
        int side = board.length;
        int set2 =side;
        int[] row = new int[side];
        int[] column = new int[side];
        int[] downslope	= new int [2*side - 1];
        int[] upslope = new int [2*side -1];
        for (int i=0; i < side; i ++){
        	for (int j=0; j<side; j++){
        		if(board[i][j]=='q'){
        			row[i]++;
        		}
        		if(board[j][i]=='q'){
        			column[i]++;
        		}
        	}
        }
        for (int i=0; i < side; i++){
        	for (int j=0; j<side-i; j++){
        		if(board[i+set][j]=='q'){
        			downslope[i]++;
        		}
        		if(board[j][i+set]=='q'&& i!=0){
        			downslope[side-1+i]++;
        		}
        		if(board[set2-i-1][j]=='q'){
        			upslope[i]++;
        		}
        		if(board[set2-1][i]=='q'&& i!=0){
        			upslope[side-1+i]++;
        		}
        			set++;
        			set2--;
        	}
        	set=0;
        	set2=side;
        }
        return check(row)&&check(column)&&check(downslope)&&check(upslope); //must change
    }
    public static boolean check(int[] object){
    	boolean result = true;
    	for(int i=0; i <object.length; i ++){
    		if (object[i]>1){
    			result = false;
    		}
    	}
    	return result;
    }
    
    /**
     * Given a 2D array of ints return the value of the
     * most valuable contiguous sub rectangle in the 2D array.
     * The sub rectangle must be at least 1 by 1. 
     * <p>pre: <tt>mat != null, mat.length > 0, mat[0].length > 0,
     * mat</tt> is a rectangular matrix.
     * <p>post: return the value of the most valuable contigous sub rectangle
     * in <tt>city</tt>.<br>
     * @param city The 2D array of ints representing the value of
     * each block in a portion of a city.
     * @return return the value of the most valuable contigous sub rectangle
     * in <tt>city</tt>.
     */
    public static int getValueOfMostValuablePlot(int[][] city) {
        // check preconditions
        if (city == null || city.length == 0 || city[0].length == 0 
                || !isRectangular(city) )
            throw new IllegalArgumentException("Violation of precondition: " +
            		"getValueOfMostValuablePlot. The parameter may not be null," +
            		" must value at least one row and at least" +
                    " one column, and must be rectangular."); 
        //CS314 STUDENTS: ADD YOUR CODE HERE
        int max = city[0][0];
        int total=0;
        for(int row=0; row< city.length; row++){
        	for(int column =0; column< city[0].length; column++){
        		for(int recHeight=0; recHeight<city.length-row; recHeight++){
        			for(int recWide=0; recWide<city[0].length-column; recWide++){
        				for(int i=row;i<row+recHeight+1;i++){
        		    		for(int j=column; j<column+recWide+1; j++){
        		    			total +=city[i][j];
        		    		}
        				}
        				if(total > max){
        					max = total;
        				}
        				total=0;
        			}
        			
        		}
        		
        	}
        }
        return max; //must change
    }
  
    
    // !!!!! ***** !!!!! ***** !!!!! ****** !!!!! ****** !!!!! ****** !!!!!!
    // CS314 STUDENTS: Put your birthday problem experiement code here:
    public static int experiments(){
    	int total=0;
    	for (int i =0;i<1000000; i++){
    		total += sharedBirthdays(182, 365);
    	}
    	return total/1000000; 
    }
    public static void experiments2(){
    	int count=0;
    	for(int i=2; i<101;i++){
    		for(int j=0; j<50000; j++){
    			if (sharedBirthdays(i, 365)>0){
    			    count++;
    			 }
    		}
    		double x=(double)count/50000;
    		System.out.print("Num people: "+i+", number of experiments with one or more shared birthday: "+count+", percentage: ");
    		System.out.printf( "%.2f\n",x*100);
    		count = 0;
    	}
    }
    
    // pre: list != null
    // post: return true if at least one element in list is null
    // otherwise return false.
    private static boolean atLeastOneNonNull(String[] list) {
        // check precondition
        if(list == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"atLeastOneNonNull. parameter may not equal null.");
        
        boolean foundNonNull = false;
        int i = 0;
        while( !foundNonNull && i < list.length ){
            foundNonNull = list[i] != null;
            i++;
        }
        return foundNonNull;
    }
    
    
    /* pre: mat != null
    post: return true if mat is a square matrix, false otherwise
     */
    private static boolean isSquare(char[][] mat) {
        if(mat == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"isSquare. paremeter may not be null.");

        final int numRows = mat.length;
        int row = 0;
        boolean isSquare = true;
        while( isSquare && row < numRows ) {
            isSquare = ( mat[row] != null) && (mat[row].length == numRows);
            row++;
        }
        return isSquare;
    }
    
    
    /* pre: mat != null, valid != null
    post: return true if all elements in mat are one of the characters in valid
     */
    private static boolean onlyContains(char[][] mat, char[] valid) {
        // check preconditions
        if(mat == null || valid == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"onlyContains. paremeters may not be null.");
        
        int row = 0;
        boolean correct = true;
        while( correct && row < mat.length) {
            int col = 0;
            while(correct && col < mat[row].length) {
                correct = contains(valid, mat[row][col]);
                col++;
            }
            row++;
        }
        return correct;
    }
    
    
    /*  pre: list != null
        post: return true if c is in list
     */
    private static boolean contains(char[] list, char tgtChar) {
        // check preconditions
        if(list == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"contains. paremeter may not be null.");

        boolean found = false;
        int index = 0;
        while( !found && index < list.length) {
            found = list[index] == tgtChar;
            index++;
        }
        return found;
    }
   
    
     // pre: mat != null, mat.length > 0
     // post: return true if mat is rectangular
    private static boolean isRectangular(int[][] mat) {
        // check preconditions
        if(mat == null || mat.length == 0)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"isRectangular. paremeter may not be null and must contain" +
            		" at least one row.");

        boolean correct = mat[0] != null;
        int row = 0;
        while(correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == mat[0].length);
            row++;
        }
        return correct;
    }
    
    // make constructor pirvate so no instances of CodeCamp can be created
    private CodeCamp() {
        
    }
}