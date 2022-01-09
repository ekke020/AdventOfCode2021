package twelve;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ChallengeTwelve {

    private static Map<String, List<String>> caveSystem;
    private static final Set<String> PATHS = new HashSet<>();

    public static void main(String[] args) {
        try {
            caveSystem =  Files.lines(Path.of("resources/test.txt"))
                    .map(o -> o.split("-"))
                    .collect(Collectors.groupingBy(a -> a[0],
                                    Collectors.mapping(a -> a[1], Collectors.toList())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        printMap();
    }

    private static void getPaths() {
        for (String start : caveSystem.get("start")) {

        }
    }

    private static void getToEnd() {
        String path = "";
        while (true) {
            caveSystem.get("start").forEach()
        }
    }

    private static void printMap() {
        for (Map.Entry<String, List<String>> e: caveSystem.entrySet()) {
            System.out.println("Key: " + e.getKey());
            for (String s : e.getValue()) {
                System.out.println("\tValue: " + s);
            }
        }
    }
}
