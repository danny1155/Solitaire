package use_case.move_card;

import data_access.GameDataAccessObject;
import entity.Card;
import entity.Game;
import entity.SinglePlayerGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MoveCardInteractor implements MoveCardInputBoundary {

    private MoveCardOutputBoundary moveCardPresenter;
    private GameDataAccessObject gameDataAccessObject;
    private boolean canBeMoved;
    public MoveCardInteractor(MoveCardOutputBoundary moveCardOutputBoundary, GameDataAccessObject gameDataAccessObject) {
        this.gameDataAccessObject = gameDataAccessObject;
        this.moveCardPresenter = moveCardOutputBoundary;
        this.canBeMoved = false;
    }
    public void execute(MoveCardInputData moveCardInputData) {
        Game game = gameDataAccessObject.getGame(gameDataAccessObject.getNumGames() - 1);
        Point point = moveCardInputData.getCardPoint();
        Card card = moveCardInputData.getCard();
        for (int i = 0; i < 7; i++) {
            System.out.println(card.getColor());
            if (!game.getColumns().get(i + 1).isEmpty()) {
                System.out.println(game.getColumns().get(i + 1).get(game.getColumns().get(i + 1).size() - 1).getColor());
            }
            System.out.println(110 * i + 70);
            System.out.println(point.getX());
            //System.out.println(point.getY());
            int size = game.getColumns().get(i + 1).size();
            if (size == 0 && 110 * i + 70 <= point.getX() + 5 && point.getX() <= 110 * i + 170 && 0 <= point.getY() && point.getY() <= 140 &&
                    card.getValue() == 13) {
                canBeMoved = true;
                MoveCardOutputData moveCardOutputData = new MoveCardOutputData(true, i + 1);
                moveCardPresenter.prepareSuccessView(moveCardOutputData);

                //game.getColumns().get(i + 1).add(card);

                break;
            } else if (size != 0 && 110 * i + 70 <= point.getX() + 5 && point.getX() <= 110 * i + 170 && size/*(i + 1)*/ * 20 <= point.getY() && point.getY() <= size * 20 + 140
            && card.getColor() == 1 - game.getColumns().get(i + 1).get(game.getColumns().get(i + 1).size() - 1).getColor() &&
                    card.getValue() == game.getColumns().get(i + 1).get(game.getColumns().get(i + 1).size() - 1).getValue() - 1) {
                System.out.println("canBeMoved");
                canBeMoved = true;
                MoveCardOutputData moveCardOutputData = new MoveCardOutputData(true, i + 1);
                moveCardPresenter.prepareSuccessView(moveCardOutputData);

                //game.getColumns().get(i + 1).add(card);

                break;
            }

        }
        if (!canBeMoved) {
            MoveCardOutputData moveCardOutputData = new MoveCardOutputData(false, -1);
            moveCardPresenter.prepareSuccessView(moveCardOutputData);
        }

        System.out.println(canBeMoved);
        canBeMoved = false;

//        MoveCardOutputData moveCardOutputData = new MoveCardOutputData(false);
//        moveCardPresenter.prepareSuccessView(moveCardOutputData);

            //JLayeredPane columnPanel = new JLayeredPane();
            //columnPanel.setBounds(110 * i + 70,0, 200, 400);
//            state.getColumns().get(i + 1).get(i).setImage_corner(110 * i + 70, i * 20);
//            Point cardPoint = new Point(110 * i + 70, i * 20);
//
//            // addCard(cardsPanel, shownCardsImage.get(i), 110 * i + 70, i * 20);
//            addCard(cardsPanel, state.getColumns().get(i + 1).get(i).getImageLink(), i + 1,  110 * i + 70, i * 20);
        //MoveCardOutputData setupOutputData = new MoveCardOutputData(listShownCardImage, game.getColumns());
        //moveCardPresenter.prepareSuccessView(setupOutputData);
    }

}
