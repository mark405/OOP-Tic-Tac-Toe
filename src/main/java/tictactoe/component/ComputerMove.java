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

package tictactoe.component;

import tictactoe.model.game.Cell;
import tictactoe.model.game.GameTable;
import tictactoe.model.game.Sign;

import java.util.Random;

/**
 * @author mark
 */
public class ComputerMove implements Move {
    @Override
    public void make(final GameTable gameTable, final Sign sign) {
        while (true) {

            final Random random = new Random();

            final var row = random.nextInt(3);
            final var col = random.nextInt(3);

            final Cell randomCell = new Cell(row, col);

            if (gameTable.isEmpty(randomCell)) {
                gameTable.setSign(randomCell, sign);
                return;
            }

        }

    }
}
