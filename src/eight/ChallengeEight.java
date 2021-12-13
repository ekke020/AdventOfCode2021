package eight;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ChallengeEight {

    private static final HashMap<String[], String[]> ENTRIES = new HashMap<>();
    private static final String ZERO = "cagedb";
    private static final String ONE = "ab";
    private static final String TWO = "gcdfa";
    private static final String THREE = "fbcad";
    private static final String FOUR = "eafb";
    private static final String FIVE = "cdfbe";
    private static final String SIX = "cdfgeb";
    private static final String SEVEN = "dab";
    private static final String EIGHT = "abcdefg";
    private static final String NINE = "cefabd";
    private static final String[] CODES = {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE};
    private static int total;

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(new FileReader("resources/test.txt"))) {
            while(scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split("[|]");
                ENTRIES.put(parts[0].trim().split(" "), parts[1].trim().split(" "));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < CODES.length; i++) {
            if(test1("bad")) {

            }
        }
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

    private static int getValue(String entry) {
        if (entry.length() == 2) {
            return 1;
        } else if (entry.length() == 4) {
            return 4;
        } else if (entry.length() == 3) {
            return 7;
        } else if (entry.length() == 7) {
            return 8;
        } else if (entry.matches("[a-]")) {

        }

        return 0;
    }

    private static boolean test1(String pattern) {
        char[] charSearch = pattern.toCharArray();
        boolean isPresent = false;
        for(int i=0; i<charSearch.length; i++) {
            char chr = pattern.charAt(i);
            for(int j = 0; j < pattern.length(); j++) {
                if(charSearch[j] == chr) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                return false;
            }
        }
        return true;
    }
}
