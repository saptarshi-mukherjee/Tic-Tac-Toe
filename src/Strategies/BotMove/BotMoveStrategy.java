package Strategies.BotMove;

import Models.Board;
import Models.Cell;
import Models.Player;

public interface BotMoveStrategy {
    public Cell makeMove(Board board, Player bot);
}
