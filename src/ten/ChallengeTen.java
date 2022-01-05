package ten;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class ChallengeTen {

    private static List<String> lines = new ArrayList<>();
    private static int scorePartOne;
    private static BigInteger completionStringScore;
    private static List<BigInteger> partTwoScores = new ArrayList<>();

    public static void main(String[] args) {
        try {
            lines = Files.lines(Path.of("resources/chunks.txt")).map(String::trim).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lines.forEach(ChallengeTen::syntaxCheck);
        System.out.println(scorePartOne);
        Collections.sort(partTwoScores);
        System.out.println(partTwoScores.get(partTwoScores.size() / 2));
    }

    private static void syntaxCheck(String line) {
        LinkedList<String> list = new LinkedList<>();

        boolean isComplete = true;
        for (String s : line.split("")) {
            if (s.matches("[]}>)]")) {
                if (list.peek().equals(getOpposite(s))) {
                    list.pop();
                } else {
                    calculateScorePartOne(s);
                    isComplete = false;
                    break;
                }
            } else {
                list.push(s);
            }
        }
        if (isComplete) {
            checkPartTwo(list);
        }
    }

    private static String getOpposite(String bracket) {
        switch(bracket) {
            case ")" -> {return "(";}
            case "}" -> {return "{";}
            case ">" -> {return "<";}
            default -> {return "[";}
        }
    }

    private static void calculateScorePartOne(String peek) {
        switch(peek) {
            case ")" -> scorePartOne += 3;
            case "]" -> scorePartOne += 57;
            case "}" -> scorePartOne += 1197;
            case ">"  -> scorePartOne += 25137;
        }
    }

    private static void checkPartTwo(LinkedList<String> list) {
        completionStringScore = BigInteger.ZERO;
        list.forEach(ChallengeTen::calculateScorePartTwo);
        partTwoScores.add(completionStringScore);
    }

    private static void calculateScorePartTwo(String e) {
        completionStringScore = completionStringScore.multiply(new BigInteger("5"));
        switch(e) {
            case "(" -> completionStringScore = completionStringScore.add(new BigInteger("1"));
            case "[" -> completionStringScore = completionStringScore.add(new BigInteger("2"));
            case "{" -> completionStringScore = completionStringScore.add(new BigInteger("3"));
            case "<"  -> completionStringScore = completionStringScore.add(new BigInteger("4"));
        }
    }

}
