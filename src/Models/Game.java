package Models;

import Strategies.Winner.SmallBoardStrategy;
import Strategies.Winner.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Board board;
    List<Player> players;
    List<Move> moves;
    GameStatus status;
    Integer current_player_index;
    WinningStrategy strategy;

    private Game(GameBuilder builder) {
        int n=builder.players.size();
        this.board=new Board(n+1);
        this.players=builder.players;
        this.moves=new ArrayList<>();
        this.current_player_index=0;
        this.status=GameStatus.IN_PROGRESS;
        this.strategy=new SmallBoardStrategy();
    }

    public void makeMove() {
        int n=players.size();
        Player player=players.get(current_player_index);
        Cell cell=player.makeMove(board,player);
        //System.out.println(cell.getPlayer().getName()+" "+cell.getPlayer().getSymbol());
        Move move=new Move(cell,player);
        moves.add(move);
        if(strategy.hasWon(board,cell)) {
            System.out.println(cell.getPlayer().getName()+" is the winner");
            status=GameStatus.WIN;
            return;
        }
        else if(moves.size()==((n+1)*(n+1))) {
            System.out.println("Game is drawn");
            status=GameStatus.DRAW;
            return;
        }
        current_player_index=(++current_player_index%n);
    }

    public static class GameBuilder {
        List<Player> players;

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Integer getCurrent_player_index() {
        return current_player_index;
    }

    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }
}
