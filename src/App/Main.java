package App;

import View.*;
import data_access.FileUserDataAccessObject;
import data_access.GameDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.login.LoginViewModel;
import interface_adapter.Setup.SetupViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


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
        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Homeview homeView = SetupUseCaseFactory.create(viewManagerModel, homeViewModel, setupViewModel, gameDataAccessObject);
        views.add(homeView, homeView.viewName);


        Gameview gameView = SetupGameUseCaseFactory.create(viewManagerModel, homeViewModel, setupViewModel, gameDataAccessObject);
        views.add(gameView, gameView.viewName);

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, homeViewModel, userDataAccessObject);
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
