package entity;

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
        deckID = deckID.substring(1, deckID.length() - 1);

        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create("https://www.deckofcardsapi.com/api/deck/" + deckID +"/shuffle/?deck_count=1"))
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();

    }

    public String drawCard(int number) {
        List<String> drawCardResponse = new ArrayList<>();
        HashMap<String, String> drawCardResponseMap = new HashMap();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.deckofcardsapi.com/api/deck/" + deckID + "/draw/?count=" + number))
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
        drawCardResponse = Arrays.stream(response.body().substring(1,response.body().length() - 1).split(", ", 2)).toList();
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
}
