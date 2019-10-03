import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


// class to test HangmanManager
public class EvilHangmanAutoTester {

    private static final String dataDir = ""; // "../../data/";
    private static int testCases;
    private static int failedTestCases;
    private static final String DICTIONARY_FILE = dataDir + "dictionary.txt";
    private static final String TEST_FILE_NAME = dataDir + "evilTest_WEB.eht";
    private static boolean DEBUG = true;

    
    public static void main(String[] args) {
        testCases = 0;
        failedTestCases = 0;
        // get the initial dictionary
        Set<String> dictionary = getDictionary();
        HangmanManager studentHangmanManager = new HangmanManager(dictionary, false);

        try {

            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(new File(TEST_FILE_NAME)));
            int numTests = reader.readInt();
            System.out.println("Number of rounds of game to play: " + numTests);
            System.out.println();

            // loop through and perform each test
            for(int i = 0; i < numTests; i++) {
                runTest(studentHangmanManager, reader);
            }
            
            // print results
            System.out.println("\ntotal test cases: " + testCases);
            System.out.println("number of test cases failed: " + failedTestCases);

            reader.close();
        }
        catch(IOException e) {
            printIOExceptionInfo(e);
        }
        catch(ClassNotFoundException e) {
            printFileNotFoundExceptionInfo(e);
        }
    }


    private static void printFileNotFoundExceptionInfo(ClassNotFoundException e) {
        System.out.println("\nError in trying to read and process automatic tests");
        System.out.println("Read in a class that could not be found.");
        System.out.println();
        System.out.println("for the Evil Hangman program.");
        System.out.println("File name: " + TEST_FILE_NAME);
        System.out.println("Error: " + e);
        System.out.println("Ending program.");
    }


    private static void printIOExceptionInfo(IOException e) {
        System.out.println("\nError in trying to read and process automatic tests");
        System.out.println("for the Evil Hangman program.");
        System.out.println("File name: " + TEST_FILE_NAME);
        System.out.println("Error: " + e);
        System.out.println("Ending program.");
    }

    
    private static void runTest(HangmanManager manager,
            ObjectInputStream reader) throws IOException, ClassNotFoundException {

        // read in header data for the test
        // TODO for Mike - create a class to store all this data
        int roundNumber = reader.readInt();
        int wordLength = reader.readInt();
        int numGuessesAllowed = reader.readInt();
        String actualGuesses = (String) reader.readObject();
        int intDifficulty = reader.readInt(); // original tests 1 indexed, not altered yet. 
        int initialNumberOfWords = reader.readInt();
        String initialPattern = (String) reader.readObject();
        HangmanDifficulty difficulty = HangmanDifficulty.HARD; // default
        if(validDifficulty(intDifficulty))
            difficulty = HangmanDifficulty.values()[intDifficulty];

        if(DEBUG)
            showInitialData(roundNumber, wordLength, numGuessesAllowed, 
                    actualGuesses, difficulty, initialNumberOfWords, 
                    initialPattern);


        // get ready for the round!
        manager.prepForRound(wordLength, numGuessesAllowed, difficulty);

        checkInitialConditions(manager, roundNumber, initialNumberOfWords, initialPattern);

        // run the actual guesses
        runRound(manager, reader, roundNumber, actualGuesses);
    }

    
    // check that initial number of words and pattern match
    private static void checkInitialConditions(HangmanManager manager,
            int testNumber, int initialNumberOfWords, String initialPattern) {

        //System.out.println();
        testCases++;
        if(initialNumberOfWords == manager.numWordsCurrent()) {
            if(DEBUG)
                System.out.println("\nRound number " + testNumber + 
                        " - passed test - initial number of words");
        }
        else {
            if(DEBUG) {
                System.out.println("\nRound number " + testNumber + 
                        " - FAILED TEST - initial number of words");
                System.out.println("Expected: " + initialNumberOfWords);
                System.out.println("Actual: " + manager.numWordsCurrent());
            }
            failedTestCases++;
        }

        testCases++;
        if(initialPattern.trim().equals(manager.getPattern().trim())) {
            if(DEBUG) 
                System.out.println("Round number " + testNumber + 
                        " - passed test - initial pattern");
        }
        else {
            if(DEBUG) {
                System.out.println("Round number " + testNumber + 
                        " - FAILED TEST - initial pattern");
                System.out.println("Expected: " + initialNumberOfWords);
                System.out.println("Actual: " + manager.numWordsCurrent());
            }
            failedTestCases++;
        }
    }

    
    @SuppressWarnings("unchecked")
    private static void runRound(HangmanManager manager,
            ObjectInputStream reader, 
            int roundNumber, String actualGuesses) 
            throws IOException, ClassNotFoundException {
        
        // run a complete round / game
        // number of guesses = actualGuesses.length()
        // make a guess, compare expected map to actual map, 

        for(int i = 0; i < actualGuesses.length(); i++) {
            char ch = actualGuesses.charAt(i);
            if(DEBUG)
                System.out.println("\nRound Number: " + roundNumber + ", guess number: " + (i + 1) + ", guessed char: " + ch);
            
            // read in expected results
            Map<String, Integer> expectedMap = (Map<String, Integer>) reader.readObject();
            
            expectedMap = buildMapNoSpaces(expectedMap);
            int expectedWordsLeft = reader.readInt();
            String expectedPattern = removeSpaces((String) reader.readObject());
            
            // make guess and get actual results;
            Map<String, Integer> actualMap = manager.makeGuess(ch);
            actualMap = buildMapNoSpaces(actualMap);
            int actualWordsLeft = manager.numWordsCurrent();
            String actualPattern = removeSpaces(manager.getPattern());
            
            if(DEBUG) {
                showResults(ch, expectedMap, actualMap, 
                        expectedPattern, actualPattern, expectedWordsLeft, actualWordsLeft);
            }
            
            // compare expected and actual results for correctness
            checkMapOfPatternsFromGuess(expectedMap, actualMap);
            
            checkNumberOfWordsLeftAfterGuess(manager, expectedWordsLeft,
                    actualWordsLeft);
            
            checkNewPatternAfterGuess(manager, expectedPattern, actualPattern);
            testCases += 3; // for map of patterns after guess, number of words left after guess, and new pattern
        }
    }


    private static void checkNewPatternAfterGuess(HangmanManager manager,
            String expectedPattern, String actualPattern) {
        if(!expectedPattern.equals(actualPattern)) {
            failedTestCases++;
            System.out.println("FAILED TEST CASE - NEW PATTERN AFTER GUESS INCORRECT");
            System.out.println("EXPECTED: " + expectedPattern);
            System.out.println("ACTUAL: " + manager.getPattern());
        }
    }


    private static void checkNumberOfWordsLeftAfterGuess(
            HangmanManager manager, int expectedWordsLeft, int actualWordsLeft) {
        if(expectedWordsLeft != actualWordsLeft ) {
            failedTestCases++;
            System.out.println("FAILED TEST CASE - NEW NUMBER OF CURRENT WORDS AFTER GUESS INCORRECT");
            System.out.println("EXPECTED: " + expectedWordsLeft);
            System.out.println("ACTUAL: " + manager.numWordsCurrent() );
        }
    }


    private static void checkMapOfPatternsFromGuess(
            Map<String, Integer> expectedMap, Map<String, Integer> actualMap) {
        // check map
        if(!expectedMap.equals(actualMap)) {
            failedTestCases++;
            System.out.println("FAILED TEST CASE - MAP OF WORDS PER PATTERN INCORRECT");
            System.out.println("EXPECTED: " + expectedMap);
            System.out.println("ACTUAL: " + actualMap);
        }
    }
    
    
    private static void showResults(char guess, Map<String, Integer> expectedMap,
            Map<String, Integer> actualMap, String expectedPattern,
            String actualPattern, int expectedWordsLeft, int actualWordsLeft) {
        System.out.println("\nDEBUGGING");
        System.out.println("Guessed char: " + guess);
        System.out.println("\nExpected patterns and frequencies: ");
        showMap(expectedMap);
        System.out.println("\nActual patterns and frequencies:");
        showMap(actualMap);
        System.out.println("\nExpected new pattern: " + expectedPattern);
        System.out.println("Actual new pattern: " + actualPattern);
        System.out.println("\nExpected new number of words: " + expectedWordsLeft);
        System.out.println("Actual new number of words: " + actualWordsLeft);
        
    }


    private static boolean validDifficulty(int intDifficulty) {
        return 0 <= intDifficulty && intDifficulty < HangmanDifficulty.values().length;
    }

    
    // print conditions for a given test
    private static void showInitialData(int roundNumber, int wordLength,
            int numGuessesAllowed, String actualGuesses, HangmanDifficulty difficulty,
            int initialNumberOfWords, String initialPattern) {

        System.out.println("Conditions for round number " + roundNumber);
        System.out.println("word length: " + wordLength);
        System.out.println("number of guesses allowed: " + numGuessesAllowed);
        System.out.println("Note, number of guesses allowed is not used.");
        System.out.print("guesses in order made: ");
        for(int i = 0; i < actualGuesses.length(); i++)
            System.out.print(actualGuesses.charAt(i) + " ");
        System.out.println("\nExpected initial number of words: " + initialNumberOfWords);
        System.out.println("Expected initial pattern(should be all dashes): " + initialPattern);
        System.out.print("Difficulty: " + difficulty);
    }

    private static Map<String, Integer> buildMapNoSpaces(
            Map<String, Integer> stu) {
        TreeMap<String, Integer> result = new TreeMap<String, Integer>();
        for(String pattern : stu.keySet()) {
            String newKey = removeSpaces(pattern);
            result.put(newKey, stu.get(pattern));
        }
        return result;
    }

    
    private static void showMap(Map<String, Integer> map) {
        for(String pattern : map.keySet())
            System.out.println(pattern + " " + map.get(pattern));
    }

    
    // open the dictionary file. Return a list containing 
    // the words in the dictionary file.
    // If the dictionary file is not found the program ends
    private static Set<String> getDictionary() {
        Set<String> dictionary = new TreeSet<String>();
        Scanner input = null;
        try {
            input = new Scanner(new File(DICTIONARY_FILE));

            while (input.hasNext())
                dictionary.add(input.next().toLowerCase());  
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found: " + e);
            System.out.println("Exiting");
            System.exit(-1);
        }
        finally {
            if(input != null)
                input.close();
        }
        
        return Collections.unmodifiableSet(dictionary);
    }

    
    private static String removeSpaces(String s) {
        s = s.trim();
        String result = "";
        for(int i = 0; i < s.length(); i++)
            if(s.charAt(i) != ' ')
                result += s.charAt(i);
        return result;
    }
}
