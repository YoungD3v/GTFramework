import gtframework.domains.tictactoe.Game;
import gtframework.domains.tictactoe.Player;

public class Main {

    public static void main(String[] args) {

        Game ttt = new Game(2);
        Player currentPlayer = ttt.getNextPlayer();

        while (!ttt.isGameEnd()) {
            ttt.playAction(currentPlayer.selectNextAction(ttt.getNextActions()));
            currentPlayer = ttt.getNextPlayer();
            System.out.println(ttt);
        }

    }
}
