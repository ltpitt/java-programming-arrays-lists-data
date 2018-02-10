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
    }
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);    
    }
    public String encryptTwoKeys(String input, int key, int key2) {
        return "";
    }
    public void testEncryptTwoKeys() {
    }
}

