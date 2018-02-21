import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String,ArrayList> wordsMap;
    private ArrayList<String> usedWordsList;
    private ArrayList<String> usedCategoryList;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        wordsMap = new HashMap<String,ArrayList>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        
    }
    
    public GladLibMap(String source){
        wordsMap = new HashMap<String,ArrayList>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        
        String[] categoriesArray = {"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};
        
        for (String category : categoriesArray) {
            wordsMap.put(category, readIt(source + "/" + category + ".txt"));
        }
        
        usedWordsList = new ArrayList<String>(); 
        usedCategoryList = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (wordsMap.containsKey(label)) {
            if (!usedCategoryList.contains(label)) usedCategoryList.add(label);
            return randomFrom(wordsMap.get(label));
        } else if (label.equals("number")) {
            return ""+myRandom.nextInt(50)+5;
        } else {
            return "**UNKNOWN**";
        }
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while (usedWordsList.contains(sub)) {
            sub = getSubstitute(w.substring(first+1,last));
        }
        usedWordsList.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap() {
        int totalNumber = 0;
        
        for(String category : wordsMap.keySet()){
            totalNumber = totalNumber + wordsMap.get(category).size();
        }
        System.out.println("Total words in map: " + totalNumber);
        return totalNumber;
    }
    
    public int totalWordsConsidered() {
        int sum = 0;
        for (int k = 0; k < usedCategoryList.size(); k++) {
            sum += wordsMap.get(usedCategoryList.get(k)).size();
        }
        System.out.println("Total words considered: " + sum);
        return sum;        
    }
    
    public void makeStory(){
        usedWordsList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
        System.out.println("Number of replaced words is " + usedWordsList.size() );
        totalWordsInMap();
        totalWordsConsidered();
    }
    


}
