package five;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ChallengeFive {

    private static final List<Integer> X_1 = new ArrayList<>();
    private static final List<Integer> X_2 = new ArrayList<>();
    private static final List<Integer> Y_1 = new ArrayList<>();
    private static final List<Integer> Y_2 = new ArrayList<>();
    private static int xMax = 0;
    private static int yMax = 0;
    private static int[][] map;

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(new FileReader("resources/test.txt"))){
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine().replace(",", " ");
                line = line.replace("-> ", "");
                String[] coords = line.split(" ");
                X_1.add(Integer.parseInt(coords[0]));
                Y_1.add(Integer.parseInt(coords[1]));
                X_2.add(Integer.parseInt(coords[2]));
                Y_2.add(Integer.parseInt(coords[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int size = getMinAndMax() + 1;
        map = new int[size][size];
        test();
        for (int[] column : map) {
            for (int row : column) {
                System.out.print(row + " ");
            }
            System.out.println();
        }
        calculateOverlap();
    }

    private static int getMinAndMax() {
        X_1.forEach(o -> {
            if (o > xMax)
                xMax = o;
        });
        Y_1.forEach(o -> {
            if (o > yMax)
                yMax = o;
        });
        return Math.max(yMax, xMax);
    }

    private static void test() {
        for (int i = 0; i < 10; i++) {
            if (X_1.get(i).equals(X_2.get(i))) {
                int yMin = Math.min(Y_1.get(i), Y_2.get(i));
                for (int j = yMin; j <= Math.max(Y_1.get(i), Y_2.get(i)) ; j++) {
                    map[j][X_1.get(i)]++;
                }
            } else if (Y_1.get(i).equals(Y_2.get(i))) {
                    int xMin = Math.min(X_1.get(i), X_2.get(i));
                for (int j = xMin; j <= Math.max(X_1.get(i), X_2.get(i)); j++) {
                    map[Y_1.get(i)][j]++;
                }
            } else {

            }
        }
    }

    private static void calculateOverlap() {
        int score = 0;
        for (int[] column : map) {
            for (int row : column) {
                if (row > 1)
                    score++;
            }
        }
        System.out.println("Score: " + score);
    }

}
