import edu.duke.*;
import java.util.*;
import java.io.*;


public class WordsinFiles2 {
    private HashMap<String, ArrayList<String>> map;
    
    public WordsinFiles2() {
        map = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f.toString());
        String name = f.getName();
        for (String word: fr.words()) {
            if (!map.containsKey(word)) {
                map.put(word, new ArrayList<String>());
                map.get(word).add(name);
            }
            else if (map.containsKey(word)) {
                if (!map.get(word).contains(name)) map.get(word).add(name);
            }
        }
    }
    
    private void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            addWordsFromFile(f);
        }
        
    }
    
    private int maxNumber() {
        int current = 0;
        int largest = 0;
        
        for (String word: map.keySet()) {
            current = map.get(word).size();
            if (largest < current) largest = current;
        }
        
        return largest;
    }
    
    private ArrayList<String> wordsInNumFiles(int number) {
        int current = 0;
        ArrayList<String> list = new ArrayList<String>();
        for (String word: map.keySet()) {
            current = map.get(word).size();
            if (current==number) list.add(word);
        }
        return list;
    }
    
    private void printFilesIn(String word) {
        System.out.println("The files contain "+word+" are(is): \t");
        ArrayList<String> list = new ArrayList<String>();
        for (String current: map.keySet()) {
            if (current==word) list = map.get(current);
        }
        for (int k = 0; k < list.size(); k++) {
            System.out.println(list.get(k)+"\t");
        }
    }
    
    public void test() {
        buildWordFileMap();
        //int max = maxNumber();
        //max = 5;
        //ArrayList<String> list = wordsInNumFiles(max);
        //System.out.println("The greatest number of files a word appears in is "+max+", and there are "+list.size()+ " such words: ");
        //for (int k = 0; k < list.size(); k++) {
        //    System.out.println(list.get(k)+" ");
        //}
        //System.out.println("\t");
        //for (int k = 0; k < list.size(); k++) {
        //    printFilesIn(list.get(k));
        //}
        printFilesIn("laid");
        
        
    }
}