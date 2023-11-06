import entity.SinglePlayerGame;
import entity.Game;
import use_case.setup_game.SetupInteractor;
import View.Gameview;
import View.Homeview;

import javax.swing.*;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        SetupInteractor setupInteractor = new SetupInteractor();
        setupInteractor.execute();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Homeview homeview = new Homeview();
                homeview.setVisible(true);
            }
        });
    }
}
