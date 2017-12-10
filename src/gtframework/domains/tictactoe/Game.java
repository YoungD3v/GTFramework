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
    private final int LENGTH_OF_WINNING_STREAK = 2;
    private final int EMPTY_CELL = -1;

    private int[][] grid = new int[N][M];
    private List<Player> players = new ArrayList<>();
    private int nextPlayer = 0;
    private Player winner = null;

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
        if (this.getNextActions().isEmpty()) return true;

        // look for winning row
        for (int i = 0; i < N; i++) {
            if (checkStreakInDirection(i, 0, 0)) return true;
        }

        // look for winning column
        for (int j = 0; j < M; j++) {
           if (checkStreakInDirection(0, j, 1)) return true;
        }

        // look for winning diagonal from left to right
        for (int i = 0; i <= N - LENGTH_OF_WINNING_STREAK; i++) {
            if (checkStreakInDirection(i, 0, 2)) return true;
        }

        for (int i = 0; i <= M - LENGTH_OF_WINNING_STREAK; i++) {
            if(checkStreakInDirection(0, i, 2)) return true;
        }

        // look for winning diagonal from left to right
        for (int i = LENGTH_OF_WINNING_STREAK-1; i < N; i++) {
            if(checkStreakInDirection(i, 0, 3)) return true;
        }

        for (int i = LENGTH_OF_WINNING_STREAK-1; i < M; i++) {
            if(checkStreakInDirection(0, i, 3)) return true;
        }

        return false;
    }

    public double[] getPayoffs() {
        if (!this.isGameEnd()) return null;

        double WIN = 1.0;
        double DRAW = -0.1;
        double LOSS = -1.0;

        double[] payoffs = new double[this.players.size()];


        if (this.getNextActions().isEmpty()) {
            Arrays.fill(payoffs, DRAW);
        } else {
            int winningPlayerId = this.winner.getPlayerId();
            for (int i = 0; i < this.players.size(); i++) {
                if (i == winningPlayerId) payoffs[i] = WIN;
                else payoffs[i] = LOSS;
            }
        }

        return payoffs;
    }

    /**
     * @param startX
     * @param startY
     * @param direction 0,1,2,3 // row, columns, left-right diag, right-left diag
     * @return
     */
    private boolean checkStreakInDirection(int startX, int startY, int direction) {

        return getWinningPlayerInDirection(startX, startY, direction) != -1;
    }

    private int getWinningPlayerInDirection(int startX, int startY, int direction) {

        int counter = 0, plrId = -1, cell, endOfDirection, iInit;

        // Check ROW
        if (direction == 0 || direction == 2) {
            endOfDirection = M;
            iInit = startY;
        } else {
            endOfDirection = N;
            iInit = startX;
        }
        for (int i = iInit; i < endOfDirection; i++) {
            if (direction == 0)
                cell = grid[startX][i];
            else if (direction == 1)
                cell = grid[i][startY];
            else if (direction == 2) {
                cell = grid[startX++][i];
                if (startX >= N) break;
            }
            else {
                cell = grid[i][startY++];
                if (startY >= M) break;
            }

            if (cell == -1 || (plrId != -1 && cell != plrId)) {
                plrId = -1; counter = 0;
                continue;
            }

            if (plrId == -1) {
                plrId = cell;
                counter++;
            }
            else if (cell == plrId) counter++;

            if (counter == LENGTH_OF_WINNING_STREAK) {
                this.winner = this.players.get(plrId);
                return plrId;
            }
        }

        return -1;
    }

    @Override
    public String toString() {
        return "Game{\n" +
                this.gridToString() +
                '}';
    }

    public String gridToString() {
        String s  = "";
        for (int[] row : this.grid) {
            for (int cell : row) {
                s += cell + "\t";
            }
            s += "\n";
        }

        return s;
    }
}
