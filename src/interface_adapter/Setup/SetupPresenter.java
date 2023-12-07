package interface_adapter.Setup;

import View.Gameview;
import interface_adapter.ViewManagerModel;
import use_case.setup_game.SetupOutputData;
import use_case.setup_game.SetupOutputBoundary;

public class SetupPresenter implements SetupOutputBoundary{

    private ViewManagerModel viewManagerModel;
    private SetupViewModel setupViewModel;

    public SetupPresenter(ViewManagerModel viewManagerModel, SetupViewModel setupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.setupViewModel = setupViewModel;
    }
    @Override
    public void prepareSuccessView(SetupOutputData game) {
        SetupState setupState = setupViewModel.getState();
        System.out.println(game.getListCardImage());
        setupState.setScore(0);
        setupState.setMoves(0);
        setupState.setCurrentlyShownCardsImage(game.getListCardImage());
        setupState.setColumns(game.getCardsShown1()); //map of card objects in columns
        setupState.setIsNewGame(true);
        setupViewModel.setState(setupState);
        setupViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(setupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {

    }

}
