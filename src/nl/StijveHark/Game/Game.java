package nl.StijveHark.Game;

import javax.swing.*;

public class Game extends JFrame {

    private GamePanel game;

    public Game(){
        // Config title bar
        super("Space Invaders");

        // Make sure the program exits when the close button is clicked
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Create an instance of the Game class and turn on double buffering to ensure animation
        game = new GamePanel();
        game.setDoubleBuffered(true);

        // Breakout instance added to this frame's content to display
        this.getContentPane().add(game);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // Start the game
        game.start();
    }


    public static void main(String[] args) {
	// write your code here
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game().setVisible(true);
            }
        });

    }
}
