import Controllers.GameController;
import Models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of players :-");
        int n = sc.nextInt();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter name :-");
            String name = sc.next();
            System.out.println("Enter symbol :-");
            Character symbol = sc.next().charAt(0);
            Player player = new HumnanPlayer(name, symbol);
            players.add(player);
        }
        System.out.println("Do you want bots? Only 1 bot available. (Y/N)");
        if (sc.next().toUpperCase().charAt(0) == 'Y') {
            Player bot = new BotPlayer("ALEX", '#');
            players.add(bot);
            System.out.println("ALEX is your bot. His symbol is #.");
        }
        GameController controller = new GameController();
        Game game = controller.createGame(players);
        controller.printBoard(game);
        while (controller.getGameStatus(game) == GameStatus.IN_PROGRESS) {
            controller.makeMove(game);
            controller.printBoard(game);
            if (controller.getLastMove(game).getPlayer() instanceof HumnanPlayer) {
                System.out.println("Do you want to undo? (Y/N)");
                if (sc.next().charAt(0) == 'Y') {
                    controller.undo(game);
                    controller.printBoard(game);
                }
            }
        }
        if (controller.getGameStatus(game) == GameStatus.WIN) {
            Player winner = controller.getWinner(game);
            System.out.println(winner.getName() + " is the winner");
        } else
            System.out.println("Game drawn.");
        controller.replay(game);
        System.out.println("Game ended");
    }
}
