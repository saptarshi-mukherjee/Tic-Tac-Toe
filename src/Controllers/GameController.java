package Controllers;

import Models.Game;
import Models.GameStatus;
import Models.Player;

import java.util.List;

public class GameController {

    public Game createGame(List<Player> players) {
        Game game=Game.getBuilder()
                .setPlayers(players)
                .build();
        return game;
    }

    public GameStatus getGameStatus(Game game) {
        return game.getStatus();
    }

    public void printBoard(Game game) {
        game.getBoard().print();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }
}
