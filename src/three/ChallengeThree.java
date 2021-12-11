package three;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ChallengeThree {

    private static List<String> binaryList = new ArrayList<>();
    private static final List<Integer> SOLUTION_LIST = new ArrayList<>();

    private static int zeroCounter;
    private static int oneCounter;
    private static int position;

    // oxygen generator rating = 010110110011
    // CO2 scrubber rating = 110001101010
    public static void main(String[] args) {
        createList();
        System.out.println(SOLUTION_LIST);
        for (int i = 0; i < 12; i++) {
            partTwo();
            removeLeastCommonValue();
            zeroCounter = 0;
            oneCounter = 0;
            position++;
            if (binaryList.size() == 1)
                break;
        }
        System.out.println(binaryList);
    }

    private static void partTwo() {
        for (String s : binaryList) {
            char c = s.charAt(position);
            zeroOrOne(c);
        }
    }

    private static void removeLeastCommonValue() {
        if (zeroCounter > oneCounter) {
            binaryList = binaryList.stream().
                    filter(s -> s.charAt(position) == '1').
                    collect(Collectors.toCollection(ArrayList::new));
        } else {
            binaryList = binaryList.stream().
                    filter(s -> s.charAt(position) == '0').
                    collect(Collectors.toCollection(ArrayList::new));
        }
    }
    private static void partOne() {
        for (int i = 0; i < 12; i++) {
            for (String s : binaryList) {
                zeroOrOne(s.charAt(i));
            }
            if(isZeroLarger()) {
                SOLUTION_LIST.add(0);
            } else {
                SOLUTION_LIST.add(1);
            }
            zeroCounter = 0;
            oneCounter = 0;
        }
    }

    private static void zeroOrOne(char c) {
        if(c == '0') {
            zeroCounter++;
        } else {
            oneCounter++;
        }
    }

    private static boolean isZeroLarger() {
        return zeroCounter > oneCounter;
    }

    private static void createList() {
        try(Scanner scanner = new Scanner(new FileReader("resources/diagnostic_report.txt"))) {
            while (scanner.hasNextLine()) {
                binaryList.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
