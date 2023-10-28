import entity.SinglePlayerGame;
import entity.Game;

public class Main {

    public static void main(String[] args) {
        Game game = new SinglePlayerGame();
        System.out.println(game.drawCard(1));
    }
}
