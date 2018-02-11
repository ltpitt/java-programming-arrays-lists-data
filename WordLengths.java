/**
 * A Java class to count word lenghts.
 * 
 * @author Davide Nastri
 * @version 2/10/2018
 */
import edu.duke.FileResource;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        int countsLen = counts.length;
        int i = 0;
        for (String word : resource.words() ) {
            System.out.println("At index: " + i + " found word: " + word);
            i++;
        }
    }
    public void testCountWordLengths() {
        int[] counts = new int[31];
        FileResource fr = new FileResource();
        countWordLengths(fr, counts);
    }
}
