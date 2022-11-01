package tictactoe.component.strategies;

import tictactoe.model.game.Sign;

/**
 * @author mark
 */
public class PreventUserWinComputerMoveStrategy extends AbstractComputerMoveStrategy {

    public PreventUserWinComputerMoveStrategy() {
        super(1);
    }

    @Override
    protected Sign getFoundSign(final Sign moveSign) {
        return moveSign.getOppositeSign();
    }
}
