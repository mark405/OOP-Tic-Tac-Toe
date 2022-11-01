package tictactoe.component.strategies;

import tictactoe.component.ComputerMoveStrategy;
import tictactoe.model.game.Cell;
import tictactoe.model.game.GameTable;
import tictactoe.model.game.Sign;

/**
 * @author mark
 */
public abstract class AbstractComputerMoveStrategy implements ComputerMoveStrategy {

    final int expectedCountOfEmptyCells;

    protected AbstractComputerMoveStrategy(int expectedCountOfEmptyCells) {
        this.expectedCountOfEmptyCells = expectedCountOfEmptyCells;
    }

    @Override
    public final boolean tryToMakeMove(final GameTable gameTable, final Sign sign) {
        final Sign resultSign = getFoundSign(sign);
        return tryToMakeMoveByRows(gameTable, resultSign, sign) ||
                tryToMakeMoveByCols(gameTable, resultSign, sign) ||
                tryToMakeWinByMainDiagonal(gameTable, resultSign, sign) ||
                tryToMakeWinBySecondDiagonal(gameTable, resultSign, sign);
    }

    private boolean tryToMakeWinByMainDiagonal(final GameTable gameTable, final Sign resultSign, final Sign moveSign) {
        return tryToMakeMoveUsingLambdaConversion(gameTable, resultSign, moveSign, -1, (k, j) -> new Cell(j, j));
    }

    private boolean tryToMakeWinBySecondDiagonal(final GameTable gameTable,  final Sign resultSign, final Sign moveSign) {
        return tryToMakeMoveUsingLambdaConversion(gameTable, resultSign, moveSign, -1, (k, j) -> new Cell(j, 2 - j));
    }

    private boolean tryToMakeMoveByRows(final GameTable gameTable,  final Sign resultSign, final Sign moveSign) {
        for (int i = 0; i < 3; i++) {
            if (tryToMakeMoveUsingLambdaConversion(gameTable, resultSign, moveSign, i, (k, j) -> new Cell(k, j))) {
                return true;
            }
        }

        return false;
    }

    private boolean tryToMakeMoveByCols(final GameTable gameTable,  final Sign resultSign, final Sign moveSign) {
        for (int i = 0; i < 3; i++) {
            if (tryToMakeMoveUsingLambdaConversion(gameTable, resultSign, moveSign, i, (k, j) -> new Cell(j, k))) {
                return true;
            }
        }

        return false;
    }

    private boolean tryToMakeMoveUsingLambdaConversion(final GameTable gameTable,
                                                       final Sign resultSign,
                                                       final Sign moveSign,
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
            } else if (gameTable.getSign(cell) == resultSign) {
                ++countOfSignedCells;
            } else {
                break;
            }
        }

        if (lastEmptyCell != null &&
                countOfEmptyCells == expectedCountOfEmptyCells &&
                countOfSignedCells == 3 - expectedCountOfEmptyCells) {
            gameTable.setSign(lastEmptyCell, moveSign);
            return true;
        }

        return false;
    }

    @FunctionalInterface
    private interface Lambda {
        Cell convert(int k, int j);
    }

    protected abstract Sign getFoundSign(final Sign moveSign);


}
