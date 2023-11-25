package use_case.move_card;

import entity.Card;

import java.awt.*;

public class MoveCardInputData {
    // Add fields as needed for setup data
    private Point cardPoint;
    private Card card;  // Example field for game mode selection

    public MoveCardInputData(Point cardPoint, Card card) {
        this.card = card;
        this.cardPoint = cardPoint;
    }

    public Point getCardPoint() {
        return cardPoint;
    }
    public Card getCard() {return card;}

    // Add other getters and setters for additional setup data
}