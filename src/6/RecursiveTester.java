
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


import java.util.ArrayList;
import java.util.Collections;

/**
 * Tester class for the methods in Recursive.java
 * @author scottm
 *
 */
public class RecursiveTester {

    // run the tests
    public static void main(String[] args) {
//        doBinaryTests();
//        doReverseTests();
//        doNextIsDoubleTests();
//        doListMnemonicsTests();
        // doCarpetTest(); 
//        doBinaryTests();
        doMazeTests();
//        doFlowOffMapTests();
       // doFairTeamsTests();
//        studentTests();
    }

    private static void doMazeTests() {        
        int mazeTestNum = 1;
        runMazeTest("GRSRE", 1, true, mazeTestNum++);
        runMazeTest("GGSRE", 1, false, mazeTestNum++);
        String maze = "RRSRR**RRRRR";
        runMazeTest(maze, 3, false, mazeTestNum++);
        maze =    "RSRR"
        		+ "R**R"
        		+ "RERR";
        runMazeTest(maze, 3, true, mazeTestNum++);
        runMazeTest(maze, 3, true, mazeTestNum++);
        maze = "*ER****RRRRRR**RRRSR";
        runMazeTest(maze, 5, false, mazeTestNum++);
        maze = "**G****R**ESRRG**R****G**";
        runMazeTest(maze, 5, true, mazeTestNum++);
        maze = "**G****R**ESRRG**R*G**G**";
        runMazeTest(maze, 5, false, mazeTestNum++);
        maze = 	  "GGE"
        		+ "GS*";
        runMazeTest(maze, 2, true, mazeTestNum++);
    }
    
    private static void runMazeTest(String rawMaze, int rows, boolean expected, int num) {
        char[][] maze = makeMaze(rawMaze, rows);
        boolean actual = Recursive.canEscapeMaze(maze);
        System.out.println("Can escape maze test number " + num);
        printMaze(maze);
        System.out.println("Expected result: " + expected);
        System.out.println("Actual result:   " + actual);
        if (expected == actual) {
            System.out.println("passed test " + num);
        } else {
            System.out.println("FAILED TEST " + num);
        }
        System.out.println();
    }

    // print the given maze
    // pre: none
    private static void printMaze(char[][] maze) {
        if (maze == null) {
            System.out.println("NO MAZE GIVEN");
        } else {
            for (char[] row : maze) {
                for (char c : row) {
                    System.out.print(c);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
    }

    // generate a char[][] given the raw string
    // pre: rawMaze != null, rawMaze.length() % rows == 0
    private static char[][] makeMaze(String rawMaze, int rows) {
        if (rawMaze == null || rawMaze.length() % rows != 0) {
            throw new IllegalArgumentException("Violation of precondition in makeMaze."
                            + "Either raw data is null or left over values: ");
        }
        int cols = rawMaze.length() / rows;
        char[][] result = new char[rows][cols];
        int rawIndex = 0;
        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[0].length; c++) {
                result[r][c] = rawMaze.charAt(rawIndex);
                rawIndex++;
            }
        }
        return result;
    }

    private static void  doCarpetTest() {
        Recursive.drawCarpet(729, 4);
        Recursive.drawCarpet(729, 1);
        
    }

    private static void doFairTeamsTests() {
        int[] abilities = {1, 2, 3, 4, 5, 6, 7};
        if(Recursive.minDifference(3, abilities) == 1)
            System.out.println( "Test 1 passed. min difference.");
        else
            System.out.println( "Test 1 failed. min difference.");

        if(Recursive.minDifference(5, abilities) == 2)
            System.out.println( "Test 2 passed. min difference.");
        else
            System.out.println( "Test 2 failed. min difference.");

        if(Recursive.minDifference(6, abilities) == 4)
            System.out.println( "Test 3 passed. min difference.");
        else
            System.out.println( "Test 3 failed. min difference.");

        abilities = new int[]{1, 12, 46, 60, 53, 86, 72, 79, 44, 7};
        if(Recursive.minDifference(3, abilities) == 3)
            System.out.println( "Test 4 passed. min difference.");
        else
            System.out.println( "Test 4 failed. min difference.");

        if(Recursive.minDifference(5, abilities) == 19)
            System.out.println( "Test 5 passed. min difference.");
        else
            System.out.println( "Test 5 failed. min difference.");


        abilities = new int[]{10, 10, 6, 6, 6};
        if(Recursive.minDifference(2, abilities) == 2)
            System.out.println( "Test 6 passed. min difference.");
        else
            System.out.println( "Test 6 failed. min difference.");  
        
        abilities = new int[]{-10, -10, -8, -8, -8};
        if(Recursive.minDifference(2, abilities) == 4)
            System.out.println( "Test 6 passed. min difference.");
        else
            System.out.println( "Test 6 failed. min difference."); 
        
        abilities = new int[]{-5, 5, 10, 5, 10, -15};
        if(Recursive.minDifference(2, abilities) == 0)
            System.out.println( "Test 7 passed. min difference.");
        else
            System.out.println( "Test 7 failed. min difference."); 
    }

    private static void doFlowOffMapTests() {
        int testNum = 1;
        int[][] world = {{5,5,5,5,5,5},
                         {5,5,5,5,5,5},
                         {5,5,5,5,5,5},
                         {5,5,4,4,5,5},
                         {5,5,3,3,5,5},
                         {5,5,2,2,5,5},
                         {5,5,5,1,5,5},
                         {5,5,5,-2,5,5}};

        doOneFlowTest(world, 0, 0, true, testNum++);
        doOneFlowTest(world, 1, 1, false, testNum++);
        doOneFlowTest(world, 3, 3, true, testNum++);
        doOneFlowTest(world, 1, 5, true, testNum++);

        world = new int[][]
           {{10, 10, 10, 10, 10, 10, 10},
            {10, 10, 10,  5, 10, 10, 10},
            {10, 10, 10,  6, 10, 10, 10},
            {10, 10, 10,  7, 10, 10, 10},
            {5,   6,  7,  8,  7,  6, 10},
            {10, 10, 10,  7, 10, 10, 10},
            {10, 10, 10,  6, 10, 10, 10},
            {10, 10, 10,  5, 10, 10, 10},
            {10, 10, 10, 10, 10, 10, 10}};
            
        doOneFlowTest(world, 3, 3, false, testNum++);
        doOneFlowTest(world, 4, 3, true, testNum++);        
    }
    
    private static void doOneFlowTest(int[][] world, int r, int c, boolean expected, int testNum) {
        System.out.println("Can Flow Off Map Test Number " + testNum);
        boolean actual = Recursive.canFlowOffMap(world, r, c);
        System.out.println("Start location = " + r + ", " + c);
        System.out.println("Expected result = " + expected + " actual result = " + actual);
        if (expected == actual) {
            System.out.println("passed test " + testNum + " can flow off map.");
        } else {
            System.out.println("FAILED TEST " + testNum + " can flow off map.");
        }
    }

    private static void doListMnemonicsTests() {
        ArrayList<String> mnemonics = Recursive.listMnemonics("1");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("1");
        if (mnemonics.equals(expected))
            System.out.println( "Test 1 passed. Phone mnemonics.");
        else
            System.out.println( "Test1 failed. Phone mnemonics.");

        mnemonics = Recursive.listMnemonics("22");
        Collections.sort(mnemonics);
        expected.clear();
        expected.add("AA");
        expected.add("AB");
        expected.add("AC");
        expected.add("BA");
        expected.add("BB");
        expected.add("BC");
        expected.add("CA");
        expected.add("CB");
        expected.add("CC");
        Collections.sort(expected);
        if (mnemonics.equals(expected))
            System.out.println( "Test 2 passed. Phone mnemonics.");
        else
            System.out.println( "Test 2 failed. Phone mnemonics.");

        mnemonics = Recursive.listMnemonics("110010");
        expected.clear();
        expected.add("110010");
        if (mnemonics.equals(expected))
            System.out.println( "Test 3 passed. Phone mnemonics.");
        else
            System.out.println( "Test 3 failed. Phone mnemonics.");

    }

    private static void doNextIsDoubleTests() {
        int[] numsForDouble = {1, 2, 4, 8, 16, 32, 64, 128, 256};
        int actualDouble = Recursive.nextIsDouble(numsForDouble);
        int expectedDouble = 8;
        if (actualDouble == expectedDouble)
            System.out.println( "Test 1 passed. next is double.");
        else
            System.out.println( "Test 1 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);


        numsForDouble = new int[]{1, 3, 4, 2, 32, 8, 128, -5, 6};
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 0;
        if (actualDouble == expectedDouble)
            System.out.println( "Test 2 passed. next is double.");
        else
            System.out.println( "Test 2 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);


        numsForDouble = new int[]{1, 0, 0, -5, -10, 32, 64, 128, 2, 9, 18};
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 5;
        if (actualDouble == expectedDouble)
            System.out.println( "Test 3 passed. next is double.");
        else
            System.out.println( "Test 3 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);
        
        numsForDouble = new int[]{37};
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 0;
        if (actualDouble == expectedDouble)
            System.out.println( "Test 4 passed. next is double.");
        else
            System.out.println( "Test 4 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);
        
        numsForDouble = new int[]{37, 74};
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 1;
        if (actualDouble == expectedDouble)
            System.out.println( "Test 5 passed. next is double.");
        else
            System.out.println( "Test 5 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);
    }

    private static void doReverseTests() {
        String actualRev = Recursive.revString("target");
        String expectedRev = "tegrat";
        if (actualRev.equals(expectedRev))
            System.out.println( "Test 1 passed. reverse string.");
        else
            System.out.println( "Test 1 failed. reverse string. expected: " + expectedRev + ", actual: " + actualRev);


        actualRev = Recursive.revString("Calvin and Hobbes");
        expectedRev = "sebboH dna nivlaC";
        if (actualRev.equals(expectedRev))
            System.out.println( "Test 2 passed. reverse string.");
        else
            System.out.println( "Test 2 failed. reverse string. expected: " + expectedRev + ", actual: " + actualRev);
        
        actualRev = Recursive.revString("U");
        expectedRev = "U";
        if (actualRev.equals(expectedRev))
            System.out.println( "Test 3 passed. reverse string.");
        else
            System.out.println( "Test 3 failed. reverse string. expected: " + expectedRev + ", actual: " + actualRev);
    }

    private static void doBinaryTests() {
        String actualBinary = Recursive.getBinary(13);
        String expectedBinary = "1101";
        if (actualBinary.equals(expectedBinary))
            System.out.println( "Test 1 passed. get binary.");
        else
            System.out.println( "Test 1 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);


        actualBinary = Recursive.getBinary(0);
        expectedBinary = "0";
        if (actualBinary.equals(expectedBinary))
            System.out.println( "Test 2 passed. get binary.");
        else
            System.out.println( "Test 2 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);

        actualBinary = Recursive.getBinary(-35);
        expectedBinary = "-100011";
        if (actualBinary.equals(expectedBinary))
            System.out.println( "Test 3 passed. get binary.");
        else
            System.out.println( "Test 3failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);

        actualBinary = Recursive.getBinary(1);
        expectedBinary = "1";
        if (actualBinary.equals(expectedBinary))
            System.out.println( "Test 4 passed. get binary.");
        else
            System.out.println( "Test 4 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);


        actualBinary = Recursive.getBinary(64);
        expectedBinary = "1000000";
        if (actualBinary.equals(expectedBinary))
            System.out.println( "Test 5 passed. get binary");
        else
            System.out.println( "Test 5 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);
    }

    // pre: r != null
    // post: run student test
    private static void studentTests() {
        // CS314 students put your tests here

    }


}