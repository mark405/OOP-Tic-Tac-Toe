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

import tictactoe.model.GameTable;
import tictactoe.model.Player;

import java.util.Random;

import static tictactoe.model.Sign.O;
import static tictactoe.model.Sign.X;

/**
 * @author mark
 */
public class Game {

    private final DataPrinter dataPrinter;

    private final ComputerMove computerMove;

    private final UserMove userMove;

    private final WinnerVerifier winnerVerifier;

    private final SellVerifier sellVerifier;

    public Game(final DataPrinter dataPrinter,
                final ComputerMove computerMove,
                final UserMove userMove,
                final WinnerVerifier winnerVerifier,
                final SellVerifier sellVerifier) {
        this.dataPrinter = dataPrinter;
        this.computerMove = computerMove;
        this.userMove = userMove;
        this.winnerVerifier = winnerVerifier;
        this.sellVerifier = sellVerifier;
    }

    public void play() {

        System.out.println("Please use the following mapping table to specify a cell using numbers from 1 to 9:");
        dataPrinter.printMappingTable();

        final GameTable gameTable = new GameTable();

        /*if (new Random().nextBoolean()) {
            computerMove.make(gameTable, O);
            dataPrinter.printGameTable(gameTable);
        }
         */

        final Player[] players = {new Player(X, userMove), new Player(O, computerMove)};

        while (true) {

            for (final Player player : players) {
                player.makeMove(gameTable);
                dataPrinter.printGameTable(gameTable);

                if (winnerVerifier.isWin(gameTable, player)) {
                    System.out.println(player + " WIN!");
                    printGameOver();
                    return;
                }

                if (sellVerifier.AllSellsFilled(gameTable)) {
                    System.out.println("SORRY DRAW!");
                    printGameOver();
                    return;
                }
            }
        }

    }

    private void printGameOver() {
        System.out.println("GAME OVER");
    }

}
