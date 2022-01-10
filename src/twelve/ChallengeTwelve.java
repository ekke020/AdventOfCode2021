package twelve;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ChallengeTwelve {

    private static Map<String, List<String>> caveSystem = new HashMap<>();
    private static Set<String> partOnePaths = new HashSet<>();
    private static Set<String> partTwoPaths = new HashSet<>();

    public static void main(String[] args) {
        try {
            Files.lines(Path.of("resources/paths.txt"))
                    .map(o -> o.split("-")).forEach(line -> {
                        if (caveSystem.putIfAbsent(line[0], new ArrayList<>(List.of(line[1]))) != null) {
                                caveSystem.get(line[0]).add(line[1]);
                        }
                        if (caveSystem.putIfAbsent(line[1], new ArrayList<>(List.of(line[0]))) != null)
                                caveSystem.get(line[1]).add(line[0]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String path : caveSystem.get("start")) {
            getPathsPartOne(path, "START");
            getPathsPartTwo(path, "START", false);
        }
        System.out.println("Part one: " + partOnePaths.size());
        System.out.println("Part two: " + partTwoPaths.size());
    }

    private static void getPathsPartOne(String cave, String currentPath) {
        currentPath += "," + cave;
        if (cave.equals("end")) {
            partOnePaths.add(currentPath);
            return ;
        }
        for (String path : caveSystem.get(cave)) {
            if (!path.equals("start") && !currentPath.contains(path.toLowerCase())) {
                getPathsPartOne(path, currentPath);
            }
        }
    }

    private static void getPathsPartTwo(String cave, String currentPath, boolean visit) {
        currentPath += "," + cave;
        if (cave.equals("end")) {
            partTwoPaths.add(currentPath);
            return ;
        }
        for (String path : caveSystem.get(cave)) {
            if (!path.equals("start") && !currentPath.contains(path.toLowerCase())) {
                getPathsPartTwo(path, currentPath, visit);
            } else if (!path.equals("start") && !visit){
                getPathsPartTwo(path, currentPath, true);
            }
        }
    }
}
