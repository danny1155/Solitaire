package use_case.move_card;

import entity.Card;

import java.awt.*;

public class MoveCardInputData {
    // Add fields as needed for setup data
    private Point cardPoint;
    private Card card;  // Example field for game mode selection
    private int numCardsMoved;
    private boolean newCardShown;
    private int columnNum;

    public MoveCardInputData(Point cardPoint, Card card, int numCardsMoved, boolean newCardShown, int columnNum) {
        this.card = card;
        this.cardPoint = cardPoint;
        this.numCardsMoved = numCardsMoved;
        this.newCardShown = newCardShown;
        this.columnNum = columnNum;
    }

    public Point getCardPoint() {
        return cardPoint;
    }
    public Card getCard() {return card;}
    public int getNumCardsMoved() {return numCardsMoved;}
    public boolean getNewCardShown() {return newCardShown;}
    public int getColumnNum() {return columnNum;}

    // Add other getters and setters for additional setup data
}