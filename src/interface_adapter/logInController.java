package interface_adapter;

import use_case.logInInputBoundary;
import use_case.logInInputData;

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

