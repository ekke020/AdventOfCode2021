package nine;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ChallengeNine {

    private static final int[][] HEIGHTMAP = new int[100][100];
    private static final List<PointMap> POINTS = new ArrayList<>();
    private static final List<Integer> BASIN_SIZES = new ArrayList<>();

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
        checkMapPartOne();
        checkMapPartTwo();
        BASIN_SIZES.sort((o1, o2) -> o2 - o1);
        System.out.println(BASIN_SIZES.get(0) * BASIN_SIZES.get(1) * BASIN_SIZES.get(2));
    }

    private static void operateLine(String line, int index) {
        String[] numbers = line.split("");
        for (int i = 0; i < 100; i++) {
            HEIGHTMAP[index][i] = Integer.parseInt(numbers[i]);
        }
    }

    private static void checkMapPartOne() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                getSurroundingNumbers(i,j);
            }
        }
        AtomicInteger score = new AtomicInteger();
        POINTS.forEach(point -> score.updateAndGet(operand -> operand += point.getLowPointValue()));
        System.out.println(score.get());
    }

    private static void getSurroundingNumbers(int row, int col) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(HEIGHTMAP[row][col]);
        if (col - 1 > -1)
            numbers.add(HEIGHTMAP[row][col - 1]);
        if (col + 1 < 100)
            numbers.add(HEIGHTMAP[row][col + 1]);
        if (row - 1 > -1)
            numbers.add(HEIGHTMAP[row - 1][col]);
        if (row + 1 < 100)
            numbers.add(HEIGHTMAP[row + 1][col]);

        POINTS.add(new PointMap(numbers));
    }

    private static void checkMapPartTwo() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (HEIGHTMAP[i][j] != 9) {
                    BASIN_SIZES.add(checkSurrounding(i, j));
                }
            }
        }
    }

    private static int checkSurrounding(int row, int col) {
        int total = 0;
        if (col - 1 > -1 && HEIGHTMAP[row][col - 1] != 9) {
            total++;
            HEIGHTMAP[row][col - 1] = 9;
            total += checkSurrounding(row, col - 1);
        }
        if (col + 1 < 100 && HEIGHTMAP[row][col + 1] != 9) {
            total++;
            HEIGHTMAP[row][col + 1] = 9;
            total += checkSurrounding(row, col + 1);
        }
        if (row - 1 > -1 && HEIGHTMAP[row - 1][col] != 9) {
            total++;
            HEIGHTMAP[row - 1][col] = 9;
            total += checkSurrounding(row - 1, col);
        }
        if (row + 1 < 100 && HEIGHTMAP[row + 1][col] != 9) {
            total++;
            HEIGHTMAP[row + 1][col] = 9;
            total += checkSurrounding(row + 1, col);
        }
        return total;
    }

    private record PointMap(List<Integer> points) {
        public int getLowPointValue() {
            for (int i = 1; i < points.size(); i++) {
                if (points.get(0) >= points.get(i)) {
                    return 0;
                }
            }
            return points.get(0) + 1;
        }
    }

}
