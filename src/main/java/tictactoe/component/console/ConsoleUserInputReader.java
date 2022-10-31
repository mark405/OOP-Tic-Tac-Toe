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

package tictactoe.component.console;

import tictactoe.component.DataPrinter;
import tictactoe.component.UserInputReader;
import tictactoe.component.console.keypad.TerminalNumericKeypadCellNumberConverter;
import tictactoe.model.game.Cell;

import java.util.Scanner;

/**
 * @author mark
 */
public class ConsoleUserInputReader implements UserInputReader {

    private final CellNumberConverter cellNumberConverter;

    DataPrinter dataPrinter;

    public ConsoleUserInputReader(final CellNumberConverter cellNumberConverter, final DataPrinter dataPrinter) {
        this.cellNumberConverter = cellNumberConverter;
        this.dataPrinter = dataPrinter;
    }


    @Override
    public Cell getUserInput() {
        while (true) {

            dataPrinter.printInfoMessage("Please type number between 1 and 9:");

            final String signOfUser = new Scanner(System.in).nextLine();

            if (signOfUser.length() == 1) {
                final char ch = signOfUser.charAt(0);
                if (ch >= '1' && ch <= '9') {
                    return cellNumberConverter.toCell(ch);
                }
            }

        }
    }
}
