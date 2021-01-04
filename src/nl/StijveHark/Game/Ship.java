package nl.StijveHark.Game;

import devices.KeyboardControl;

import javax.swing.*;
import java.awt.*;

public class Ship extends ControlledGameObject{

    private final ImageIcon shipImage = new ImageIcon("images/shipSkin.gif");
    private final ImageIcon bonusAlien = new ImageIcon("images/bonusEnemySkin.gif");
    private final ImageIcon lifeCounter = new ImageIcon("images/shipSkinSmall.gif");

    // Constructor for all ship objects
    public Ship(int xValue, int yValue, Color color, KeyboardControl control) {
        super(xValue, yValue, color, control);
    }

    // Bonus enemy ship
    public void bonusDraw(Graphics graphics){
        bonusAlien.paintIcon(null, graphics, this.getXCoordinateValue(), getYCoordinateValue());
    }

    // Draw ships for lice counter
    public void lifeCounter(Graphics graphics){
        lifeCounter.paintIcon(null, graphics, this.getXCoordinateValue(), this.yCoordinateValue);
    }

    // Player controlled ship
    @Override
    public void draw(Graphics graphics) {
        shipImage.paintIcon(null, graphics, this.getXCoordinateValue(), this.getYCoordinateValue());
    }

    // Gets the hitbox for all the ship objectes
    @Override
    public Rectangle getBounds(){
        Rectangle shipHitRectangle = new Rectangle(this.getXCoordinateValue(), this.getYCoordinateValue(), 50, 50);
        return shipHitRectangle;
    }

    // move ship objects
    @Override
    public void move() {
        if(control.getKeyStatus(37)){
            xCoordinateValue -= 10;
        }
        if(control.getKeyStatus(39)){
            xCoordinateValue += 10;
        }

        // Edge to edge without stopping
        if(xCoordinateValue > 800){
            xCoordinateValue = -50;
        }
        if(xCoordinateValue < -50){
            xCoordinateValue = 800;
        }
    }
}
