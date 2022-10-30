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

import tictactoe.model.PlayerType;

import static tictactoe.model.PlayerType.COMPUTER;
import static tictactoe.model.PlayerType.USER;

/**
 * @author mark
 */
public class CommandLineArgumentParser {

    private final String[] args;

    public CommandLineArgumentParser(final String[] args) {
        this.args = args;
    }

    public PlayerTypes parse() {
        PlayerType playerType1 = null;
        PlayerType playerType2 = null;

        for (final String arg : args) {
            if (USER.name().equalsIgnoreCase(arg) || COMPUTER.name().equalsIgnoreCase(arg)) {
                if (playerType1 == null) {
                    playerType1 = PlayerType.valueOf(arg.toUpperCase());
                } else if (playerType2 == null) {
                    playerType2 = PlayerType.valueOf(arg.toUpperCase());
                } else {
                    System.err.println("Unsupported command line argument: '" + arg + "'");
                }
            } else {
                System.err.println("Unsupported command line argument: '" + arg + "'");
            }
        }

        if (playerType1 == null) {
            return new PlayerTypes(USER, COMPUTER);
        } else if (playerType2 == null) {
            return new PlayerTypes(USER, playerType1);
        } else {
            return new PlayerTypes(playerType1, playerType2);
        }
    }

    public static class PlayerTypes {
        private final PlayerType playerType1;

        private final PlayerType playerType2;

        private PlayerTypes(final PlayerType playerType1, final PlayerType playerType2) {
            this.playerType1 = playerType1;
            this.playerType2 = playerType2;
        }

        public PlayerType getPlayerType1() {
            return playerType1;
        }

        public PlayerType getPlayerType2() {
            return playerType2;
        }
    }
}
