package six;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ChallengeSix {

    private static List<AtomicLong> lanternFish = new ArrayList<>();

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(new FileReader("resources/test.txt"))) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                Arrays.stream(line).forEach(num -> lanternFish.add(new AtomicLong(Integer.parseInt(num))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 256; i++) {
            scanFishes();
        }
        System.out.println(lanternFish.size());
    }

    private static void scanFishes() {
        AtomicInteger count = new AtomicInteger();
        lanternFish.forEach(AtomicLong::decrementAndGet);
        lanternFish.forEach(age -> {
            if (age.get() < 0) {
                count.incrementAndGet();
                age.set(6);
            }
        });
        for (int i = 0; i < count.get(); i++) {
            lanternFish.add(new AtomicLong(8));
        }
    }
}
