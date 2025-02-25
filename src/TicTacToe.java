import Controllers.GameController;
import Models.Game;
import Models.GameStatus;
import Models.HumnanPlayer;
import Models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of players :-");
        int n=sc.nextInt();
        List<Player> players=new ArrayList<>();
        for(int i=0;i<n;i++) {
            System.out.println("Enter name :-");
            String name=sc.next();
            System.out.println("Enter symbol :-");
            Character symbol=sc.next().charAt(0);
            Player player=new HumnanPlayer(name,symbol);
            players.add(player);
        }
        GameController controller=new GameController();
        Game game=controller.createGame(players);
        while(controller.getGameStatus(game)==GameStatus.IN_PROGRESS) {
            controller.printBoard(game);
            controller.makeMove(game);
        }
        controller.printBoard(game);
        System.out.println("Game ended");
    }
}
