package tictactoe.services;

import tictactoe.entity.Board;

import static tictactoe.msg.Msg.*;

public class GameAnalyzer {
    private final Board board;
    private final int[][] WIN_CONDITIONS = {
            {0, 0, 0, 1, 0, 2}, {1, 0, 1, 1, 1, 2}, {2, 0, 2, 1, 2, 2},
            {0, 0, 1, 0, 2, 0}, {0, 1, 1, 1, 2, 1}, {0, 2, 1, 2, 2, 2},
            {0, 0, 1, 1, 2, 2}, {2, 0, 1, 1, 0, 2}
    };

    public GameAnalyzer(Board board) {
        this.board = board;
    }

    public String gameStatus() {

        int xs;
        int os;

        for (int[] win_condition : WIN_CONDITIONS) {
            xs = 0;
            os = 0;
            for (int j = 0; j < win_condition.length; j += 2) {
                if (board.getBoard()[win_condition[j]][win_condition[j + 1]] == X) {
                    xs++;
                    if (xs == 3) {
                        return X_WINS;
                    }
                    continue;
                }
                if (board.getBoard()[win_condition[j]][win_condition[j + 1]] == O) {
                    os++;
                    if (os == 3) {
                        return O_WINS;
                    }
                }
            }
        }

        if (board.getNumOfX() + board.getNumOfO() == 9) {
            return DRAW;
        }
        return GAME_NOT_FINISHED;
    }

    public int[] mediumAIMove(char player) {

        int tempI = -1;
        int tempJ = -1;


        int[] coordinates = new int[2];
        int xs;
        int os;
        boolean fresh;

        for (int[] win_condition : WIN_CONDITIONS) {
            xs = 0;
            os = 0;
            fresh = false;

            for (int j = 0; j < win_condition.length; j += 2) {
                if (board.getBoard()[win_condition[j]][win_condition[j + 1]] == X) {
                    xs++;
                }
                if (board.getBoard()[win_condition[j]][win_condition[j + 1]] == O) {
                    os++;
                }
                if (board.getBoard()[win_condition[j]][win_condition[j + 1]] == SPACE) {
                    coordinates[0] = win_condition[j];
                    coordinates[1] = win_condition[j + 1];
                    fresh = true;
                }

                if (player == X && xs == 2 && fresh) {
                    return coordinates;
                } else if (player == X && os == 2 && fresh) {
                    tempI = coordinates[0];
                    tempJ = coordinates[1];
                    continue;
                }

                if (player == O && os == 2 && fresh) {
                    return coordinates;
                } else if (player == O && xs == 2 && fresh) {
                    tempI = coordinates[0];
                    tempJ = coordinates[1];
                }


            }
        }

        return tempI == -1 ? null : new int[]{tempI, tempJ};
    }

    public int[] bestMove(char player) {

        int bestValForX = Integer.MIN_VALUE;
        int bestValForO = Integer.MAX_VALUE;

        int[] coord = new int[2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBoard()[i][j] == SPACE) {
                    board.getBoard()[i][j] = player;

                    int moveVal = minimax(0, X != player);

                    board.getBoard()[i][j] = SPACE;

                    if (X == player) {
                        if (moveVal > bestValForX) {
                            bestValForX = moveVal;
                            coord[0] = i;
                            coord[1] = j;
                        }
                    }

                    if (O == player) {
                        if (moveVal < bestValForO) {
                            bestValForO = moveVal;
                            coord[0] = i;
                            coord[1] = j;
                        }
                    }
                }
            }
        }

        return coord;
    }


    //private
    private boolean isMovesLeft() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBoard()[i][j] == SPACE) {
                    return true;
                }
            }
        }
        return false;
    }

    private int evaluate() {
        String status = gameStatus();

        if (status.equals(X_WINS)) {
            return 1;
        }
        if (status.equals(O_WINS)) {
            return -1;
        }

        return 0;
    }

    private int minimax(int depth, boolean isMax) {

        int score = evaluate();

        if (score == 1) {
            return score;
        }
        if (score == -1) {
            return score;
        }
        if (!isMovesLeft()) {
            return 0;
        }

        int best;
        if (isMax) {

            best = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getBoard()[i][j] == SPACE) {
                        board.getBoard()[i][j] = X;

                        best = Math.max(best, minimax(depth + 1, false));

                        board.getBoard()[i][j] = SPACE;
                    }
                }
            }
        } else {

            best = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getBoard()[i][j] == SPACE) {
                        board.getBoard()[i][j] = O;

                        best = Math.min(best, minimax(depth + 1, true));
                        board.getBoard()[i][j] = SPACE;

                    }
                }
            }
        }
        return best;
    }
}
