package six;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ChallengeSix {

    private static final long[] fishArray = new long[9];
    private static long deadFish = 0;

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(new FileReader("resources/fishes.txt"))) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                Arrays.asList(line).forEach(amount -> fishArray[Integer.parseInt(amount)] += 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 256; i++) {
            shiftFish();
            createNewFish();
        }
        calcValue();
    }

    private static void calcValue() {
        long value = 0;
        for (long l : fishArray) {
            value += l;
        }
        System.out.println("Current value: " + value);
    }

    private static void shiftFish() {
        long[] newArr = new long[9];
        deadFish = fishArray[0];
        System.arraycopy(fishArray, 1, newArr, 0, 8);
        System.arraycopy(newArr, 0, fishArray, 0, 9);
    }

    private static void createNewFish() {
        fishArray[8] = deadFish;
        fishArray[6] += deadFish;
    }

}
