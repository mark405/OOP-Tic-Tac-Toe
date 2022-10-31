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
        return ifCanWinByVertical(gameTable, sign) ||
                ifCanWinByHorizontal(gameTable, sign) ||
                ifCanWinByMainDiagonal(gameTable, sign) ||
                ifCanWinBySecondDiagonal(gameTable, sign);
    }

    private boolean ifCanWinByMainDiagonal(final GameTable gameTable, final Sign sign) {
        var countOfEmptyCells = 0;
        var countOfSignedCells = 0;
        Cell lastEmptyCell = null;
        for (int j = 0; j < 3; j++) {
            final Cell cell = new Cell(j, j);
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

    private boolean ifCanWinBySecondDiagonal(final GameTable gameTable, final Sign sign) {
        var countOfEmptyCells = 0;
        var countOfSignedCells = 0;
        Cell lastEmptyCell = null;
        for (int j = 0; j < 3; j++) {
            final Cell cell = new Cell(j, 2 - j);
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

    private boolean ifCanWinByHorizontal(final GameTable gameTable, final Sign sign) {
        for (int i = 0; i < 3; i++) {
            var countOfEmptyCells = 0;
            var countOfSignedCells = 0;
            Cell lastEmptyCell = null;
            for (int j = 0; j < 3; j++) {
                final Cell cell = new Cell(i, j);
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
        }

        return false;
    }

    private static boolean ifCanWinByVertical(final GameTable gameTable, final Sign sign) {
        for (int i = 0; i < 3; i++) {
            var countOfEmptyCells = 0;
            var countOfSignedCells = 0;
            Cell lastEmptyCell = null;
            for (int j = 0; j < 3; j++) {
                final Cell cell = new Cell(j, i);
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
        }

        return false;
    }
}
