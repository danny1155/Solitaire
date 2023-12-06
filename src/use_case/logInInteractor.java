package use_case;

import View.HomeViewModel;
import View.Homeview;
import View.ViewManager;
import data_access.UserLoginDataAccessInterface;
import entity.User;
import entity.UserFactory;
import interface_adapter.LoginViewModel;
import interface_adapter.Setup.SetupController;
import interface_adapter.Setup.SetupViewModel;
import interface_adapter.SignupViewModel;
import interface_adapter.ViewManagerModel;
import use_case.setup_game.SetupInputBoundary;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class logInInteractor implements logInInputBoundary {
    final UserLoginDataAccessInterface userDataAccessObject;
    final logInOutputBoundary userPresenter;
    final UserFactory userFactory;

    public logInInteractor(UserLoginDataAccessInterface userlogInDataAccessInterface,
                            logInOutputBoundary logInOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = userlogInDataAccessInterface;
        this.userPresenter = logInOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(logInInputData logInInputData) {

        if (userDataAccessObject.existsByName(logInInputData.getUsername())) {
            try {
                //System.out.println("yo");
                System.out.println("Username is correct. Navigating to HomeView.");
                logInOutputData logInOutputData = new logInOutputData(logInInputData.getUsername(), false);
                userPresenter.prepareSuccessView(logInOutputData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Username is incorrect. Showing fail view.");
            userPresenter.prepareFailView("wrong username or password");
        }
    }
}