
/**
 * Methods to check and modify words.
 * 
 * @author Davide Nastri 
 * @version 2/8/2018
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        if (vowels.indexOf(ch) != -1 ) {
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
    public String replaceVowels(String phrase, char ch) {
        StringBuilder returnPhrase = new StringBuilder(phrase);        
        for (int i = 0; i < returnPhrase.length(); i++) {
            char currChar = returnPhrase.charAt(i);
            if ( isVowel(currChar) ) { 
                    System.out.println("Found vowel: " + currChar);
                    returnPhrase.setCharAt(i, '*');
            }
        }
        return returnPhrase.toString();
    }
    public void testReplaceVowels() {
        System.out.println(replaceVowels("Davide", '*'));
    }
    public String emphasize(String phrase, char ch) {
        StringBuilder returnPhrase = new StringBuilder(phrase);
        for (int i = 0; i < returnPhrase.length(); i++) {
            char currChar = returnPhrase.charAt(i);
            if ( Character.toUpperCase(currChar) == ch || Character.toLowerCase(currChar) == ch) {
                if (i % 2 == 0) {
                    returnPhrase.setCharAt(i, '*');
                } else {
                    returnPhrase.setCharAt(i, '+');
                }
            }
        }
        return returnPhrase.toString();
    }
    public void testEmphasize() {
        System.out.println("Expected result: dn* ctg+*+ctg+");
        System.out.println(emphasize("dna ctgaaactga",'a'));
        System.out.println("Expected result: M+ry Bell+ +br*c*d*br+");        
        System.out.println(emphasize("Mary Bella Abracadabra",'a'));
    }
}
