
/**
 * Methods to check and modify words.
 * 
 * @author Davide Nastri 
 * @version 2/8/2018
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        String vowels = "aeiou";
        if (vowels.toLowerCase().contains(Character.toString(ch)) || vowels.toUpperCase().contains(Character.toString(ch)) ) {
            return true;
        } else {
            return false;
        }
    }
    public void testIsVowel() {
        char ch = 'F';
        System.out.println("Is F a vowel: " + isVowel(ch));
        ch = 'a';
        System.out.println("Is a a vowel: " + isVowel(ch));
    }

}
