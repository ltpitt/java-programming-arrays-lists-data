/**
 * A Java class to find how many times each codon
 * occurs in a strand of DNA based on reading frames.
 * 
 * @author Davide Nastri
 * @version 2/18/2017
 */

import edu.duke.*;
import java.util.*;

public class DNAHashMap {
        private HashMap<String,Integer> map;
        DNAHashMap () {
            map = new HashMap<String,Integer>();
        }
        public void buildCodonMap (int start, String dna) {
            map.clear();          
            String current;
            for(int i = 0;i < (dna.length() - start)/3;i++) {
                current = dna.substring(start+i*3, start+i*3+3);
                if (map.containsKey(current)) {
                    map.put(current, map.get(current)+1);
                } else {
                    map.put(current, 1);
                }
            }
        }
        public String getMostCommonCodon() {
            String mostCommonCodon = "";
            int maxCodonOccurrence = 0;
            if (map.size() > 0) {
                for(String currentCodon : map.keySet()){
                    int currentCodonOccurrence = map.get(currentCodon);
                    if (currentCodonOccurrence > maxCodonOccurrence){
                      maxCodonOccurrence = currentCodonOccurrence;
                      mostCommonCodon = currentCodon;
                    }
                }            
            }
            return mostCommonCodon;
        }
        public void printCodonCounts (int start, int end) {
            for(String key : map.keySet()){
                int value = map.get(key);
                if (value >= start && value <= end) {
                    System.out.println(value + "\t" + key);
                }
            }       
        }
        public void tester () {
            FileResource fr = new FileResource(); 
            String dna = fr.asString().trim();
            //dna = "CGTTCAAGTTCAA";
            dna = dna.toUpperCase();
            int frame = 1;            
            System.out.println("1st test using frame " + frame);
            buildCodonMap(frame, dna);
            int totalCodons = 0;
            int uniqueCodons = 0;
            int start = 1;
            int end = 5;
            for(String codon : map.keySet()){
                int occurrencies = map.get(codon);
                System.out.println(occurrencies + "\t" + codon);
                if (occurrencies == 1) {
                    uniqueCodons++;
                }
                totalCodons += occurrencies;
            }
            System.out.println("Total codons: " + totalCodons);
            System.out.println("Total unique codons: " + uniqueCodons);
            
            //System.out.println("Most common codon is " + getMostCommonCodon());
            //System.out.println("Codon count between " + start + " and " + end);
            //printCodonCounts(start, end);
            
            /*
            System.out.println("2nd test");            
            buildCodonMap(1, dna);
            total = 0;
            start = 1;
            end = 5;
            for(String w : map.keySet()){
                int value = map.get(w);
                System.out.println(value+"\t"+w);
            total += value;
            }
            System.out.println("Most common codon is " + getMostCommonCodon());
            System.out.println("Codon count between " + start + " and " + end);
            printCodonCounts(start, end);            
            
            System.out.println("3rd test");            
            buildCodonMap(2, dna);
            total = 0;
            start = 1;
            end = 5;
            for(String w : map.keySet()){
                int value = map.get(w);
                System.out.println(value+"\t"+w);
        total += value;
            }
            System.out.println("Most common codon is " + getMostCommonCodon());
            System.out.println("Codon count between " + start + " and " + end);
            printCodonCounts(start, end);            
            */
        }
    }