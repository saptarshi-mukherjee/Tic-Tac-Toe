package Models;

import Factories.WinningStrategyFactory;
import Strategies.Winner.LargeBoardStrategy;
import Strategies.Winner.SmallBoardStrategy;
import Strategies.Winner.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game {
    Board board;
    List<Player> players;
    List<Move> moves;
    GameStatus status;
    Integer current_player_index;
    WinningStrategy strategy;
//    Stack<Board> board_states;

    private Game(GameBuilder builder) {
        int n=builder.players.size();
        this.board=new Board(n+1);
        this.players=builder.players;
        this.moves=new ArrayList<>();
        this.current_player_index=0;
        this.status=GameStatus.IN_PROGRESS;
        this.strategy= WinningStrategyFactory.getStrategy(n);
//        this.board_states=new Stack<>();
//        board_states.push(board);
    }

    public void makeMove() {
        int n=players.size();
        Player player=players.get(current_player_index);
        Cell cell=player.makeMove(board);
        Move move=new Move(cell,player);
        moves.add(move);
        //board_states.push(board);
        if(strategy.hasWon(board,cell)) {
            status=GameStatus.WIN;
            return;
        }
        else if(moves.size()==((n+1)*(n+1))) {
            status=GameStatus.DRAW;
            return;
        }
        current_player_index=(++current_player_index%n);
    }

    public void undo() {
        Move move=moves.remove(moves.size()-1);
        int row=move.getCell().getX();
        int col=move.getCell().getY();
        if(current_player_index==0)
            current_player_index=players.size()-1;
        else
            current_player_index--;
//        board_states.pop();
//        board=board_states.peek();
        strategy.undoCell(move.getCell());
        board.getGrid().get(row).get(col).setPlayer(null);
    }

    public void replay() {
        int row=-1, col=-1;
        Player player=null;
        board=new Board(players.size()+1);
        board.print();
        System.out.println();
        System.out.println();
        for(Move move : moves) {
            row=move.getCell().getX();
            col=move.getCell().getY();
            player= move.getPlayer();
            board.getGrid().get(row).get(col).setPlayer(player);
            board.print();
            System.out.println();
            System.out.println();
        }
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
