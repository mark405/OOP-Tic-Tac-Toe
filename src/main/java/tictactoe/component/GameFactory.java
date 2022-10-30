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

import static tictactoe.model.PlayerType.COMPUTER;
import static tictactoe.model.PlayerType.USER;
import static tictactoe.model.Sign.O;
import static tictactoe.model.Sign.X;

/**
 * @author mark
 */
public class GameFactory {

    private final PlayerType playerType1;

    private final PlayerType playerType2;

    public GameFactory(final String[] args) {

        final CommandLineArgumentParser.PlayerTypes playerTypes =
                new CommandLineArgumentParser(args).parse();

        this.playerType1 = playerTypes.getPlayerType1();
        this.playerType2 = playerTypes.getPlayerType2();

    }

    public Game create() {

        final CellNumberConverter cellNumberConverter = new DesktopNumericKeypadCellNumberConverter();

        final Player player1;
        if (playerType1 == USER) {
            player1 = new Player(X, new UserMove(cellNumberConverter));
        } else {
            player1 = new Player(X, new ComputerMove());
        }

        final Player player2;
        if (playerType2 == USER) {
            player2 = new Player(O, new UserMove(cellNumberConverter));
        } else {
            player2 = new Player(O, new ComputerMove());
        }

        final boolean canSecondPlayerMakeFirstMove = playerType1 != playerType2;

        return new Game(
                new DataPrinter(cellNumberConverter),
                //FIXME
                player1,
                player2,
                new WinnerVerifier(),
                new SellVerifier(),
                canSecondPlayerMakeFirstMove);
    }
}
