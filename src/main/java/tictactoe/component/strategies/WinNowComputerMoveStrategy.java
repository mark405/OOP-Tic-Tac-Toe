package tictactoe.component.strategies;

import tictactoe.model.game.Sign;

/**
 * @author mark
 */
public class WinNowComputerMoveStrategy extends AbstractComputerMoveStrategy {

    public WinNowComputerMoveStrategy() {
        super(1);
    }

    @Override
    protected Sign getFoundSign(final Sign moveSign) {
        return moveSign;
    }
}
