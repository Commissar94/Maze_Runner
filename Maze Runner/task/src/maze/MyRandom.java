package maze;

import java.util.Random;

public class MyRandom {
    private static final Random random = new Random();

    static int exitOrEnter(int x) {

        if (x % 2 == 0) {
            int number = random.nextInt((x - 2)) + 1;
            return number % 2 == 0 ? number + 1 : number;
        }

        return random.nextInt((x - 2)) + 1;
    }
}