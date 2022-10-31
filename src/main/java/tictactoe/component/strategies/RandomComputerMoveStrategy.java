package tictactoe.component.strategies;

import tictactoe.component.ComputerMoveStrategy;
import tictactoe.model.game.Cell;
import tictactoe.model.game.GameTable;
import tictactoe.model.game.Sign;

import java.util.Random;

/**
 * @author mark
 */
public class RandomComputerMoveStrategy implements ComputerMoveStrategy {
    @Override
    public boolean tryToMakeMove(GameTable gameTable, Sign sign) {
        final Cell[] emptyCells = new Cell[9];

        int count = getCountOfEmptyCells(gameTable, emptyCells);

        if (count > 0) {
            final Cell randomCell = emptyCells[new Random().nextInt(count)];
            gameTable.setSign(randomCell, sign);

            return true;
        } else {
            throw new IllegalArgumentException("Game table does not contain empty cell");
        }
    }

    private int getCountOfEmptyCells(final GameTable gameTable, Cell[] emptyCells) {
        var index = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final Cell cell = new Cell(i, j);
                if (gameTable.isEmpty(cell)) {
                    emptyCells[index++] = cell;
                }
            }
        }
        return index;
    }
}
