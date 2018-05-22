/*  Student information for assignment:
*
*  On <MY|OUR> honor, <NAME1> and <NAME2), this programming assignment is <MY|OUR> own work
*  and <I|WE> have not provided this code to any other student.
*
*  Number of slip days used:
*
*  Student 1 (Student whose Canvas account is being used)
*  UTEID:
*  email address:
*  Grader name:
*  Section number:
*
*  Student 2
*  UTEID:
*  email address:
*
*/


//imports

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Various recursive methods to be implemented.
 * Given shell file for CS314 assignment.
 */
public class Recursive {

   /**
    * Problem 1: convert a base 10 int to binary recursively.
    *   <br>pre: n != Integer.MIN_VALUE<br>
    *   <br>post: Returns a String that represents N in binary.
    *   All chars in returned String are '1's or '0's. 
    *   Most significant digit is at position 0
    *   @param n the base 10 int to convert to base 2
    *   @return a String that is a binary representation of the parameter n
    */
   public static String getBinary(int n) {
       if (n == Integer.MIN_VALUE) {
           throw new IllegalArgumentException("Failed precondition: getBinary. "
                           + "n cannot equal Integer.MIN_VALUE. n: " + n);
       }
       if(n/2!=0){
    	   return ""+getBinary(n/2)+Math.abs(n%2);
       }else
    	   return ""+n%2; //dummy return, replace as necessary
   }


   /**
    * Problem 2: reverse a String recursively.<br>
    *   pre: stringToRev != null<br>
    *   post: returns a String that is the reverse of stringToRev
    *   @param stringToRev the String to reverse.
    *   @return a String with the characters in stringToRev in reverse order.
    */
   public static String revString(String stringToRev) {
       if (stringToRev == null) {
           throw new IllegalArgumentException("Failed precondition: revString. parameter may not be null.");
       }
       if(stringToRev.length()!=1){
    	   return revString(stringToRev.substring(1, stringToRev.length()))+stringToRev.charAt(0);
       }
       return ""+stringToRev.charAt(0); //dummy return, replace as necessary
   }


   /**
    * Problem 3: Returns the number of elements in data
    * that are followed directly by value that is
    * double that element.
    * pre: data != null
    * post: return the number of elements in data that are followed immediately by double the value
    * @param data The array to search.
    * @return The number of elements in data that are followed immediately by a value that
    * is double the element.
    */
   public static int nextIsDouble(int[] data) {
       if (data == null) {
           throw new IllegalArgumentException("Failed precondition: revString. parameter may not be null.");
       }
       if(data.length>=2){
    	   int[] temp= new int[data.length-1];
    	   for (int i=0;i<temp.length;i++){
    		   temp[i]=data[i+1];
    	   }
    	   if(data[0]*2==data[1]){
    		   return 1+nextIsDouble(temp);
    	   }else
    		   return 0+nextIsDouble(temp);
    	   
       }
       return 0; // must change. Need to write a helper method
   }


   // CS314 students, add your nextIsDouble helper method here


   /**  Problem 4: Find all combinations of mnemonics for the given number.<br>
    *   pre: number != null, number.length() > 0, all characters in number are digits<br>
    *   post: see tips section of assignment handout
    *   @param number The number to find mnemonics for
    *   @return An ArrayList<String> with all possible mnemonics for the given number
    */
   public static ArrayList<String> listMnemonics(String number) {
       if (number == null ||  number.length() == 0 || !allDigits(number)) {
           throw new IllegalArgumentException("Failed precondition: listMnemonics");
       }
       
       ArrayList<String> result = new ArrayList<>();
       recursiveMnemonics(result, "", number);
       return result;
   }


   /*
    * Helper method for listMnemonics
    * mnemonics stores completed mnemonics
    * mneominicSoFar is a partial (possibly complete) mnemonic
    * digitsLeft are the digits that have not been used from the original number
    */
   private static void recursiveMnemonics(ArrayList<String> mnemonics,
           String mnemonicSoFar, String digitsLeft) {
       // CS314 students, complete this method
	   String temp = digitLetters(digitsLeft.charAt(0));
	   if(digitsLeft.length()!=1){
		   for(int i=0;i<temp.length();i++){
			   recursiveMnemonics(mnemonics, mnemonicSoFar+temp.charAt(i), digitsLeft.substring(1));
		   }
	   }
	   else
		   for(int i=0;i<temp.length();i++){
			   mnemonics.add(mnemonicSoFar+temp.charAt(i));
		   }
   }

   
   // used by method digitLetters
   private static final String[] letters = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};


   /* helper method for recursiveMnemonics
    * pre: ch is a digit '0' through '9'
    * post: return the characters associated with this digit on a phone keypad
    */
   private static String digitLetters(char ch) {
       if (ch < '0' || ch > '9') {
           throw new IllegalArgumentException("parameter ch must be a digit, 0 to 9. Given value = " + ch);
       }
       int index = ch - '0';
       return letters[index];
   }


   /* helper method for listMnemonics
    * pre: s != null
    * post: return true if every character in s is a digit ('0' through '9')
    * */
   private static boolean allDigits(String s) {
       if (s == null) {
           throw new IllegalArgumentException("Failed precondition: allDigits. String s cannot be null.");
       }
       boolean allDigits = true;
       int i = 0;
       while (i < s.length() && allDigits) {
           allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
           i++;
       }
       return allDigits;
   }


   /**
    * Problem 5: Draw a Sierpinski Carpet.
    * @param size the size in pixels of the window
    * @param limit the smallest size of a square in the carpet.
    */
   public static void drawCarpet(int size, int limit) {
       DrawingPanel p = new DrawingPanel(size, size);
       Graphics g = p.getGraphics();
       g.setColor(Color.BLACK);
       g.fillRect(0,0,size,size);
       g.setColor(Color.WHITE);
       drawSquares(g, size, limit, 0, 0);
   }


   /* Helper method for drawCarpet
    * Draw the individual squares of the carpet.
    * @param g The Graphics object to use to fill rectangles
    * @param size the size of the current square
    * @param limit the smallest allowable size of squares
    * @param x the x coordinate of the upper left corner of the current square
    * @param y the y coordinate of the upper left corner of the current square
    */
   private static void drawSquares(Graphics g, int size, int limit, double x, double y) {

   }


   /**
    * Problem 6: Determine if water at a given point
    * on a map can flow off the map.
    * <br>pre: map != null, map.length > 0,
    * map is a rectangular matrix, 0 <= row < map.length, 0 <= col < map[0].length
    * <br>post: return true if a drop of water starting at the location
    * specified by row, column can reach the edge of the map, false otherwise.
    * @param map The elevations of a section of a map.
    * @param row The starting row of a drop of water.
    * @param col The starting column of a drop of water.
    * @return return true if a drop of water starting at the location
    * specified by row, column can reach the edge of the map, false otherwise.
    */
   public static boolean canFlowOffMap(int[][] map, int row, int col) {
       if( map == null || map.length == 0 || !isRectangular(map) || !inbounds(row, col, map)) {
           throw new IllegalArgumentException("Failed precondition: canFlowOffMap");
       }
       return true;
   }

   /* helper method for canFlowOfMap - CS314 students you should not have to
    * call this method,
    * pre: mat != null,
    */
   private static boolean inbounds(int r, int c, int[][] mat) {
       assert mat != null : "Failed precondition: inbounds";
       return r >= 0 && r < mat.length && mat[r] != null && c >= 0 && c < mat[r].length;
   }

   /*
    * helper method for canFlowOfMap - CS314 stdents you should not have to
    * call this method,
    * pre: mat != null, mat.length > 0
    * post: return true if mat is rectangular
    */
   private static boolean isRectangular(int[][] mat) {
       assert (mat != null) && (mat.length > 0) : "Violation of precondition: isRectangular";

       boolean correct = true;
       final int numCols = mat[0].length;
       int row = 0;
       while( correct && row < mat.length) {
           correct = (mat[row] != null) && (mat[row].length == numCols);
           row++;
       }
       return correct;
   }


   /**
    * Problem 7: Find the minimum difference possible between teams
    * based on ability scores. The number of teams may be greater than 2.
    * The goal is to minimize the difference between the team with the
    * maximum total ability and the team with the minimum total ability.
    * <br> pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
    * <br> post: return the minimum possible difference between the team
    * with the maximum total ability and the team with the minimum total
    * ability.
    * @param numTeams the number of teams to form.
    * Every team must have at least one member
    * @param abilities the ability scores of the people to distribute
    * @return return the minimum possible difference between the team
    * with the maximum total ability and the team with the minimum total
    * ability. The return value will be greater than or equal to 0.
    */
   public static int minDifference(int numTeams, int[] abilities) { 
	   assert (numTeams < 2|| abilities == null|| abilities.length < numTeams): "Violation of precondition:";
	   //create a arraylist that collect all difference generated by each case 
	   ArrayList<Integer> diffCollection = new ArrayList<>();
	   //create a array with same length as int[]abilities, 
	   int [] team = new int[abilities.length];
	   int currentTeam = 0; 
	   currentDiff(numTeams, team, abilities, currentTeam, diffCollection);
	   Collections.sort(diffCollection);
	   return diffCollection.get(0);
   
   }
  
   private static void currentDiff (int numTeams, int[]team, int[] abilities, int pos,ArrayList<Integer> diffCollection){
	   
	   if(pos == abilities.length){
		   int diff=0;
		   int [] groupElement = new int[numTeams];
		   for(int i = 0; i < abilities.length; i++){
			   for(int j = 1; j <=numTeams; j ++) {
				   if(team[i] == j){
					   groupElement[j-1] += abilities[i];
				   }
			   }
		   }
		   Arrays.sort(groupElement);
		   diff = groupElement[numTeams-1] - groupElement[0];
		   diffCollection.add(diff);
	   }
	   else{
		   for(int i = 1; i <= numTeams; i++){
			   team[pos] = i;
			   currentDiff(numTeams, team, abilities,pos+1,diffCollection);
		   }
	   }
	   
   }

   
   
   
   /**
    * Problem 8: Maze solver. Return true if it is possible to escape the maze after
    * collecting all the coins. More details in the assignment handout.
    * <br>pre: board != null
    * <br>pre: board is a rectangular matrix
    * <br>pre: board only contains characters 'G', 'E', 'S', 'R', and '*'
    * <br>pre: there is a single 'S' character present
    * <br>post: Return true if it is possible to escape the maze after
    * collecting all the coins.
    * @param maze The maze to escape
    * @return Return true if it is possible to escape the maze after collecting all
    * the coins, false otherwise. 
    */
   public static boolean canEscapeMaze(char[][] maze) {
	   int [] start = startPoint(maze);
	   int startRow = start[0];
	   int startCol = start[1];
	   int [][] numRep = numberRep(maze);
	   return helpMethod(startRow, startCol, numRep);
	  
   }
   private static boolean helpMethod( int row, int col,int[][] num){  
	  // else 
		   
		   if( col < num[0].length-1 && (num[row][col+1] == 0 ||num[row][col+1] == 3 ||(noGoldLeft(num) && num[row][col+1] == 4))){
			   if(num[row][col+1] == 4) {
				   return true;
			   }
			   if(num[row][col] == 3){
    			   num[row][col] = 2;
    			   num = reset(num);
    		   }
    		   else if(num[row][col] == 0){
    			   num[row][col] = 1;
    		   }
			   System.out.println(Arrays.deepToString(num));
    		   if(helpMethod(row, col+1,num)){
    			   return true;
    		   }
    		   else if(noGoldLeft(num) ){
    			   if(num[row][col] == 2){
        			   num[row][col] = 3;
        		   }
    		   }
    	   }
		   if(col > 0  &&( num[row][col-1] == 0||num[row][col-1] == 3||(noGoldLeft(num) && num[row][col-1] == 4))){
			   if(num[row][col-1] == 4) {
				   return true;
			   }
			   if(num[row][col] == 3){
    			   num[row][col] = 2;
    			   num = reset(num);
    		   }
			   else if(num[row][col] == 0) {
				   num[row][col] =1;
			   }
			   System.out.println(Arrays.deepToString(num));
    		   if(helpMethod(row, col-1,num)){
    			   return true;
    		   }
    		   else if(noGoldLeft(num)){
    			   if(num[row][col] == 2){
        			   num[row][col] = 3;
    			   }
    		   }
    	   }
	  
		   if(row < num.length-1 &&( num[row+1][col] == 0||num[row+1][col] == 3||(noGoldLeft(num) && num[row+1][col] == 4))){
			   if(num[row+1][col] == 4) {
				   return true;
			   }
			   if(num[row][col] == 3){
				   num[row][col] = 2;
				   num = reset(num);
			   }
			   else if(num[row][col] == 0){
				   num[row][col] =1;
			   }
			   System.out.println(Arrays.deepToString(num));
			   if(helpMethod(row+1, col,num)){
				   return true;
			   }
			   else if(noGoldLeft(num)){
				   if(num[row][col] == 2){
					   num[row][col] = 3;
				   
				   }
			   }
    	   }
		   if(row > 0 &&( num[row-1][col] == 0||num[row-1][col] == 3||(noGoldLeft(num) && num[row-1][col] == 4))){
			   if(num[row-1][col] == 4) {
				   return true;
			   }
			   if(num[row][col] == 3){
				   num[row][col] = 2;
				   num = reset(num);
			   }
			   else if(num[row][col] == 0) {
				   num[row][col] =1;
			   }
			   System.out.println(Arrays.deepToString(num));
			   if(helpMethod(row-1, col,num)){
				   return true;
			   }
			   else if(noGoldLeft(num)){
				   if(num[row][col] == 2){
					   num[row][col] = 3;
				   }
			   }
		   } 
		   
    	  return false;
       }
	   
   
   private static int [] startPoint(char[][]maze){
	   int [] result = new int[2];
	   for(int i = 0; i < maze.length; i++){
		   for(int j = 0; j < maze[0].length; j++){
			   if(maze[i][j] == 'S'){
				   result[0] = i;
				   result[1] = j;
			   }
		   }
	   }
	   return result;
   }
   private static int [][] numberRep(char [][]maze){
	   int [][] result = new int [maze.length][maze[0].length];
	   for(int i = 0; i < maze.length; i++){
		   for(int j = 0; j < maze[0].length; j++){
			   if(maze[i][j] == 'G'){
				   result[i][j] = 3;
			   }
			   if(maze[i][j] == 'R' || maze[i][j] == 'S'){
				   result[i][j] = 0;
			   }
			   if(maze[i][j] == '*'){
				   result[i][j] = 2;
			   }
			   if(maze[i][j] == 'E'){
				   result[i][j] = 4;
			   }
		   }
	   }
	   return result;
   }
   private final static int[][] reset(int [][] numberRep){
   
	   for(int i = 0; i < numberRep.length; i++){
		   for(int j = 0; j < numberRep[0].length; j++){
			   if(numberRep[i][j] == 1){
				   numberRep[i][j] = 0;
			   }
		   }
	   }
	   return numberRep;
		  
	   
   }
   private static boolean noGoldLeft(int [][] numberRep){
	   for(int i = 0; i < numberRep.length; i++){
		   for(int j = 0; j < numberRep[0].length; j++){
			   if(numberRep[i][j] == 3){
				   return false;
			   }
		   }
	   }
	   return true;
   }

}