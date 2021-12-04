package four;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ChallengeFour {

    private static String[] numbersToDraw;
    private static List<BingoCard> cards = new ArrayList<>();

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(new FileReader("resources/boards.txt"))){
            String line = scanner.nextLine();
            numbersToDraw = line.split(",");
            scanner.nextLine();
            loadBoards(scanner);
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawNumbers();
    }

    private static void loadBoards(Scanner scanner) {
        String line;
        List<String> numbers = new ArrayList<>();
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.isEmpty()) {
                numbers.removeAll(Arrays.asList("", null));
                BingoCard bingoCard = new BingoCard(numbers);
                cards.add(bingoCard);
                numbers.clear();
            } else {
                String[] arr = line.split(" ");
                Collections.addAll(numbers, arr);
            }
        }
    }

    private static void drawNumbers() {
        for (String s : numbersToDraw) {
            int num = Integer.parseInt(s);
            for (BingoCard card : cards) {
                card.checkBoard(num);
                if (!card.isBingo() && card.getBingoStatus()) {
                    card.printBoard();
                    System.out.println("------------------");
                    System.out.println("Final score is: " + card.calculateScore(num) + "\n");
                }
            }
        }
    }

}
