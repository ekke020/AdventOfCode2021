import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class ChallengeTwo {

    private static int horizontal;
    private static int depth;

    public static void main(String[] args) throws IOException {

        try(Scanner scanner = new Scanner(new FileReader("resources/position.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] content = line.split(" ");
                calculatePosition(content[0], Integer.parseInt(content[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(horizontal * depth);
    }

    private static void calculatePosition(String direction, int number) {
        switch (direction) {
            case "forward" -> horizontal += number;
            case "down" -> depth += number;
            case "up" -> depth -= number;
        }
    }
}
