package data_access;

import entity.Game;
import entity.GameCreator;

import java.util.ArrayList;

public class GameDataAccessObject {
    private final ArrayList<Game> games = new ArrayList<>();
    public GameDataAccessObject() {}
    public int getNumGames() {return games.size();}
    public Game getGame(int i) {
        return games.get(i);
    }
    public void addGame(Game game) {this.games.add(game);}
}
