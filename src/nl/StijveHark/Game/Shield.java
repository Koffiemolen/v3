package nl.StijveHark.Game;

import java.awt.*;

public class Shield extends GameObject{

    private int shieldWidth;
    private int shieldHeight;

    public Shield(int xValue, int yValue, int shieldWidth, int shieldHeight, Color color) {
        super(xValue, yValue, color);
        this.shieldWidth = shieldWidth;
        this.shieldHeight = shieldHeight;
    }

    public  int getShieldWidth(){
        return shieldWidth;
    }

    public int getShieldHeight(){
        return shieldHeight;
    }

    public void setShieldWidth(int shieldWidth){
        this.shieldWidth = shieldWidth;
    }

    public void setShieldHeight(int shieldHeight){
        this.shieldHeight = shieldHeight;
    }

    @Override
    public void draw(Graphics graphics){
        graphics.setColor(color);
        graphics.fillRect(this.getXCoordinateValue(), this.getYCoordinateValue(), 90, 10);
    }

    @Override
    public Rectangle getBounds(){
        Rectangle shieldHitRectangle = new Rectangle(this.getXCoordinateValue(), this.getYCoordinateValue(), 90, 10);
        return shieldHitRectangle;
    }
}
