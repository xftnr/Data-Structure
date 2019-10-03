/*  Student information for assignment:
 *
 *  On <OUR> honor, <Pengdi Xia> and <Peijie Yang), this programming assignment is <OUR> own work
 *  and <WE> have not provided this code to any other student.
 *
 *  Number of slip days used:1
 *
 *  Student 1 (Student whose turnin account is being used)
 *  UTEID:px353	
 *  email address:xiapengdi@yahoo.com
 *  Grader name:Gilbert Maldonado
 *  Section number:51970
 *  
 *  Student 2 
 *  UTEID:py2554
 *  email address:loarek1996@hotmail.com
 *  Grader name:Gilbert Maldonado
 *  Section number:51970
 *  
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;


/*
CS 314 Students, put your results to the experiments and answers to
questions here:

**********SortedSet**********
File    Size     Number of Words        Distinct Words          Time
Small1  31KB        3927                    3927                0.1451893
Big2    5.35X       5.07X                   5.07X               16X
Big3    5.41X      13.56X                  1.38X               	1.58X
Big4    14.25X      12.8665X                  4.456X            11.7X
**********UnsortedSet**********
File    Size     Number of Words        Distinct Words          Time
Small1  31KB        3927                    3927                0.032676547
Big2    5.35X       5.07X                   5.07X               6.42X
Big3    5.41X      13.56X                  1.38X               	21.57X
Big4    14.25X      12.8665X                  4.456X            19.57X
**********HashSet**********
File    Size     Number of Words        Distinct Words          Time
Small1  31KB        3927                    3927                0.007203706
Big2   	5.35X       5.07X                   5.07X               4.4569X
Big3    12.3X      13.56X                  1.38X               	11.36X
Big4    14.25X      12.8665X                  4.456X            8.21X
**********TreeSet**********
File    Size     Number of Words        Distinct Words          Time
Small1  31KB        3927                    3927                0.010491123
Big2    5.35X       5.07X                   5.07X               6.345X
Big3    5.41X      13.56X                  1.38X               	4.62X
Big4    14.25X      12.8665X                  4.456X            7.0448X
 m
What do you think the order (Big O) of the two processTextethods are for each kind of Set?
Assume N = total number of words in a file and M = number of distinct words in the file.
M = the size of the set when finished.

    SortedSet: O(MlogN)
    UnsortedSet: O(N)
    HashSet: O(N)
    TreeSet: O(MlogN)
    
What are the orders (Big O) of your add methods?
What do you think the Big O of the HashSet and TreeSet add methods are?

    SortedSet add method: O(N)
    UnsortedSet add method: O(N)
    HashSet add method: O(1)
    TreeSet add method: O(logN)
    
What are the differences between HashSet and TreeSet when printing out the contents of the Set?
    HashSet prints out the contents without sorting, but treeSet print out content with ascending sort 

CS314 Students, why is it unwise to implement all three of the
intersection, union, and difference methods in the AbstractSet class:
first because they all have different Big O, meaning we need to write different version for both sortset and
unsortedset.However, we can still use difference method to calculation union of both set, so, union 
method can be written in abstract class; and both sets class dont need to override it. 

*/


public class SetTester {

    public static void main(String[] args){

    	UnsortedSet<String>s10 = new UnsortedSet<String>();
        s10.add("B");
        // unsortedset tester 
        
        //test 1    add  method  
        if( s10.contains("B") )
            System.out.println("Passed test 1: add methods unSortedSet");
        else
            System.out.println("Failed test 1: add methods unSortedSet");
        s10.add("C");
        s10.add("X");
        s10.add("Y");
        s10.add("Z");
        s10.add("W");
        
        // test 2 contains 
        if( s10.contains("W") )
            System.out.println("Passed test 2: contains methods unSortedSet");
        else
            System.out.println("Failed test 2: contains methods unSortedSet");
        
        //test 3 remove method 
        s10.remove("A");
        if( !s10.contains("A") )
            System.out.println("Passed test 3: remove method UnsortedSet");
        else
            System.out.println("Failed test 3: remove method UnsortedSet");

        //test 4 size method 
        if( s10.size() == 6 )
            System.out.println("Passed test 4: size method UnsortedSet");
        else
            System.out.println("Failed test 4: size method UnsortedSet");

        UnsortedSet<String> s2 = new UnsortedSet<String>();
        s2.add("C");
        s2.add("B");

        //test 5 containsAll method 
        
        if( s10.containsAll(s2) == true )
            System.out.println("Passed test 5: containsAll method UnsortedSet");
        else
            System.out.println("Failed test 5: containsAll method UnsortedSet");

        //test 6 difference method 
        ISet<String> s3 = s10.difference(s2);
        UnsortedSet<String> expected = new UnsortedSet<String>();
        expected.add("X");
        expected.add("Y");
        expected.add("Z");
        expected.add("W");
        if( s3.equals(expected))
            System.out.println("Passed test 6: difference methods UnsortedSet");
        else
            System.out.println("Failed test 6: difference equals methods UnsortedSet");

        //test 7 union method 
        s3 = s2.union(s10);
        expected.add("B");
        expected.add("C");
        expected.add("X");
        expected.add("Y");
        expected.add("Z");
        expected.add("W");

        if( s3.equals(expected))
            System.out.println("Passed test 7: union methods UnsortedSet");
        else
            System.out.println("Failed test 7: union methods UnsortedSet");

        //test 8 remove method
        s3 = s2.intersection(s10);
        expected.remove("X");
        expected.remove("Y");
        expected.remove("Z");
        expected.remove("W");
        ISet<String> expected2 = new UnsortedSet<String>();
        expected2.add("B");
        expected2.add("C");
       
        if( expected.equals(expected))
            System.out.println("Passed test 8: remove methods UnsortedSet");
        else
            System.out.println("Failed test 8: remove methods UnsortedSet");
        
        // test 9 intersection 
        if( s3.equals(expected))
            System.out.println("Passed test 9: intersection methods UnsortedSet");
        else
            System.out.println("Failed test 9: intersection methods UnsortedSet");
        
        //test 10 addAll method 
        s2.addAll(s10);
        expected = new UnsortedSet<String>();
        expected.add("B");
        expected.add("C");
        expected.add("X");
        expected.add("Y");
        expected.add("Z");
        expected.add("W");
        if( s2.equals(expected))
            System.out.println("Passed test 10: addAll methods UnsortedSet");
        else
            System.out.println("Failed test 10: addAll methods UnsortedSet");

        //test 11 clear method 
        expected.clear();
        expected2 = new UnsortedSet<String>();
        if( expected.equals(expected2))
            System.out.println("Passed test 11: clear methods UnsortedSet");
        else
            System.out.println("Failed test 11: clear methods UnsortedSet");
        
        
        // Sortedset tester
        SortedSet<String> s11 = new SortedSet<String>();
        s11.add("B");
         
        
        //test 1    add  method  
        if( s11.contains("B") )
            System.out.println("Passed test 1: add methods SortedSet");
        else
            System.out.println("Failed test 1: add methods SortedSet");
        s11.add("C");
        s11.add("X");
        s11.add("Y");
        s11.add("Z");
        s11.add("W");
        
        // test 2 contains 
        if( s11.contains("W") )
            System.out.println("Passed test 2: contains methods SortedSet");
        else
            System.out.println("Failed test 2: contains methods SortedSet");
        
        //test 3 remove method 
        s11.remove("A");
        if( !s11.contains("A") )
            System.out.println("Passed test 3: remove method SortedSet");
        else
            System.out.println("Failed test 3: remove method SortedSet");

        //test 4 size method 
        if( s11.size() == 6 )
            System.out.println("Passed test 4: size method SortedSet");
        else
            System.out.println("Failed test 4: size method SortedSet");
        SortedSet<String> s20 = new SortedSet<String>();
        s20.add("C");
        s20.add("B");

        //test 5 containsAll method 
        
        if( s11.containsAll(s20) == true )
            System.out.println("Passed test 5: containsAll method SortedSet");
        else
            System.out.println("Failed test 5: containsAll method SortedSet");

        //test 6 difference method 
        s3 = s11.difference(s20);
        SortedSet<String> expected6 = new SortedSet<String>();
        expected6.add("X");
        expected6.add("Y");
        expected6.add("Z");
        expected6.add("W");
        if( s3.equals(expected6))
            System.out.println("Passed test 6: difference and equals methods SortedSet");
        else
            System.out.println("Failed test 6: difference and equals methods SortedSet");

        //test 7 union method 
        s3 = s20.union(s11);
        expected6.add("B");
        expected6.add("C");
        expected6.add("X");
        expected6.add("Y");
        expected6.add("Z");
        expected6.add("W");

        if( s3.equals(expected6))
            System.out.println("Passed test 7: union and equals methods SortedSet");
        else
            System.out.println("Failed test 7: union and equals methods SortedSet");

        //test 8 remove method
        s3 = s20.intersection(s11);
        expected6.remove("X");
        expected6.remove("Y");
        expected6.remove("Z");
        expected6.remove("W");
        expected2 = new SortedSet<String>();
        expected2.add("B");
        expected2.add("C");
       
        if( expected6.equals(expected6))
            System.out.println("Passed test 8: remove methods SortedSet");
        else
            System.out.println("Failed test 8: remove methods SortedSet");
        
        // test 9 intersection 
        if( s3.equals(expected6))
            System.out.println("Passed test 9: intersection and equals methods SortedSet");
        else
            System.out.println("Failed test 9: intersection and equals methods SortedSet");
        
        //test 10 addAll method 
        s20.addAll(s11);
        expected6 = new SortedSet<String>();
        expected6.add("B");
        expected6.add("C");
        expected6.add("X");
        expected6.add("Y");
        expected6.add("Z");
        expected6.add("W");
        if( s20.equals(expected6))
            System.out.println("Passed test 10: intersection and equals methods SortedSet");
        else
            System.out.println("Failed test 10: intersection and equals methods SortedSet");

        //test 11 clear method 
        expected6.clear();
        expected2 = new SortedSet<String>();
        if( expected6.equals(expected2))
            System.out.println("Passed test 11: intersection and equals methods SortedSet");
        else
            System.out.println("Failed test 11: intersection and equals methods SortedSet");
        
        //test 12 mergeSort
        UnsortedSet<String>expected5 = new UnsortedSet<String>();
        expected5.add("B");
        expected5.add("C");
        expected5.add("X");
        expected5.add("Y");
        expected5.add("Z");
        expected5.add("W");
        expected2 = new SortedSet<String>(expected5);
        SortedSet<String> expected3 = new SortedSet<String>();
        expected3.add("B");
        expected3.add("C");
        expected3.add("W");
        expected3.add("X");
        expected3.add("Y");
        expected3.add("Z");

        
        if( expected3.equals(expected2))
            System.out.println("Passed test 12: mergesort methods SortedSet");
        else
            System.out.println("Failed test 12: mergesort and equals methods SortedSet");
        
        //test13
        
        if( expected3.max() == "Z")
            System.out.println("Passed test 13: max methods SortedSet");
        else
            System.out.println("Failed test 13: max and equals methods SortedSet");
        //test 14
        if( expected3.min() == "B")
            System.out.println("Passed test 14: min methods SortedSet");
        else
            System.out.println("Failed test 14: min and equals methods SortedSet");
        // CS314 Students. Uncomment this section when ready to 
         //run your experiments
               try {                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch(Exception e) {
                    System.out.println("Unable to change look and feel");
                }
        		Scanner sc = new Scanner(System.in);
        		String response = "";
        		do {
        			largeTest();
        			System.out.print("Another file? Enter y to do another file: ");
        			response = sc.next();
        			} while( response != null && response.length() > 0 
                      && response.substring(0,1).equalsIgnoreCase("y") );

    }

    /*
     * Method asks user for file and compares run times to add words from file to
     * various sets, including CS314 UnsortedSet and SortedSet and Java's
     * TreeSet and HashSet.
     */
    private static void largeTest(){
        System.out.println();
        System.out.println("Opening Window to select file. You may have to minimize other windows.");
        String text = convertFileToString();
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets( new HashSet<String>(), text);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets( new TreeSet<String>(), text);
    }

    
    /*
     * pre: set != null, text != null
     * Method to add all words in text to the given set. Words are delimited by
     * white space.
     * This version for CS314 sets.
     */
    private static void processTextCS314Sets(ISet<String> set, String text){
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while( sc.hasNext() ){
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size());
    }


    /*
     * pre: set != null, text != null
     * Method to add all words in text to the given set. Words are delimited by
     * white space.
     * This version for Java Sets.
     */
    private static void processTextJavaSets(Set<String> set, String text){
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while( sc.hasNext() ){
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size());
    }

    
    /*
     * Show results of add words to given set.
     */
    private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, 
            int totalWords, int setSize) {

        System.out.println("Time to add the elements in the text to this set: " + s.toString() );
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);


        System.out.print("Enter y to see the contents of this set: ");
        Scanner sc = new Scanner(System.in);
        String response = sc.next();

        if( response != null && response.length() > 0 && response.substring(0,1).equalsIgnoreCase("y") ){
            for(Object o : set)
                System.out.println(o);
        }	
        System.out.println();
    }


    /*
     * Ask user to pick a file via a file choosing window and
     * convert that file to a String. Since we are evalutatin the file
     * with many sets convert to string once instead of reading through
     * file multiple times.
     */
    private static String convertFileToString() {
        //create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        //read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            try {
                Scanner s = new Scanner( new FileReader( source ) );

                while( s.hasNextLine() ) {
                    text.append( s.nextLine() );
                    text.append(" ");
                }

                s.close();
            }
            catch(IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            }
        }

        return text.toString();
    }
}


 