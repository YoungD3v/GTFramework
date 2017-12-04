package gtframework.domains.tictactoe;

import gtframework.domains.interfaces.*;
import gtframework.domains.interfaces.Player;

/**
 * Created by YoungD3v on 4.12.2017.
 */
public class Action implements gtframework.domains.interfaces.Action{

    private gtframework.domains.tictactoe.Player player;
    private Position position = null;
    private int x;
    private int y;

    public Action(Position position, gtframework.domains.tictactoe.Player player) {
        this.position = position;
        this.player = player;
    }

    public Action(int x, int y, gtframework.domains.tictactoe.Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public String getName() {
        return  "[" + this.player + ", " + this.position + "]";
    }

    @Override
    public int[] getValue() {
        return new int[]{x, y};
    }

    @Override
    public String toString() {
        return "[" +
                "[" + x +
                ", " + y +
                "], " + player +
                ']';
    }
}
