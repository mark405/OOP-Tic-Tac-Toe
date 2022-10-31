package tictactoe.component.strategies;

import tictactoe.component.ComputerMoveStrategy;
import tictactoe.model.game.Cell;
import tictactoe.model.game.GameTable;
import tictactoe.model.game.Sign;

/**
 * @author mark
 */
public class CenterComputerMoveStrategy implements ComputerMoveStrategy {
    @Override
    public boolean tryToMakeMove(final GameTable gameTable, final Sign sign) {
        final Cell centerCell = new Cell(1, 1);
        if (gameTable.isEmpty(centerCell)) {
            gameTable.setSign(centerCell, sign);

            return true;
        }

        return false;
    }
}
