package interface_adapter;

import use_case.logInOutputBoundary;
import use_case.logInOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import View.HomeViewModel;

public class logInPresenter implements logInOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;
    private HomeViewModel homeViewModel;

    public logInPresenter(ViewManagerModel viewManagerModel,
                           HomeViewModel homeViewModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel) {

        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(logInOutputData response) {
        // On success, switch to the login view.
        //LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        //response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        // set state for account page
        // account state.firePropertyChanged();
        //homeViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState logInState = loginViewModel.getState();
        logInState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
