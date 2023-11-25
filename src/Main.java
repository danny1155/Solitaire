import View.Gameview;
import View.HomeViewModel;
import View.Homeview;
import View.ViewManager;
import interface_adapter.Setup.SetupViewModel;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame application = new JFrame("Solitaire");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //application.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //application.setUndecorated(false);
        application.setSize(1100,800);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        HomeViewModel homeViewModel = new HomeViewModel();
        SetupViewModel setupViewModel = new SetupViewModel();

        Homeview homeView = SetupUseCaseFactory.create(viewManagerModel, homeViewModel, setupViewModel);
        views.add(homeView, homeView.viewName);

        Gameview gameView = SetupGameUseCaseFactory.create(viewManagerModel, setupViewModel);
        views.add(gameView, gameView.viewName);

        viewManagerModel.setActiveView(homeView.viewName);
        viewManagerModel.firePropertyChanged();

        //application.pack();
        application.setVisible(true);

//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                Homeview homeview = new Homeview();
//                homeview.setVisible(true);
//            }
//        });
    }
}