package gtframework.domains.tictactoe;

import gtframework.domains.interfaces.*;
import gtframework.domains.tictactoe.Action;
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

    @Override
    public Action getNextAction(GameState gs) {
        return null;
    }

    public Action selectNextAction(List<Action> nextActions) {
        int randomAction = ThreadLocalRandom.current().nextInt(0, nextActions.size());
        return nextActions.get(randomAction);
    }
}
