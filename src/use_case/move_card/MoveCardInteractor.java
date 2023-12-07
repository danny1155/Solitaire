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
    private boolean movedToDifferentColumn;
    public MoveCardInteractor(MoveCardOutputBoundary moveCardOutputBoundary, GameDataAccessObject gameDataAccessObject) {
        this.gameDataAccessObject = gameDataAccessObject;
        this.moveCardPresenter = moveCardOutputBoundary;
        this.canBeMoved = false;
        this.movedToDifferentColumn = false;
    }
    public void execute(MoveCardInputData moveCardInputData) {

        Game game = gameDataAccessObject.getGame(gameDataAccessObject.getNumGames() - 1);
        Point point = moveCardInputData.getCardPoint();
        Card card = moveCardInputData.getCard();
        System.out.println(point.getX());
        System.out.println(point.getY());
        System.out.println(card.getName());
        System.out.println(card.getValue());
        if (point.getX() + 100 < card.getImage_corner().getX() || point.getX() > card.getImage_corner().getX() + 100
                || point.getY() + 140 < card.getImage_corner().getY() || point.getY() > card.getImage_corner().getY() + 140) {
            this.movedToDifferentColumn = true;
        }
        if (point.getX() >= /*880*/840 && point.getX() + 100 <= /*980*/1020 && moveCardInputData.getNumCardsMoved() == 1){
            System.out.println("By foundation X");
            if (point.getY() >= -30 && point.getY() <= 100){
                System.out.println("By foundation Y");

                if (card.getName().substring(1).equals("C")) {
                    if (card.getValue() == 1) {
                        System.out.println("First foundation for Club");
                        canBeMoved = true;
                        MoveCardOutputData moveCardOutputData;
                        if (this.movedToDifferentColumn && moveCardInputData.getNewCardShown()) {
                            moveCardOutputData = new MoveCardOutputData(true, 8, 15);
                        } else if (this.movedToDifferentColumn) {
                            moveCardOutputData = new MoveCardOutputData(true, 8, 10);
                        } else {
                            moveCardOutputData = new MoveCardOutputData(true, 8, 0);

                        }
                        moveCardPresenter.prepareSuccessView(moveCardOutputData);
                    } else if (!game.getColumns().get(8).isEmpty() && card.getValue() == game.getColumns().get(8).get(game.getColumns().get(8).size() - 1).getValue() + 1) {
                        System.out.println("Add card to Club Foundation");
                        canBeMoved = true;
                        MoveCardOutputData moveCardOutputData;
                        if (this.movedToDifferentColumn && moveCardInputData.getNewCardShown()) {
                            moveCardOutputData = new MoveCardOutputData(true, 8, 15);
                        } else if (this.movedToDifferentColumn) {
                            moveCardOutputData = new MoveCardOutputData(true, 8, 10);
                        } else {
                            moveCardOutputData = new MoveCardOutputData(true, 8, 0);

                        }
                        moveCardPresenter.prepareSuccessView(moveCardOutputData);
                    }
                }
            } else if (point.getY() >= 120 && point.getY() <= 250) {
                System.out.println("By foundation Y");
                if (card.getName().substring(1).equals("S")){
                    if (card.getValue() == 1){
                        System.out.println("First foundation for Spade");
                        canBeMoved = true;
                        MoveCardOutputData moveCardOutputData;
                        if (this.movedToDifferentColumn && moveCardInputData.getNewCardShown()) {
                            moveCardOutputData = new MoveCardOutputData(true, 9, 15);
                        } else if (this.movedToDifferentColumn) {
                            moveCardOutputData = new MoveCardOutputData(true, 9, 10);
                        } else {
                            moveCardOutputData = new MoveCardOutputData(true, 9, 0);

                        }
                        moveCardPresenter.prepareSuccessView(moveCardOutputData);
                    } else if (!game.getColumns().get(9).isEmpty() && card.getValue() == game.getColumns().get(9).get(game.getColumns().get(9).size() - 1).getValue() + 1) {
                        System.out.println("Add card to Spade Foundation");
                        canBeMoved = true;
                        MoveCardOutputData moveCardOutputData;
                        if (this.movedToDifferentColumn && moveCardInputData.getNewCardShown()) {
                            moveCardOutputData = new MoveCardOutputData(true, 9, 15);
                        } else if (this.movedToDifferentColumn) {
                            moveCardOutputData = new MoveCardOutputData(true, 9, 10);
                        } else {
                            moveCardOutputData = new MoveCardOutputData(true, 9, 0);

                        }
                        moveCardPresenter.prepareSuccessView(moveCardOutputData);
                    }
                }
            } else if (point.getY() >= 270 && point.getY() <= 400) {
                System.out.println("By foundation Y");
                if (card.getName().substring(1).equals("D")) {
                    if (card.getValue() == 1) {
                        System.out.println("First foundation for Diamond");
                        canBeMoved = true;
                        MoveCardOutputData moveCardOutputData;
                        if (this.movedToDifferentColumn && moveCardInputData.getNewCardShown()) {
                            moveCardOutputData = new MoveCardOutputData(true, 10, 15);
                        } else if (this.movedToDifferentColumn) {
                            moveCardOutputData = new MoveCardOutputData(true, 10, 10);
                        } else {
                            moveCardOutputData = new MoveCardOutputData(true, 10, 0);

                        }
                        moveCardPresenter.prepareSuccessView(moveCardOutputData);
                    } else if (!game.getColumns().get(10).isEmpty() && card.getValue() == game.getColumns().get(10).get(game.getColumns().get(10).size() - 1).getValue() + 1) {
                        System.out.println("Add card to Diamond Foundation");
                        canBeMoved = true;
                        MoveCardOutputData moveCardOutputData;
                        if (this.movedToDifferentColumn && moveCardInputData.getNewCardShown()) {
                            moveCardOutputData = new MoveCardOutputData(true, 10, 15);
                        } else if (this.movedToDifferentColumn) {
                            moveCardOutputData = new MoveCardOutputData(true, 10, 10);
                        } else {
                            moveCardOutputData = new MoveCardOutputData(true, 10, 0);

                        }
                        moveCardPresenter.prepareSuccessView(moveCardOutputData);
                    }
                }
            } else if (point.getY() >= 420 && point.getY() <= 550) {
                System.out.println("By foundation Y");
                if (card.getName().substring(1).equals("H")) {
                    if (card.getValue() == 1) {
                        System.out.println("First foundation for Heart");
                        canBeMoved = true;
                        MoveCardOutputData moveCardOutputData;
                        if (this.movedToDifferentColumn && moveCardInputData.getNewCardShown()) {
                            moveCardOutputData = new MoveCardOutputData(true, 11, 15);
                        } else if (this.movedToDifferentColumn) {
                            moveCardOutputData = new MoveCardOutputData(true, 11, 10);
                        } else {
                            moveCardOutputData = new MoveCardOutputData(true, 11, 0);

                        }
                        moveCardPresenter.prepareSuccessView(moveCardOutputData);
                    } else if (!game.getColumns().get(11).isEmpty() && card.getValue() == game.getColumns().get(11).get(game.getColumns().get(11).size() - 1).getValue() + 1) {
                        System.out.println("Add card to Heart Foundation");
                        canBeMoved = true;
                        MoveCardOutputData moveCardOutputData;
                        if (this.movedToDifferentColumn && moveCardInputData.getNewCardShown()) {
                            moveCardOutputData = new MoveCardOutputData(true, 11, 15);
                        } else if (this.movedToDifferentColumn) {
                            moveCardOutputData = new MoveCardOutputData(true, 11, 10);
                        } else {
                            moveCardOutputData = new MoveCardOutputData(true, 11, 0);

                        }
                        moveCardPresenter.prepareSuccessView(moveCardOutputData);
                    }
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            System.out.println(card.getColor());
            if (!game.getColumns().get(i + 1).isEmpty()) {
                System.out.println(game.getColumns().get(i + 1).get(game.getColumns().get(i + 1).size() - 1).getColor());
            }
            System.out.println(110 * i + 70);
            System.out.println(point.getX());
            //System.out.println(point.getY());
            int size = game.getColumns().get(i + 1).size();
            if (size == 0 && 110 * i + /*110*/ 70 <= point.getX() && point.getX() + 100 <= 110 * i + /*210*/+ 250 && 0 <= point.getY() && point.getY() + 140 <= 180 &&
                    card.getValue() == 13) {
                canBeMoved = true;
                MoveCardOutputData moveCardOutputData;
                System.out.println("here" + moveCardInputData.getNewCardShown());
                if (this.movedToDifferentColumn && moveCardInputData.getColumnNum() >= 8 && moveCardInputData.getColumnNum() <= 11) {
                    moveCardOutputData = new MoveCardOutputData(true, i + 1, -15);
                } else if (moveCardInputData.getNewCardShown()) {
                    moveCardOutputData = new MoveCardOutputData(true, i + 1, 5);
                } else {
                    moveCardOutputData = new MoveCardOutputData(true, i + 1, 0);
                }
                moveCardPresenter.prepareSuccessView(moveCardOutputData);

                //game.getColumns().get(i + 1).add(card);

                break;
            } else if (size != 0 && 110 * i + /*110*/ 70 <= point.getX() && point.getX() + 100 <= 110 * i + /*210*/ 250 && size/*(i + 1)*/ * 20 <= point.getY() && point.getY() + 140 <= size * 20 + 180
            && card.getColor() == 1 - game.getColumns().get(i + 1).get(game.getColumns().get(i + 1).size() - 1).getColor() &&
                    card.getValue() == game.getColumns().get(i + 1).get(game.getColumns().get(i + 1).size() - 1).getValue() - 1) {
//                System.out.println("canBeMoved");
                canBeMoved = true;
                MoveCardOutputData moveCardOutputData;
                System.out.println("here" + moveCardInputData.getNewCardShown());
                if (this.movedToDifferentColumn && moveCardInputData.getColumnNum() >= 8 && moveCardInputData.getColumnNum() <= 11) {
                    moveCardOutputData = new MoveCardOutputData(true, i + 1, -15);
                } else if (/*this.movedToDifferentColumn &&*/ moveCardInputData.getNewCardShown()) {

                    moveCardOutputData = new MoveCardOutputData(true, i + 1, 5);
                } else {
                    moveCardOutputData = new MoveCardOutputData(true, i + 1, 0);
                }
                moveCardPresenter.prepareSuccessView(moveCardOutputData);

                //game.getColumns().get(i + 1).add(card);

                break;
            }

        }
        if (!canBeMoved) {
            MoveCardOutputData moveCardOutputData = new MoveCardOutputData(false, -1, 0);
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
