package tictactoe.services;

import java.util.Random;

public class AIClass {
    private final GameAnalyzer gameAnalyzer;
    private final Random random = new Random();

    public AIClass(GameAnalyzer gameAnalyzer) {
        this.gameAnalyzer = gameAnalyzer;
    }

    public int[] aiEasy() {
        int i = random.nextInt(3);
        int j = random.nextInt(3);
        return new int[]{i, j};
    }

    public int[] aiMedium(char player) {
        int[] move = gameAnalyzer.mediumAIMove(player);
        if (move == null) {
            return aiEasy();
        }
        return move;
    }

    public int[] aiHard(char player) {
        return gameAnalyzer.bestMove(player);
    }

}
