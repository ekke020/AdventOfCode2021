package eight;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class ChallengeEight {

    private static final HashMap<String[], String[]> ENTRIES = new HashMap<>();
    private static final String ONE = "cf";
    private static final String FOUR = "eafb";
    private static final String SEVEN = "dab";
    private static final String EIGHT = "abcdefg";
    private static int total;

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(new FileReader("resources/digits.txt"))) {
            while(scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split("[|]");
                ENTRIES.put(parts[0].trim().split(" "), parts[1].trim().split(" "));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        test();
        System.out.println(total);
    }

    private static void test() {
        for(String[] output : ENTRIES.values()) {
            Stream.of(output).forEach(ChallengeEight::addValue);
        }
    }

    private static void addValue(String entry) {
        if (entry.length() == 2) {
            total++;
        } else if (entry.length() == 4) {
            total++;
        } else if (entry.length() == 3) {
            total++;
        } else if (entry.length() == 7) {
            total++;
        }
    }
}
