package eleven;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ChallengeEleven {

    private static final List<List<Octopus>> OCTOPUS_LIST = new ArrayList<>();
    private static int flashes = 0;
    private static int steps = 0;
    public static void main(String[] args) {
        try {
            List<Octopus> octopuses = Files.lines(Path.of("resources/octopuses.txt"))
                    .map(e -> e.split(""))
                    .flatMap(Arrays::stream)
                    .mapToInt(Integer::valueOf)
                    .mapToObj(Octopus::new)
                    .toList();
            for (int i = 0; i < 100; i+= 10) {
                OCTOPUS_LIST.add(octopuses.subList(i, i + 10));
            }
            for (int y = 0; y < OCTOPUS_LIST.size(); y++) {
                for (int x = 0; x < OCTOPUS_LIST.get(0).size(); x++) {
                    OCTOPUS_LIST.get(y).get(x).x = x;
                    OCTOPUS_LIST.get(y).get(x).y = y;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(isAllSynced()) {
            increaseLevelByOne();
            steps++;
            takeStep();
            if (steps == 100)
                System.out.println(flashes);
        }
        System.out.println(steps);
    }

    private static boolean isAllSynced() {
        return OCTOPUS_LIST.stream().flatMap(List::stream).anyMatch(o -> o.level != 0);
    }
    private static void takeStep() {
        boolean stepIsOver = true;
        while (stepIsOver) {
            OCTOPUS_LIST.stream()
                    .flatMap(List::stream)
                    .forEach(ChallengeEleven::checkIfOctopusShouldFlash);
            stepIsOver = OCTOPUS_LIST.stream()
                    .flatMap(List::stream)
                    .anyMatch(Octopus::stillNeedsToFlash);
        }
    }

    private static void increaseLevelByOne() {
        OCTOPUS_LIST.stream()
                .flatMap(List::stream)
                .forEach(o -> o.level++);
    }

    private static void checkIfOctopusShouldFlash(Octopus octopus) {
        if (octopus.level > 9) {
            incrementOctopusNeighbors(octopus.y, octopus.x);
            octopus.level = 0;
            flashes++;
        }
    }

    private static void incrementOctopusNeighbors(int y, int x) {
        if (y < 9) {
            OCTOPUS_LIST.get(y + 1).get(x).incrementLevel(); // down
        }
        if (y > 0) {
            OCTOPUS_LIST.get(y - 1).get(x).incrementLevel(); // up
        }

        if (x < 9) {
            OCTOPUS_LIST.get(y).get(x + 1).incrementLevel(); // right
            if (y < 9)
                OCTOPUS_LIST.get(y + 1).get(x + 1).incrementLevel(); // down right
            if (y > 0)
                OCTOPUS_LIST.get(y - 1).get(x + 1).incrementLevel(); // up right
        }
        if (x > 0) {
            OCTOPUS_LIST.get(y).get(x - 1).incrementLevel(); // left
            if (y < 9)
                OCTOPUS_LIST.get(y + 1).get(x - 1).incrementLevel(); //  down left
            if (y > 0)
                OCTOPUS_LIST.get(y - 1).get(x - 1).incrementLevel(); // up left
        }
    }

    private static class Octopus {
        private int x;
        private int y;
        private int level;

        public Octopus(int level) {
            this.level = level;
        }

        public boolean stillNeedsToFlash() {
            return level > 9;
        }

        public void incrementLevel() {
            if (level != 0)
                level++;
        }
    }
}
