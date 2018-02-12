import edu.duke.*;

/**
 * A Java implementation of the classic Caesar Cipher.
 * 
 * @author Davide Nastri
 * @version 2/7/2018
 */
public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            boolean isCurrCharUppercase = Character.isUpperCase(currChar);
            currChar = Character.toUpperCase(currChar);
            int idx = alphabet.indexOf(currChar);
            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                if (!isCurrCharUppercase) {
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    public void testEncrypt() {
        System.out.println(encrypt("Hello there", 3));
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
    }
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);    
    }
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabetKey1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedAlphabetKey2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        //System.out.println(shiftedAlphabetKey1);
        //System.out.println(shiftedAlphabetKey2);        
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            boolean isCurrCharUppercase = Character.isUpperCase(currChar);
            currChar = Character.toUpperCase(currChar);
            int idx = alphabet.indexOf(currChar);
            char newChar;
            if (idx != -1) {
                if (i % 2 == 0) {
                    newChar = shiftedAlphabetKey1.charAt(idx);                    
                } else {
                    newChar = shiftedAlphabetKey2.charAt(idx);                    
                }
                if (!isCurrCharUppercase) {
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    public void testEncryptTwoKeys() {
        int key1 = 2;
        int key2 = 20;        
        FileResource fr = new FileResource();
        String message = fr.asString();
        message = "Top ncmy qkff vi vguv vbg ycpx";
        String encrypted = encryptTwoKeys(message, 26-key1, 26-key2);
        //System.out.println("Correct result is: Czojq Ivdzle");
        System.out.println(encrypted);
        
    }
    public void textFingerPrint(String s) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counters = new int[26];
        for (int k=0; k < s.length(); k++) {
            char ch = s.charAt(k);
            int index = alpha.indexOf(Character.toLowerCase(ch));
            if (index != -1) {
                counters[index] += 1;
            }
        }
        for (int k=0; k < counters.length; k++) {
            System.out.println(alpha.charAt(k)+"\t"+counters[k]);
        }
    }

}

