package interface_adapter.Setup;

import use_case.setup_game.SetupInputBoundary;
import use_case.setup_game.SetupInputData;

public class SetupController {
    final SetupInputBoundary setupInteractor;

    public SetupController(SetupInputBoundary setupInteractor) {
        this.setupInteractor = setupInteractor;
    }

    public void execute(String gameMode) {
        SetupInputData setupInputData = new SetupInputData(gameMode);
        setupInteractor.execute(setupInputData);

    }
}