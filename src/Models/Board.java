package Models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<List<Cell>> grid;

    public Board(int n) {
        grid=new ArrayList<>(n);
        for(int i=0;i<n;i++) {
            ArrayList<Cell> col=new ArrayList<>(n);
            for(int j=0;j<n;j++) {
                Cell cell=new Cell(i,j);
                col.add(cell);
            }
            grid.add(col);
        }
    }

    public List<List<Cell>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<Cell>> grid) {
        this.grid = grid;
    }

    public void print() {
        int n=grid.size();
        int i=0, j=0;
        for(i=0;i<n;i++) {
            for(j=0;j<n;j++) {
                grid.get(i).get(j).print();
                if(j!=(n-1))
                    System.out.print("|");
            }
            System.out.println();
        }
    }

    public Cell setPlayer(int row, int column, Player player) throws RuntimeException {
        if(grid.get(row).get(column).getPlayer()==null) {
            grid.get(row).get(column).setPlayer(player);
            //System.out.println(grid.get(row).get(column).getPlayer().getName());
        }
        else
            throw new RuntimeException("Cell occupied");
        return grid.get(row).get(column);
    }
}
