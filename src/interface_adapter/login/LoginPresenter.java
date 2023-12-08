package interface_adapter.login;

import View.HomeViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import javax.swing.*;


public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    //private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;
    private HomeViewModel homeViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          HomeViewModel homeViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.
        viewManagerModel.setActiveView(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        //LoginState loginState = loginViewModel.getState();
        //loginState.setUsernameError(error);
        //loginViewModel.firePropertyChanged();
        JOptionPane.showMessageDialog(null, "wrong username or password!!");
    }
}
