/**
 * A Java class to count word lenghts.
 * 
 * @author Davide Nastri
 * @version 2/10/2018
 */
import edu.duke.FileResource;

public class WordLengths {
    public boolean isLetter(char ch) {
        return Character.isLetter(ch);
    }
    public void testIsLetter() {
        System.out.println("F is letter: " + isLetter('F'));
        System.out.println("- is letter: " + isLetter('-'));
    }
    public int indexOfMax(int [] values) {
        int valuesLen = values.length;
        int maxValue = -1;
        for (int i = 0; i < valuesLen; i++) {
            if (i > maxValue) {
                maxValue = i;
            }
        }
        return maxValue;
    }
    public void testIndexOfMax() {
        int[] myIntArray = new int[]{1,7,9,55};
        System.out.println("The max value index in [1,7,9,55] is: " + indexOfMax(myIntArray));
    }
    public void countWordLengths(FileResource resource, int[] counts) {
        int countsLen = counts.length;
        for (String word : resource.words() ) {
            counts[countWordLength(word)] ++;
        }
        int idx = 0;
        for (int wordOccurrencyNumber : counts) {
            System.out.println(wordOccurrencyNumber + " words of length " + idx);
            idx++;
        }
    }
    public int countWordLength(String word) {
        int len = 0;
        char [] charArray = word.toCharArray();
        int charArrayLen = charArray.length - 1;
        for (char ch : charArray) {
            if (Character.isLetter(ch)) {
                len++;
            } else {
                if (len != 0 && len != charArrayLen) {
                    len++;
                }
            }
        }
        return len;
    }
    public void testCountWordLength() {
        System.out.println("Word: Davide is long: " + countWordLength("Davide"));
        System.out.println("Word: -Minus is long: " + countWordLength("-Minus"));        
        System.out.println("Word: Mi-nus is long: " + countWordLength("Mi-nus"));        
        System.out.println("Word: Minus- is long: " + countWordLength("Minus-"));                
    }
    public void testCountWordLengths() {
        int[] counts = new int[31];
        FileResource fr = new FileResource();
        countWordLengths(fr, counts);
    }
}
