package nl.StijveHark.Game;

import devices.KeyboardControl;
import providers.Highscore;
import sound.SoundFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {

    private Timer gameTimer;
    private static int bossHealth = 10;

    // Size of window and framerate
    private final int gameWidth = 800;
    private final int gameHeight = 800;
    private final int framesPerSecond = 120;

    Random random = new Random();

    // Store Highscores in file
    // Added new class for this Highscore
    File file = new File("/resources/highscore.dat");

    private final KeyboardControl control;
    private int score = 0;
    private int level = 1;
    private int numberOfLives = 3;
    private int hitmarkerX;
    private int hitmarkerY;
    private String user;

    // Objects
    private Ship playerShip;
    private Ship singleLife;
    private Ship bonusAlien;
    private Alien alien;
    private Shield shield;
    private PlayerWeapon playerWeapon;
    private AlienBomb alienBomb;
    private AlienBomb alienBomb2;
    private AlienBomb alienBomb3;
    private Highscore highScore = new Highscore();

    // Booleans to keep track of certain values
    private boolean playerCanFire = true;
    private boolean alienCanFire = true;
    private boolean newBonusAlien = true;
    private boolean hitMarker = false;

    // ArrayLists
    private final ArrayList<Ship> lifeList = new ArrayList<>();
    private ArrayList<Ship> bonusAlienList = new ArrayList<>();
    private final ArrayList<Alien> alienList = new ArrayList<>();
    private ArrayList<Shield> shieldList = new ArrayList<>();
    private ArrayList<AlienBomb> alienBombList = new ArrayList<>();

    private ImageIcon background = new ImageIcon("images/backgroundSkin.jpg");

    // Audio files
    SoundFactory sounds = new SoundFactory();

    // Extra methods

    // Alien boss
    public static int getHealthAlienBoss() {
        return bossHealth;
    }

    // Setting up the game
    public final void setupGame() {
        // TODO use modules as evaluation
        // Formula for normal levels
        if (level != 3 && level != 6 && level != 9 && level != 12) {
            // number or rows
            for (int row = 0; row < 6; row++) {
                // 5 columns
                for (int column = 0; column < 5; column++) {
                    // Enemy speed will increase each level
                    alien = new Alien((20 + (row * 100)), (20 + (column * 60)), level, 0, column, null, 40, 40);
                    alienList.add(alien);
                }
            }
        }

        // TODO use modules as evaluation
        // Formula for boss levels
        if (level == 3 || level == 6 || level == 9 || level == 12) {
            sounds.alienBoss();
            alien = new Alien(20, 20, 3, 0, 100, null, 150, 150);
            alienList.add(alien);
        }

        //Help for beginners in level 1
        if (level == 1) {
            JOptionPane.showMessageDialog(null, "Welcome to Space Intruders!\n" +
                    "\n" +
                    "RULES:\n" +
                    "\n" +
                    "- Use ← and → keys to move\n" +
                    "- Hit spacebar to shoot\n" +
                    "- Aliens will go faster with every level" +
                    "\n" +
                            "- Big Alien BOSS every 3 levels\n" +
                            "- A bonus enemy will appear randomly\n" +
                            "- Shoot it for extra points!\n" +
                            "- Press R to reset high score\n" +
                            "- PLAY WITH SOUND\n" +
                            "\n" +
                            "HAVE FUN!");

        }

        // Resets all controller movement
        control.resetController();

        // Sets the players ship values
        // TODO Make player positions relative to window size
        playerShip = new Ship(375, 730, null, control);

        // Set life counter
        for (int column = 0; column < numberOfLives; column++) {
            singleLife = new Ship(48 + (column * 20), 10, Color.WHITE, null);
            lifeList.add(singleLife);
        }

        // Set the values for 3 rows and 3 columns of shields
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                shield = new Shield(100 + (column * 250), 650 - (row * 10), 70, 10, Color.RED);
                shieldList.add(shield);
            }
        }
    }

    // Painting
    @Override
    public void paint(Graphics graphics) {
        // set background image
        background.paintIcon(null, graphics, 0, -150);

        //157 - Make string that says "+100" on enemy hit
        //TODO make smarter and dependent on alien
        if (playerWeapon != null) {
            if (hitMarker) {
                graphics.setColor(Color.WHITE);
                // TODO use modules as evaluation
                if (level != 3 && level != 6 && level != 9 && level != 12) {
                    graphics.drawString("+ 100", hitmarkerX + 20, hitmarkerY -= 1);
                } else {
                    graphics.drawString("- 1", hitmarkerX + 75, hitmarkerY += 1);
                }
            }
        }

        // Draw ship: player
        playerShip.draw(graphics);


        // Draw 3 evenly-spaced shields
        for (int index = 0; index < shieldList.size(); index++) {
            shieldList.get(index).draw(graphics);
        }

        // Draw the aliens from arraylist alienList different kinds of aliens
        // TODO expand to more aliens
        try {
            for (int index = 0; index < alienList.size(); index++) {
                alienList.get(index).draw(graphics);
            }
        } catch (IndexOutOfBoundsException exception) {

        }

        // Draw player projectile on space bar press
        if (control.getKeyStatus(32)) {
            if (playerCanFire) {
                playerWeapon = new PlayerWeapon(playerShip.getXCoordinateValue() + 22, playerShip.getYCoordinateValue() - 20, 0, Color.RED);
                sounds.projectile();
                playerCanFire = false;
            }
        }

        // Only draw player projectile after key press
        if (playerWeapon != null) {
            playerWeapon.draw(graphics);
        }

        // Make the aliens shoot randomly
        // TODO use modules as evaluation
        if (level != 3 && level != 6 && level != 9 && level != 12) {
            if (alienCanFire) {
                for (int index = 0; index < alienList.size(); index++) {
                    if (random.nextInt(30) == index) {
                        alienBomb = new AlienBomb(alienList.get(index).getXCoordinateValue(), alienList.get(index).getYCoordinateValue(), 0, Color.YELLOW);
                        alienBombList.add(alienBomb);
                        sounds.alienBomb();
                    }
                    alienCanFire = false;
                }
            }
        }

        // Generates beams at a faster rate for boss
        // TODO use modules as evaluation
        if (level == 3 || level == 6 || level == 9 || level == 12) {
            if (alienCanFire) {
                for (int index = 0; index < alienList.size(); index++) {
                    if (random.nextInt(5) == index) {
                        alienBomb = new AlienBomb(alienList.get(index).getXCoordinateValue() + 75, alienList.get(index).getYCoordinateValue() + 140, 0, Color.YELLOW);
                        alienBomb2 = new AlienBomb(alienList.get(index).getXCoordinateValue(), alienList.get(index).getYCoordinateValue() + 110, 0, Color.YELLOW);
                        alienBomb3 = new AlienBomb(alienList.get(index).getXCoordinateValue() + 150, alienList.get(index).getYCoordinateValue() + 110, 0, Color.YELLOW);
                        alienBombList.add(alienBomb);
                        alienBombList.add(alienBomb2);
                        alienBombList.add(alienBomb3);
                        //AudioPlayer.player.start(beamSoundAudio); // Plays beam sound for boss
                        sounds.alienBoss();
                    }
                    alienCanFire = false;
                }
            }
        }

        // Draw the alien bombs
        for (int index = 0; index < alienBombList.size(); index++) {
            alienBombList.get(index).draw(graphics);
        }

        // Generate random bonus enemy
        if (newBonusAlien) {
            if (random.nextInt(3000) == 1500) {
                bonusAlien = new Ship(-50, 30, Color.RED, null);
                bonusAlienList.add(bonusAlien);
                newBonusAlien = false;
            }
        }

        // Draw bonus alien
        for (int index = 0; index < bonusAlienList.size(); index++) {
            bonusAlienList.get(index).bonusDraw(graphics);
        }

        // Sets the score display
        graphics.setColor(Color.WHITE);
        graphics.drawString("Score: " + score, 260, 20);

        // Create score on top of screen
        graphics.setColor(Color.WHITE);
        graphics.drawString("Lives:", 11, 20);
        for (int index = 0; index < lifeList.size(); index++) {
            lifeList.get(index).lifeCounter(graphics);
        }

        // Create level display
        graphics.setColor(Color.WHITE);
        graphics.drawString("Level: " + level, 750, 20);

        // Verify if highscore is already present
        if (highScore.getPoints() == -1){
            // init highscore
            String oldHighscore = highScore.GetHighScore(file);
            if(oldHighscore.equals("0")){
                highScore.setHighscorevalue(0);
            } else {
                // Setting highscore from file to highscore
                user = oldHighscore.split(":")[0];
                highScore.setPoints(Integer.parseInt(oldHighscore.split(":")[1]));
            }
        }


        // Create highscore display
        graphics.setColor(Color.WHITE);
        graphics.drawString("Highscore: " + highScore.getPoints(), 440, 20);

        // Create display health of boss
        // TODO use modules as evaluation
        if (level == 3 || level == 6 || level == 9 || level == 12) {
            graphics.setColor(Color.WHITE);
            graphics.drawString("Boss Health: " + bossHealth, 352, 600);
        }

    }

    // Updating game
    public void updateGameState(int frameNumber) {

        // Allow player to move
        playerShip.move();

//        // Option to reset HighScore
//        if (control.getKeyStatus(82)) {
//            int response = JOptionPane.showConfirmDialog(null, "Would you like to reset the high score?", "To be or not to be ...", 0);
//            control.resetController();
//            if (response == 0) {
//                try {
//                    String highScoreString = Integer.toString(0);
//                    PrintWriter toFile = new PrintWriter(new FileOutputStream(file, false));
//                    toFile.write(highScoreString);
//                    toFile.close();
//                } catch (FileNotFoundException exception) {
//
//                }
//            }
//        }

        // When aliens reach the end of the board they need to change direction
        if ((alienList.get(alienList.size() - 1).getXCoordinateValue() + alienList.get(alienList.size() - 1).getVelocityX()) > 760 || (alienList.get(0).getXCoordinateValue() + alienList.get(0).getVelocityX()) < 0) {
            for (int index = 0; index < alienList.size(); index++) {
                alienList.get(index).setVelocityX(alienList.get(index).getVelocityX() * -1);
                alienList.get(index).setYCoordinateValue(alienList.get(index).getYCoordinateValue() + 10);
            }

        } else {
            for (int index = 0; index < alienList.size(); index++) {
                alienList.get(index).move();
            }
        }

        // Move the projectile when it is fired
        // TODO when difficulty level is chosen use different projectile speed
        if (playerWeapon != null) {
            playerWeapon.setYCoordinateValue(playerWeapon.getYCoordinateValue() - 30);
            // TODO based on difficulty level player should be able to fire 1 or 3 shots in burst
            if (playerWeapon.getYCoordinateValue() < 0) {
                playerCanFire = true;
            }

            // Check for collisions with regular aliens
            for (int index = 0; index < alienList.size(); index++) {
                if (playerWeapon.isColliding(alienList.get(index))) {
                    // TODO Play hit sound if enemy is hit
                    sounds.explosion();
                    playerWeapon = new PlayerWeapon(0, 0, 0, null);
                    playerCanFire = true;
                    highScore.setHighscorevalue(score);
                    // When alien is hit score needs to be updated
                    // TODO improve if statement if mod 3 → if % 3
                    if (level != 3 && level != 6 && level != 9 && level != 12) {
                        score += 100;
                        hitMarker = true;
                        // To present the marker "+100 Well done!" near the correct alien that was shot
                        // It is needed to retrieve those coordinates x and y
                        hitmarkerX = alienList.get(index).getXCoordinateValue();
                        hitmarkerY = alienList.get(index).getYCoordinateValue();
                        // After the marker has shown alien can be removed
                        alienList.remove(index);
                        highScore.setHighscorevalue(score);
                    }

                    //347 When alien bos is hit the hit score needs to be updated
                    // TODO improve if statement if mod 3 → if % 3
                    if (level == 3 || level == 6 || level == 9 || level == 12) {
                        hitMarker = true;
                        // Alien boss has more health so every time when the alien boss is hit -1 will appear
                        hitmarkerX = alienList.get(index).getXCoordinateValue();
                        hitmarkerY = alienList.get(index).getYCoordinateValue() + 165;
                        bossHealth -= 1;
                        // check if alien boss health 0 → alien boss is dead
                        if (bossHealth == 0) {
                            alienList.remove(index);
                            // Special bonus for defeating alien boss
                            score += 9000;
                            highScore.setHighscorevalue(score);
                        }
                    }
                }
            }

            // Verify if the shields gets damaged by player
            // If one of the shields get it the color changes
            // Looping through the available shields
            // TODO make smarter, duplicate code detected. Maybe Switch-statement?
            for (int index = 0; index < shieldList.size(); index++) {
                if (playerWeapon.isColliding(shieldList.get(index))) {
                    // %100 health
                    if (shieldList.get(index).getColor() == Color.RED) {
                        shieldList.get(index).setColor(Color.ORANGE);
                        // TODO play audio sound bunker get hit
                        playerWeapon = new PlayerWeapon(0, 0, 0, null);
                        playerCanFire = true;
                    } else if (shieldList.get(index).getColor() == Color.ORANGE) {
                        shieldList.get(index).setColor(Color.YELLOW);
                        // TODO play sound bunker gets hit
                        playerWeapon = new PlayerWeapon(0, 0, 0, null);
                        playerCanFire = true;
                    } else if (shieldList.get(index).getColor() == Color.YELLOW) {
                        shieldList.get(index).setColor(Color.WHITE);
                        // TODO play sound bunker gets hit
                        playerWeapon = new PlayerWeapon(0, 0, 0, null);
                        playerCanFire = true;
                    } else if (shieldList.get(index).getColor() == Color.WHITE) {
                        shieldList.remove(index);
                        // TODO play sound bunker gets hit
                        playerWeapon = new PlayerWeapon(0, 0, 0, null);
                        playerCanFire = true;
                    }
                }
            }
        }

        // Move bonus alien
        if (!bonusAlienList.isEmpty()) {
            for (int index = 0; index < bonusAlienList.size(); index++) {
                // Todo bonus alien speed dependent on difficulty level
                bonusAlienList.get(index).setXCoordinateValue(bonusAlienList.get(index).getXCoordinateValue() + (2));
                // TODO replace 800 for boardwidth variable
                if (bonusAlienList.get(index).getXCoordinateValue() > gameWidth) {
                    bonusAlienList.remove(index);
                    newBonusAlien = true;
                }
            }
            // Bonus alien collides with player weapon
            for (int index = 0; index < bonusAlienList.size(); index++) {
                if (playerWeapon != null) {
                    if (bonusAlienList.get(index).isColliding(playerWeapon)) {
                        bonusAlienList.remove(index);
                        playerWeapon = new PlayerWeapon(0, 0, 0, null);
                        playerCanFire = true;
                        newBonusAlien = true;
                        // TODO play sound bonus alien hit
                        // When bonus alien is hit extra points
                        score += 5000;
                        highScore.setHighscorevalue(score);
                    }
                }
            }
        }

        // Drop speed alien bombs
        // TODO improve if statement if mod 3 → if % 3
        if (level != 3 && level != 6 && level != 9 && level != 12) {
            if (alienBomb != null) {
                for (int index = 0; index < alienBombList.size(); index++) {
                    // TODO implement alien bomb drop speed variable
                    alienBombList.get(index).setYCoordinateValue(alienBombList.get(index).getYCoordinateValue() + 4);
                    // TODO implement board variable parameter
                    if (alienBombList.get(index).getYCoordinateValue() > gameHeight) {
                        alienBombList.remove(index);
                    }
                }
            }
        }

        // Alien boss was faster dropping bombs
        // TODO improve if statement if mod 3 → if % 3
        if (level == 3 || level == 6 || level == 9 || level == 12) {
            if (alienBomb != null) {
                for (int index = 0; index < alienBombList.size(); index++) {
                    // The higher the level the increased speed of the boss
                    alienBombList.get(index).setYCoordinateValue(alienBombList.get(index).getYCoordinateValue() + (2 * level));
                    if (alienBombList.get(index).getYCoordinateValue() > gameHeight) {
                        alienBombList.remove(index);
                    }
                }
            }
        }

        // Alien bomb collision with shields
        // TODO think of better solution
        try {
            for (int shieldListIndex = 0; shieldListIndex < shieldList.size(); shieldListIndex++) {
                for (int alienBombListIndex = 0; alienBombListIndex < alienBombList.size(); alienBombListIndex++) {
                    if (alienBombList.get(alienBombListIndex).isColliding(shieldList.get(shieldListIndex))) {
                        // Full strength
                        if (shieldList.get(shieldListIndex).getColor() == Color.RED) {
                            shieldList.get(shieldListIndex).setColor(Color.ORANGE);
                            // TODO play audio
                            alienBombList.remove(alienBombListIndex);
                        } else if (shieldList.get(shieldListIndex).getColor() == Color.ORANGE) {
                            shieldList.get(shieldListIndex).setColor(Color.YELLOW);
                            // TODO play audio
                            alienBombList.remove(alienBombListIndex);
                        } else if (shieldList.get(shieldListIndex).getColor() == Color.YELLOW) {
                            shieldList.get(shieldListIndex).setColor(Color.WHITE);
                            // TODO play audio
                            alienBombList.remove(alienBombListIndex);
                        } else if (shieldList.get(shieldListIndex).getColor() == Color.WHITE) {
                            shieldList.remove(shieldListIndex);
                            // TODO play audio
                            alienBombList.remove(alienBombListIndex);
                        }
                    }
                }
            }
        } catch (IndexOutOfBoundsException exception) {

        }

        // Check if alien bomb meets player
        for (int index = 0; index < alienBombList.size(); index++) {
            if (alienBombList.get(index).isColliding(playerShip)) {
                alienBombList.remove(index);
                // TODO play tun tun tun audio
                // Player has been hit, reduce amount of lives
                lifeList.remove(lifeList.size() - 1);
            }
        }

        // Prevent continues rain of alien bombs. When all bombs are off the screen reset trigger
        if (alienBombList.isEmpty()) {
            alienCanFire = true;
        }

        // When aliens collide with shields, the shields are removed
        for (int alienListIndex = 0; alienListIndex < alienList.size(); alienListIndex++) {
            for (int shieldListIndex = 0; shieldListIndex < shieldList.size(); shieldListIndex++) {
                if (alienList.get(alienListIndex).isColliding(shieldList.get(shieldListIndex))) {
                    shieldList.remove(shieldListIndex);
                }
            }

            // When the aliens reaches the outer limit the level will be reset and a life is being reduced
            // TODO Use boardwidth variable boardWith - alienSize
            if (alienList.get(alienListIndex).getYCoordinateValue() + 50 >= 750) {
                // resetting level
                // TODO depending on difficulty level same aliens appear or damaged shields won't
                alienList.clear();
                shieldList.clear();
                lifeList.clear();
                alienBombList.clear();
                bossHealth = 30;
                numberOfLives -= 1;
                // TODO play sound player looses
                setupGame();
                if(score <= highScore.getPoints()) {
                    String name = JOptionPane.showInputDialog("You have beaten: " + user + " You set a new highscore!. What is your name?", "User");
                    highScore.registerNewHighscore(file, name);
                }
            }
        }

        // Update life counter on display
        if (playerShip.isCollided) {
            int index = lifeList.size() - 1;
            lifeList.remove(index);
        } else if (lifeList.isEmpty()) {
            // Player is out of lives
            // TODO Play game over sound
            // Present option to play again or exit the game
            if(score <= highScore.getPoints()) {
                String name = JOptionPane.showInputDialog("You set a new highscore!. What is your name?", "User");
                highScore.registerNewHighscore(file, name);
            }
            int response = JOptionPane.showConfirmDialog(null, "Play again?", "GAME OVER!! " + "Your score: " + score + " points", 0);
            if (response == 0) {
                // If player chooses to play again all will be cleared and started over
                lifeList.clear();
                alienList.clear();
                shieldList.clear();
                alienBombList.clear();
                bonusAlienList.clear();
                score = 0;
                level = 1;
                bossHealth = 30;
                numberOfLives = 3;
                playerCanFire = true;
                alienCanFire = true;
                newBonusAlien = true;
                setupGame();
            }
            // If the player chooses the other option, exit the game
            if (response == 1) {
                System.exit(0);
            }
        }

        // When all aliens are killed, player goes to next level
        if (alienList.isEmpty()) {
            alienBombList.clear();
            shieldList.clear();
            bonusAlienList.clear();
            lifeList.clear();
            level += 1;
            bossHealth = 10 * level;
            setupGame();
            // TODO play victory song
        }

    }

    public GamePanel() {
        // Set size of board
        this.setSize(gameWidth, gameHeight);
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBackground(Color.BLACK);

        // Register KeyboardController as KeyListener
        control = new KeyboardControl();
        this.addKeyListener(control);

        // Call setupGame to initialize fields
        this.setupGame();
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    /*
     * Method to start the Timer that drives the animation for the game.
     */
    public void start() {
        // Set up a new Timer to repeat every 20 milliseconds (50 FPS)
        gameTimer = new Timer(1000 / framesPerSecond, new ActionListener() {

            // Tracks the number of frames that have been produced.
            // May be useful for limiting action rates
            private int frameNumber = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the game's state and repaint the screen
                updateGameState(frameNumber++);
                repaint();
            }
        });
        Timer gameTimerHitMarker = new Timer(1000, new ActionListener() {

            // Tracks the number of frames that have been produced.
            // May be useful for limiting action rates
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the game's state and repaint the screen
                hitMarker = false;
            }
        });

        gameTimer.setRepeats(true);
        gameTimer.start();
        gameTimerHitMarker.setRepeats(true);
        gameTimerHitMarker.start();
    }
}