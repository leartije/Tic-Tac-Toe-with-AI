package tictactoe.msg;

public class Msg {


    //input
    public static final String INPUT_COMMAND = "Input command: ";
    public static final String ENTER_COORDINATES = "Enter the coordinates: ";
    public static final String MAKING_MOVE_EASY = "Making move level \"easy\"";
    public static final String MAKING_MOVE_MEDIUM = "Making move level \"medium\"";

    public static final String MAKING_MOVE_DIE_HARD = "Making move level \"hard\"";

    //errors
    public static final String OCCUPIED = "This cell is occupied! Choose another one!";
    public static final String ONLY_NUMBERS = "You should enter numbers!";
    public static final String WRONG_COORDINATES = "Coordinates should be from 1 to 3!";
    public static final String WRONG_FORMAT = "Enter two numbers separate by space";
    public static final String BAD_PARAMETERS = "Bad parameters!";


    //game status
    public static final String GAME_NOT_FINISHED = "Game not finished";
    public static final String DRAW = "Draw";
    public static final String X_WINS = "X wins";
    public static final String O_WINS = "O wins";


    //constants
    public static final String EXIT = "exit";
    public static final String START = "start";
    public static final char X = 'X';
    public static final char O = 'O';
    public static final char SPACE = ' ';
    public static final String USER = "user";
    public static final String EASY = "easy";
    public static final String MEDIUM = "medium";
    public static final String HARD = "hard";



}
