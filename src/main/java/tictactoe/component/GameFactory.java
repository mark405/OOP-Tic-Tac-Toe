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

import tictactoe.component.keypad.DesktopNumericKeypadCellNumberConverter;
import tictactoe.model.Player;
import tictactoe.model.PlayerType;

import static tictactoe.model.Sign.O;
import static tictactoe.model.Sign.X;

/**
 * @author mark
 */
public class GameFactory {

    private final PlayerType playerType1 = PlayerType.USER;

    private final PlayerType playerType2 = PlayerType.COMPUTER;

    public GameFactory(final String[] args) {
        // TODO
    }

    public Game create() {
        final CellNumberConverter cellNumberConverter = new DesktopNumericKeypadCellNumberConverter();

        return new Game(
                new DataPrinter(cellNumberConverter),
                //FIXME
                new Player(X, new UserMove(cellNumberConverter)),
                new Player(O, new ComputerMove()),
                new ComputerMove(),
                new UserMove(cellNumberConverter),
                new WinnerVerifier(),
                new SellVerifier(),
                false);
    }
}
