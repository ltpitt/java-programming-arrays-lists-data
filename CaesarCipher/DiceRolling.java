/**
 * Java code to simulate DiceRolling.
 * 
 * @author Davide Nastri
 * @version 2/10/2018
 */
import java.util.Random;

public class DiceRolling {
    public void simulate(int rolls) {
    Random rand = new Random();
    int [] counts = new int [13];    
    for (int k=0; k < rolls; k++) {
        int d1 = rand.nextInt(6) + 1;
        int d2 = rand.nextInt(6) + 1;
        System.out.println("Roll is " + d1 + "+" + d2 + "=" + (d1 + d2));
        counts[d1+d2] += 1;
    }
    for (int k=2; k <= 12; k++) {
        System.out.println(k + "'s=\t" + counts[k] + "\t" + 100.0 * counts[k]/rolls);
    }
    }
}
