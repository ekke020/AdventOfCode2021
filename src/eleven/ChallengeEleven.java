package eleven;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChallengeEleven {

    private static Octopus[][] octopusList = new Octopus[5][5];

    public static void main(String[] args) {
        Octopus[] arg = null;
        try {
            arg = Files.lines(Path.of("resources/test.txt"))
                    .map(e -> e.split(""))
                    .flatMap(Arrays::stream)
                    .mapToInt(Integer::valueOf)
                    .mapToObj(Octopus::new)
                    .toArray(Octopus[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int row = 0;
        int col = 0;
        for (Octopus octopus : arg) {
            octopusList[row][col++] = octopus;
            if (col == 5) {
                row++;
                col = 0;
            }
        }
//        System.out.println(Arrays.deepToString(octopusList));
        printLevelMap();
    }

    private static void increaseLevelByOne() {
        Arrays.stream(octopusList)
                .flatMap(Arrays::stream)
                .forEach(Octopus::incrementLevel);
    }

    private static void printLevelMap() {
        for (Octopus[] octopi : octopusList) {
            for (Octopus octopus : octopi) {
                System.out.print(octopus.level + " ");
            }
            System.out.println();
        }
    }
    private static class Octopus {

        private int level;
        private boolean hasFlashed = false;

        public Octopus(int level) {
            this.level = level;
        }

        public void resetFlash() {
            hasFlashed = false;
        }

        public void incrementLevel() {
            if (!hasFlashed)
                level++;
            if (level == 10) {
                level = 0;
                hasFlashed = true;
            }
        }
    }
}
