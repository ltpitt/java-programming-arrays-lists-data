/**
 * A Java class to find word frequencies in a file.
 * 
 * @author Davide Nastri
 * @version 2/15/2018
 */
import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    public void countWordsMap() {
        FileResource resource = new FileResource();
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        
        for( String w : resource.words() ) {
            w= w.toLowerCase();
            if (!map.containsKey(w)){
                map.put(w,1);
            } else {
                map.put(w,map.get(w)+1);
            }
        }
    }
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        
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
        for (int k=0; k < myWords.size(); k++) {
            System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
        }
        System.out.println("# unique words: " + myWords.size());
        System.out.println("The most used word is " + myWords.get(findIndexOfMax()));
    }
    public int findIndexOfMax() {
        int indexOfMax = -1;
        int highestFreqValue = myFreqs.get(0);
        for (int k=0; k < myFreqs.size(); k++) {
            int freqValue = myFreqs.get(k);
            if ( freqValue > highestFreqValue) {
                //System.out.println("Current indexOfMax: " + indexOfMax);
                //System.out.println("Found a more frequent word");
                //System.out.println("Current word: " + myWords.get(k));
                //System.out.println("Current freq: " + myFreqs.get(k));                
                //System.out.println("New indexOfMax value: " + myWords.indexOf(myWords.get(k)));
                highestFreqValue = freqValue;
                indexOfMax = k;
            }
        }
        return indexOfMax;
    }
}
