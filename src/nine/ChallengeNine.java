package nine;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChallengeNine {

    private static final int[][] HEIGHTMAP = new int[100][100];
    private static final List<PointMap> POINTS = new ArrayList<>();

    public static void main(String[] args) {
        int i = 0;
        try (Scanner scanner = new Scanner(new FileReader("resources/points.txt"))){
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                operateLine(line, i++);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkMap();
        int score = 0;
        for (PointMap point : POINTS) {
            int value = point.getLowPointValue();
            if (value != -1)
                score += value;
        }
        System.out.println(score);
    }

    private static void operateLine(String line, int index) {
        String[] numbers = line.split("");
        for (int i = 0; i < 100; i++) {
            HEIGHTMAP[index][i] = Integer.parseInt(numbers[i]);
        }
    }

    private static void checkMap() {
        int text = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                getSurroundingNumbers(i,j);
                text++;
            }
        }
        System.out.println(text);
    }

    private static void getSurroundingNumbers(int row, int col) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(HEIGHTMAP[row][col]);
        int left = col - 1;
        if (left > -1)
            numbers.add(HEIGHTMAP[row][left]);
        int right = col + 1;
        if (right < 100)
            numbers.add(HEIGHTMAP[row][right]);
        int up = row - 1;
        if (up > -1)
            numbers.add(HEIGHTMAP[up][col]);
        int down = row + 1;
        if (down < 100) {
            numbers.add(HEIGHTMAP[down][col]);
        }
        POINTS.add(new PointMap(numbers));
    }

    private record PointMap(List<Integer> points) {
        public int getLowPointValue() {
            for (int i = 1; i < points.size(); i++) {
                if (points.get(0) >= points.get(i)) {
                    return -1;
                }
            }
            return points.get(0) + 1;
        }
    }
}
