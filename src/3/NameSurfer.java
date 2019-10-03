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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

public class NameSurfer {

    public static final String NAME_FILE = "names.txt";

    // constants for menu choices
    public static final int SEARCH = 1;
    public static final int ONE_NAME = 2;
    public static final int APPEAR_ONCE = 3;
    public static final int APPEAR_ALWAYS = 4;
    public static final int MORE_POPULAR=5;
    public static final int	LESS_POPULAR=6;
    public static final int MY_DESIGN=7;
    public static final int QUIT = 8;
    private static String input;

    // CS314 students, explain your menu option 7 here:
    /*At beginning I was wantting to make a list of top 10 popular name of certain decade.
     * I change my mind when I found out every time there will be a boy name and a girl name for same ranks.
     * So I thought why not make a search enginner with given a decade and rank.
     * Then I found out the Mary and Micheal keep top.
     * Slowly, girls Name change faster than boys name recent.
     * Emily and Jessica came to the top.
     * 
	*/
    // CS314 students, Explain your interesting search / trend here:
    /* My interesting search is that when I search my English name, Tony
     * i found that my name popularity is curve.
     * During the 1940-1950 was the most popular period.
     * So I was curious that is that happen to another name.
     * I searched few name which not very popular also not very un-popular
     * like Gary, Emma, Andew and so on.
     * The changing curve almost happen to every name I searched.
     * Some is curve up,some is down.
     * I think the reason for that because that society was changing so big that idea of the names also became different.
     * So that is My interesting search.
*/
    
    // CS314 students, add test code for NameRecord class here:
    public static void seriousTest(){
    	String Data1 = "Tony 228 157 159 171 138 93 50 82 123 195 284";
    	String Data2 = "Tom 123 151 140 122 97 138 121 369 618 858 0";
        int baseDecade = 1900;
        NameRecord record1 = new NameRecord(baseDecade, 11, Data1);
        NameRecord record2 = new NameRecord(baseDecade, 11, Data2);
        // test 1-2 get name
        String expected1 = "Tony";
        String expected2 = "Tom";
        String name1 = record1.getName();
        String name2 = record2.getName();
        if(expected1.equals(name1)){
        	System.out.println("Test 1 getname, Passed");
        }
        else
        	System.out.println("Test 1 getname, Failed");
        if(expected2.equals(name2)){
        	System.out.println("Test 2 getname, Passed");
        }
        else
        	System.out.println("Test 2 getname, Failed");
        
        // test 3-4 the base decade
        int expectedInt = 1900;
        if(expectedInt==record1.decade()){
        	System.out.println("Test 3 Base Decade, Passed");
        }
        else
        	System.out.println("Test 3 Base Decade, Failed");
        if(expectedInt==record2.decade()){
        	System.out.println("Test 4 Base Decade, Passed");
        }
        else
        	System.out.println("Test 4 Base Decade, Failed");
        
        // Test 5-6 getranks
        if(record1.getRanks(1950)==93){
        	System.out.println("Test 5 getranks, Passed");
        }
        else
        	System.out.println("Test 5 getranks, Failed");
        if(record2.getRanks(1960)==121){
        	System.out.println("Test 6 getranks, Passed");
        }
        else
        	System.out.println("Test 6 getranks, Failed");
        
        // Test 7-8 the best decade 
        if(record1.bestDecade()==1960){
        	System.out.println("Test 7 best decade, Passed");
        }
        else
        	System.out.println("Test 7 best decade, Failed");
        if(record2.bestDecade()==1940){
        	System.out.println("Test 8 best decade, Passed");
        }
        else
        	System.out.println("Test 8 best decade, Failed");
        
        // Test 9-10 the number of the decades this name has been ranked
        if(record1.numDecadeRanked()==11){
        	System.out.println("Test 9 number of decade ranked, Passed");
        }
        else
        	System.out.println("Test 9 number of decade ranked, Failed");
        if(record2.numDecadeRanked()==10){
        	System.out.println("Test 10 number of decade ranked, Passed");
        }
        else
        	System.out.println("Test 10 number of decade ranked, Failed");
        
        // Test 11-12 Ranked every decade
        if(record1.rankedEveryDecade()){
        	System.out.println("Test 11 reanked every decade, Passed");
        }
        else
        	System.out.println("Test 11 ranked every decade, Failed");
        if(!record2.rankedEveryDecade()){
        	System.out.println("Test 12 ranked every decade, Passed");
        }
        else
        	System.out.println("Test 12 ranked every decade, Failed");
        
        // Test 13-14 ranked only one decade
        if(!record1.rankedOnlyDecade()){
        	System.out.println("Test 13 ranked only one decade, Passed");
        }
        else
        	System.out.println("Test 13 ranked only one decade, Failed");
        if(!record2.rankedOnlyDecade()){
        	System.out.println("Test 14 ranked only one decade, Passed");
        }
        else
        	System.out.println("Test 14 ranked only one decade, Failed");
        
        //get 15-16 more popular
        String data3 = "Luis 245 203 154 118 111 108 94 68 60 46 45";
        NameRecord record3= new NameRecord(baseDecade, 11, data3);
        if(record3.rankedBetter()){
        	System.out.println("Test 15 ranked get better, Passed");
        }
        else
        	System.out.println("Test 15 ranked get better, Failed");
        if(!record2.rankedBetter()){
        	System.out.println("Test 16 ranked get better, Passed");
        }
        else
        	System.out.println("Test 16 ranked get better, Failed");
        
        //get 17-18 less popular
        String data4 = "Parker 23 54 109 354 654 0 0";
        NameRecord record4= new NameRecord(baseDecade, 7, data4);
        if(!record3.rankedWorse()){
        	System.out.println("Test 17 ranked get worse, Passed");
        }
        else
        	System.out.println("Test 17 ranked get worse, Failed");
        if(!record2.rankedWorse()){
        	System.out.println("Test 18 ranked get worse, Passed");
        }
        else
        	System.out.println("Test 18 ranked get worse, Failed");
        
        //Test 19-20 toString
        expected1 = "Tony\n1900: 228\n1910: 157\n1920: 159\n1930: 171\n1940: 138\n1950: 93\n1960: 50\n1970: 82\n1980: 123\n1990: 195\n2000: 284\n";
        if(expected1.equals(record1.toString())){
        	System.out.println("Test 19 toStirng, Passed");
        }
        else
        	System.out.println("Test 19 toString, Failed");
        expected2 = "Parker\n1900: 23\n1910: 54\n1920: 109\n1930: 354\n1940: 654\n1950: 0\n1960: 0\n";
        if(expected2.equals(record4.toString())){
            System.out.println("Test 20 toStirng, Passed");
        }
        else
        	System.out.println("Test 20 toString, Failed");
    }
    
    // A few simple tests for the Names and NameRecord class.
    /*public static void simpleTest() {
        // raw data for Jake. Alter as necessary for your NameRecord
        String jakeRawData = "Jake 262 312 323 479 484 630 751 453 225 117 97";
        int baseDecade = 1900;
        NameRecord jakeRecord = new NameRecord(baseDecade, 11, jakeRawData); // complete with your constructor
        String expected = "Jake\n1900: 262\n1910: 312\n1920: 323\n1930: 479\n1940: 484\n1950: 630\n1960: 751\n1970: 453\n1980: 225\n1990: 117\n2000: 97\n";
        String actual = jakeRecord.toString();
        System.out.println("expected string:\n" + expected);
        System.out.println("actual string:\n" + actual);
        if(expected.equals(actual))
            System.out.println("passed Jake toString test.");
        else
            System.out.println("FAILED Jake toString test.");  

        // Some getName Tests

        Names names = new Names(getFileScannerForNames(NAME_FILE));
        String[] testNames = {"Isabelle", "isabelle", "sel", "X1178", "ZZ", "via", "kelly"};
        boolean[] expectNull = {false, false, true, true, true, true, false};
        for (int i = 0; i < testNames.length; i++) {
            performGetNameTest(names, testNames[i], expectNull[i]);
        }
    }
    
    private static void performGetNameTest(Names names, String name, boolean expectNull) {
        System.out.println("Performing test for this name: " + name);
        if (expectNull) {
            System.out.println("Expected return value is null");
        } else {
            System.out.println("Expected return value is not null");
        }
        NameRecord result = names.getName(name);
        if ( (expectNull && result == null) || (!expectNull && result != null)) {
            System.out.println("PASSED TEST.");
        } else {
            System.out.println("Failed test");
        }
    }*/

    // main method. Driver for the whole program
    public static void main(String[] args) {
        seriousTest();
        // uncomment the next two lines if you want to let user obtain file via GUI
        /* System.out.println("Opening GUI to choose file with names data...");
               Scanner fileScanner = new Scanner(getFile());
         */
        // uncomment next line to hard code name file
        Scanner fileScanner = getFileScannerForNames(NAME_FILE);
        Names n = new Names(fileScanner);
        fileScanner.close();
        int choice;
        Scanner keyboard = new Scanner(System.in);
        do {
            showMenu();
            choice = getChoice(keyboard);
            if( choice == SEARCH)
                search(n, keyboard);
            else if( choice == ONE_NAME )
                oneName(n, keyboard);
            else if( choice == APPEAR_ONCE )
                appearOnce(n);
            else if( choice == APPEAR_ALWAYS )
                appearAlways(n);
            // CS314 students, complete this section
            else if( choice == MORE_POPULAR )
            	morePopular(n);
            else if( choice == LESS_POPULAR )
            	lessPopular(n);
            else if( choice == MY_DESIGN )
            	myDesign(n, keyboard);
            else
                System.out.println("\n\nGoodbye.");

        } while( choice != QUIT);

    }
    // the method I did not used it
    private static void setLookAndFell() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {
            System.out.println("Unable to set look at feel to local settings. " +
                            "Continuing with default Java look and feel.");
        }
    }

    //File Scanner
    private static Scanner getFileScannerForNames(String fileName) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fileName));
        }
        catch(FileNotFoundException e) {
            System.out.println("Problem reading the data file. Returning null for Scanner"
                            + "object. Problems likely to occur." + e);
        }
        return sc;
    }
    
    //The helper method to reduce redundancy
    private static void print(ArrayList<String> result){
    	if(result.size()!=0){
    		for(int i=0; i<result.size();i++){
        		System.out.println(result.get(i));
        		}
    	}
    	else
    		System.out.println("There is no result for this file.");
    }
    
    //The method to display all names that are more popular in every decade
    private static void morePopular(Names n){
    	if(n == null)
            throw new IllegalArgumentException("The parameter n cannot be null");
    	else
    		System.out.println(n.alwaysMorePopular().size()+" names are more popular in every decade.");
        	print(n.alwaysMorePopular());
    }
    
    //The method to display all names that are less popular in every decade
    private static void lessPopular(Names n){
    	if(n == null)
            throw new IllegalArgumentException("The parameter n cannot be null");
    	else
    		System.out.println(n.alwaysLessPopular().size()+" names are less popular in every decade.");
    		print(n.alwaysLessPopular());
    }
    
    //The method that my own design for fun
    private static void myDesign(Names n, Scanner keyboard){
    	int decade=0;
    	int rank=0;
    	if(n == null || keyboard==null)
            throw new IllegalArgumentException("The parameter n cannot be null");
        else
        	decade= getInt(keyboard, "Please enter a decade: ");
    		keyboard.nextLine();
    		while( (decade-n.baseDecade)%10!=0 || (decade-n.baseDecade)/10>n.numOfDecade-1 || (decade-n.baseDecade)/10<0){
    			System.out.println("\n" + decade +" is not a valid decade");
    			decade= getInt(keyboard, "Please enter a decade: ");
    		}
    		rank= getInt(keyboard, "Please enter a rank: ");
    		keyboard.nextLine();
    		while( rank>1000 || rank<=0){
    			System.out.println("\n" + rank +" is not a valid rank");
    			decade= getInt(keyboard, "Please enter a rank: ");
    		}
    		print(n.rankList(decade, rank));
    }

    // method that shows names that have appeared in ever decade
    // pre: n != null
    // post: print out names that have appeared in ever decade
    private static void appearAlways(Names n) {
        if(n == null)
            throw new IllegalArgumentException("The parameter n cannot be null");
        else
        	System.out.println(n.rankedEveryDecade().size()+" names appear in every decade. The names are:");
        	print(n.rankedEveryDecade());
    }

    // method that shows names that have appeared in only one decade
    // pre: n != null
    // post: print out names that have appeared in only one decade
    private static void appearOnce(Names n) {
        if(n == null){
            throw new IllegalArgumentException("The parameter n cannot be null");
        }
        else
        	System.out.println(n.rankedOnlyOneDecade().size()+" names appear in exactly one decade. The names are:");
        	print(n.rankedOnlyOneDecade());
    }

    // method that shows data for one name, or states that name has never been ranked
    // pre: n != null, keyboard != null and is connected to System.in
    // post: print out the data for n or a message that n has never been in the top 1000 for any decade
    private static void oneName(Names n, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is. 
        if(n == null || keyboard == null)
            throw new IllegalArgumentException("The parameters cannot be null");
        else{
        	System.out.print("Please enter a name: ");
        	input= keyboard.next();
        	NameRecord result = n.getName(input);
        	if(result!= null){
        		System.out.println(n.getName(input).toString());
        	}
        	else
        		System.out.println(input+" does not appear in any decade.");
        }
    }

    // method that shows all names that contain a substring from the user
    // and the decade they were most popular in
    // pre: n != null, keyboard != null and is connected to System.in
    // post: show the correct data      
    private static void search(Names n, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is. 
        if(n == null || keyboard == null){
            throw new IllegalArgumentException("The parameters cannot be null");
        }
        System.out.print("Please enter a partial name: ");
        input= keyboard.next();
        ArrayList<NameRecord> result = n.getMatches(input);
        System.out.println("\nThere are "+ result.size()+" matches for "+ input);
        if(result.size()!=0){
        	System.out.println("\nThe matches with their highest ranking decade are:");
        	for(int i=0; i<result.size();i++){
        		System.out.println(result.get(i).getName()+ " "+ result.get(i).bestDecade());
        	}
       	}
    }


    // get choice from the user
    // keyboard != null and is connected to System.in
    // return an int that is >= SEARCH and <= QUIT
    private static int getChoice(Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is. 
        if(keyboard == null)
            throw new IllegalArgumentException("The parameter keyboard cannot be null");

        int choice = getInt(keyboard, "Enter choice: ");
        keyboard.nextLine();
        while( choice < SEARCH || choice > QUIT){
            System.out.println("\n" + choice + " is not a valid choice");
            choice = getInt(keyboard, "Enter choice: ");
            keyboard.nextLine();
        }
        return choice;
    }

    // ensure an int is entered from the keyboard
    // pre: s != null and is connected to System.in
    private static int getInt(Scanner s, String prompt) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is. 
        if(s == null)
            throw new IllegalArgumentException("The parameter s cannot be null");

        System.out.print(prompt);
        while( !s.hasNextInt() ){
            s.next();
            System.out.println("That was not an int.");
            System.out.print(prompt);
        }
        return s.nextInt();
    }

    // show the user the menu
    private static void showMenu() {
        System.out.println("\nOptions:");
        System.out.println("Enter " + SEARCH + " to search for names.");
        System.out.println("Enter " + ONE_NAME + " to display data for one name.");
        System.out.println("Enter " + APPEAR_ONCE+ " to display all names that appear in only one decade.");
        System.out.println("Enter " + APPEAR_ALWAYS + " to display all names that appear in all decades.");
        // CS314 students fill in other options
        System.out.println("Enter " + MORE_POPULAR + " to display all names that are more popular in every decade.");
        System.out.println("Enter " + LESS_POPULAR + " to display all names that are less popular in every decade.");
        System.out.println("Enter " + MY_DESIGN + " to display top 1000 rank list for a certain decade.");
        System.out.println("Enter " + QUIT + " to quit.\n");
    }

    /** Method to choose a file using a traditional window.
     * @return the file chosen by the user. Returns null if no file picked.
     */ 
    public static File getFile() {
        // create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        chooser.setDialogTitle("Select File With Baby Names Data.");
        int retval = chooser.showOpenDialog(null);
        File f =null;
        chooser.grabFocus();
        if (retval == JFileChooser.APPROVE_OPTION)
            f = chooser.getSelectedFile();
        return f;
    }

}
