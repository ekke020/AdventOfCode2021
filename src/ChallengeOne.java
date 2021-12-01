import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChallengeOne {

    private static int previousMeasurement;
    private static int currentMeasurement;
    private static int count = 0;

    public static void main(String[] args) {
        partOne();
        count = 0;
        partTwo();
    }

    private static void partOne() {
        try(Scanner scanner = new Scanner(new FileReader("resources/measurements.txt"))) {
            previousMeasurement = scanner.nextInt();
            while (scanner.hasNextLine()) {
                currentMeasurement = scanner.nextInt();
                if (isMeasurementIncreased()) {
                    count++;
                }
                previousMeasurement = currentMeasurement;
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void partTwo() {
        List<Integer> list = new ArrayList<>();
        try(Scanner scanner = new Scanner(new FileReader("resources/measurements.txt"))) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextInt());
                if (list.size() == 4) {
                    addMeasurementsTogether(list);
                    if (isMeasurementIncreased()) {
                        count++;
                    }
                    list.remove(0);
                    previousMeasurement = 0;
                    currentMeasurement = 0;
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addMeasurementsTogether(List<Integer> list) {
        for (int i = 0; i < 3; i++) {
            previousMeasurement += list.get(i);
        }
        for (int i = 1; i < 4; i++) {
            currentMeasurement += list.get(i);
        }
    }

    private static boolean isMeasurementIncreased() {
        return currentMeasurement > previousMeasurement;
    }

}
