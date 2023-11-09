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

public class SinglePlayerGame extends Game {
    private boolean paused;
    private GameState gameState;
    private String deckID;
    private String shownCards;
    private String hiddenCards;
    private String cardSelected;
    public SinglePlayerGame() {
        this.paused = false;
        setUpGame();
        this.gameState = new GameState(shownCards);
    }
    @Override
    public void pauseGame() {

    }

    public HttpResponse<String> getHttpResponse(String url) {
        HashMap<String, String> deck = new HashMap<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public void setUpGame() {
        String hiddenCardsData;
        List<String> hiddenCardsDataList;
        hiddenCards = "";
        String shownCardsData;
        List<String> shownCardsDataList;
        shownCards = "";
        String[] deckResponse;
        HashMap<String, String> deck = new HashMap<>();

        HttpResponse<String> createDeckResponse = getHttpResponse("https://www.deckofcardsapi.com/api/deck/new/");
        deckResponse = createDeckResponse.body().substring(1,createDeckResponse.body().length() - 1).split(", ");
        for (String pair : deckResponse) {
            String[] parameter = pair.split(": ");
            deck.put(parameter[0], parameter[1]);
        }
        deckID = deck.get("\"deck_id\"");
        deckID = deckID.substring(1, deckID.length() - 1);

        HttpResponse<String> shuffleResponse = getHttpResponse("https://www.deckofcardsapi.com/api/deck/" + deckID +"/shuffle/?deck_count=1");

        hiddenCardsData = drawCard(21);
        hiddenCardsDataList = Arrays.stream(hiddenCardsData.split("\"code\": ")).toList();
        List<String> hiddenCardsDataListCopy = new ArrayList<>(hiddenCardsDataList);
        hiddenCardsDataListCopy.remove(0);
        for (String dict : hiddenCardsDataListCopy) {
            hiddenCards = hiddenCards.concat(dict.substring(1,3) + ",");
        }
        hiddenCards = hiddenCards.substring(0, hiddenCards.length() - 1);

        HttpResponse<String> createHiddenPileResponse = getHttpResponse("https://www.deckofcardsapi.com/api/deck/" + deckID + "/pile/hidden_pile/add/?cards=");
        HttpResponse<String> addToHiddenPileResponse = getHttpResponse("https://www.deckofcardsapi.com/api/deck/" + deckID + "/pile/hidden_pile/add/?cards=" + hiddenCards);

        shownCardsData = drawCard(7);
        shownCardsDataList = Arrays.stream(shownCardsData.split("\"code\": ")).toList();
        List<String> shownCardsDataListCopy = new ArrayList<>(shownCardsDataList);
        shownCardsDataListCopy.remove(0);
        for (String dict : shownCardsDataListCopy) {
            shownCards = shownCards.concat(dict.substring(1,3) + ",");
        }
        shownCards = shownCards.substring(0, shownCards.length() - 1);

        HttpResponse<String> createShownPileResponse = getHttpResponse("https://www.deckofcardsapi.com/api/deck/" + deckID + "/pile/shown_pile/add/?cards=");
        HttpResponse<String> addToShownPileResponse = getHttpResponse("https://www.deckofcardsapi.com/api/deck/" + deckID + "/pile/shown_pile/add/?cards=" + shownCards);




        System.out.println(hiddenCards);
        System.out.println(shownCards);
        System.out.println(addToHiddenPileResponse.body());
        System.out.println(addToShownPileResponse.body());
        for (String code : shownCards.split(",")) {
            System.out.println(getCardImageLink(code));
        }


    }

    public String drawCard(int number) {
        List<String> drawCardResponse = new ArrayList<>();
        HashMap<String, String> drawCardResponseMap = new HashMap();

        HttpResponse<String> drawCardResponse1 = getHttpResponse("https://www.deckofcardsapi.com/api/deck/" + deckID + "/draw/?count=" + number);

        drawCardResponse = Arrays.stream(drawCardResponse1.body().substring(1,drawCardResponse1.body().length() - 1).split(", ", 2)).toList();
        List<String> drawCardResponse2 = new ArrayList<>(drawCardResponse.subList(0, 1));
        drawCardResponse2.add(Arrays.stream(drawCardResponse.get(1).split(", ", 2)).toList().get(0));
        List<String> drawCardResponse3 = Arrays.stream(drawCardResponse.get(1).split(", ", 2)).toList();
        drawCardResponse2.add(drawCardResponse3.get(1).substring(0, drawCardResponse3.get(1).lastIndexOf(",")));
        drawCardResponse2.add(drawCardResponse3.get(1).substring(drawCardResponse3.get(1).lastIndexOf(",") + 1, drawCardResponse3.get(1).length()));


        for (String pair : drawCardResponse2) {
            String[] parameter = pair.split(": ", 2);
            drawCardResponseMap.put(parameter[0], parameter[1]);
        }
        return drawCardResponseMap.get("\"cards\"").substring(1,drawCardResponseMap.get("\"cards\"").length() - 1);
    }

    public String getDeckID() {
        return deckID;
    }
    public String getShownCards() {return shownCards;}
    public String getHiddenCards() {return hiddenCards;}

    public String getCardImageLink(String card) {
        return "https://deckofcardsapi.com/static/img/" + card + ".png";
    }

    public boolean selectCard() {
        cardSelected = null;
        return true;
    }
    public boolean isLegal(String position, String cardSelected) {
        //determine whether the cardSelected is heart/diamond or spade/club
        //suppose the cardSelected is heart/diamond; if the card in the position is also heart/diamond, then it's illegal
        //holds similarly for when it's spade/club
        //if it's one of the required shapes, determine the value of the cardSelected
        //if the card in the position has a value one less than the value of cardSelected, the drop is legal; otherwise, it's illegal
        return false;
    }
}
