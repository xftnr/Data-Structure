/*  Student information for assignment:
 *
 *  UTEID:px353
 *  email address:xiapengdi@yahoo.com
 *  Grader name:51925 
 *  Unique section number:Gilbert Maldonado
 *  Number of slip days I am using:0
 */


/*
 * Place results of experiments here:
 * Binary Tree
//Average time: 5.375209999999999E-4
//Height: 23
//Size: 1000
//Average time: 4.4041490000000006E-4
//Height: 23
//Size: 2000
//Average time: 6.763457000000001E-4
//Height: 24
//Size: 4000
//Average time: 0.0014231704
//Height: 30
//Size: 8000
//Average time: 0.0027837232999999998
//Height: 33
//Size: 16000
//Average time: 0.0073940544
//Height: 34
//Size: 32000
//Average time: 0.0167706469
//Height: 36
//Size: 64000
//Average time: 0.0376620642
//Height: 38
//Size: 128000
//Average time: 0.1081339259
//Height: 45
//Size: 255996
//Average time: 0.28285084489999995
//Height: 45
//Size: 511972
//Average time: 0.7314469135000001
//Height: 51
//Size: 1023878
 * For each value of N, the minimum possible tree height will be logN
 * 
 * Tree set
//Average time: 8.183704E-4
//Average time: 8.377285000000002E-4
//Average time: 9.359010999999999E-4
//Average time: 0.0016692543000000001
//Average time: 0.0031660247999999993
//Average time: 0.0074949531
//Average time: 0.0212351603
//Average time: 0.05046166919999999
//Average time: 0.12162615280000003
//Average time: 0.3167596641
//Average time: 0.8161475554
 * The O(logN) for tree set add method,since when N is smaller, average almost not change.
 * It is similar with the binary tree
 * 
 * binary tree
//Average time: 0.0020881777999999995
//Height: 999
//Size: 1000
//Average time: 0.0070047998
//Height: 1999
//Size: 2000
//Average time: 0.030678241899999997
//Height: 3999
//Size: 4000
//Average time: 0.12302542230000002
//Height: 7999
//Size: 8000
//Average time: 0.5099839211
//Height: 15999
//Size: 16000

predict
//average time: 2.0
//Height: 31999
//Size:32000
//average time: 8.0
//Height: 63999
//Size:64000
//average time: 32.0
//Height: 127999
//Size:128000
//average time: 128.0
//Height: 255999
//Size:256000
//average time: 512.0
//Height: 511999
//Size:512000
 *
 *Treeset
 *Average time: 8.364247E-4
Average time: 5.803061999999999E-4
Average time: 0.0011505381999999998
Average time: 9.090371000000002E-4
Average time: 0.0017427752
Average time: 0.004185955600000001
Average time: 0.010278952999999997
Average time: 0.021656217100000003
Average time: 0.0528132739
Average time: 0.1120565729
Average time: 0.24628116539999997
 *The big O for binary tree to insert sorted element is N^2
 *the big O for tree set to insert sorted element is N
 *The reason is that binary tree became only one long chain instead of a tree.
 *It become worst case.
 *The tree set only need add the element end of set and no need on sort.
 *So it is O(N) for tree set.
 */


import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;


/**
 * Some test cases for CS314 Binary Search Tree assignment.
 *
 */
public class BSTTester {

    /**
     * The main method runs the tests.
     * @param args Not used
     */
    public static void main(String[] args) {
        
        //test 1-2 constructor
    	System.out.println("Test 1-2: empty tree created.");
    	BinarySearchTree<String> test1 = new BinarySearchTree<>();
    	showTestResults( test1.size() == 0, 1 );
    	BinarySearchTree<Integer> test2 = new BinarySearchTree<>();
    	showTestResults( test2.size() == 0, 2 );
        
    	//test 3-4 add
    	System.out.println("Test 3-4: add the element correctly.");
    	test2.add(10);
    	showTestResults( test2.size() == 1, 3 );
    	test2.add(10);
    	showTestResults( test2.size() == 1, 4 );
    	
        //test 5-6 remove
    	System.out.println("Test 5-6: remove the element correctly.");
    	test2.add(5);
    	test2.add(15);
    	test2.add(11);
    	test2.remove(10);
    	showTestResults( test2.size() == 3, 5 );
    	test2.add(1);
    	showTestResults( test2.remove(10) == false, 6 );
    	
        //test 7-8 isPresent
    	System.out.println("Test 7-8: tree contain the element correctly.");
    	showTestResults( test2.isPresent(10)==false, 7 );
    	showTestResults( test2.isPresent(11)==true, 8 );
    	
        //test 9-10 size
    	System.out.println("Test 9-10: tree shows size correctly.");
    	showTestResults( test2.size() == 4, 9 );
    	showTestResults( test1.size() == 0, 10 );
    	
        //test 11-12 height
    	System.out.println("Test 11-12: tree shows height correctly.");
    	showTestResults( test2.height() == 2, 11 );
    	showTestResults( test1.height() == -1, 12 );
    	
        //test 13-14 getAll
    	System.out.println("Test 13-14: tree shows ascending order correctly.");
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(5);
        expected.add(11);
        expected.add(15);
        showTestResults( expected.equals( test2.getAll() ) == true, 13 );
        ArrayList<String> expected2 = new ArrayList<>();
        showTestResults( expected2.equals( test1.getAll() ) == true, 14 );
        
        //test 15-16 max
        System.out.println("Test 15-16: tree shows maximum correctly.");
        showTestResults( test2.max()==15, 15);
        test1.add("alpha");
        test1.add("abandon");
        test1.add("delta");
        showTestResults( test1.max().equals("delta"), 16);
        
        //test 17-18 min
        System.out.println("Test 17-18: tree shows minimum correctly.");
        showTestResults( test2.min()==1, 17);
        showTestResults( test1.min().equals("abandon"), 18);
        
        //test 19-20 iterativeAdd
        System.out.println("Test 19-20: tree shows iterativeAdd correctly.");
        test1.iterativeAdd("cs");
        showTestResults( test2.size()==4, 19);
        showTestResults( test1.iterativeAdd("cs")==false, 20);
        
        //test 21-22 get
        System.out.println("Test 21-22: get nth value in ascending order correctly.");
        showTestResults( test2.get(0)==1, 21);
        showTestResults( test2.get(3)==15, 22);
        
        //test 23-24 getAllLessThan
        System.out.println("Test 23-24: tree find a sublist with less than target value correctly.");
        expected.remove(3);
        showTestResults( expected.equals( test2.getAllLessThan(12) ) == true, 23 );
        expected.remove(2);
        showTestResults( expected.equals( test2.getAllLessThan(11) ) == true, 24 );
        
        //test 25-26 getAllGreaterThan
        System.out.println("Test 25-26: tree find a sublist with greater than target value correctly.");
        expected.add(11);
        expected.add(15);
        showTestResults( expected.equals( test2.getAllGreaterThan(0) ) == true, 25 );
        expected.remove(0);
        showTestResults( expected.equals( test2.getAllGreaterThan(1) ) == true, 26 );
        
        //test 27-28 numNodesAtDepth
        System.out.println("Test 27-28: tree shows num of nodes at n depth correctly.");
        showTestResults( test2.numNodesAtDepth(2)==1, 27 );
        test2.add(4);
        test2.add(0);
        showTestResults( test2.numNodesAtDepth(2)==3, 28 );
        
        //experiment
//        Stopwatch s1= new Stopwatch();
//        for(int i=0;i<=10;i++){
//        	experiment2(s1,i);
//        }
//   
    }
    private static void experiment2(Stopwatch s1, int pow){
    	 int n = (int) Math.pow(2, pow)*1000;
         //Random r = new Random();
         double total=0.0;
         TreeSet<Integer> test4 = new TreeSet<Integer>();
         for(int j=0;j<10;j++){
         	test4 = new TreeSet<Integer>();
         	s1.start();
         	for(int i = 0; i < n; i++) {
         		test4.add( i ); 
         	}
         	s1.stop();
         	total+=s1.time();
         }
         System.out.println("Average time: " + total/10);
    }
    private static void experiment(Stopwatch s1, int pow){
    	
        int n = (int) Math.pow(2, pow)*1000;
        //Random r = new Random();
        double total=0.0;
        BinarySearchTree<Integer> test3 = new BinarySearchTree<>();
        for(int j=0;j<10;j++){
        	test3 = new BinarySearchTree<>();
        	s1.start();
        	for(int i = 0; i < n; i++) {
        		test3.add( i ); 
        	}
        	s1.stop();
        	total+=s1.time();
        }
        System.out.println("Average time: " + total/10);
        System.out.println("Height: " + test3.height());
        System.out.println("Size: "+ test3.size());
    }

    private static void showTestResults(boolean passed, int testNum) {
        if( passed )
            System.out.println( "Test " + testNum + " passed.");
        else
            System.out.println( "TEST " + testNum + " FAILED.");
    }
}