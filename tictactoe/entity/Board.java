package tictactoe.entity;

import tictactoe.msg.Msg;

import java.util.Arrays;

public class Board {
    private final char[][] board = new char[3][3];
    private int numOfX;
    private int numOfO;

    public Board() {
        for (char[] row : board) {
            Arrays.fill(row, Msg.SPACE);
        }
    }
    
    public void showBoard() {
        System.out.println("---------");
        for (int i = 0; i < getBoard().length; i++) {
            for (int j = 0; j < getBoard()[i].length; j++) {
                if (j == 0) {
                    System.out.print("| " + getBoard()[i][j]);
                    continue;
                }
                if (j == 2) {
                    System.out.print(getBoard()[i][j] + " |");
                    continue;
                }
                System.out.print(" " + getBoard()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------");
    }

    public char[][] getBoard() {
        return board;
    }

    public int getNumOfX() {
        return numOfX;
    }

    public void setNumOfX() {
        this.numOfX++;
    }

    public int getNumOfO() {
        return numOfO;
    }

    public void setNumOfO() {
        this.numOfO++;
    }
}
