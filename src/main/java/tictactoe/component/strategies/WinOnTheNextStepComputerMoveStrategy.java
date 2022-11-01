package tictactoe.component.strategies;

import tictactoe.component.ComputerMoveStrategy;
import tictactoe.model.game.Cell;
import tictactoe.model.game.GameTable;
import tictactoe.model.game.Sign;

/**
 * @author mark
 */
public class WinOnTheNextStepComputerMoveStrategy extends AbstractComputerMoveStrategy {


    public WinOnTheNextStepComputerMoveStrategy() {
        super(2);
    }

    @Override
    protected Sign getFoundSign(Sign moveSign) {
        return moveSign;
    }
}
