package Models;

import Strategies.BotMove.BotMoveStrategy;
import Strategies.BotMove.EasyBotMoveStrategy;

public class BotPlayer extends Player {

    BotMoveStrategy strategy;

    public BotPlayer(String name, Character symbol) {
        super(name, symbol);
        strategy=new EasyBotMoveStrategy();
    }

    @Override
    public Cell makeMove(Board board) {
        return strategy.makeMove(board,this);
    }
}
