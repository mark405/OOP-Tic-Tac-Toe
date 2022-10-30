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

package tictactoe.component.swing;

import tictactoe.component.DataPrinter;
import tictactoe.component.GameOverHandler;
import tictactoe.component.UserInputReader;
import tictactoe.model.game.Cell;
import tictactoe.model.game.GameTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author mark
 */
public class GameWindow extends JFrame implements DataPrinter, UserInputReader, GameOverHandler {

    private static final int GAME_TABLE_SIZE = 3;

    private static final int FONT_SIZE = 35;

    private static final int CELL_SIZE = 90;

    private Cell clickedSell;

    private final JLabel[][] cells = new JLabel[GAME_TABLE_SIZE][GAME_TABLE_SIZE];

    public GameWindow() {
        super("Tic-Tac-Toe");
        setSystemLookAndFill();
        setLayout(new GridLayout(GAME_TABLE_SIZE, GAME_TABLE_SIZE));
        createGameTable();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        displayInTheMiddleOfTheScreen();
    }

    private void setSystemLookAndFill() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (final ClassNotFoundException | UnsupportedLookAndFeelException |
                       IllegalAccessException | InstantiationException ex) {
            ex.printStackTrace();
        }
    }

    private void createGameTable() {
        for (int i = 0; i < GAME_TABLE_SIZE; i++) {
            for (int j = 0; j < GAME_TABLE_SIZE; j++) {
                final JLabel jLabel = new JLabel();
                cells[i][j] = jLabel;
                jLabel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                jLabel.setHorizontalAlignment(SwingConstants.CENTER);
                jLabel.setVerticalAlignment(SwingConstants.CENTER);
                jLabel.setFont(new Font(Font.SERIF, Font.PLAIN, FONT_SIZE));
                jLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(jLabel);
                final Cell cell = new Cell(i, j);
                jLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(final MouseEvent unused) {
                        synchronized (GameWindow.this) {
                            clickedSell = cell;
                            GameWindow.this.notify();
                        }

                    }
                });
            }
        }
    }

    private void displayInTheMiddleOfTheScreen() {
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
        setVisible(true);
    }


    @Override
    public void printInfoMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void printErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void printGameTable(GameTable gameTable) {
        for (int i = 0; i < GAME_TABLE_SIZE; i++) {
            for (int j = 0; j < GAME_TABLE_SIZE; j++) {
                cells[i][j].setText(String.valueOf(gameTable.getSign(new Cell(i, j))));
            }
        }
    }

    @Override
    public void printInstructions() {
        //do nothing
    }

    @Override
    public Cell getUserInput() {
        synchronized (this) {
            try {
                wait();
            } catch(final InterruptedException exception) {
                exception.printStackTrace();
                System.exit(2);
            }
        }

        return clickedSell;
    }

    @Override
    public void gameOver() {
        System.exit(0);
    }
}
