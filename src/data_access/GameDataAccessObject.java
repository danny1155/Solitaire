package data_access;

import entity.Game;

import java.util.ArrayList;
import java.util.HashMap;

public class GameDataAccessObject {
    private final ArrayList<Game> games = new ArrayList<>();
    public GameDataAccessObject() {}
    public int getNumGames() {return games.size();}
    public Game getGame(int i) {
        return games.get(i);
    }
    public void addGame(Game game) {this.games.add(game);}
}
