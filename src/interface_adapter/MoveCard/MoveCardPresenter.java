package interface_adapter.MoveCard;

import interface_adapter.Setup.SetupState;
import interface_adapter.Setup.SetupViewModel;
import interface_adapter.ViewManagerModel;
import use_case.move_card.MoveCardOutputBoundary;
import use_case.move_card.MoveCardOutputData;
import use_case.setup_game.SetupOutputBoundary;
import use_case.setup_game.SetupOutputData;

public class MoveCardPresenter implements MoveCardOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private SetupViewModel setupViewModel;

    public MoveCardPresenter(ViewManagerModel viewManagerModel, SetupViewModel setupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.setupViewModel = setupViewModel;
    }
    @Override
    public void prepareSuccessView(MoveCardOutputData game) {
        SetupState setupState = setupViewModel.getState();
//        if (game.getCanBeMoved()) {
            setupState.setCanBeMoved(game.getCanBeMoved());
            setupState.setMovedColumn(game.getMovedColumn());
//        } else {
//            setupState.setCanBeMoved(false);
//        }
        //setupState.setCurrentlyShownCardsImage(game.getListCardImage());
        //setupState.setColumns(game.getCardsShown1()); //map of card objects in columns
        setupState.addScoreBy(game.getScoreChange());
        setupState.incrementMove();
        setupViewModel.setState(setupState);
        setupViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(setupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();


    }

    @Override
    public void prepareFailView(String error) {

    }

}
