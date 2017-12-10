package gtframework.domains.tictactoe;

import gtframework.domains.interfaces.*;
import gtframework.domains.interfaces.Action;

import java.util.List;

/**
 * Created by YoungD3v on 6.12.2017.
 */
public class GameState implements gtframework.domains.interfaces.GameState {

    @Override
    public List<Action> getHistory() {
        return null;
    }

    // TODO table for keeping game state | GameState . Action = new GameState with independent representation of
        // GameState (table)

    // TODO Expander? - separate class

    // TODO isGameState end of the game?

    // TODO payoffs?

    // TODO check validity of action

    // TODO function for creating successor state


}
