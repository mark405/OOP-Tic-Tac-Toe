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

package tictactoe.component.console.keypad;

import tictactoe.component.console.CellNumberConverter;
import tictactoe.model.game.Cell;

import static java.lang.String.format;

/**
 * @author mark
 */
public class DesktopNumericKeypadCellNumberConverter implements CellNumberConverter {

    char[][] tableOfNumbers = {
            {'7', '8', '9'},
            {'4', '5', '6'},
            {'1', '2', '3'},
    };

    @Override
    public Cell toCell(final char number) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tableOfNumbers[i][j] == number) {
                    return new Cell(i, j);
                }
            }
        }

        throw new IllegalArgumentException(
                format("Number must be from 1 to 9", number));
    }

    @Override
    public char toNumber(final Cell cell) {
        try {
            return tableOfNumbers[cell.getRow()][cell.getCol()];
        } catch (final ArrayIndexOutOfBoundsException ignored) {
            throw new IllegalArgumentException(
                    format("Row and col indexes must be from 0 to 2", cell.getRow(), cell.getCol())
            );
        }
    }
}

