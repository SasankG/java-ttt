package TTT;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * The Frame that holds the gameboard
 */

public class Frame extends JFrame {

    // Turn indicator -> true = player
    private char turn = 'X';

    // Create the grid to play the game
    // We will use a 2D array
    private Cell[][] cells = new Cell[3][3];

    // Status Label
    JLabel jlblStatus = new JLabel("Your turn to play");

    // Create a constructor to make a game board
    // CONSTRUCTORS NEED TO HAVE THE SAME NAME AS THE FILE
    public Frame() {

        // Create a 3x3 grid using swing GridLayout
        JPanel panel = new JPanel(new GridLayout(3, 3, 0, 0));

        // Fill grid with cells using a simple for loops, one for rows and one for columns
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                panel.add(cells[i][j] = new Cell());
            }
        }

        // 1px border
        panel.setBorder((new LineBorder(Color.BLUE, 1)));

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);
        add(jlblStatus, BorderLayout.SOUTH);
    }

    /**
     * @return True = full | False = Vacant spots open
     */
    // Check if game board is full
    public boolean isFull() {
        // Using for loops to check cells
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // If there is an open spot function returns false
                // getToken method is made in the Cell.java class
                if (cells[i][j].getToken() == ' ') {
                    return false;
                }
            }
        }
        // default true if board is full
        return true;
    }

    /**
     * Check for win
     *
     * @param token = from the token class, a token is returned for unvacant spots
     * @return True = win | False = not yet / loss
     */
    public boolean isWon(char token) {

        // Check rows
        for (int i = 0; i < 3; i++) {
            if ((cells[i][0].getToken() == token) && (cells[i][1].getToken() == token) && (cells[i][2]).getToken() == token) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if ((cells[0][j].getToken() == token) && (cells[1][j].getToken() == token) && (cells[2][j].getToken() == token)) {
                return true;
            }
        }

        // Check diagonals
        if ((cells[0][0].getToken() == token) && (cells[1][1].getToken() == token) && (cells[2][2]).getToken() == token) {
            return true;
        }
        if ((cells[0][2].getToken() == token) && (cells[1][1].getToken() == token) && (cells[2][0]).getToken() == token) {
            return true;
        }

        // default
        return false;
    }

    // Each cell itself will be a JPanel
    public class Cell extends JPanel {
        // Define the token
        // Default is blank
        private char token = ' ';

        // Cell constructor
        public Cell() {

            setBorder(new LineBorder(Color.BLACK, 1));

            // Mouse Event handler
            addMouseListener(new MouseListener());
        }

        // Return Tokens
        public char getToken() {
            return token;
        }

        // Place Token in cell
        public void setToken(char c) {

            token = c;

            // repaint the cell
            repaint();
        }

        // repaint needs to Override the Swing paintComponent
        @Override
        protected void paintComponent(Graphics g) {

            // call parent class from paintComponent so we can override
            super.paintComponent(g);

            // Check tokens value
            if (token == 'X') {
                // Draw the X
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLUE);
                g2.setStroke(new BasicStroke(8));
                g2.drawLine(10, 10, getWidth() - 10, getHeight() - 10);
                g2.drawLine(getWidth() - 10, 10, 10, getHeight() - 10);
            } else if (token == 'O') {
                // Draw the O
                Graphics2D g3 = (Graphics2D) g;
                g3.setStroke(new BasicStroke(8));
                g3.setColor(Color.RED);
                g3.drawOval(10, 10, getWidth() - 20, getHeight() - 20);
            }
        }

        // Mouse click listener
        private class MouseListener extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {

                // if game is not over and still has empty spots
                if (token == ' ' && turn != ' ') {
                    setToken(turn);
                }

                // game status
                if (isWon(turn)) {
                    jlblStatus.setText(turn + " Won the Game!");
                } else if (isFull()) {
                    jlblStatus.setText(("Tie"));
                } else {
                    // Switch turns
                    turn = (turn == 'X') ? 'O' : 'X';
                }
            }
        }

    }
}
