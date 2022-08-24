package tictactoe.services;

import tictactoe.entity.Board;

import java.util.Scanner;

import static tictactoe.msg.Msg.*;

public class InputService {

    private final Scanner scanner = new Scanner(System.in);
    private final Board board;

    private final String[] validCommands = {USER, EASY, MEDIUM, HARD};

    public InputService(Board board) {
        this.board = board;
    }

    public int[] getCoordinates() {
        while (true) {
            System.out.print(ENTER_COORDINATES);
            String[] input = scanner.nextLine().trim().split("\\s");
            if (input.length != 2) {
                System.out.println(WRONG_FORMAT);
                continue;
            }
            int[] coordinates = new int[2];
            try {
                coordinates[0] = Integer.parseInt(input[0]) - 1;
                coordinates[1] = Integer.parseInt(input[1]) - 1;
            } catch (NumberFormatException e) {
                System.out.println(ONLY_NUMBERS);
                continue;
            }
            if (coordinates[0] > 2 || coordinates[0] < 0 || coordinates[1] > 2 || coordinates[1] < 0) {
                System.out.println(WRONG_COORDINATES);
                continue;
            }
            return coordinates;
        }
    }

    public boolean insertCoordinates(int[] coor, char xO) {
        int i = coor[0];
        int j = coor[1];

        if (board.getBoard()[i][j] == X || board.getBoard()[i][j] == O) {
            return false;
        }
        board.getBoard()[i][j] = xO;
        if (xO == X) {
            board.setNumOfX();
        } else {
            board.setNumOfO();
        }
        return true;
    }

    public String[] getCommands() {
        while (true) {
            System.out.print(INPUT_COMMAND);
            String[] commands = scanner.nextLine().trim().toLowerCase().split("\\s");
            if (commands.length == 1 && EXIT.equals(commands[0])) {
                return new String[]{commands[0]};
            }
            if (commands.length == 3 && START.equals(commands[0])) {
                int count = 0;
                for (int i = 1; i < commands.length; i++) {
                    for (String validCommand : validCommands) {
                        if (commands[i].equals(validCommand)) {
                            count++;
                            if (count == 2) {
                                return new String[]{commands[1], commands[2]};
                            }
                        }
                    }
                }
            }
            System.out.println(BAD_PARAMETERS);
        }
    }
}

