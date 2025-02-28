package Models;

import java.util.Scanner;

public class HumnanPlayer extends Player {
    public HumnanPlayer(String name, Character symbol) {
        super(name, symbol);
    }

    @Override
    public Cell makeMove(Board board) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter row :-");
        int row=sc.nextInt();
        System.out.println("Enter column :-");
        int column=sc.nextInt();
        return board.setPlayer(row, column, this);
    }
}
