package tictactoe.component.strategies;

import tictactoe.component.ComputerMoveStrategy;
import tictactoe.model.game.Cell;
import tictactoe.model.game.GameTable;
import tictactoe.model.game.Sign;

import java.util.Random;

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
        final BestSells bestSells = new BestSells();

        findBestCellsForMoveByRows(gameTable, resultSign, bestSells);
        findBestCellsForMoveByCols(gameTable, resultSign ,bestSells);
        findBestCellsForMoveByMainDiagonal(gameTable, resultSign, bestSells);
        findBestCellsForMoveBySecondDiagonal(gameTable, resultSign, bestSells);

        if (bestSells.count > 0) {
            final Cell randomCell = bestSells.emptyCells[new Random().nextInt(bestSells.count)];
            gameTable.setSign(randomCell, sign);
            return true;
        }

        return false;
    }

    private void findBestCellsForMoveByMainDiagonal(final GameTable gameTable, final Sign resultSign, final BestSells bestSells) {
        findBestCellsForMoveUsingLambdaConversion(gameTable, resultSign, bestSells, -1, (k, j) -> new Cell(j, j));
    }

    private void findBestCellsForMoveBySecondDiagonal(final GameTable gameTable,  final Sign resultSign, final BestSells bestSells) {
        findBestCellsForMoveUsingLambdaConversion(gameTable, resultSign, bestSells, -1, (k, j) -> new Cell(j, 2 - j));
    }

    private void findBestCellsForMoveByRows(final GameTable gameTable,  final Sign resultSign, final BestSells bestSells) {
        for (int i = 0; i < 3; i++) {
            findBestCellsForMoveUsingLambdaConversion(gameTable, resultSign, bestSells, i, (k, j) -> new Cell(k, j));

        }
    }

    private void findBestCellsForMoveByCols(final GameTable gameTable,  final Sign resultSign, final BestSells bestSells) {
        for (int i = 0; i < 3; i++) {
            findBestCellsForMoveUsingLambdaConversion(gameTable, resultSign, bestSells, i, (k, j) -> new Cell(j, k));
        }
    }

    private void findBestCellsForMoveUsingLambdaConversion(final GameTable gameTable,
                                                       final Sign resultSign,
                                                       final BestSells bestSells,
                                                       final int i,
                                                       final Lambda lambda) {
        var countOfEmptyCells = 0;
        var countOfSignedCells = 0;

        final Cell[] localEmptyCells = new Cell[3];

        int count = 0;

        for (int j = 0; j < 3; j++) {
            final Cell cell = lambda.convert(i, j);
            if (gameTable.isEmpty(cell)) {
                ++countOfEmptyCells;
                localEmptyCells[count++] = cell;
            } else if (gameTable.getSign(cell) == resultSign) {
                ++countOfSignedCells;
            } else {
                break;
            }
        }

        if (countOfEmptyCells == expectedCountOfEmptyCells &&
                countOfSignedCells == 3 - expectedCountOfEmptyCells) {

            for (int j = 0; j < count; j++) {
                bestSells.add(localEmptyCells[j]);
            }
        }

    }

    @FunctionalInterface
    private interface Lambda {
        Cell convert(int k, int j);
    }

    protected abstract Sign getFoundSign(final Sign moveSign);

    private static class BestSells {
        private final Cell[] emptyCells = new Cell[9];
        private int count;
        private void add(final Cell cell) {
            emptyCells[count++] = cell;
        }
    }

}
