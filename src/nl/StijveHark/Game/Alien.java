package nl.StijveHark.Game;

import javax.swing.*;
import java.awt.*;

public class Alien extends MovingGameObject{

    ImageIcon alien1 = new ImageIcon("images/alien1Skin.gif");
    ImageIcon alien2 = new ImageIcon("images/alien2Skin.gif");
    ImageIcon alien3 = new ImageIcon("images/alien3Skin.gif");
    ImageIcon alienBoss = new ImageIcon("images/boss1.gif");
    ImageIcon alienBoss2 = new ImageIcon("images/boss2.gif");
    ImageIcon alienBoss3 = new ImageIcon("images/boss3.gif");

    private int alienType;
    private int alienWidth;
    private int alienHeight;

    public Alien(int xValue, int yValue, int velocityX, int velocityY, int alienType, Color color, int alienWidth, int alienHeight) {
        super(xValue, yValue, velocityX, velocityY, color);
        this.alienType = alienType;
        this.alienWidth = alienWidth;
        this.alienHeight = alienHeight;
    }

    public void draw(Graphics g) {
        // Varient 1
        if (this.alienType % 3 == 0) {
            alien1.paintIcon(null, g, this.getXCoordinateValue(), this.getYCoordinateValue());
            // Varient 2
        } else if (this.alienType % 3 == 1 && this.alienType != 100) {
            alien2.paintIcon(null, g, this.getXCoordinateValue(), this.getYCoordinateValue());
            // Varient 3
        } else if (this.alienType % 3 == 2) {
            alien3.paintIcon(null, g, this.getXCoordinateValue(), this.getYCoordinateValue());
            // Boss Enemy
        } if (this.alienType == 100)
        {
            if(GamePanel.getHealthAlienBoss()>20){
                alienBoss.paintIcon(null, g, this.getXCoordinateValue(), this.getYCoordinateValue());
            }
            else if(GamePanel.getHealthAlienBoss()>10){
                alienBoss2.paintIcon(null, g, this.getXCoordinateValue(), this.getYCoordinateValue());
            }
            else if(GamePanel.getHealthAlienBoss()>0){
                alienBoss3.paintIcon(null, g, this.getXCoordinateValue(), this.getYCoordinateValue());
            }
        }
    }

    @Override
    public Rectangle getBounds(){
        Rectangle alienHitRectangle = new Rectangle(this.getXCoordinateValue(), this.getYCoordinateValue(), alienWidth, alienHeight);
        return  alienHitRectangle;
    }

    @Override
    public void move(){
        xCoordinateValue += velocityX;
    }
}

