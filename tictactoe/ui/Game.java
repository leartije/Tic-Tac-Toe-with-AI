package tictactoe.ui;

import tictactoe.entity.Board;
import tictactoe.services.AIClass;
import tictactoe.services.GameAnalyzer;
import tictactoe.services.InputService;

import static tictactoe.msg.Msg.*;

public class Game {
    private final Board board = new Board();
    private final GameAnalyzer gameAnalyzer = new GameAnalyzer(board);
    private final InputService inputService = new InputService(board);
    private final AIClass AIClass = new AIClass(gameAnalyzer);


    public void game() {
        String[] input = inputService.getCommands();
        if (EXIT.equals(input[0])) {
            return;
        }
        board.showBoard();

        String firstPlayer = input[0];
        String secondPlayer = input[1];

        while (true) {
            choosePlayer(firstPlayer, X);
            if (isOver()) {
                break;
            }
            choosePlayer(secondPlayer, O);
            if (isOver()) {
                break;
            }
        }
    }

    //
    private void choosePlayer(String player, char oX) {
        switch (player) {
            case USER-> user(oX);
            case EASY -> easy(oX);
            case MEDIUM -> medium(oX);
            case HARD -> hard(oX);

        }
    }

    private void user(char oX) {
        while (true) {
            int[] coordinates = inputService.getCoordinates();
            boolean isValid = inputService.insertCoordinates(coordinates, oX);
            if (isValid) {
                break;
            }
            System.out.println(OCCUPIED);
        }
    }

    private void easy(char oX) {
        System.out.println(MAKING_MOVE_EASY);
        while (true) {
            int[] coordinates = AIClass.aiEasy();
            boolean isValid = inputService.insertCoordinates(coordinates, oX);
            if (isValid) {
                break;
            }
        }
    }

    private void medium(char oX) {
        System.out.println(MAKING_MOVE_MEDIUM);
        while (true) {
            int[] coordinates = AIClass.aiMedium(oX);
            boolean isValid = inputService.insertCoordinates(coordinates, oX);
            if (isValid) {
                break;
            }
        }
    }

    private void hard(char oX) {
        System.out.println(MAKING_MOVE_DIE_HARD);
        while (true) {
            int[] coordinates = AIClass.aiHard(oX);
            boolean isValid = inputService.insertCoordinates(coordinates, oX);
            if (isValid) {
                break;
            }
        }
    }


    private boolean isOver() {
        board.showBoard();
        String s = gameAnalyzer.gameStatus();
        if (!s.equals(GAME_NOT_FINISHED)) {
            System.out.println(s);
        }
        return !s.equals(GAME_NOT_FINISHED);
    }


}
