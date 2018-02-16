/**
 * A Java class to manipulate words with arrays.
 * 
 * @author Davide Nastri
 * @version 2/15/2018
 */
import edu.duke.*;

public class WordsWithArrays {
    StorageResource myWords;
    
    public WordsWithArrays() {
        myWords = new StorageResource();
    }
    
    public void readWords(){
        myWords.clear();
        FileResource resource = new FileResource();
        for (String word : resource.words()){
            myWords.add(word.toLowerCase());       
        }
    }
    
    public boolean contains(String[] list, String word, int number) {
        for(int k=0; k < number; k++) {
            if (list[k].equals(word)) {
                return true;
            }
        }
        return false;
    }
    
    public int numberOfUniqueWords() {
        int numStored = 0;
        String[] words = new String[myWords.size()];
        for (String s : myWords.data()) {
            if (! contains(words, s, numStored)) {
                words[numStored] = s;
                numStored++;
            }
        }
        return numStored;
    }
    
    public void testNumberOfUniqueWords() {
        readWords();
        System.out.println("Number of words read: " + myWords.size());
        int unique = numberOfUniqueWords();
        System.out.println("Array count " + unique);
    }
}
