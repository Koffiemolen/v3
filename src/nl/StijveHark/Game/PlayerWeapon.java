package nl.StijveHark.Game;

import java.awt.*;

public class PlayerWeapon extends MovingGameObject {

    // In future release player can get different weapons
    int projectileDiameter;
    int velocityY;

    // Constructor for projectile

    public PlayerWeapon(int xValue, int yValue, int projectileDiameter, Color color) {
        super(xValue, yValue, 0, 0, color);
        this.projectileDiameter = projectileDiameter;
    }

    // Get the diameter of the projectile
    public int getProjectileDiameter(){
        return projectileDiameter;
    }

    // Draw the projectile
    public void draw(Graphics graphics){
        graphics.setColor(color);
        graphics.fillRect(this.getXCoordinateValue(), this.getYCoordinateValue(),7, 15);
    }

    @Override
    public Rectangle getBounds(){
        Rectangle projectileHitRectangle = new Rectangle(xCoordinateValue, yCoordinateValue, 7, 15);
        return projectileHitRectangle;
    }
}
