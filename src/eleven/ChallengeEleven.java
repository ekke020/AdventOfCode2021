package eleven;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChallengeEleven {

    private static final List<List<Octopus>> OCTOPUS_LIST = new ArrayList<>();

    public static void main(String[] args) {
        try {
            List<Octopus> octopuses = Files.lines(Path.of("resources/test.txt"))
                    .map(e -> e.split(""))
                    .flatMap(Arrays::stream)
                    .mapToInt(Integer::valueOf)
                    .mapToObj(Octopus::new)
                    .toList();
            for (int i = 0; i < 25; i+= 5) {
                OCTOPUS_LIST.add(octopuses.subList(i, i + 5));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        printLevelMap();
        increaseLevelByOne();
        printLevelMap();
    }

    private static void increaseLevelByOne() {
        OCTOPUS_LIST.stream()
                .flatMap(List::stream)
                .forEach(Octopus::incrementLevel);
    }

    private static void printLevelMap() {
        for (List<Octopus> octopi : OCTOPUS_LIST) {
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
