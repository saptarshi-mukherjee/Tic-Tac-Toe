package Strategies.Winner;

import Models.Board;
import Models.Cell;

public interface WinningStrategy {
    public boolean hasWon(Board board, Cell cell);
    public void undoCell(Cell cell);
}
