import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//  CodeCamp.java - CS314 Assignment 1 - Tester class

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


/* CS314 Students: place results of shared birthdays experiments in this comment.
	1.I think there will be 50 pairs of people shared birthdays.
	2.There are 23 people takes 50% chance that at least 2 of the people have a shared birthday
	3.i am I little bit shocked by the number. It is half of my predication.
Num people: 2, number of experiments with one or more shared birthday: 144, percentage: 0.29
Num people: 3, number of experiments with one or more shared birthday: 433, percentage: 0.87
Num people: 4, number of experiments with one or more shared birthday: 836, percentage: 1.67
Num people: 5, number of experiments with one or more shared birthday: 1396, percentage: 2.79
Num people: 6, number of experiments with one or more shared birthday: 2018, percentage: 4.04
Num people: 7, number of experiments with one or more shared birthday: 2827, percentage: 5.65
Num people: 8, number of experiments with one or more shared birthday: 3833, percentage: 7.67
Num people: 9, number of experiments with one or more shared birthday: 4797, percentage: 9.59
Num people: 10, number of experiments with one or more shared birthday: 5915, percentage: 11.83
Num people: 11, number of experiments with one or more shared birthday: 7127, percentage: 14.25
Num people: 12, number of experiments with one or more shared birthday: 8482, percentage: 16.96
Num people: 13, number of experiments with one or more shared birthday: 9744, percentage: 19.49
Num people: 14, number of experiments with one or more shared birthday: 11300, percentage: 22.60
Num people: 15, number of experiments with one or more shared birthday: 12650, percentage: 25.30
Num people: 16, number of experiments with one or more shared birthday: 13958, percentage: 27.92
Num people: 17, number of experiments with one or more shared birthday: 15705, percentage: 31.41
Num people: 18, number of experiments with one or more shared birthday: 17289, percentage: 34.58
Num people: 19, number of experiments with one or more shared birthday: 19071, percentage: 38.14
Num people: 20, number of experiments with one or more shared birthday: 20396, percentage: 40.79
Num people: 21, number of experiments with one or more shared birthday: 22053, percentage: 44.11
Num people: 22, number of experiments with one or more shared birthday: 23662, percentage: 47.32
Num people: 23, number of experiments with one or more shared birthday: 25278, percentage: 50.56
Num people: 24, number of experiments with one or more shared birthday: 27054, percentage: 54.11
Num people: 25, number of experiments with one or more shared birthday: 28383, percentage: 56.77
Num people: 26, number of experiments with one or more shared birthday: 29748, percentage: 59.50
Num people: 27, number of experiments with one or more shared birthday: 31391, percentage: 62.78
Num people: 28, number of experiments with one or more shared birthday: 32672, percentage: 65.34
Num people: 29, number of experiments with one or more shared birthday: 33963, percentage: 67.93
Num people: 30, number of experiments with one or more shared birthday: 35374, percentage: 70.75
Num people: 31, number of experiments with one or more shared birthday: 36525, percentage: 73.05
Num people: 32, number of experiments with one or more shared birthday: 37699, percentage: 75.40
Num people: 33, number of experiments with one or more shared birthday: 38816, percentage: 77.63
Num people: 34, number of experiments with one or more shared birthday: 39917, percentage: 79.83
Num people: 35, number of experiments with one or more shared birthday: 40881, percentage: 81.76
Num people: 36, number of experiments with one or more shared birthday: 41695, percentage: 83.39
Num people: 37, number of experiments with one or more shared birthday: 42448, percentage: 84.90
Num people: 38, number of experiments with one or more shared birthday: 43185, percentage: 86.37
Num people: 39, number of experiments with one or more shared birthday: 43946, percentage: 87.89
Num people: 40, number of experiments with one or more shared birthday: 44472, percentage: 88.94
Num people: 41, number of experiments with one or more shared birthday: 45227, percentage: 90.45
Num people: 42, number of experiments with one or more shared birthday: 45744, percentage: 91.49
Num people: 43, number of experiments with one or more shared birthday: 46276, percentage: 92.55
Num people: 44, number of experiments with one or more shared birthday: 46615, percentage: 93.23
Num people: 45, number of experiments with one or more shared birthday: 46921, percentage: 93.84
Num people: 46, number of experiments with one or more shared birthday: 47443, percentage: 94.89
Num people: 47, number of experiments with one or more shared birthday: 47742, percentage: 95.48
Num people: 48, number of experiments with one or more shared birthday: 48006, percentage: 96.01
Num people: 49, number of experiments with one or more shared birthday: 48314, percentage: 96.63
Num people: 50, number of experiments with one or more shared birthday: 48509, percentage: 97.02
Num people: 51, number of experiments with one or more shared birthday: 48772, percentage: 97.54
Num people: 52, number of experiments with one or more shared birthday: 48912, percentage: 97.82
Num people: 53, number of experiments with one or more shared birthday: 49040, percentage: 98.08
Num people: 54, number of experiments with one or more shared birthday: 49183, percentage: 98.37
Num people: 55, number of experiments with one or more shared birthday: 49345, percentage: 98.69
Num people: 56, number of experiments with one or more shared birthday: 49393, percentage: 98.79
Num people: 57, number of experiments with one or more shared birthday: 49466, percentage: 98.93
Num people: 58, number of experiments with one or more shared birthday: 49579, percentage: 99.16
Num people: 59, number of experiments with one or more shared birthday: 49658, percentage: 99.32
Num people: 60, number of experiments with one or more shared birthday: 49674, percentage: 99.35
Num people: 61, number of experiments with one or more shared birthday: 49751, percentage: 99.50
Num people: 62, number of experiments with one or more shared birthday: 49785, percentage: 99.57
Num people: 63, number of experiments with one or more shared birthday: 49833, percentage: 99.67
Num people: 64, number of experiments with one or more shared birthday: 49859, percentage: 99.72
Num people: 65, number of experiments with one or more shared birthday: 49899, percentage: 99.80
Num people: 66, number of experiments with one or more shared birthday: 49898, percentage: 99.80
Num people: 67, number of experiments with one or more shared birthday: 49917, percentage: 99.83
Num people: 68, number of experiments with one or more shared birthday: 49939, percentage: 99.88
Num people: 69, number of experiments with one or more shared birthday: 49955, percentage: 99.91
Num people: 70, number of experiments with one or more shared birthday: 49970, percentage: 99.94
Num people: 71, number of experiments with one or more shared birthday: 49973, percentage: 99.95
Num people: 72, number of experiments with one or more shared birthday: 49970, percentage: 99.94
Num people: 73, number of experiments with one or more shared birthday: 49981, percentage: 99.96
Num people: 74, number of experiments with one or more shared birthday: 49976, percentage: 99.95
Num people: 75, number of experiments with one or more shared birthday: 49986, percentage: 99.97
Num people: 76, number of experiments with one or more shared birthday: 49986, percentage: 99.97
Num people: 77, number of experiments with one or more shared birthday: 49986, percentage: 99.97
Num people: 78, number of experiments with one or more shared birthday: 49995, percentage: 99.99
Num people: 79, number of experiments with one or more shared birthday: 49995, percentage: 99.99
Num people: 80, number of experiments with one or more shared birthday: 49996, percentage: 99.99
Num people: 81, number of experiments with one or more shared birthday: 49995, percentage: 99.99
Num people: 82, number of experiments with one or more shared birthday: 49999, percentage: 100.00
Num people: 83, number of experiments with one or more shared birthday: 49997, percentage: 99.99
Num people: 84, number of experiments with one or more shared birthday: 49999, percentage: 100.00
Num people: 85, number of experiments with one or more shared birthday: 49999, percentage: 100.00
Num people: 86, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 87, number of experiments with one or more shared birthday: 49997, percentage: 99.99
Num people: 88, number of experiments with one or more shared birthday: 49998, percentage: 100.00
Num people: 89, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 90, number of experiments with one or more shared birthday: 49999, percentage: 100.00
Num people: 91, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 92, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 93, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 94, number of experiments with one or more shared birthday: 49999, percentage: 100.00
Num people: 95, number of experiments with one or more shared birthday: 49999, percentage: 100.00
Num people: 96, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 97, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 98, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 99, number of experiments with one or more shared birthday: 50000, percentage: 100.00
Num people: 100, number of experiments with one or more shared birthday: 50000, percentage: 100.00
*/

 
public class CodeCampTester {

    public static void main(String[] args){
        final String newline = System.getProperty("line.separator");

        //test 1, hamming distance
        int[] h1 = {1, 2, 3, 4, 5, 0, 3, 7, 1};
        int[] h2 = {1, 2, 10, 4, 5, 4, 3, -10, 1};
        int expected = 3;
        int actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println("Test 1 hamming distance: expected value: " + 
                expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed test 1, hamming distance");
        else
            System.out.println(" ***** FAILED ***** test 1, hamming distance");
        
        // test 2, hamming distance
        h1 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        h2 = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        expected = 10;
        actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println(newline + "Test 2 hamming distance: expected value: " 
                + expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed test 2, hamming distance");
        else
            System.out.println(" ***** FAILED ***** test 2, hamming distance");
        
        // test 3, hamming distance
        h1 = new int[152];
        h2 = new int[152];
        expected = 0;
        actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println(newline + "Test 3 hamming distance: expected value: " 
                + expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed test 3, hamming distance");
        else
            System.out.println(" ***** FAILED ***** test 3, hamming distance");
        
        //test 4, isPermutation
        int[] a = {2,2,1};
        int[] b = {3,2,1};
        boolean expectedBool = false;
        boolean actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 4 isPermutation: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 4, isPermutation");
        else
            System.out.println(" ***** FAILED ***** test 4, isPermutation");

        //test 5, is Permutation
        b = new int[]{2, 1, 3, 3, 3, 3};
        expectedBool = false;
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 5 isPermutation: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 5, isPermutation");
        else
            System.out.println(" ***** FAILED ***** test 5, isPermutation");
        
        //test 6, is Permutation
        a = new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 300, Integer.MAX_VALUE / 2};
        b = new int[]  {300, Integer.MAX_VALUE, Integer.MAX_VALUE / 2, 0, Integer.MIN_VALUE};
        expectedBool = true;
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 6 isPermutation: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 6, isPermutation");
        else
            System.out.println(" ***** FAILED ***** test 6, isPermutation");
        
        
        //test 7, is Permutation
        a = new int[] {-15,-3,0,-3,5,8};
        b = new int[] {0,-3,8,-15,-3,5};
        expectedBool = true;
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 7 isPermutation: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 7, isPermutation");
        else
            System.out.println(" ***** FAILED ***** test 7, isPermutation");
        
 
        //test 8, is Permutation
        a = new int[1];
        b = new int[1];
        expectedBool = true;
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 8 isPermutation: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 8, isPermutation");
        else
            System.out.println(" ***** FAILED ***** test 8, isPermutation");
        
        //test 9, is Permutation
        a = new int[152];
        b = new int[521];
        expectedBool = false;
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 9 isPermutation: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 9, isPermutation");
        else
            System.out.println(" ***** FAILED ***** test 9, isPermutation");
        
        //test 10, is Permutation
        a = new int[14];
        b = new int[14];
        expectedBool = true;
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 10 isPermutation: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 10, isPermutation");
        else
            System.out.println(" ***** FAILED ***** test 10, isPermutation");
        
        //test 11, is Permutation
        a = new int[16];
        b = new int[10];
        expectedBool = false;
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 11 isPermutation: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 11, isPermutation");
        else
            System.out.println(" ***** FAILED ***** test 11, isPermutation");      
        
        //test 12, is Permutation
        a = new int[] {-5};
        b = new int[] {-5};
        expectedBool = true;
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 11 isPermutation: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 11, isPermutation");
        else
            System.out.println(" ***** FAILED ***** test 12, isPermutation");  
        
        //test 13, is Permutation
        a = new int[] {2, 2, 2, -3, 3};
        b = new int[] {0, -2, 0, 0, 14};
        expectedBool = false;
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 13 isPermutation: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 13, isPermutation");
        else
            System.out.println(" ***** FAILED ***** test 13, isPermutation");   
        
        //test 14, is Permutation
        final int NUM_ELEMENTS = 10000;
        ArrayList<Integer> temp = new ArrayList<Integer>(NUM_ELEMENTS);
        Random r = new Random();
        for(int i = 0; i < NUM_ELEMENTS; i++) {
            temp.add(r.nextInt());
        }
        
        a = intListToArray(temp);
        Collections.shuffle(temp);
        b = intListToArray(temp);
        
        expectedBool = true;
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 14 isPermutation: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 14, isPermutation");
        else
            System.out.println(" ***** FAILED ***** test 14, isPermutation"); 
        
        
        
        // test 15, mostVowels
        String[] sList = {"aaaaaeiaa", "aieou"};
        int expectedResult = 0;
        int actualResult = CodeCamp.mostVowels(sList);
        System.out.println(newline + "Test 15 mostVowels: expected result: " 
                + expectedResult + ", actual result: " + actualResult);
        if( actualResult == expectedResult )
            System.out.println("passed test 15, mostVowels");
        else
            System.out.println("***** FAILED ***** test 15, mostVowels");

        
        // test 16, mostVowels
        sList = new String[] {"Staying", null, "", "moo", "SEqUOIA NAtIPPPxvvOnAl FOrEst", "!!!!>>+_mkkgggt)(*&^%$#@!>)))???\\///\n\n/n"};
        expectedResult = 4;
        actualResult = CodeCamp.mostVowels(sList);
        System.out.println(newline + "Test 16 mostVowels: expected result: " 
                + expectedResult + ", actual result: " + actualResult);
        if( actualResult == expectedResult )
            System.out.println("passed test 16, mostVowels");
        else
            System.out.println("***** FAILED ***** test 16, mostVowels");
        
        
        // test 17, mostVowels
        sList = new String[] {null, null, "100,000,000", "XXX", "", "!!!!45512354>>+_)(*&^%$#@!>)))???\\///\n\n/n", null};
        expectedResult = 2;
        actualResult = CodeCamp.mostVowels(sList);
        System.out.println(newline + "Test 17 mostVowels: expected result: " 
                + expectedResult + ", actual result: " + actualResult);
        if( actualResult == expectedResult )
            System.out.println("passed test 17, mostVowels");
        else
            System.out.println("***** FAILED ***** test 17, mostVowels"); 
        
        
        // test 18 mostVowels
        sList = new String[] {"", null, null, null, "", "", null};
        expectedResult = 0;
        actualResult = CodeCamp.mostVowels(sList);
        System.out.println(newline + "Test 18 mostVowels: expected result: " 
                + expectedResult + ", actual result: " + actualResult);
        if( actualResult == expectedResult )
            System.out.println("passed test 18, mostVowels");
        else
            System.out.println("***** FAILED ***** test 18, mostVowels");
        
 
        // test 19 mostVowels
        sList = new String[] {""};
        expectedResult = 0;
        actualResult = CodeCamp.mostVowels(sList);
        System.out.println(newline + "Test 19 mostVowels: expected result: " 
                + expectedResult + ", actual result: " + actualResult);
        if( actualResult == expectedResult )
            System.out.println("passed test 19, mostVowels");
        else
            System.out.println("***** FAILED ***** test 19, mostVowels");
        
        
        // test 20 mostVowels
        sList = new String[] {null, "AIuiBA", ""};
        expectedResult = 1;
        actualResult = CodeCamp.mostVowels(sList);
        System.out.println(newline + "Test 20 mostVowels: expected result: " 
                + expectedResult + ", actual result: " + actualResult);
        if( actualResult == expectedResult )
            System.out.println("passed test 20, mostVowels");
        else
            System.out.println("***** FAILED ***** test 20, mostVowels");
 
        
        //test 21, sharedBirthdays, simple test
        int pairs = CodeCamp.sharedBirthdays(1, 365);
        System.out.println(newline + "Test 21 shared birthdays: expected: 0, actual: " + pairs);
        int expectedShared = 0;
        if( pairs == expectedShared )
            System.out.println("passed test 21, shared birthdays");
        else
            System.out.println("***** FAILED ***** test 21, shared birthdays");
        
        //test 22, sharedBirthdays, simple test
        pairs = CodeCamp.sharedBirthdays(366, 365);
        System.out.println(newline + "Test 22 shared birthdays: expected: " +
                "a value of 1 or more, actual: " + pairs);
        if( pairs > 0 )
            System.out.println("passed test 22, shared birthdays");
        else
            System.out.println("***** FAILED ***** test 22, shared birthdays");        
        
        //test 23, sharedBirthdays, simple test
        pairs = CodeCamp.sharedBirthdays(2, 1);
        System.out.println(newline + "Test 23 shared birthdays: expected: 1" +
                ", actual: " + pairs);
        if( pairs == 1 )
            System.out.println("passed test 23, shared birthdays");
        else
            System.out.println("***** FAILED ***** test 23, shared birthdays.");
        
        //test 24, sharedBirthdays, simple test
        pairs = CodeCamp.sharedBirthdays(3, 1);
        System.out.println(newline + "Test 24 shared birthdays: expected: 3" +
                ", actual: " + pairs);       
        if( pairs == 3 )
            System.out.println("passed test 24, shared birthdays");
        else
            System.out.println("***** FAILED ***** test 24, shared birthdays. " +
                    "Expected pairs to be 3. Value returned: " + pairs);
        
        //test 25, sharedBirthdays, simple test
        pairs = CodeCamp.sharedBirthdays(4, 1);
        System.out.println(newline + "Test 25 shared birthdays: expected: 6" +
                ", actual: " + pairs);
        if( pairs == 6 )
            System.out.println("passed test 25, shared birthdays");
        else
            System.out.println("***** FAILED ***** test 25, shared birthdays. " +
                    "Expected pairs to be 6. Value returned: " + pairs);
        
        //test 26, sharedBirthdays, simple test
        pairs = CodeCamp.sharedBirthdays(5, 1);
        System.out.println(newline + "Test 26 shared birthdays: expected: 10" +
                ", actual: " + pairs);
        if( pairs == 10 )
            System.out.println("passed test 26, shared birthdays");
        else
            System.out.println("***** FAILED ***** test 26, shared birthdays. " +
                    "Expected pairs to be 10. Value returned: " + pairs);   

        
        //test 27, sharedBirthdays, simple test
        pairs = CodeCamp.sharedBirthdays(100, 1);
        System.out.println(newline + "Test 27 shared birthdays: expected: 4950" +
                ", actual: " + pairs);
        if( pairs == 4950 )
            System.out.println("passed test 27, shared birthdays");
        else
            System.out.println("***** FAILED ***** test 27, shared birthdays. " +
                    "Expected pairs to be 4950. Value returned: " + pairs);
 
   
        //test 28, sharedBirthdays, stress test
        pairs = CodeCamp.sharedBirthdays(100000, 100);
        System.out.println(newline + "Test 28 shared birthdays - stress test. (Is solution slow?) : expected: > 0" +
                ", actual: " + pairs);
        if( pairs > 0 )
            System.out.println("passed test 28, shared birthdays");
        else
            System.out.println("***** FAILED ***** test 28, shared birthdays. " +
                    "Expected at least 1 pair. Value returned: " + pairs);
        
        //test 29, sharedBirthdays, stress test
        pairs = CodeCamp.sharedBirthdays(10000, 10000);
        System.out.println(newline + "Test 29 shared birthdays - stress test. (Is solution slow?) : expected: > 0" +
                ", actual: " + pairs);
        if( pairs > 0 )
            System.out.println("passed test 29, shared birthdays");
        else
            System.out.println("***** FAILED ***** test 29, shared birthdays. " +
                    "Expected at least 1 pair. Value returned: " + pairs);
 
        
        //test 30, queensAreASafe
        char[][] board = { {'.', '.', '.'},
                          {'q', '.', '.'},
                          {'.', '.', 'q'}};
        
        expectedBool = true;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "Test 30 queensAreSafe: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 30, queensAreSafe");
        else
            System.out.println(" ***** FAILED ***** test 30, queensAreSafe");

        //test 31, queensAreASafe
        board = new char[][]{{'.', '.', '.', 'q'},
                             {'.', '.', '.', '.'},
                             {'.', '.', '.', '.'},
                             {'q', '.', '.', 'q'}};
        expectedBool = false;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "Test 31 queensAreSafe: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 31, queensAreSafe");
        else
            System.out.println(" ***** FAILED ***** test 31, queensAreSafe");
        
        
        //test 32, queensAreASafe
        board = new char[][] {{'q', '.', '.', '.', '.', '.', '.'},
                             {'.', '.', '.', '.', 'q', '.', '.'},
                             {'.', 'q', '.', '.', '.', '.', '.'},
                             {'.', '.', '.', '.', '.', 'q', '.'},
                             {'.', '.', 'q', '.', '.', '.', '.'},
                             {'.', '.', '.', '.', '.', '.', 'q'},
                             {'.', '.', '.', 'q', '.', '.', '.'}};
        expectedBool = true;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "Test 32 queensAreSafe: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 32, queensAreSafe");
        else
            System.out.println(" ***** FAILED ***** test 32, queensAreSafe");

         //test 33, queensAreASafe
        board = new char[][] {{'q', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                             {'.', '.', '.', '.', 'q', '.', '.', '.', '.', '.'},
                             {'.', 'q', '.', '.', '.', '.', '.', '.', '.', 'q'},
                             {'.', '.', '.', '.', '.', 'q', '.', '.', '.', '.'},
                             {'.', '.', 'q', '.', '.', '.', '.', '.', '.', '.'},
                             {'.', '.', '.', '.', '.', '.', 'q', '.', '.', '.'},
                             {'.', '.', '.', 'q', '.', '.', '.', '.', '.', '.'},
                             {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                             {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                             {'.', '.', '.', '.', '.', '.', '.', '.', 'q', '.'},};
        expectedBool = false;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "Test 33 queensAreSafe: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 33, queensAreSafe");
        else
            System.out.println(" ***** FAILED ***** test 33, queensAreSafe");   
        
      
        // test 34, getValueOfMostValuablePlot
        int[][] city = {{0, -2, -7, 0, -1},
                          {9, 2, -6, 2, 0},
                          {0, 1, -4, 1, 0},
                          {-1, 8, 0, -2, 1},
                          {-10, 1, 1, -5, -6},
                          {-15, -1, 1, 5, 4}};
        
        expected = 19;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println(newline + "Test 34 getValueOfMostValuablePlot: expected value: " 
                + expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed test 34, getValueOfMostValuablePlot");
        else
            System.out.println(" ***** FAILED ***** test 34, getValueOfMostValuablePlot");


         // test 35, getValueOfMostValuablePlot
        city[4][1] = 6;
        expected = 19;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println(newline + "Test 35 getValueOfMostValuablePlot: expected value: " 
                + expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed test 35, getValueOfMostValuablePlot");
        else
            System.out.println(" ***** FAILED ***** test 35, getValueOfMostValuablePlot");
        
        // test 36, getValueOfMostValuablePlot
        city = new int[][] {{1}};
        expected = 1;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println("\nTest 36 getValueOfMostValuablePlot: expected value: " 
                + expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed test 36, getValueOfMostValuablePlot");
        else
            System.out.println(" ***** FAILED ***** test 36, getValueOfMostValuablePlot");
        
        // test 37, getValueOfMostValuablePlot
        city = new int[][]{{1, 2, 3, 4, 5, 6, 7}};
        expected = 28;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println(newline + "Test 37 getValueOfMostValuablePlot: expected value: " 
                + expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed test 37, getValueOfMostValuablePlot");
        else
            System.out.println(" ***** FAILED ***** test 37, getValueOfMostValuablePlot");
        
        // test 38, getValueOfMostValuablePlot
        city = new int[][]{{-10, -10, -10, -5},
                          {-15, -15, -10, -10},
                          {-5, -10, -20, -5},
                          {-3, -5, -10, -20}};
        
        expected = -3;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println("\nTest 38 getValueOfMostValuablePlot: expected value: " 
                + expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed test 38, getValueOfMostValuablePlot");
        else
            System.out.println(" ***** FAILED ***** test 38, getValueOfMostValuablePlot");
        
        
        // comment in when ready for stress test
        // test 39, getValueOfMostValuablePlot
/*        city = new int[100][100];
        Random rand = new Random();
        for(int row = 0; row < city.length; row++) {
            for(int col = 0; col < city[row].length; col++) {
                city[row][col] = rand.nextInt(200) - 100;
            }
        }
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println("\nTest 39 getValueOfMostValuablePlot: expected value: > 0" 
                    + ", actual value: " + actual);
        if( actual > 0 )          
            System.out.println(" passed test 39, getValueOfMostValuablePlot");
        else
            System.out.println(" ***** FAILED ***** test 39, getValueOfMostValuablePlot");*/
        
        // DELETE THE ABOVE TESTS IN THE VERSION OF THE FILE YOU TURN IN
        
        // CS314 Students: add tests here.
        
    } // end of main method
    
    // pre: list != null
    private static int[] intListToArray(List<Integer> list) {
        if(list == null)
            throw new IllegalArgumentException("list parameter may not be null.");
        int[] result = new int[list.size()];
        int arrayIndex = 0;
        for(int x : list) {
            result[arrayIndex++] = x;
        }
        return result;
    }
}
