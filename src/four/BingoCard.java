package four;

import java.util.Arrays;
import java.util.List;

public class BingoCard {

    private final int[][] board = new int[5][5];
    private final boolean[][] markedNumbers = new boolean[5][5];
    private boolean bingo = false;

    public boolean isBingo() {
        return bingo;
    }

    public BingoCard(List<String> numbers) {
        populateBoard(numbers);
    }

    private void populateBoard(List<String> numbers) {
        int index = 0;
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                board[r][c] = Integer.parseInt(numbers.get(index++));
            }
        }
    }

    public void checkBoard(int number) {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (board[r][c] == number) {
                    markedNumbers[r][c] = true;
                }
            }
        }
    }

    public boolean getBingoStatus() {
        boolean[] column = new boolean[5];
        var wrapper = new Object(){boolean bingo = false; };

        Arrays.stream(markedNumbers).forEach(row -> {
            if(isBingo(row)) {
                bingo = true;
                wrapper.bingo = true;
            }
        });
        for (int c = 0; c < 5; c++) {
            for (int r = 0; r < 5; r++) {
                column[r] = markedNumbers[r][c];
            }
            if(isBingo(column)) {
                bingo = true;
                wrapper.bingo = true;
            }
        }
        return wrapper.bingo;
    }

    private boolean isBingo(boolean[] row) {
        Boolean[] result = new Boolean[5];
        for (int i = 0; i < 5; i++) {
            result[i] = row[i];
        }
        return !Arrays.asList(result).contains(false);
    }

    public void printBoard() {
        Arrays.asList(markedNumbers).forEach(row -> System.out.println(Arrays.toString(row)));
        System.out.println("-----------------");
        Arrays.asList(board).forEach(row -> System.out.println(Arrays.toString(row)));
    }

    public int calculateScore(int lastDraw) {
        int total = 0;
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if(!markedNumbers[r][c]) {
                    total += board[r][c];
                }
            }
        }
        return total * lastDraw;
    }
}
