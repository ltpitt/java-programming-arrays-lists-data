/**
 * A Java class to find word frequencies in a file.
 * 
 * @author Davide Nastri
 * @version 2/15/2018
 */
import java.util.ArrayList;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        FileResource resource = new FileResource();
        
        for (String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index,value+1);
            }
        }
    }
    public void testFindUnique() {
        findUnique();
        System.out.println("# unique words: " + myWords.size());
        for (int k=0; k < myWords.size(); k++) {
            System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
        }
    }
}
