package tictactoe.component;

import tictactoe.model.game.GameTable;
import tictactoe.model.game.Sign;

/**
 * @author mark
 */
public interface ComputerMoveStrategy {
    boolean tryToMakeMove(final GameTable gameTable, final Sign sign);
}
