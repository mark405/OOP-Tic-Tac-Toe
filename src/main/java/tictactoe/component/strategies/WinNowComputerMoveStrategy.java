package tictactoe.component.strategies;

import tictactoe.component.ComputerMoveStrategy;
import tictactoe.model.game.Cell;
import tictactoe.model.game.GameTable;
import tictactoe.model.game.Sign;

/**
 * @author mark
 */
public class WinNowComputerMoveStrategy implements ComputerMoveStrategy {
    @Override
    public boolean tryToMakeMove(GameTable gameTable, Sign sign) {
        return tryToMakeMoveByRows(gameTable, sign) ||
                tryToMakeMoveByCols(gameTable, sign) ||
                tryToMakeWinByMainDiagonal(gameTable, sign) ||
                tryToMakeWinBySecondDiagonal(gameTable, sign);
    }

    private boolean tryToMakeWinByMainDiagonal(final GameTable gameTable, final Sign sign) {
        return tryToMakeMoveUsingLambdaConversion(gameTable, sign, -1, (k, j) -> new Cell(j, j));
    }

    private boolean tryToMakeWinBySecondDiagonal(final GameTable gameTable, final Sign sign) {
        return tryToMakeMoveUsingLambdaConversion(gameTable, sign, -1, (k, j) -> new Cell(j, 2 - j));
    }

    private boolean tryToMakeMoveByRows(final GameTable gameTable, final Sign sign) {
        for (int i = 0; i < 3; i++) {
            if (tryToMakeMoveUsingLambdaConversion(gameTable, sign, i, (k, j) -> new Cell(k, j))) {
                return true;
            }
        }

        return false;
    }

    private boolean tryToMakeMoveByCols(final GameTable gameTable, final Sign sign) {
        for (int i = 0; i < 3; i++) {
            if (tryToMakeMoveUsingLambdaConversion(gameTable, sign, i, (k, j) -> new Cell(j, k))) {
                return true;
            }
        }

        return false;
    }

    private boolean tryToMakeMoveUsingLambdaConversion(final GameTable gameTable,
                                                       final Sign sign,
                                                       final int i,
                                                       final Lambda lambda) {
        var countOfEmptyCells = 0;
        var countOfSignedCells = 0;
        Cell lastEmptyCell = null;
        for (int j = 0; j < 3; j++) {
            final Cell cell = lambda.convert(i, j);
            if (gameTable.isEmpty(cell)) {
                ++countOfEmptyCells;
                lastEmptyCell = cell;
            } else if (gameTable.getSign(cell) == sign) {
                ++countOfSignedCells;
            } else {
                break;
            }
        }

        if (countOfEmptyCells == 1 && countOfSignedCells == 2) {
            gameTable.setSign(lastEmptyCell, sign);
            return true;
        }

        return false;
    }

    @FunctionalInterface
    private interface Lambda {
        Cell convert(int k, int j);
    }
}
