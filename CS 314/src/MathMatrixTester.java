import java.util.Random;

/*  Student information for assignment:
 *
 *  UTEID:px353
 *  email address:xiapengdi@yahoo.com
 *  Grader name:51925 
 *  Unique section number:Gilbert Maldonado
 *  Number of slip days I am using:0
 */



/* CS314 Students. Put your experiment results and
* answers to questions here.
* * Experiment: 
* 1000*1000 matrix took average 0.001810372175 seconds to calculate addition
* 2000*2000 matrix took average 0.006447449544 seconds to calculate addition
* 4000*4000 matrix took average 0.024735386783 seconds to calculate addition 
* 200*200 matrix took average 0.00923098165 seconds to calculate multiplication
* 400*400 matrix took average 0.10767832374 second to calculate multiplication
* 800*800 matrix took average 1.25270358593 second to calculate multiplication
* 
* questions: 
* 1. It would take about 0.08 seconds if size was doubled again for addition
* 2. It is O(N), T= 4N so time increase by about 4 times.
* 3. It to take about  16 seconds to calculate if size was doubled again for multiplication
* 4. It is O(N^2), T=4N , time increase approximately by 16 times
* 5. Probably when size bigger than 15000*15000
* because the default heap size is for java 8 is approximately 1GB = 1,000,000,000 bytes and 15000*15000*4 is 900,000,000 bytes
*/


/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester {

    /**
     * main method that runs simple test on the MathMatrix class
     *
     * @param args not used
     */
    public static void main(String[] args) {
    	
    	// CS314 Students. When ready delete the above tests 
        // and add your 24 tests here.
    	int[][] data1 = { {1, 2, -1},
                       {2, 3, 4}};
        int[][] data2 = { {2, 1, 1},
                       {2, 3, 1}};
        int[][] e1;

        //test 1-2 change element 
        MathMatrix mat1 = new MathMatrix(2, 2, -1);
        int expected = 4 ;
        mat1.changeElement(0, 0, 4);
        if( mat1.getVal(0, 0)== expected)
        	System.out.println("Test number 1 tests the changeElement method, Test 1 passed");
        else
        	System.out.println("Test 1 failed");
        //************************************************
        expected = 9;
        mat1.changeElement(1, 1, 9);
        if(mat1.getVal(1, 1)== expected)
        	System.out.println("Test number 2 tests the changeElement method, Test 2 passed");
        else
        	System.out.println("Test 2 failed");
       
        //tests 3-4, number of rows
        expected = 5;
        mat1 = new MathMatrix(5, 2, 10);
        if(mat1.numRows() == expected)
        	System.out.println("Test number 3 tests the numRows method, Test 3 passed");
        else
        	System.out.println("Test 3 failed");
        //*************************************************
        expected = 1;
        mat1 = new MathMatrix(1, 5, 10);
        if(mat1.numRows() == expected)
        	System.out.println("Test number 4 tests the numRows method, Test 4 passed");
        else
        	System.out.println("Test 4 failed");
        
        // test 5-6 number of columns
        expected = 3;
        mat1 = new MathMatrix(data1);
        if(mat1.numCols() == expected)
        	System.out.println("Test number 5 tests the numCols method, Test 5 passed");
        else
        	System.out.println("Test 5 failed");
        //*************************************************
        expected = 3;
        mat1 = new MathMatrix(data2);
        if(mat1.numCols() == expected)
        	System.out.println("Test number 6 tests the numCols method, Test 6 passed");
        else
        	System.out.println("Test 6 failed");
        
        //test 7-8 get value
        expected = 1;
        mat1 = new MathMatrix(data1);
        if(mat1.getVal(0,0) == expected)
        	System.out.println("Test number 7 tests the getVal method, Test 7 passed");
        else
        	System.out.println("Test 7 failed");
        //*************************************************
        expected = 4;
        mat1 = new MathMatrix(data1);
        if(mat1.getVal(1,2) == expected)
        	System.out.println("Test number 8 tests the getVal method, Test 8 passed");
        else
        	System.out.println("Test 8 failed");
        
        //tests 9-10 addition
        data1[0][0] = 0;
        mat1 = new MathMatrix(data1);
        MathMatrix mat2 = new MathMatrix(data2);
        MathMatrix mat3 = mat1.add(mat2);
        e1 = new int[][] { {2, 3, 0}, {4, 6, 5} };
        printTestResult( get2DArray(mat3), e1, 9, "addition method");
        //*************************************************
        mat3 = mat1.add(mat3);
        e1 = new int[][] { {2, 5, -1}, {6, 9, 9} };
        printTestResult( get2DArray(mat3), e1, 10, "addition method");
        
        //Test 11-12 subtraction 
        e1 = new int[][] {{5,5},{5,5}};
        mat1 = new MathMatrix(2, 2, 15);
        mat2 = new MathMatrix(2, 2, 10);
        mat3 = mat1.subtract(mat2);
        printTestResult(get2DArray(mat3), e1, 11, "subtraction method");        
        //**************************************************
        e1 = new int[][] {{-2,1,-2},{0,0,3}};        
        mat1 = new MathMatrix(data1);
        mat2 = new MathMatrix(data2);
        mat3 = mat1.subtract(mat2);
        printTestResult(get2DArray(mat3), e1, 12, "subtraction method");
        
        //test 13-14 multiplication
        data2 = new int[][] { {1, 2}, {0, 1} };
        mat2 = new MathMatrix(data2);
        mat1 = new MathMatrix(data1);
        mat3 = mat2.multiply(mat1);
        e1 = new int[][] { {4, 8, 7}, {2, 3, 4}};
        printTestResult( get2DArray(mat3), e1, 13, "multiply method");
        //*************************************************
        data2 = new int[][] { {1, 2}, {3, 1}, {2, 1} };
        mat2 = new MathMatrix(data2);
        mat1 = new MathMatrix(data1);
        mat3 = mat2.multiply(mat1);
        e1 = new int[][] { {4, 8, 7}, {2, 9, 1}, {2, 7, 2} };
        printTestResult( get2DArray(mat3), e1, 14, "multiply method");
        
        //test 15-16 scale
        mat1 = new MathMatrix(data1);
		int scaleValue = 3;
		e1 = new int[][] { { 0, 6, -3 }, { 6, 9, 12 } };
		mat1.scale(scaleValue);
		printTestResult(get2DArray(mat1), e1, 15, "scale method");
		// **************************************************
		mat1 = new MathMatrix(data2);
		scaleValue = -2;
		e1 = new int[][] { { -2, -4}, { -6, -2 }, { -4, -2 } };
		mat1.scale(scaleValue);
		printTestResult(get2DArray(mat1), e1, 16, "scale method");
        
        //test 17-18 transpose
		mat1 = new MathMatrix(data1);
		e1 = new int[][] { { 0, 2 }, { 2, 3 }, { -1, 4 } };
		mat2 = mat1.getTranspose();
		printTestResult(get2DArray(mat2), e1, 17, "getTranspose method");
		//*****************************************************
		mat1 = new MathMatrix(data2);
		e1 = new int[][] { { 1, 3, 2 }, { 2, 1, 1 } };
		mat2 = mat1.getTranspose();
		printTestResult(get2DArray(mat2), e1, 18, "getTranspose method");
        
        //test 19-20, toString()
        data1 = new int[][] {{15, 2, 10, -1000},
                          {1000, 10, 55, 4},
                          {1, -1, 4, 0}};
        mat1 = new MathMatrix(data1);
        String expected2 = "|    15     2    10 -1000|\n|  1000    10    55     4|\n|     1    -1     4     0|\n";
        if( mat1.toString().equals( expected2 ) )
            System.out.println("Test number 19 tests the toString method, Test 19 passed.");
        else
            System.out.println("Test number 19 tests the toString method, Test 19 failed.");     
        //***********************************************************
        data1 = new int[][] {{2}};
        mat1 = new MathMatrix(data1);
        expected2 = "| 2|\n";
        if( mat1.toString().equals( expected2 ) )
        	System.out.println("Test number 20 tests the toString method, Test 20 passed.");
        else
        	System.out.println("Test number 20 tests the toString method, Test 20 failed.");
        
        //21-22 equals 
        int[][] mat4 = new int[][] {{10, 100, 101, 2000},
        					{1000, 10, 55, 4},
            				{1, -1, 4, 0}};
        mat1 = new MathMatrix(mat4);
        mat2 = new MathMatrix(mat4);
        boolean results = true;
        if( mat1.equals(mat2) == results )
    	   System.out.println("Test number 21 tests the equals method, Test 22 passed");
        else
    	   System.out.println("Test 21 failed");
        //************************************************************
        mat1 = new MathMatrix(data1);
        mat2 = new MathMatrix(data2);
        results = false;
        if( mat1.equals(mat2) == results )
    	   System.out.println("Test number 22 tests the equals method, Test 21 passed");
        else
    	   System.out.println("Test 22 failed");
        
        
        //test 23-24 upperTriangular
        data1 = new int[][] {{1, 2, 3, 0}, {0, 0, 2, 3}, {0, 0, 4, -1}, {0, 0, 0, 12}};
        mat1 = new MathMatrix(data1);
        if( mat1.isUpperTriangular())
            System.out.println("Test number 23 tests the upperTriangular, Test 23 passed.");
        else
            System.out.println("Test number 23 tests the upperTriangular, Test 23 failed.");
        //**************************************************************
        data1 = new int[][] {{1, 2, 3, 0}, {0, 3, 2, 3}, {0, 0, 0, -1}, {1, 2, 3, 4}};
        mat1 = new MathMatrix(data1);
        if( !mat1.isUpperTriangular())
            System.out.println("Test number 24 tests the upperTriangular, Test 24 passed.");
        else
            System.out.println("Test number 24 tests the upperTriangular, Test 24 failed.");   
        
        //experiment
        //addition
        /*Random rand = new Random();
        Stopwatch st = new Stopwatch();
        MathMatrix mat5 = new MathMatrix(1000,1000,rand.nextInt(200));
        MathMatrix mat6 = new MathMatrix(1000,1000,rand.nextInt(200));
        MathMatrix mat7;
        /*st.start();	
        for(int i = 1; i <= 1000; i++){
        	mat7 = mat5.add(mat6);	
        }
        st.stop();
        System.out.println(st.time());
        System.out.println(st.time()/1000);
        //double
        mat5 = new MathMatrix(2000,2000,rand.nextInt(200));
        mat6 = new MathMatrix(2000,2000,rand.nextInt(200));
        for(int i = 1; i <= 1000; i++){
        	mat7 = mat5.add(mat6);	
        }
        st.stop();
        System.out.println(st.time());
        System.out.println(st.time()/1000);
        // 4N
        mat5 = new MathMatrix(4000,4000,rand.nextInt(200));
        mat6 = new MathMatrix(4000,4000,rand.nextInt(200));
        for(int i = 1; i <= 1000; i++){
        	mat7 = mat5.add(mat6);	
        }
        st.stop();
        System.out.println(st.time());
        System.out.println(st.time()/1000);*/
        // multiply N
       // mat5 = new MathMatrix(800,800,rand.nextInt(200));
        //mat6 = new MathMatrix(800,800,rand.nextInt(200));
        //st.start();
        //for(int i = 1; i <= 100; i++){
        //	mat7 = mat5.multiply(mat6);	
       // }
       // st.stop();
        //System.out.println(st.time());
       // System.out.println(st.time()/100);
        //2N
        /*mat5 = new MathMatrix(200,200,rand.nextInt(200));
        mat6 = new MathMatrix(200,200,rand.nextInt(200));
        st.start();
        for(int i = 1; i <= 100; i++){
        	mat7 = mat5.multiply(mat6);	
        }
        st.stop();
        System.out.println(st.time());
        System.out.println(st.time()/100);
        //4N
        mat5 = new MathMatrix(400,400,rand.nextInt(200));
        mat6 = new MathMatrix(400,400,rand.nextInt(200));
        for(int i = 1; i <= 100; i++){
        	mat7 = mat5.multiply(mat6);	
        }
        st.stop();
        System.out.println(st.time());
        System.out.println(st.time()/100);*/
        
    }
    
    // method that sums elements of mat, may overflow int!
    // pre: mat != null
    private static int sumVals(MathMatrix mat) {
        if(mat == null)
            throw new IllegalArgumentException("mat may not be null");
        
        int result = 0;
        final int ROWS =  mat.numRows();
        final int COLS = mat.numCols();
        for(int r = 0; r < ROWS; r++)
            for(int c = 0; c < COLS; c++) 
                result += mat.getVal(r, c); // likely to overflow, but can still do simple check
        return result;
    }
    
    // create a matrix with random values
    // pre: rows > 0, cols > 0, randNumGen != null
    private static MathMatrix createMat(Random randNumGen, int rows,
            int cols, final int LIMIT) {
        
        if(randNumGen == null)
            throw new IllegalArgumentException("randomNumGen variable may no be null");
        else if(rows <= 0 || cols <= 0)
            throw new IllegalArgumentException("rows and columns must be greater than 0. " +
            		"rows: " + rows + ", cols: " + cols);
        
        int[][] temp = new int[rows][cols];
        final int SUB = LIMIT / 4;
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++)
                temp[r][c] = randNumGen.nextInt(LIMIT) - SUB;
        
        return new MathMatrix(temp);
    }

    private static void printTestResult(int[][] data1, int[][] data2, int testNum, String testingWhat) {
        System.out.print( "Test number " + testNum + " tests the " + testingWhat +". The test ");
        String result = equals(data1, data2) ? "passed" : "failed";
        System.out.println( result );
    }

    // pre: m != null, m is at least 1 by 1 in size
    private static int[][] get2DArray(MathMatrix m) {
        //check precondition
        assert ( m != null ) && ( m.numRows() > 0 ) && ( m.numCols()> 0 )
                : "Violation of precondition: get2DArray";

        int[][] result = new int[m.numRows()][m.numCols()];
        for(int r = 0; r < result.length; r++)
        {   for(int c = 0; c < result[0].length; c++)
            {   result[r][c] = m.getVal(r,c);
            }
        }
        return result;
    }

    // pre: data1 != null, data2 != null, data1 and data2 are at least 1 by 1 matrices
    //      data1 and data2 are rectangular matrices
    // post: return true if data1 and data2 are the same size and all elements are
    //      the same
    private static boolean equals(int[][] data1, int[][] data2) {
       //check precondition
        if( ( data1 == null ) || ( data1.length == 0 )
               || ( data1[0].length == 0 ) || !rectangularMatrix(data1)
               ||  ( data2 == null ) || ( data2.length == 0 )
               || ( data2[0].length == 0 ) || !rectangularMatrix(data2))
                throw new IllegalArgumentException( "Violation of precondition: equals check on 2d arrays of ints");

        boolean result = (data1.length == data2.length) && (data1[0].length == data2[0].length);
        int row = 0;
        while( result && row < data1.length ) {
            int col = 0;
            while( result && col < data1[0].length ) {
               result = (data1[row][col] == data2[row][col]);
                col++;
            }
            row++;
        }

        return result;
    }


    // method to ensure mat is rectangular
    // pre: mat != null, mat is at least 1 by 1
    private static boolean rectangularMatrix( int[][] mat ) {
        if(mat == null || mat.length == 0 || mat[0].length == 0)
            throw new IllegalArgumentException("Violation of precondition: "
                    + " Parameter mat may not be null" 
                    + " and must be at least 1 by 1");
        return MathMatrix.rectangularMatrix(mat);
    }
}

