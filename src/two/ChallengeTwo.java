package two;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class ChallengeTwo {

    private static int horizontal;
    private static int depth;
    private static int aim;

    public static void main(String[] args) {

        try(Scanner scanner = new Scanner(new FileReader("resources/position.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                partTwo(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(horizontal * depth);
    }

    private static void partTwo(String line) {
        String[] content = line.split(" ");
        calculatePositionPartTwo(content[0], Integer.parseInt(content[1]));
    }

    private static void calculatePositionPartTwo(String direction, int number) {
        switch (direction) {
            case "forward" -> {
                horizontal += number;
                depth += aim * number;
            }
            case "down" -> aim += number;
            case "up" -> aim -= number;
        }
    }

    private static void partOne(String line) {
        String[] content = line.split(" ");
        calculatePositionPartOne(content[0], Integer.parseInt(content[1]));
    }

    private static void calculatePositionPartOne(String direction, int number) {
        switch (direction) {
            case "forward" -> horizontal += number;
            case "down" -> depth += number;
            case "up" -> depth -= number;
        }
    }
}
