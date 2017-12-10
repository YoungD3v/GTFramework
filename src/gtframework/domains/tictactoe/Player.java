package gtframework.domains.tictactoe;

import gtframework.domains.interfaces.*;
import gtframework.domains.tictactoe.Action;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import java.util.List;

/**
 * Created by YoungD3v on 4.12.2017.
 */
public class Player implements gtframework.domains.interfaces.Player {

    private int id;

    public Player(int id) {
        this.id = id;
    }

    @Override
    public int getPlayerId() {
        return this.id;
    }

    public String getPlayersChar() {
        if (this.id == 0) return "x";
        else if (this.id == 1) return "o";
        return "t";
    }

    public Action selectNextAction(List<Action> nextActions) {
        Random r = new Random(13);

        return nextActions.get(r.nextInt(nextActions.size()));
    }
}
