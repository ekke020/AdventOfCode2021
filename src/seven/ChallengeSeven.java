package seven;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ChallengeSeven {

    private static List<Integer> positions;
    private static final List<Integer> RESULTS_PART_ONE = new ArrayList<>();
    private static final List<Integer> RESULTS_PART_TWO = new ArrayList<>();
    private static int highPoint;
    private static int lowPoint;

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(new FileReader("resources/crab_position.txt"))) {
            String[] line = scanner.nextLine().split(",");
            positions = parseIntArray(List.of(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        highPoint = positions.stream().max(Comparator.comparingInt(o -> o)).get();
        lowPoint = positions.stream().min(Comparator.comparingInt(o -> o)).get();
        moveCrabsToPosition();
        RESULTS_PART_ONE.sort(Comparator.comparingInt(o -> o));
        System.out.println("Part one: " + RESULTS_PART_ONE.get(0));
        RESULTS_PART_TWO.sort(Comparator.comparingInt(o -> o));
        System.out.println("Part Two: " + RESULTS_PART_TWO.get(0));
    }

    private static List<Integer> parseIntArray(List<String> arr) {
        return arr.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    private static void moveCrabsToPosition() {
        int laps = lowPoint;
        int total = 0;
        while (laps <= highPoint) {
            int steps = laps;
            total += positions.stream().map(numb -> Math.abs(steps - numb)).mapToInt(numb -> numb).sum();
            RESULTS_PART_ONE.add(total);
            total = 0;
            total += positions.stream().map(numb -> getCost(Math.abs(steps - numb))).mapToInt(numb -> numb).sum();
            RESULTS_PART_TWO.add(total);
            laps++;
            total = 0;
        }
    }
    private static int getCost(int sum) {
        int cost = 0;
        for (int i = 0; i <= sum; i++) {
            cost += i;
        }
        return cost;
    }
}
