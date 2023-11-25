package use_case.setup_game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.Card;

public class SetupOutputData {
    private List<String> listCardImage;
    private String cardsShown;
    private Map<Integer, ArrayList<Card>> cardsShown1;
    public SetupOutputData(List<String> listCardImage, Map<Integer, ArrayList<Card>> cardsShown1) {
        this.cardsShown1 = cardsShown1;
        this.listCardImage = listCardImage;
        String cards = "";
        for (String url : listCardImage) {
            cards = cards.concat(url.substring(38, 40) + ",");
        }
        this.cardsShown = cards.substring(0, cards.length() - 1);
    };
    public List<String> getListCardImage() {
        return this.listCardImage;
    }
    public String getCardsShown() {
        return this.cardsShown;
    }
    public Map<Integer, ArrayList<Card>> getCardsShown1() {return this.cardsShown1;}
}
