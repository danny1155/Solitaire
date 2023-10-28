package entity;

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
        HashMap<String, String> deck = new HashMap<>();
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
        deckResponse = response.body().substring(1,response.body().length() - 1).split(", ");
        for (String pair : deckResponse) {
            String[] parameter = pair.split(": ");
            deck.put(parameter[0], parameter[1]);
        }
        deckID = deck.get("\"deck_id\"");
        System.out.println(deckID);

        /*HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("https://www.deckofcardsapi.com/api/deck/" + deckID +"/shuffle/?deck_count=1"))
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();*/

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
