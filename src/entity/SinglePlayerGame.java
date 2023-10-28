package src.entity;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class SinglePlayerGame extends Game {
    private boolean legalPlacement;
    private boolean paused;

    private String deckID;
    public SinglePlayerGame() {
        legalPlacement = false;
        paused = false;
        setUpGame();
    }
    @Override
    public void pauseGame() {

    }

    @Override
    public void setUpGame() {
        String[] deckResponse;
        HashMap deck = new HashMap<String, String>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.deckofcardsapi.com/api/deck/new/"))
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
        deckResponse = response.body().split(",");
        for (String pair : deckResponse) {
            String[] parameter = pair.split(": ");
            deck.put(parameter[0], parameter[1]);
        }
        deckID = (String) deck.get("deck_id");
    }

    public String drawCard(int number) {
        String[] drawCardResponse;
        HashMap drawCardResponseMap = new HashMap();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.deckofcardsapi.com/api/deck/<<deck_id>>/draw/?count=" + number))
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
        drawCardResponse = response.body().split(",");
        for (String pair : drawCardResponse) {
            String[] parameter = pair.split(": ");
            drawCardResponseMap.put(parameter[0], parameter[1]);
        }
        return (String) drawCardResponseMap.get("cards");
    }

    public String getDeckID() {
        return deckID;
    }
}
