package gtframework.domains.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by YoungD3v on 4.12.2017.
 */
public class Game {

    private final int N = 3;  // rows
    private final int M = 3;  // columns
    private final int LENGTH_OF_WINNING_STREAK = 3;
    private final int EMPTY_CELL = -1;

    private int[][] grid = new int[N][M];
    private List<Player> players = new ArrayList<>();
    private int nextPlayer = 0;

    public Game(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++)
            this.players.add(new Player(i));
        for (int[] row : grid)
            Arrays.fill(row, EMPTY_CELL);
    }

    public List<Action> getNextActions() {
        ArrayList<Action> nextActions = new ArrayList<>();

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (grid[i][j] == EMPTY_CELL) nextActions.add(new Action(i, j, players.get(nextPlayer)));

        return nextActions;
    }

    public void playAction(Action a) {

        if (grid[a.getValue()[0]][a.getValue()[1]] != EMPTY_CELL)
            throw new IllegalArgumentException("You must provide available action");

        grid[a.getValue()[0]][a.getValue()[1]] = a.getPlayer().getPlayerId();
        this.nextPlayer = (this.nextPlayer + 1) % players.size();
    }

    public Player getNextPlayer() {
        return players.get(nextPlayer);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean isGameEnd() {
        return this.getNextActions().isEmpty();
    }

    @Override
    public String toString() {
        return "Game{" +
                "grid=" + Arrays.deepToString(grid) +
                '}';
    }
}
