package eight;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ChallengeEight {

    private static final List <String[]> ENTRIES = new ArrayList<>();
    private static final List <Integer> ENTRIES_VALUES = new ArrayList<>();
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
//        try(Scanner scanner = new Scanner(new FileReader("resources/test.txt"))) {
//            while(scanner.hasNextLine()) {
//                String[] parts = scanner.nextLine().split("[|]");
//                ENTRIES.add(parts[1].trim().split(" "));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (String[] entry : ENTRIES) {
//            for (String pattern : entry) {
//                for (String code : CODES) {
//                    if (isTargetInPattern(pattern, code)) {
//                        System.out.println(code);
//                        break;
//                    }
//                }
//            }
//        }
        List<Double> doubleList = new ArrayList<>(Arrays.asList(1.1,1.2,1.4,0.5));
        double sum = doubleList.stream()
                .mapToDouble(Double::intValue)
                .sum();
//        sum = 0;
//        for (Double aDouble : doubleList) {
//            sum += aDouble;
//        }
        System.out.println(sum);
    }

//    private static void test() {
//        for(String[] output : ENTRIES.values()) {
//            Stream.of(output).forEach(ChallengeEight::addValue);
//        }
//    }

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

    private static boolean isTargetInPattern(String pattern, String target) {
        char[] targetChars = target.toCharArray();
        char[] patternChars = pattern.toCharArray();

        boolean isPresent = false;

        for (char targetChar : targetChars) {
            for (char patternChar : patternChars) {
                if (targetChar == patternChar) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                return false;
            }
            isPresent = false;
        }
        return true;
    }

}
