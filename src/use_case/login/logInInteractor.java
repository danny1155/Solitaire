package use_case.login;

import data_access.UserLoginDataAccessInterface;
import entity.UserFactory;

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
            //try {
            //    //System.out.println("yo");
            //    System.out.println("Username is correct. Navigating to HomeView.");
            //    logInOutputData logInOutputData = new logInOutputData(logInInputData.getUsername(), false);
            //    userPresenter.prepareSuccessView(logInOutputData);
            //} catch (Exception e) {
            //    e.printStackTrace();
            //}
            System.out.println("Username is correct. Navigating to HomeView.");
            logInOutputData logInOutputData = new logInOutputData(logInInputData.getUsername(), false);
            userPresenter.prepareSuccessView(logInOutputData);
        } else {
            System.out.println("Username is incorrect. Showing fail view.");
            userPresenter.prepareFailView("wrong username or password");
        }
    }
}