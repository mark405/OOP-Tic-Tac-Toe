package tictactoe.component.strategies;

import tictactoe.component.ComputerMoveStrategy;
import tictactoe.model.game.Cell;
import tictactoe.model.game.GameTable;
import tictactoe.model.game.Sign;

/**
 * @author mark
 */
public class PreventUserWinComputerMoveStrategy extends AbstractComputerMoveStrategy {

    @Override
    protected Sign getFoundSign(final Sign moveSign) {
        return moveSign.getOppositeSign();
    }
}
