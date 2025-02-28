package Strategies.BotMove;

import Models.Board;
import Models.Cell;
import Models.Player;

import java.util.List;

public class EasyBotMoveStrategy implements BotMoveStrategy {
    @Override
    public Cell makeMove(Board board, Player bot) {
        Cell current_cell = null;
        outer:
        for (List<Cell> cells : board.getGrid()) {
            for (Cell cell : cells) {
                if (cell.getPlayer() == null) {
                    cell.setPlayer(bot);
                    current_cell = cell;
                    break outer;
                }
            }
        }
        return current_cell;
    }
}
