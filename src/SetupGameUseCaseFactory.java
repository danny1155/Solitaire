import View.Gameview;
import interface_adapter.Setup.SetupController;
import interface_adapter.Setup.SetupPresenter;
import interface_adapter.Setup.SetupViewModel;
import interface_adapter.ViewManagerModel;
import use_case.setup_game.SetupInputBoundary;
import use_case.setup_game.SetupInteractor;
import use_case.setup_game.SetupOutputBoundary;

public class SetupGameUseCaseFactory {
    private SetupGameUseCaseFactory() {}

    public static Gameview create(
            ViewManagerModel viewManagerModel, SetupViewModel setupViewModel) {
        SetupController setupController = createSetupUseCase(viewManagerModel, setupViewModel);
        return new Gameview(setupViewModel, setupController);
    }

    private static SetupController createSetupUseCase(ViewManagerModel viewManagerModel, SetupViewModel setupViewModel) {

        // Notice how we pass this method's parameters to the Presenter.
        SetupOutputBoundary setupOutputBoundary = new SetupPresenter(viewManagerModel, setupViewModel);

        SetupInputBoundary setupInteractor = new SetupInteractor(setupOutputBoundary);

        return new SetupController(setupInteractor);
    }
}