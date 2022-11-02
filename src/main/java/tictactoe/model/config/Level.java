package tictactoe.model.config;

import tictactoe.component.ComputerMoveStrategy;
import tictactoe.component.strategies.*;

/**
 * @author mark
 */
public enum Level {

    LEVEL1(new ComputerMoveStrategy[]{
            new CenterComputerMoveStrategy(),
            new RandomComputerMoveStrategy(),
    }),

    LEVEL2(new ComputerMoveStrategy[]{
            new WinNowComputerMoveStrategy(),
            new PreventUserWinComputerMoveStrategy(),
            new CenterComputerMoveStrategy(),
            new RandomComputerMoveStrategy(),
    }),

    LEVEL3(new ComputerMoveStrategy[]{
            new WinNowComputerMoveStrategy(),
            new PreventUserWinComputerMoveStrategy(),
            new WinOnTheNextStepComputerMoveStrategy(),
            new CenterComputerMoveStrategy(),
            new RandomComputerMoveStrategy(),
    });

    private final ComputerMoveStrategy[] strategies;


    Level(final ComputerMoveStrategy[] strategies) {
        this.strategies = strategies;
    }

    public ComputerMoveStrategy[] getStrategies() {
        return strategies.clone();
    }
}
