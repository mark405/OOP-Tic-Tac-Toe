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

package tictactoe.component.config;

import tictactoe.model.config.PlayerType;
import tictactoe.model.config.UserInterface;

import static tictactoe.model.config.PlayerType.COMPUTER;
import static tictactoe.model.config.PlayerType.USER;
import static tictactoe.model.config.UserInterface.CONSOLE;
import static tictactoe.model.config.UserInterface.GUI;

/**
 * @author mark
 */
public class CommandLineArgumentParser {

    private final String[] args;

    public CommandLineArgumentParser(final String[] args) {
        this.args = args;
    }

    public CommandLineArguments parse() {
        PlayerType playerType1 = null;
        PlayerType playerType2 = null;
        UserInterface userInterface = null;

        for (final String arg : args) {
            if (GUI.name().equalsIgnoreCase(arg) || CONSOLE.name().equalsIgnoreCase(arg)) {
                if (userInterface == null) {
                    userInterface = UserInterface.valueOf(arg);
                } else {
                    System.err.println("User interface has already chosen: " + userInterface.name());
                }
            } else {
                if (USER.name().equalsIgnoreCase(arg) || COMPUTER.name().equalsIgnoreCase(arg)) {
                    if (playerType1 == null) {
                        playerType1 = PlayerType.valueOf(arg.toUpperCase());
                    } else if (playerType2 == null) {
                        playerType2 = PlayerType.valueOf(arg.toUpperCase());
                    } else {
                        System.err.println("Game mode has already chosen: " + playerType1 + " " + playerType2);
                    }
                } else {
                    System.err.println("Unknown argument: '" + arg + "'");
                }
            }
        }

        if (userInterface == null) {
            userInterface = CONSOLE;
        }

        if (playerType1 == null) {
            return new CommandLineArguments(USER, COMPUTER, userInterface);
        } else if (playerType2 == null) {
            return new CommandLineArguments(USER, playerType1, userInterface);
        } else {
            return new CommandLineArguments(playerType1, playerType2, userInterface);
        }
    }

    public static class CommandLineArguments {
        private final PlayerType playerType1;

        private final PlayerType playerType2;

        private final UserInterface userInterface;

        private CommandLineArguments(final PlayerType playerType1,
                                     final PlayerType playerType2,
                                     final UserInterface userInterface) {
            this.playerType1 = playerType1;
            this.playerType2 = playerType2;
            this.userInterface = userInterface;
        }

        public UserInterface getUserInterface() {
            return userInterface;
        }

        public PlayerType getPlayerType1() {
            return playerType1;
        }

        public PlayerType getPlayerType2() {
            return playerType2;
        }
    }
}
