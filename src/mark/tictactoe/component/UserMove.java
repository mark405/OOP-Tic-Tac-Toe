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

import java.util.Scanner;

/**
 * @author mark
 */
public class UserMove {

    private final char[][] tableOfNumbers = {
            {'7', '8', '9'},
            {'4', '5', '6'},
            {'1', '2', '3'},
    };

    public void make(final GameTable gameTable) {

        while(true) {
            final Cell userCell = getUserInput();

            if (gameTable.isEmpty(userCell)) {
                gameTable.setSign(userCell, 'X');
                return;
            } else {
                System.out.println("Can't make a move, because the cell is not free. Try again!");
            }
        }
    }

    private Cell getUserInput() {
        while(true) {

            System.out.println("Please type number between 1 and 9:");

            final String signOfUser = new Scanner(System.in).nextLine();

            if (signOfUser.length() == 1) {
                final char ch = signOfUser.charAt(0);
                if (ch >= '1' && ch <= '9') {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (tableOfNumbers[i][j] == ch) {
                                return new Cell(i, j);
                            }
                        }

                    }
                }
            }

        }
    }
}
