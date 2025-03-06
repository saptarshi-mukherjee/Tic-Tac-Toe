package Factories;

import Strategies.Winner.LargeBoardStrategy;
import Strategies.Winner.SmallBoardStrategy;
import Strategies.Winner.WinningStrategy;

public class WinningStrategyFactory {
    public static WinningStrategy getStrategy(int player_size) {
        if(player_size>100)
            return new LargeBoardStrategy(player_size+1);
        else
            return new SmallBoardStrategy();
    }
}
