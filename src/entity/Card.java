package entity;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Card {
    private String name;
    private String color;
    private int value;
    private boolean inDeck;
    private int column;
    private String imageLink;
    private boolean isSelected;
    private boolean isShown;
    private int index;

    public Card (String cardName){
        this.name = cardName;
        this.color = cardName.substring(1);
        if (cardName.charAt(0) == '0'){
            this.value = 10;
        } else if (cardName.charAt(0) == 'J') {
            this.value = 11;
        } else if (cardName.charAt(0) == 'Q') {
            this.value = 12;
        } else if (cardName.charAt(0) == 'K') {
            this.value = 13;
        } else if (cardName.charAt(0) == 'A') {
            this.value = 1;
        } else {
            this.value = Integer.parseInt(cardName.substring(0,1));
        }
        this.inDeck = true;
        this.column = 0;
        this.imageLink = "https://www.deckofcardsapi.com/static/img/" + cardName + ".png";
        this.isSelected = false;
        this.isShown = false;
        this.index = 0;
    }

    public String getName(){return name;}
    public String getColor(){return color;}
    public int getValue(){return value;}
    public int getColumn(){return column;}
    public boolean checkInDeck(){return inDeck;}
    public String getImageLink() {return imageLink;}
    public boolean checkIsSelected() {return isSelected;}
    public boolean checkIsShown() {return isShown;}
    public void showCard(){
        this.isShown = true;
    }
    public void selectCard(){
        this.isSelected = true;
    }
    public int getIndex(){return index;}
    public void moveCard(int Destination){
        this.column = Destination;
        this.isSelected = false;
    }
    public void setIndex(ArrayList<Card> column){
        this.index = column.indexOf(this);
    }

    public boolean isTopCard(ArrayList<Card> column){
        return column.size() - 1 - column.indexOf(this) == 0;
    }

}