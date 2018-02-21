/**
 * A Java class to determine which words occur
 * in the greatest number of files, and
 * for each word, which files they occur in.
 * 
 * @author Davide Nastri
 * @version 2/18/2017
 */

import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList> map;
    WordsInFiles() {
        map = new HashMap<String, ArrayList>();
    }
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String fileName = f.getName();
        for(String word : fr.words() ) {
            if (map.containsKey(word)) {
                if (!map.get(word).contains(fileName)) {
                    map.get(word).add(fileName);
                }
            } else {
                ArrayList filenameList = new ArrayList<String>();
                map.put(word, filenameList);
                map.get(word).add(fileName);
            }
        }   
    }
    private void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles() ) {
            addWordsFromFile(f);
        }
    }
    private int maxNumber() {
        int maxNumber = -1;
        for (String word : map.keySet()) {
            System.out.println(word);
            ArrayList <String> fileNames = map.get(word);
            int currentNumber = fileNames.size();
            if (currentNumber > maxNumber) {
                maxNumber = currentNumber;
            }
        }
        return maxNumber;
    }
    private ArrayList<String> wordsInNumFiles(int number) {
        //returns an ArrayList of words that appear in exactly number files
        ArrayList<String> wordList = new ArrayList<String>();
        for (String word : map.keySet()) {
            ArrayList<String> currentList = map.get(word);
            int currentFileCount = currentList.size();
            if (currentFileCount == number) {
                wordList.add(word);
            }
        }
        return wordList;
    }
    private void printFilesIn(String word) {
        for (String currentWord : map.keySet()) {
            System.out.println(currentWord);
            if (currentWord.equals(word)) {
                System.out.println(" ***** Word found! *****");
                ArrayList <String> fileNames = map.get(word);
                for (int i = 0; i < fileNames.size(); i++) {
                    System.out.println(fileNames.get(i));
                }
            }
        }
    }
    public void tester() {
        buildWordFileMap();
        System.out.println("Max number: " + maxNumber());
        String wordToSearch = "sea";
        System.out.println("Found " + wordToSearch + " in:");
        printFilesIn("sea");
        
        int fileNumber = 4;
        System.out.println("Following words were found in " + fileNumber + " files");
        ArrayList<String> wordsFound = wordsInNumFiles(fileNumber);        
        for (String word : wordsFound) {
            System.out.println(word);
        }
        System.out.println("This is the number of words appearing in all of the files:");
        wordsFound.size();
    }
}
