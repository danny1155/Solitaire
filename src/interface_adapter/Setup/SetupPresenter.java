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
        System.out.println(game.getCardsShown());
        setupState.setCurrentlyShownCards(game.getCardsShown());
        setupViewModel.setState(setupState);
        setupViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(setupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {

    }

}
