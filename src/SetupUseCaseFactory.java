import View.HomeViewModel;
import View.Homeview;
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
            ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, SetupViewModel setupViewModel) {
        SetupController setupController = createSetupUseCase(viewManagerModel, setupViewModel);
        return new Homeview(homeViewModel, setupController);
    }

    private static SetupController createSetupUseCase(ViewManagerModel viewManagerModel, SetupViewModel setupViewModel) {

        // Notice how we pass this method's parameters to the Presenter.
        SetupOutputBoundary setupOutputBoundary = new SetupPresenter(viewManagerModel, setupViewModel);

        SetupInputBoundary setupInteractor = new SetupInteractor(setupOutputBoundary);

        return new SetupController(setupInteractor);
    }
}