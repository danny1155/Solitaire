package use_case.draw_card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawcardInteractor implements DrawcardInputBoundary {
    private final DrawcardOutputBoundary outputBoundary;
    private final List<Card> deck;

    public DrawcardInteractor(DrawcardOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
        this.deck = initializeDeck();
    }

    @Override
    public void drawCard(DrawcardInputData inputData) {
        // Use outputBoundary to communicate the result
        if (!deck.isEmpty()) {
            Card drawnCard = drawRandomCard();
            outputBoundary.drawCardSuccess(drawnCard);
        } else {
            outputBoundary.drawCardFailure("The deck is empty");
        }
    }

    private List<Card> initializeDeck() {
        // Sample suits and ranks (you can customize this based on your card representation)
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        List<Card> deck = new ArrayList<>();

        // Create a deck of cards
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(new Card(suit, rank));
            }
        }

        return deck;
    }

    private Card drawRandomCard() {
        // Use Random to get a random card from the deck
        Random random = new Random();
        int randomCardIndex = random.nextInt(deck.size());
        return deck.remove(randomCardIndex);
    }

    public class Card {
        private String suit;
        private String rank;

        public Card(String suit, String rank) {
            this.suit = suit;
            this.rank = rank;
        }

        // Getters, setters, and any other necessary methods
    }
}
