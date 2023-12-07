import View.HomeViewModel;
import View.Homeview;
import data_access.GameDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.Setup.SetupViewModel;
import interface_adapter.Setup.SetupController;
import use_case.setup_game.SetupOutputBoundary;
import use_case.setup_game.SetupInputBoundary;
import use_case.setup_game.SetupInteractor;
import interface_adapter.Setup.SetupPresenter;


public class SetupUseCaseFactory {

    private SetupUseCaseFactory() {}

    public static Homeview create(
            ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, SetupViewModel setupViewModel, GameDataAccessObject gameDataAccessObject) {
            SetupController setupController = createSetupUseCase(viewManagerModel, setupViewModel, gameDataAccessObject);
            return new Homeview(homeViewModel, setupController);
    }

    private static SetupController createSetupUseCase(ViewManagerModel viewManagerModel, SetupViewModel setupViewModel, GameDataAccessObject gameDataAccessObject) {

        // Notice how we pass this method's parameters to the Presenter.
        SetupOutputBoundary setupOutputBoundary = new SetupPresenter(viewManagerModel, setupViewModel);

        SetupInputBoundary setupInteractor = new SetupInteractor(setupOutputBoundary, gameDataAccessObject);

        return new SetupController(setupInteractor);
    }
}
