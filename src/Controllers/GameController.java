package Controllers;

import Models.Game;
import Models.GameStatus;
import Models.Move;
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

    public void undo(Game game) {
        game.undo();
    }

    public void replay(Game game) {
        game.replay();
    }

    public Player getWinner(Game game) {
        return game.getMoves().getLast().getPlayer();
    }

    public Move getLastMove(Game game) {
        return game.getMoves().getLast();
    }
}
