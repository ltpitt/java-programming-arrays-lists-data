/**
 * A Java class to find data about character in a play.
 * 
 * @author Davide Nastri
 * @version 2/15/2018
 */
import java.util.ArrayList;
import edu.duke.*;

public class CharactersInPlay {
   
   private ArrayList<String> characterNames;
   private ArrayList<Integer> characterFreqs;

   public CharactersInPlay() {
       characterNames = new ArrayList<String>();
       characterFreqs = new ArrayList<Integer>();
   }
   public void update(String person) {
       int personIndex = characterNames.indexOf(person);

       if (personIndex == -1) {
           characterNames.add(person);
           characterFreqs.add(1);
       } else {
           int characterFreqValue = characterFreqs.get(personIndex);
           characterFreqs.set(personIndex,characterFreqValue+1);
       }
       
   }
   public void tester() {
        findAllCharacters();
        System.out.println("# characters: " + characterNames.size());
        charactersWithNumParts(9,500);
   }
   public void findAllCharacters() {
        characterNames.clear();
        characterFreqs.clear();
        FileResource resource = new FileResource();
        for (String s : resource.lines()) {
            s = s.toLowerCase();
            System.out.println("Current line: " + s);
            int dotIndex = s.indexOf('.');
            if (dotIndex != -1) {
                System.out.println("Dot found in " + s.substring(0,dotIndex));
                update(s.substring(0,dotIndex));
            }
        }       
       
   }
   public void charactersWithNumParts(int num1, int num2) {
       for (int k=0; k < characterNames.size(); k++) {
            if (characterFreqs.get(k) > num1 && characterFreqs.get(k) < num2) {
                System.out.println(characterFreqs.get(k) + "\t" + characterNames.get(k));
            }
        }
   }
}
