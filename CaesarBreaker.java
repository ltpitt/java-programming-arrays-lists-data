import edu.duke.*;

/**
 * A Java class to break the Caesar cipher.
 * 
 * @author Davide Nastri 
 * @version 2/12/2018
 */
public class CaesarBreaker {
    public int [] countLetters(String message) {
        message = message.toLowerCase();
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k=0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }
    public int maxIndex(int [] values) {
        int maxValueIndex = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[maxValueIndex]) {
                maxValueIndex = i;
            }
        }
        return maxValueIndex;
    }
    public void testMaxIndex() {
        int[] myIntArray = new int[]{1,7,9,55};
        System.out.println("The max value index in [1,7,9,55] is: " + maxIndex(myIntArray));
    }    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4-maxDex);
        }
        return cc.encrypt(encrypted,26-dkey);
    }
    public void testDecrypt() {
        FileResource fr = new FileResource();
        String encryptedMessage = fr.asString();
        System.out.println(decrypt(encryptedMessage));
    }
    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String firstHalfOfString = halfOfString(encrypted, 0);
        String secondHalfOfString = halfOfString(encrypted, 1);
        int[] freqsFirstHalfOfString = countLetters(firstHalfOfString);
        int[] freqsSecondHalfOfString = countLetters(secondHalfOfString);        
        int maxDexFirst = maxIndex(freqsFirstHalfOfString);
        int maxDexSecond = maxIndex(freqsSecondHalfOfString);
        int dkey1 = maxDexFirst - 4;
        int dkey2 = maxDexSecond - 4;
        System.out.println("Key 1: " + dkey1);
        System.out.println("Key 2: " + dkey2);        
        if (maxDexFirst < 4) {
            dkey1 = 26 - (4-maxDexFirst);
        }
        if (maxDexSecond < 4) {
            dkey1 = 26 - (4-maxDexSecond);
        }
        return cc.encryptTwoKeys(encrypted,26-dkey1,26-dkey2);
    }    
    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String encryptedMessage = fr.asString();
        //encryptedMessage = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        System.out.println(decryptTwoKeys(encryptedMessage));
        
    }
    public String halfOfString(String message, int start) {
        char[] messageCharArray = message.toCharArray();
        String halfMessage = "";
        for (int i=start; i < messageCharArray.length; i+=2) {
                halfMessage = halfMessage + messageCharArray[i];
        }
        return halfMessage;
    }
    public void testhalfOfString() {
            String message = "Qbkm Zgis";
            System.out.println(halfOfString(message, 1));
    }
    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int foundKey = maxIndex(freqs);
        return foundKey;
    }
    public void testGetKey() {
        System.out.println(getKey("Daaaaavide"));
    }
}
