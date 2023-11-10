import entity.SinglePlayerGame;
import entity.Game;
import use_case.setup_game.SetupInteractor;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        SetupInteractor setupInteractor = new SetupInteractor();
        setupInteractor.execute();
    }


}
