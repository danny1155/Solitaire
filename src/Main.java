import View.*;
import data_access.GameDataAccessObject;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Setup.SetupViewModel;
import interface_adapter.Signup.SignupViewModel;
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

        GameDataAccessObject gameDataAccessObject = new GameDataAccessObject();

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        HomeViewModel homeViewModel = new HomeViewModel();
        SetupViewModel setupViewModel = new SetupViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();


        Homeview homeView = SetupUseCaseFactory.create(viewManagerModel, homeViewModel, setupViewModel, gameDataAccessObject);
        views.add(homeView, homeView.viewName);

//        MoveCardOutputBoundary moveCardOutputBoundary = new MoveCardPresenter(viewManagerModel, setupViewModel);
//
//        MoveCardInputBoundary moveCardInteractor = new MoveCardInteractor(moveCardOutputBoundary, game);
//
//        MoveCardController moveCardController = new MoveCardController(moveCardInteractor);

        Gameview gameView = SetupGameUseCaseFactory.create(viewManagerModel, homeViewModel, setupViewModel, gameDataAccessObject);
        views.add(gameView, gameView.viewName);

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, homeViewModel);
        views.add(signupView, signupView.viewName);

        LoginView loginView = logInUseCaseFactory.create(viewManagerModel, homeViewModel, loginViewModel, signupViewModel);
        views.add(loginView, loginView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        //application.pack();
        application.setVisible(true);

//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                Homeview homeview = new Homeview();
//                homeview.setVisible(true);
//
//            }
//        });
    }
}
