package interface_adapter.Login;

import use_case.login.logInInputBoundary;
import use_case.login.logInInputData;

public class logInController {

    final logInInputBoundary userlogInUseCaseInteractor;
    public logInController(logInInputBoundary userlogInUseCaseInteractor) {
        this.userlogInUseCaseInteractor = userlogInUseCaseInteractor;
    }

    public void execute(String username, String password1) {
        logInInputData logInInputData = new logInInputData(
                username, password1);

        userlogInUseCaseInteractor.execute(logInInputData);
    }
}

