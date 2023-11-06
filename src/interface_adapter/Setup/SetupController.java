package interface_adapter.Setup;

import use_case.setup_game.SetupInputBoundary;
import use_case.setup_game.SetupInputData;

public class SetupController {
    final SetupInputBoundary SetupUseCaseInteractor;

    public SetupController(SetupInputBoundary setupUseCaseInteractor) {
        this.SetupUseCaseInteractor = setupUseCaseInteractor;
    }

    public void execute(String gameMode){
        SetupInputData setupInputData = new SetupInputData(gameMode);
        SetupUseCaseInteractor.execute(setupInputData);
    }
}
