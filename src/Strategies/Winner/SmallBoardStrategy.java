package Strategies.Winner;

import Models.Board;
import Models.Cell;

public class SmallBoardStrategy implements WinningStrategy {
    @Override
    public boolean hasWon(Board board, Cell cell) {
        int row= cell.getX();
        int col=cell.getY();
        Character symbol=cell.getPlayer().getSymbol();
        boolean row_match=checkRow(board,row,col,symbol);
        boolean column_math=checkColumn(board,row,col,symbol);
        boolean diagonal_match=false;
        boolean reverse_diagonal_match=false;
        if(row==col)
            diagonal_match=checkDiagonal(board,row,col,symbol);
        else if((row+col)==board.getGrid().size()-1)
            reverse_diagonal_match=checkReverseDiagonal(board,row,col,symbol);
        if(row_match || column_math || diagonal_match || reverse_diagonal_match)
            return true;
        else
            return false;
    }

    private boolean checkRow(Board board, int row, int col, Character symbol) {
        int n=board.getGrid().size();
        boolean flag=true;
        for(int i=0; i<n; i++) {
            if(board.getGrid().get(row).get(i).getPlayer()==null || board.getGrid().get(row).get(i).getPlayer().getSymbol()!=symbol) {
                flag=false;
                break;
            }
        }
        return flag;

    }

    private boolean checkColumn(Board board, int row, int col, Character symbol) {
        int n=board.getGrid().size();
        boolean flag=true;
        for(int i=0; i<n; i++) {
            if(board.getGrid().get(i).get(col).getPlayer()==null || board.getGrid().get(i).get(col).getPlayer().getSymbol()!=symbol) {
                flag=false;
                break;
            }
        }
        return flag;
    }

    private boolean checkDiagonal(Board board, int row, int col, Character symbol) {
        int n=board.getGrid().size();
        boolean flag=true;
        int i=0, j=0;
        while(i<n && j<n) {
            if(board.getGrid().get(i).get(j).getPlayer()==null || board.getGrid().get(i).get(j).getPlayer().getSymbol()!=symbol) {
                flag=false;
                break;
            }
            i++;
            j++;
        }
        return flag;
    }

    private boolean checkReverseDiagonal(Board board, int row, int col, Character symbol) {
        int n=board.getGrid().size();
        boolean flag=true;
        int i=0, j=n-1;
        while(i<n && j>=0) {
            if(board.getGrid().get(i).get(j).getPlayer()==null || board.getGrid().get(i).get(j).getPlayer().getSymbol()!=symbol) {
                flag=false;
                break;
            }
            i++;
            j--;
        }
        return flag;
    }
}
