package use_case.setup_game;

import java.util.List;

public class SetupOutputData {
    private List<String> listCardImage;
    private String cardsShown;
    public SetupOutputData(List<String> listCardImage) {
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
}
