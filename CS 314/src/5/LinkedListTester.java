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

// Experiment results. CS314 students, place your experiment
// results here:


import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class LinkedListTester {
	private static LinkedList<String> list;
    public static <E> void main(String[] args) {

    	list = new LinkedList<String>();
    	LinkedList<String> l = new LinkedList<>();
    	IList<E> list2= new LinkedList<>();
        Object[] actual;
        Object[] expected;
        
        //Test 1-3 add
        list.add("A");
        test("[A]" ,Arrays.toString(toArray(list)), "Add method");
        list.add("B");
        test("[A, B]" ,Arrays.toString(toArray(list)), "Add method");
        list.add("A");
        test("[A, B, A]" ,Arrays.toString(toArray(list)), "Add method");
        
        //Test 4-6 insert
        list.insert(0, "T");
        test("[T, A, B, A]" ,Arrays.toString(toArray(list)), "Insert method");
        list.insert(2, "P");
        test("[T, A, P, B, A]" ,Arrays.toString(toArray(list)), "Insert method");
        list.insert(5, "K");
        test("[T, A, P, B, A, K]" ,Arrays.toString(toArray(list)), "Insert method");
        
        //Test 7-9 set
        list.set(3, "P");
        test("[T, A, P, P, A, K]" ,Arrays.toString(toArray(list)), "Set method");
        list.set(5, "P");
        test("[T, A, P, P, A, P]" ,Arrays.toString(toArray(list)), "Set method");
        list.set(0, "U");
        test("[U, A, P, P, A, P]" ,Arrays.toString(toArray(list)), "Set method");
        
        //Test 10-12 get
        test("U" ,list.get(0), "Get method");
        test("A" ,list.get(1), "Get method");
        test("P" ,list.get(5), "Get method");
        
        //Test 13-15 remove(indexOf)
        list.remove(0);
        test("[A, P, P, A, P]" ,Arrays.toString(toArray(list)), "Remove(indexOf) method");
        list.remove(1);
        test("[A, P, A, P]" ,Arrays.toString(toArray(list)), "Remove(indexOf) method");
        list.remove(3);
        test("[A, P, A]" ,Arrays.toString(toArray(list)), "Remove(indexOf) method");
        
        //Test 16-18 remove(Obj)
        test(true ,list.remove("A") , "Remove(Obj) method");
        test(false ,list.remove("K") , "Remove(Obj) method");
        test(true ,list.remove("P") , "Remove(Obj) method");
        
        //Test 19-21 get SubString
        list.add("A");
        list.add("B");
        list.add("A");
        list.add("D");
        list.add("G");
        list.add("H");
        list.add("T");
        list.add("A");
        test("[A, A, B, A, D, G, H, T]" ,Arrays.toString(toArray(list.getSubList(0, 8))), "GetSubString method");
        test("[A, B, A, D]" ,Arrays.toString(toArray(list.getSubList(1, 5))), "GetSubString method");
        test("[A, B, A]" ,Arrays.toString(toArray(list.getSubList(0, 3))), "GetSubString method");
        
        //Test 22-24 size
        test(3 ,list.size() , "Size method");
        list.add("U");
        test(4,list.size() , "Size method");
        test(0,l.size() , "Size method");

        //Test 25-27 Indexof(obj)
        test(0 ,list.indexOf("A") , "Indexof(obj) method");
        test(1 ,list.indexOf("B") , "Indexof(obj) method");
        test(3 ,list.indexOf("U") , "Indexof(obj) method");
        
        //Test 28-30 indexof(position)
        test(0 ,list.indexOf("A", 0), "Indexof(pos) method");
        test(-1 ,list.indexOf("D", 0) , "Indexof(pos) method");
        test(3 ,list.indexOf("U", 3) , "Indexof(pos) method");
        
        //Test 31-33 make empty
        list.makeEmpty();
        test("[]" ,Arrays.toString(toArray(list)), "Make empty method");
        l.makeEmpty();
        test("[]" ,Arrays.toString(toArray(l)), "Make empty method");
        list.add("A");
        list.makeEmpty();
        test("[]" ,Arrays.toString(toArray(list)), "Make empty method");
        
        //Test 34-42 iterator has next and next and remove
        list.add("A");
        list.add("B");
        list.add("A");
        Iterator<String> it = list.iterator();
        test(true ,it.hasNext() , "Iterator hasnext method");
        test("A",it.next() , "Iterator next method");
        it.remove();
        test("[B, A]",Arrays.toString(toArray(list)),"Iterator remove method");
        test(true ,it.hasNext() , "Iterator hasnext method");
        test("B",it.next() , "Iterator next method");
        it.remove();
        test("[A]",Arrays.toString(toArray(list)),"Iterator remove method");
        test(true ,it.hasNext() , "Iterator hasnext method");
        test("A",it.next() , "Iterator next method");
        it.remove();
        test("[]",Arrays.toString(toArray(list)),"Iterator remove method");
        
        //Test 43-45 remove range
        list = new LinkedList<>();
        list.add("A");
        list.add("B");
        list.add("A");
        list.add("D");
        list.add("G");
        list.add("H");
        list.add("T");
        list.add("A");
        list.removeRange(1, 1);
        test("[A, B, A, D, G, H, T, A]" ,Arrays.toString(toArray(list)), "RemoveRange method");
        list.removeRange(0, 2);
        test("[A, D, G, H, T, A]" ,Arrays.toString(toArray(list)), "RemoveRange method");
        list.removeRange(0, 1);
        test("[D, G, H, T, A]" ,Arrays.toString(toArray(list)), "RemoveRange method");
        
        //Test 46-48 toString
        test("[D, G, H, T, A]" ,list.toString(), "ToString method");
        list.add("Y");
        test("[D, G, H, T, A, Y]" ,list.toString(), "ToString method");
        list.add("T");
        test("[D, G, H, T, A, Y, T]" ,list.toString(), "ToString method");
        
        //Test 49-51 equals
        test(false ,list.equals(l), "Equals method");
        l=list;
        test(true ,list.equals(l), "Equals method");
        test(true ,l.equals(list), "Equals method");
        
        //Test 52-54 addfirst
        list.addFirst("P");
        test("[P, D, G, H, T, A, Y, T]" ,list.toString(), "Addfirst method");
        list.addFirst("A");
        test("[A, P, D, G, H, T, A, Y, T]" ,list.toString(), "Addfirst method");
        list.addFirst("H");
        test("[H, A, P, D, G, H, T, A, Y, T]" ,list.toString(), "Addfirst method");
        
        //Test 55-57 addlast
        list.addLast("I");
        test("[H, A, P, D, G, H, T, A, Y, T, I]" ,list.toString(), "Addlast method");
        list.addLast("I");
        test("[H, A, P, D, G, H, T, A, Y, T, I, I]" ,list.toString(), "Addlast method");
        list.addLast("O");
        test("[H, A, P, D, G, H, T, A, Y, T, I, I, O]" ,list.toString(), "Addlast method");
        
        //Test 58-60 removefirst
        list.removeFirst();
        test("[A, P, D, G, H, T, A, Y, T, I, I, O]" ,list.toString(), "Removefirst method");
        list.removeFirst();
        test("[P, D, G, H, T, A, Y, T, I, I, O]" ,list.toString(), "Removefirst method");
        list.removeFirst();
        test("[D, G, H, T, A, Y, T, I, I, O]" ,list.toString(), "Removefirst method");
        
        //Test 61-63 removelast
        list.removeLast();
        test("[D, G, H, T, A, Y, T, I, I]" ,list.toString(), "Removelast method");
        list.removeLast();
        test("[D, G, H, T, A, Y, T, I]" ,list.toString(), "Removelast method");
        list.removeLast();
        test("[D, G, H, T, A, Y, T]" ,list.toString(), "Removelast method");
        
        comparison();
      //CS314 students. Add your tests here:
        /*Num Repeats: 100
        Adding at end: ArrayList
        N = 30000, total time:  0.0396
        N = 60000, total time:  0.0726
        N = 120000, total time:  0.1492
        N = 240000, total time:  0.2743
        N = 480000, total time:  0.6156
        O(1)
        
        Num Repeats: 100
        Adding at end: LinkedList
        N = 30000, total time:  0.0359
        N = 60000, total time:  0.0664
        N = 120000, total time:  0.1819
        N = 240000, total time:  0.2677
        N = 480000, total time:  0.5944
		O(1)
		The Arraylist is a little bit faster 
		
		
        Num Repeats: 100
        Adding at front: ArrayList
        N = 2000, total time:  0.0200
        N = 4000, total time:  0.0664
        N = 8000, total time:  0.2547
        N = 16000, total time:  1.1884
        N = 32000, total time:  4.9181
        O(N)

        Num Repeats: 100
        Adding at front: LinkedList
        N = 10000, total time:  0.0135
        N = 20000, total time:  0.0272
        N = 40000, total time:  0.0502
        N = 80000, total time:  0.1309
        N = 160000, total time:  0.1973
        O(1)
 		LinkedList faster.

        Num Repeats: 100
        Removing from front: ArrayList
        N = 2000, total time:  0.0177
        N = 4000, total time:  0.0612
        N = 8000, total time:  0.2327
        N = 16000, total time:  1.0906
        N = 32000, total time:  4.6560
        O(N)
        
        Num Repeats: 100
        removing from front: LinkedList
        N = 5000, total time:  0.0099
        N = 10000, total time:  0.0236
        N = 20000, total time:  0.0555
        N = 40000, total time:  0.1121
        N = 80000, total time:  0.2217
        O(1)
        The LinkedList faster.


        Num Repeats: 100
        Getting random: ArrayList
        N = 10000, total time:  0.0212
        N = 20000, total time:  0.0464
        N = 40000, total time:  0.1093
        N = 80000, total time:  0.2541
        N = 160000, total time:  0.6389
        O(1)

        Num Repeats: 100
        Getting random: LinkedList
        N = 1000, total time:  0.0902
        N = 2000, total time:  0.3663
        N = 4000, total time:  1.4957
        N = 8000, total time:  5.9959
        N = 16000, total time: 24.3778
        O(N)
        The ArrayList faster.


        Num Repeats: 100
        Getting all using iterator: ArrayList
        N = 50000, total time:  0.0147
        N = 100000, total time:  0.0248
        N = 200000, total time:  0.0464
        N = 400000, total time:  0.0927
        N = 800000, total time:  0.1732
		O(1)

        Num Repeats: 100
        Getting all using iterator: LinkedList
        N = 50000, total time:  0.0248
        N = 100000, total time:  0.0421
        N = 200000, total time:  0.0870
        N = 400000, total time:  0.1664
        N = 800000, total time:  0.3380
		O(1)
		About the same, ArrayList a little bit faster


        Num Repeats: 100
        Getting all using get method: ArrayList
        N = 100000, total time:  0.0155
        N = 200000, total time:  0.0303
        N = 400000, total time:  0.0606
        N = 800000, total time:  0.1069
        N = 1600000, total time:  0.2143
        O(1)
        
        Num Repeats: 100
		Getting all using get method: LinkedList
		N = 1000, total time:  0.0842
		N = 2000, total time:  0.3645
		N = 4000, total time:  1.4962
		N = 8000, total time:  6.0698
		N = 16000, total time: 24.9738
		O(N)
		ArrayList faster
		
		
		*/
        // CS314 Students:
        // uncomment the following line to run tests comparing
        // your LinkedList class to the java ArrayList class.
        
    }
    private static int testNum = 0;
    private static <E> void test(E exp, E act, String name){
    	testNum++;
    	if(exp.equals(act)){
    		System.out.println("Pass the test " + testNum+ " "+ name);
    	}else{
    		System.out.println("Fail the test " + testNum+ " "+ name);
    	}
    	System.out.print("  Expected: " + exp + "  Actual: " + act);
    	System.out.println();
    }

    private static Object[] toArray(IList<String> list) {
        Object[] result = new Object[list.size()];
        Iterator<String> it = list.iterator();
        int index = 0;
        while( it.hasNext() ){
            result[index] = it.next();
            index++;
        }
        return result;
    }

    private static Object[] toArray(LinkedList<String> list) {
        Object[] result = new Object[list.size()];
        Iterator<String> it = list.iterator();
        int index = 0;
        while( it.hasNext() ){
            result[index] = it.next();
            index++;
        }
        return result;
    }

    //pre: none
    private static boolean arraysSame(Object[] one, Object[] two)  {
        boolean same;
        if( one == null || two == null ) {
            same = (one == two);
        }
        else {
            //neither one or two are null
            assert one != null && two != null;
            same = one.length == two.length;
            if( same ) {
                int index = 0;
                while( index < one.length && same ) {
                    same = ( one[index].equals(two[index]) );
                    index++;
                }
            }
        }
        return same;
    }
   
    
    public static final int NUM_DOUBLINGS_OF_N = 5;
    private static final int NUM_REPEATS_OF_TEST = 100;

    // A method to be run to compare the LinkedList you are completing and the Java ArrayList class
    public static void comparison(){
        Stopwatch s = new Stopwatch();
        
        int initialN = 30000;
        addEndArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        addEndLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 2000;
        addFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 10000;
        addFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 2000;
        removeFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 5000;
        removeFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 10000;
        getRandomArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 1000;
        getRandomLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 50000;
        getAllArrayListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);
        getAllLinkedListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);

        initialN = 100000;
        getAllArrayListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);
        initialN = 1000;
        getAllLinkedListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);

    }

    // These pairs of method illustarte a failure to use polymorphism
    // If the students had implemented the Java list interface there
    // could be a single method. Also we could use function objects to
    // reduce the awful repitition of code.
    public static void addEndArrayList(Stopwatch s, int initialN, int numTests){

        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for(int i = 0; i < numTests; i++){
                ArrayList<Integer> javaList = new ArrayList<Integer>();
                s.start();
                for(int j = 0; j < n; j++)
                    javaList.add(j);
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at end: ArrayList", totalTimes, initialN);
    }

    private static void showResults(String title, double[] times, int initialN) {
        System.out.println();
        System.out.println("Num Repeats: " + NUM_REPEATS_OF_TEST);
        System.out.println(title);
        for(double time : times) {
            System.out.print("N = " + initialN + ", total time: ");
            System.out.printf("%7.4f\n", time);
            
            initialN *= 2;
        }
        System.out.println();
    }

    public static void addEndLinkedList(Stopwatch s, int initialN, int numTests){
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for(int i = 0; i < numTests; i++){
                LinkedList<Integer> studentList = new LinkedList<Integer>();
                s.start();
                for(int j = 0; j < n; j++)
                    studentList.add(j);
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at end: LinkedList", totalTimes, initialN);
    }

    public static void addFrontArrayList(Stopwatch s, int initialN, int numTests){

        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for(int i = 0; i < numTests; i++){
                ArrayList<Integer> javaList = new ArrayList<Integer>();
                s.start();
                for(int j = 0; j < n; j++)
                    javaList.add(0, j);
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at front: ArrayList", totalTimes, initialN);
    }

    public static void addFrontLinkedList(Stopwatch s, int initialN, int numTests){
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for(int i = 0; i < numTests; i++){
                LinkedList<Integer> studentList = new LinkedList<Integer>();
                s.start();
                for(int j = 0; j < n; j++)
                    studentList.insert(0, j);
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Adding at front: LinkedList", totalTimes, initialN);
    }

    public static void removeFrontArrayList(Stopwatch s, int initialN, int numTests){     
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for(int i = 0; i < numTests; i++){
                ArrayList<String> javaList = new ArrayList<String>();
                for(int j = 0; j < n; j++)
                    javaList.add(j + "");
                s.start();
                while(!javaList.isEmpty())
                    javaList.remove(0);
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Removing from front: ArrayList", totalTimes, initialN);
    }

    public static void removeFrontLinkedList(Stopwatch s, int initialN, int numTests){
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;    
            for(int i = 0; i < numTests; i++){
                LinkedList<String> studentList = new LinkedList<String>();
                for(int j = 0; j < n; j++)
                    studentList.add(j + "");
                s.start();
                while( studentList.size() != 0 )
                    studentList.removeFirst();
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("removing from front: LinkedList", totalTimes, initialN);
    }

    public static void getRandomArrayList(Stopwatch s, int initialN, int numTests){
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            int total = 0;
            Random r = new Random();
            for(int i = 0; i < numTests; i++){
                ArrayList<Integer> javaList = new ArrayList<Integer>();
                for(int j = 0; j < n; j++)
                    javaList.add(j);
                s.start();
                for(int j = 0; j < n; j++){
                    total += javaList.get( r.nextInt(javaList.size()) );
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting random: ArrayList", totalTimes, initialN);
    }

    public static void getRandomLinkedList(Stopwatch s, int initialN, int numTests){
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            int total = 0;
            Random r = new Random();
            for(int i = 0; i < numTests; i++){
                LinkedList<Integer> studentList = new LinkedList<Integer>();
                for(int j = 0; j < n; j++)
                    studentList.add(j);
                s.start();
                for(int j = 0; j < n; j++){
                    total += studentList.get( r.nextInt(studentList.size()) );
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting random: LinkedList", totalTimes, initialN);
    }

    public static void getAllArrayListUsingIterator(Stopwatch s, int initialN, int numTests){

        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            int total = 0;
            for(int i = 0; i < numTests; i++){
                ArrayList<Integer> javaList = new ArrayList<Integer>();
                for(int j = 0; j < n; j++)
                    javaList.add(j);
                Iterator<Integer> it = javaList.iterator();
                s.start();
                while( it.hasNext() ){
                    total += it.next();
                }        
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using iterator: ArrayList", totalTimes, initialN);
    }

    public static void getAllLinkedListUsingIterator(Stopwatch s, int initialN, int numTests){
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            int total = 0;
            for(int i = 0; i < numTests; i++){
                LinkedList<Integer> studentList = new LinkedList<Integer>();
                for(int j = 0; j < n; j++)
                    studentList.add(j);
                Iterator<Integer> it = studentList.iterator();
                s.start();
                while(it.hasNext()){
                    total += it.next();
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using iterator: LinkedList", totalTimes, initialN);
    }

    public static void getAllArrayListUsingGetMethod(Stopwatch s, int initialN, int numTests){
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for(int i = 0; i < numTests; i++){
                ArrayList<Integer> javaList = new ArrayList<Integer>();
                for(int j = 0; j < n; j++)
                    javaList.add(j);

                s.start();
                int x = 0;
                for(int j = 0; j < javaList.size(); j++){
                    x += javaList.get(j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using get method: ArrayList", totalTimes, initialN);
    }

    public static void getAllLinkedListUsingGetMethod(Stopwatch s, int initialN, int numTests){
        double[] totalTimes = new double[numTests];        
        for(int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
            int n = initialN;
            for(int i = 0; i < numTests; i++){
                LinkedList<Integer> studentList = new LinkedList<Integer>();
                for(int j = 0; j < n; j++)
                    studentList.add(j);

                s.start();
                int x = 0;
                for(int j = 0; j < studentList.size(); j++){
                    x += studentList.get(j);
                }
                s.stop();
                totalTimes[i] += s.time();
                n *= 2;
            }
        }
        showResults("Getting all using get method: LinkedList", totalTimes, initialN);
    }

    // for future changes
    private static interface ArrayListOp {
        public <E> void op(ArrayList<E> list, E data);
    }
    
    private ArrayListOp addAL = new ArrayListOp() {
        public <E> void op(ArrayList<E> list, E data) {
            list.add(data);
        }
    };
    
    private ArrayListOp addFrontAL = new ArrayListOp() {
        public <E> void op(ArrayList<E> list, E data) {
            list.add(0, data);
        }
    };
    
    private ArrayListOp removeFrontAL = new ArrayListOp() {
        public <E> void op(ArrayList<E> list, E data) {
            list.remove(0);
        }
    };

    private static interface LinkedListOp<E> {
        public void op(LinkedList<E> list);
    }
}
