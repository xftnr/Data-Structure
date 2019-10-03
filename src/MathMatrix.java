//  MathMatrix.java - CS314 Assignment 2

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


/**
 * A class that models systems of linear equations (Math Matrices)
 * as used in linear algebra.
 *
 * @version Skeleton file for students
 */
public class MathMatrix
{
    // instance variables
    /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    private int[][] matrix;

    
    /**
     * create a MathMatrix with cells equal to the values in mat.
     * A "deep" copy of mat is made.
     * Changes to mat after this constructor do not affect this
     * Matrix and changes to this MathMatrix do not affect mat
     * @param  mat  mat !=null, mat.length > 0, mat[0].length > 0,
     * mat is a rectangular matrix
     */
    public MathMatrix(int[][] mat) {
        // check the precondition. rectangularMatrix is a private method at end of Matrix class
        if((mat == null) || (mat.length == 0) || (mat[0].length == 0)
                || !rectangularMatrix(mat)) 
            throw new IllegalArgumentException("Violation of precondition: " +
            		"int[][] Matrix constructor");
                
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
        //build the m*n matrix
        matrix = new int[mat.length][mat[0].length];
        //copy the mat
        for(int row=0; row< mat.length; row++){
        	for(int column=0; column<mat[0].length; column++){
        		matrix[row][column]= mat[row][column];
        	}
        }
    }


    /**
     * create a MathMatrix of the specified size with all cells set to the intialValue.
     * <br>pre: numRows > 0, numCols > 0
     * <br>post: create a matrix with numRows rows and numCols columns. 
     * All elements of this matrix equal initialVal.
     * In other words after this method has been called getVal(r,c) = initialVal 
     * for all valid r and c.
     * @param numRows numRows > 0
     * @param numCols numCols > 0
     * @param initialVal all cells of this Matrix are set to initialVal
     */
    public MathMatrix(int numRows, int numCols, int initialVal) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	//build the array
    	matrix = new int[numRows][numCols];
    	// initialize
    	for (int row=0; row <numRows; row++){
    		for (int col=0; col<numCols; col++){
    			matrix[row][col]=initialVal;
    		}
    	}
    }


    /**
     * change the value of one of the cells in this MathMatrix.
     * <br>pre: 0 <= row < numRows(), 0 <= col < numCols()
     * <br>post: getVal(row, col) = newValue
     * @param row 0 <= row < numRows()
     * @param col 0 <= col < numCols()
     */
    public void changeElement(int row, int col, int newValue) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	// throw the exception
    if(!(0 <= row|| row < numRows()|| 0 <= col|| col < numCols())){
    	throw new IllegalArgumentException("Violation of precondition: " +
        		"method changeElement");
    }
    // change to new value
    matrix[row][col]=newValue;
    }


    /**
     * Get the number of rows.
     * @return the number of rows in this MathMatrix
     */
    public int numRows() {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	// return rows
        return matrix.length; // alter as necessary
    }


    /**
     * Get the number of columns.
     * @return the number of columns in this MathMatrix
     */
    public int numCols() {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	//return columns
        return matrix[0].length; // alter as necessary
    }


    /**
     * get the value of a cell in this MathMatrix.
     * <br>pre: row  0 <= row < numRows(), col  0 <= col < numCols()
     * @param  row  0 <= row < numRows()
     * @param  col  0 <= col < numCols()
     * @return the value at the specified position
     */
    public int getVal(int row, int col) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	// throw the exception
    	if(!(0 <= row|| row < numRows()|| 0 <= col|| col < numCols())){
        	throw new IllegalArgumentException("Violation of precondition: " +
            		"method getVal");
    	}
    	// get the value
        return matrix[row][col]; // alter as necessary
    }


   /**
    * implements MathMatrix addition, (this MathMatrix) + rightHandSide.
    * <br>pre: rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()
    * <br>post: This method does not alter the calling object or rightHandSide
    * @param rightHandSide rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()
    * @return a new MathMatrix that is the result of adding this Matrix to rightHandSide.
    * The number of rows in the returned Matrix is equal to the number of rows in this MathMatrix.
    * The number of columns in the returned Matrix is equal to the number of columns in this MathMatrix.
    */
    public MathMatrix add(MathMatrix rightHandSide) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	// build new matrix which type: MathMatrix
    	MathMatrix result = new MathMatrix(numRows(),numCols(),0);
    	// add to matrix
    	for (int row=0; row <numRows(); row++){
    		for (int col=0; col<numCols(); col++){
    			result.changeElement(row, col, matrix[row][col] + rightHandSide.getVal(row, col));
    		}
    	}
        return result; // alter as necessary
    }


   /**
    * implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
    * <br>pre: rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()
    * <br>post: This method does not alter the calling object or rightHandSide
    * @param rightHandSide rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()
    * @return a new MathMatrix that is the result of subtracting rightHandSide from this MathMatrix.
    * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
    * The number of columns in the returned MathMatrix is equal to the number of columns in this MathMatrix.
    */
    public MathMatrix subtract(MathMatrix rightHandSide) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	// build new matrix which type: MathMatrix
    	MathMatrix result = new MathMatrix(numRows(),numCols(),0);
    	// subtract to matrix
    	for (int row=0; row <numRows(); row++){
    		for (int col=0; col<numCols(); col++){
    			result.changeElement(row, col, matrix[row][col] - rightHandSide.getVal(row, col));
    		}
    	}
        return result; // alter as necessary
    }


   /**
    * implements matrix multiplication, (this MathMatrix) * rightHandSide.
    * <br>pre: rightHandSide.numRows() = numCols()
    * <br>post: This method should not alter the calling object or rightHandSide
    * @param rightHandSide rightHandSide.numRows() = numCols()
    * @return a new MathMatrix that is the result of multiplying this MathMatrix and rightHandSide.
    * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
    * The number of columns in the returned MathMatrix is equal to the number of columns in rightHandSide.
    */
    public MathMatrix multiply(MathMatrix rightHandSide) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	int value=0;
    	// build new matrix which type: MathMatrix careful about the side of result
    	MathMatrix result = new MathMatrix(numRows(), rightHandSide.numCols(),0);
    	// multiply the matrix
    	for (int row=0; row <numRows(); row++){
    		for (int col=0; col<rightHandSide.numCols(); col++){
    			// get the change value
    			for (int i=0; i< rightHandSide.numRows();i++){
    	    		value += matrix[row][i]*rightHandSide.getVal(i, col);
    	    	}
    			// change the result value 
    			result.changeElement(row, col, value);
    			value=0;
    		}
    	}
        return result; // alter as necessary
    } 


   /**
    * Multiply all elements of this MathMatrix by factor.
    * <br>pre: none
    * <br>post: all elements in this matrix have been multiplied by factor. 
    * In other words after this method has been called getVal(r,c) = old getVal(r, c) * factor
    * for all valid r and c.
    * @param factor the value to multipy every cell in this Matrix by.
    */
    public void scale(int factor) {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	//matrix multiply the factor
    	for (int row=0; row <numRows(); row++){
    		for (int col=0; col<numCols(); col++){
    			matrix[row][col] *=factor;
    		}
    	}
    }


    /**
     * accessor: get a transpose of this MathMatrix. 
     * This Matrix is not changed.
     * <br>pre: none
     * @return a transpose of this MathMatrix
     */
    public MathMatrix getTranspose() {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	// build new matrix which type: MathMatrix rows become columns, columns become rows
    	MathMatrix result = new MathMatrix(numCols(),numRows(),0);
    	// transpose the matrix
    	for (int row=0; row <numCols(); row++){
    		for (int col=0; col<numRows(); col++){
    			result.changeElement(row, col, matrix[col][row]);
    		}
    	}
        return result; // alter as necessary
    }


    /**
     * override equals.
     * @return true if rightHandSide is the same size as this MathMatrix and all values in the
     * two MathMatrix objects are the same, false otherwise
     */
    public boolean equals(Object rightHandSide) {
        /* CS314 Students. The following is standard equals
         * method code. We will learn about it in a few weeks.
         */
        boolean result = false;
        if( rightHandSide != null && this.getClass() == rightHandSide.getClass()){
            // rightHandSide is a non null MathMatrix
            MathMatrix otherMatrix = (MathMatrix)rightHandSide;
            
            // cs314 students: determine if otherMatrix is equal
            // to this MathMatrix and set result to true if they are.
            
            /*CS314 STUDENTS: ADD YOUR CODE HERE*/
            // same size
            if (otherMatrix.numCols()==numCols()&&otherMatrix.numRows()==numRows()){
            // same element
            	int count =0;
            	for(int row=0; row<numRows();row++){
            		for (int col=0; col<numCols();col++){
            			if (matrix[row][col]==otherMatrix.getVal(row, col)){
            				count++;
            			}
            		}
            	}
            	if (count == numCols()*numRows()){
            		result = true;
            	}
            }
        }
        return result;
    }


    /**
     * override toString.
     * @return a String with all elements of this MathMatrix. 
     * Each row is on a seperate line.
     * Spacing based on longest element in this Matrix.
     * Each row stats and ends with a vertical bar: '|'
     */
    public String toString(){
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
        //set local valuable
    	int[][] index = new int[numRows()][numCols()];
    	int longest=0;
    	//find the longest element in the matrix
    	for(int row=0; row<numRows();row++){
    		for (int col=0; col<numCols();col++){
    			String element="";
    			element +=matrix[row][col];
    			index[row][col] = element.length();
    			if (index[row][col] >longest){
    				longest = index[row][col];
    			}
    		}
    	}
    	//String builder
    	StringBuilder result = new StringBuilder();
    	for(int row=0; row<numRows();row++){
    		result.append("|"); 
    		for (int col=0; col<numCols();col++){
    			for(int space=0; space<longest+1-index[row][col];space++){
    				result.append(" ");
    			}
    			result.append(matrix[row][col]);
    		}
    		result.append("|\n");
    	}
    	//careful the type of the result
        return result.toString(); // alter as necessary
    }
    
    /**
     * Return true if this MathMatrix is upper triangular. To
     * be upper triangular all elements below the main 
     * diagonal must be 0.<br>
     * pre: this is a square matrix. numRows() == numCols()  
     * @return <tt>true</tt> if this MathMatrix is upper triangular,
     * <tt>false</tt> otherwise. 
     */
    public boolean isUpperTriangular() {
        /*CS314 STUDENTS: ADD YOUR CODE HERE*/
    	//local valuable
    	boolean result = true;
    	int index =0;
    	//check the down left
    	for(int row=0; row<numRows(); row++){
    		for(int col=0; col<index;col++){
    			if(matrix[row][col]!=0){
    				result = false;
    			}
    		}
    		index++;
    	}
        return result; // alter as necessary
    }
    
    // method to ensure mat is rectangular
    // pre: mat != null
    public static boolean rectangularMatrix(int[][] mat) {
        if(mat == null)
            throw new IllegalArgumentException("Violation of precondition: "
                    + " Parameter mat may not be null");
        boolean isRectangular = true;
        int row = 1;
        final int COLUMNS = mat[0].length;

        while( isRectangular && row < mat.length ) {
            isRectangular = ( mat[row].length == COLUMNS );
            row++;
        }

        return isRectangular;
    }
}