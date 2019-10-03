import java.awt.Point;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Models a maze for the problem on the CS314 assignment
 * that checks to see if it is possible to escape a maze while
 * collection all the coins in the maze.
 * See the explanation at this web site:
 * http://www.cs.utexas.edu/~scottm/cs314/Assignments/A6_Recursion.html
 *
 */
public class Maze {

    /**
     * Represents cells where you can exit the maze.
     */
    public static final char EXIT = 'E';

    /**
     * Represents cells that contain a gold coin. 
     * We can enter these cells, but when we leave they
     * become IMPASSABLE cells.
     */   
    public static final char GOLD = 'G';

    /**
     * Represents cells that are impassable. We can
     * not enter these cells.
     */
    public static final char IMPASSABLE = '*';

    /**
     * The starting cell. This is where we start in the maze.
     */
    public static final char START = 'S';

    /**
     * Regular, open cells in the maze. We can enter
     * these cells as many times as we want.
     */
    public static final char REGULAR = 'R';

    private static HashSet<Character> legalChars;

    static {
        legalChars = new HashSet<>();
        legalChars.add(GOLD);
        legalChars.add(START);
        legalChars.add(IMPASSABLE);
        legalChars.add(REGULAR);
        legalChars.add(EXIT);
    }
    
    private char[][] cells;
    private final int numCoins;
    private final int numExits;
    private final Point startPos;
    
    /**
     * Create a new maze based on the given 2d array of chars.
     * <br> rawMaze != null, all chars, there is exactly one start
     * cell in the 2d array of chars.
     * in rawMaze are legal chars
     * @param rawMaze The raw data to build the maze out of.
     */
    public Maze(char[][] rawMaze) {
        if (rawMaze == null) {
            throw new IllegalArgumentException("The parameter rawMaze cannot be null: " + rawMaze);
        } else if (!allLegalChars(rawMaze)) {
            throw new IllegalArgumentException("The parameter rawMaze contains illegal "
                            + "characters for the maze.");
        } else if (numStartCells(rawMaze) != 1) {
            throw new IllegalArgumentException("The parameter rawMaze must contain "
                            + "exactly one start cell, 'S'.");
        }
        cells = getCopyOfMaze(rawMaze);
        int[] initData = findStartCoinsAndExits();
        startPos = new Point(initData[1], initData[0]);
        numExits = initData[3];
        numCoins = initData[2];
    }

    private static int numStartCells(char[][] rawMaze) {
        int count = 0;
        for (char[] row : rawMaze) {
            for (char c: row) {
                if (c == START) {
                    count++;
                }
            }
        }
        return count;
    }
    
    /**
     * Return the character in this cell.
     * <br> pre: inbounds(r, c)
     * @param r row
     * @param c column
     * @return Return the character in this cell.
     */
    public char getChar(int r, int c) {
        if (!inbounds(r, c)) {
            throw new IllegalArgumentException("Given row and column are not inbounds for this maze.");
        }
        return cells[r][c];
    }
    
    /**
     * Return true if the cell specified by r, c is inbounds for
     * this maze.
     * @param r the row to check.
     * @param c the column to check.
     * @return Return true if the cell specified by r, c is inbounds for this maze.
     */
    public boolean inbounds(int r, int c) {
        return r >= 0 && r < cells.length && cells[r] != null 
                        && c >= 0 && c < cells[r].length;
    }

    /**
     * Return true if we can enter this cell of the maze. 
     * To be able to enter the cell r and c must be inbounds
     * and the cell must be the start cell, a regular cell,
     * a gold cell, or an exit cell. We can not enter cells
     * that are out of bounds or are impassable.
     * @param r The row of the cell to check.
     * @param c The column of the cell to check.
     * @return true if we can enter this cell, false otherwise.
     */
    public  boolean canEnterCell(int r, int c) {
        boolean canEnter = false;
        if (inbounds(r, c)) {
            canEnter = cells[r][c] != IMPASSABLE;
        }
        return canEnter;
    }
    

    /**
     * Return true if c is a legal character for this maze.
     * @param c The char to check.
     * @return true if the parameter c is a legal character for this maze.
     */
    public static boolean isLegalCharacter(char c) {
        return legalChars.contains(c);
    }
    
    /**
     * Return the number of exit cells in this maze.
     * @return Return the number of exit cells in this maze.
     */
    public int getNumExits() {
        return numExits;
    }
    
    /**
     * Return the number of cells that contains coins in this maze.
     * @return Return the number of cells that contains coins in this maze.
     */
    public int getNumCoins() {
        return numCoins;
    }
    
    /**
     * Return the row of the starting cell.
     * @return Return the row of the starting cell.
     */
    public int getStartRow() {
        return startPos.y;
    }
    
    /**
     * Return the column of the starting cell.
     * @return Return the column of the starting cell.
     */
    public int getStartColumn() {
        return startPos.x;
    }
    

    /**
     * Flip the contents of the given cell. The cell must either
     * be a GOLD coin cell or an IMPASSABLE cell. If it is a GOLD
     * coin cell it becomes IMPASSABLE and if it is IMPASSABLE
     * it becomes a GOLD coin cell.
     * <br> r and c are inbounds and the cell contains GOLD or is IMPASSABLE
     * @param r the row of the cell to flip
     * @param c the column of the cell to flip
     */
    public void flipCoinCell(int r, int c) {
        if (!inbounds(r, c)) {
            throw new IllegalArgumentException("Given row and column are not inbounds for this maze.");
        } else if (cells[r][c] != GOLD && cells[r][c] != IMPASSABLE) {
            throw new IllegalArgumentException("cell must contain gold coin or be impassable. "
                            + "value = " + cells[r][c]);
        }
        if (cells[r][c] == GOLD) {
            cells[r][c] = IMPASSABLE;
        } else {
            cells[r][c] = GOLD;
        }
    }
    
    private static boolean allLegalChars(char[][] rawMaze) {
        for (char[] row : rawMaze) {
            for (char c : row) {
                if (!legalChars.contains(c)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    // make a copy of the maze so we can change gold coins to '*'
    private static char[][] getCopyOfMaze(char[][] raw) {
        char[][] result = new char[raw.length][];
        for (int r = 0; r < raw.length; r++) {
            result[r] = Arrays.copyOf(raw[r], raw[r].length);
        }
        return result;
    }
    
    // Find the starting position and count the number of coins in the maze
    // The returned array will have a length == 4. 
    // The first two values are the row and column of the start cell. 
    // The third value is the number of coins in the maze.
    // The fourth value in the number of exits in the maze
    private int[] findStartCoinsAndExits() {
        int[] result = new int[4];
        for (int r = 0; r < cells.length; r++) {
            for (int c = 0; c < cells[r].length; c++) {
                if (cells[r][c] == START) {
                    result[0] = r;
                    result[1] = c;
                } else if (cells[r][c] == GOLD) {
                    result[2]++;
                } else if(cells[r][c] == EXIT) {
                    result[3]++;
                }
            }
        }
        return result;
    }
    
    
}
