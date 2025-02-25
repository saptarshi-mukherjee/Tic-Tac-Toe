package Strategies.Winner;

import Models.Board;
import Models.Cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LargeBoardStrategy implements WinningStrategy {

    List<HashMap<Character,Integer>> rows;
    List<HashMap<Character,Integer>> columns;
    HashMap<Character,Integer> diagonal;
    HashMap<Character,Integer> reverse_diagonal;

    public LargeBoardStrategy(int n) {
        int i;
        rows=new ArrayList<>();
        columns=new ArrayList<>();
        diagonal=new HashMap<>();
        reverse_diagonal=new HashMap<>();
        for(i=0;i<n;i++) {
            HashMap<Character,Integer> map=new HashMap<>();
            rows.add(map);
        }
        for(i=0;i<n;i++) {
            HashMap<Character,Integer> map=new HashMap<>();
            columns.add(map);
        }
    }


    @Override
    public boolean hasWon(Board board, Cell cell) {
        updateCell(cell);
        boolean row_check=checkRows(cell);
        boolean column_check=checkColumns(cell);
        boolean diagonal_check=false;
        boolean reverse_diagonal_check=false;
        if(cell.getX()== cell.getY())
            diagonal_check=checkDiagonal(cell);
        if(cell.getX()+ cell.getY() == rows.size()-1)
            reverse_diagonal_check=checkReverseDiagonal(cell);
        if(row_check || column_check || diagonal_check || reverse_diagonal_check)
            return true;
        else
            return false;
    }


    private void updateCell(Cell cell) {
        int row=cell.getX();
        int col=cell.getY();
        int n=rows.size();
        Character symbol=cell.getPlayer().getSymbol();
        putInMap(rows.get(row),symbol);
        putInMap(columns.get(col),symbol);
        if(row==col)
            putInMap(diagonal,symbol);
        if(row+col == n-1)
            putInMap(reverse_diagonal,symbol);
    }

    private void putInMap(HashMap<Character,Integer> map, Character symbol) {
        if(!map.containsKey(symbol))
            map.put(symbol,1);
        else
            map.put(symbol, map.get(symbol)+1);
    }

    private boolean checkRows(Cell cell) {
        Character symbol=cell.getPlayer().getSymbol();
        int row=cell.getX();
        int col=cell.getY();
        if(rows.get(row).get(symbol)==rows.size())
            return true;
        else
            return false;
    }

    private boolean checkColumns(Cell cell) {
        int row=cell.getX();
        int col=cell.getY();
        Character symbol=cell.getPlayer().getSymbol();
        if(columns.get(col).get(symbol)==columns.size())
            return true;
        else
            return false;
    }

    private boolean checkDiagonal(Cell cell) {
        Character symbol=cell.getPlayer().getSymbol();
        if(diagonal.get(symbol)==rows.size())
            return true;
        else
            return false;
    }

    private boolean checkReverseDiagonal(Cell cell) {
        Character symbol=cell.getPlayer().getSymbol();
        if(reverse_diagonal.get(symbol)==rows.size())
            return true;
        else
            return false;
    }
}
