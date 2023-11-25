import View.Gameview;
import View.HomeViewModel;
import data_access.GameDataAccessObject;
import entity.Game;
import interface_adapter.MoveCard.MoveCardController;
import interface_adapter.MoveCard.MoveCardPresenter;
import interface_adapter.Setup.SetupController;
import interface_adapter.Setup.SetupPresenter;
import interface_adapter.Setup.SetupViewModel;
import interface_adapter.ViewManagerModel;
import use_case.move_card.MoveCardInputBoundary;
import use_case.move_card.MoveCardInteractor;
import use_case.move_card.MoveCardOutputBoundary;
import use_case.setup_game.SetupInputBoundary;
import use_case.setup_game.SetupInteractor;
import use_case.setup_game.SetupOutputBoundary;

public class SetupGameUseCaseFactory {
    private SetupGameUseCaseFactory() {}

    public static Gameview create(
            ViewManagerModel viewManagerModel, HomeViewModel homeViewModel, SetupViewModel setupViewModel, GameDataAccessObject gameDataAccessObject) {
        SetupController setupController = createSetupUseCase(viewManagerModel, setupViewModel, gameDataAccessObject);
        MoveCardController moveCardController = createMoveCardupUseCase(viewManagerModel, setupViewModel, gameDataAccessObject);
        return new Gameview(setupViewModel, homeViewModel, viewManagerModel, setupController, moveCardController);
    }

    private static SetupController createSetupUseCase(ViewManagerModel viewManagerModel, SetupViewModel setupViewModel, GameDataAccessObject gameDataAccessObject) {

        // Notice how we pass this method's parameters to the Presenter.
        SetupOutputBoundary setupOutputBoundary = new SetupPresenter(viewManagerModel, setupViewModel);

        SetupInputBoundary setupInteractor = new SetupInteractor(setupOutputBoundary, gameDataAccessObject);

        return new SetupController(setupInteractor);
    }

    private static MoveCardController createMoveCardupUseCase(ViewManagerModel viewManagerModel, SetupViewModel setupViewModel, GameDataAccessObject gameDataAccessObject) {

        // Notice how we pass this method's parameters to the Presenter.
        MoveCardOutputBoundary moveCardOutputBoundary = new MoveCardPresenter(viewManagerModel, setupViewModel);

        MoveCardInputBoundary moveCardInteractor = new MoveCardInteractor(moveCardOutputBoundary, gameDataAccessObject);

        return new MoveCardController(moveCardInteractor);
    }
}
