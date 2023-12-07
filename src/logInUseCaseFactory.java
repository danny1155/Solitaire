import View.HomeViewModel;
import View.LoginView;
import data_access.FileUserDataAccessObject;
import data_access.UserLoginDataAccessInterface;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.*;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Login.logInController;
import interface_adapter.Login.logInPresenter;
import use_case.login.logInInputBoundary;
import use_case.login.logInInteractor;
import use_case.login.logInOutputBoundary;

import javax.swing.*;
import java.io.IOException;

public class logInUseCaseFactory {

    /** Prevent instantiation. */
    private logInUseCaseFactory() {}

    public static LoginView create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel) {

        try {
            logInController logInController = createUserlogInUseCase(viewManagerModel, homeViewModel, signupViewModel, loginViewModel);
            return new LoginView(logInController, loginViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static logInController createUserlogInUseCase(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel) throws IOException {
        UserLoginDataAccessInterface userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());

        // Notice how we pass this method's parameters to the Presenter.
        logInOutputBoundary logInOutputBoundary = new logInPresenter(viewManagerModel, homeViewModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        logInInputBoundary userlogInInteractor = new logInInteractor(
                userDataAccessObject, logInOutputBoundary, userFactory);

        return new logInController(userlogInInteractor);
    }
}
