/*
 * Copyright (c) 2019. Mark Zavgorodnii
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package mark.tictactoe.component;

import mark.tictactoe.model.Cell;
import mark.tictactoe.model.GameTable;

/**
 * @author mark
 */
public class WinnerVerifier {

    private boolean isWin(final GameTable gameTable, final char cell) {

        return isWinnerByRows(gameTable, cell) ||
                isWinnerByColumns(gameTable, cell) ||
                isWinnerByMainDiagonale(gameTable, cell) ||
                isWinnerBySecondaryDiagonale(gameTable, cell);
    }

    private boolean isWinnerByMainDiagonale(GameTable gameTable, char cell) {
        if (gameTable.getSign(new Cell(0, 0)) == cell &&
                gameTable.getSign(new Cell(1, 1)) == cell &&
                gameTable.getSign(new Cell(2, 2)) == cell) {
            return true;
        }


        return false;
    }

    private boolean isWinnerBySecondaryDiagonale(GameTable gameTable, char cell) {
        if (gameTable.getSign(new Cell(0, 2)) == cell &&
                gameTable.getSign(new Cell(1, 1)) == cell &&
                gameTable.getSign(new Cell(2, 0)) == cell) {
            return true;
        }
        return false;
    }

    private boolean isWinnerByRows(GameTable gameTable, char cell) {
        for (int i = 0; i < 3; i++) {
            if (gameTable.getSign(new Cell(i, 0)) == cell &&
                    gameTable.getSign(new Cell(i, 1)) == cell &&
                    gameTable.getSign(new Cell(i, 2)) == cell) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinnerByColumns(GameTable gameTable, char cell) {
        for (int i = 0; i < 3; i++) {
            if (gameTable.getSign(new Cell(0, i)) == cell &&
                    gameTable.getSign(new Cell(1, i)) == cell &&
                    gameTable.getSign(new Cell(2, i)) == cell) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserWin(final GameTable gameTable) {

        return isWin(gameTable, 'X');
    }

    public boolean isComputerWin(GameTable gameTable) {

        return isWin(gameTable, '0');
    }
}
