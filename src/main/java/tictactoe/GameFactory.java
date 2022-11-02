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

package tictactoe;

import tictactoe.component.*;
import tictactoe.component.config.CommandLineArgumentParser;
import tictactoe.component.console.CellNumberConverter;
import tictactoe.component.console.ConsoleDataPrinter;
import tictactoe.component.console.ConsoleGameOverHandler;
import tictactoe.component.console.ConsoleUserInputReader;
import tictactoe.component.console.keypad.DesktopNumericKeypadCellNumberConverter;
import tictactoe.component.strategies.*;
import tictactoe.component.swing.GameWindow;
import tictactoe.model.config.Level;
import tictactoe.model.config.PlayerType;
import tictactoe.model.config.UserInterface;
import tictactoe.model.game.Player;

import static tictactoe.model.config.Level.*;
import static tictactoe.model.config.PlayerType.USER;
import static tictactoe.model.config.UserInterface.GUI;
import static tictactoe.model.game.Sign.O;
import static tictactoe.model.game.Sign.X;

/**
 * @author mark
 */
public class GameFactory {

    private final PlayerType playerType1;

    private final PlayerType playerType2;

    private final UserInterface userInterface;

    private final Level level;

    public GameFactory(final String[] args) {

        final CommandLineArgumentParser.CommandLineArguments commandLineArguments = new CommandLineArgumentParser(args).parse();

        userInterface = commandLineArguments.getUserInterface();
        playerType1 = commandLineArguments.getPlayerType1();
        playerType2 = commandLineArguments.getPlayerType2();
        level = commandLineArguments.getLevel();
    }

    public Game create() {

        final DataPrinter dataPrinter;
        final UserInputReader userInputReader;
        final GameOverHandler gameOverHandler;

        if (userInterface == GUI) {
            final GameWindow gameWindow = new GameWindow();
            dataPrinter = gameWindow;
            userInputReader = gameWindow;
            gameOverHandler = gameWindow;
        } else {
            final CellNumberConverter cellNumberConverter = new DesktopNumericKeypadCellNumberConverter();
            dataPrinter = new ConsoleDataPrinter(cellNumberConverter);
            userInputReader = new ConsoleUserInputReader(cellNumberConverter, dataPrinter);
            gameOverHandler = new ConsoleGameOverHandler(dataPrinter);
        }


        final Player player1;
        if (playerType1 == USER) {
            player1 = new Player(X, new UserMove(userInputReader, dataPrinter));
        } else {
            player1 = new Player(X, new ComputerMove(level.getStrategies()));
        }

        final Player player2;
        if (playerType2 == USER) {
            player2 = new Player(O, new UserMove(userInputReader, dataPrinter));
        } else {
            player2 = new Player(O, new ComputerMove(level.getStrategies()));
        }

        final boolean canSecondPlayerMakeFirstMove = playerType1 != playerType2;

        return new Game(
                player1,
                player2,
                new WinnerVerifier(),
                new SellVerifier(),
                canSecondPlayerMakeFirstMove,
                dataPrinter,
                gameOverHandler);
    }
}
