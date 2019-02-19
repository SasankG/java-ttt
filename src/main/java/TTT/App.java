package TTT;

import javax.swing.JFrame;


/**
 * A simple TicTacToe Game
 */
public class App {

    public static void main(String[] args) {
        JFrame tictactoe = new Frame();
        // Sets the title of the window
        tictactoe.setTitle("Java - TicTacToe");
        // Sets size of window
        tictactoe.setSize(600,600);
        // What to do when closing
        tictactoe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Places frame in middle of screen
        tictactoe.setLocationRelativeTo(null);
        // Make sure its visible
        tictactoe.setVisible(true);
    }

}
