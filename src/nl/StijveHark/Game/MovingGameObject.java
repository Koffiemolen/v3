package nl.StijveHark.Game;

import java.awt.*;

public abstract class MovingGameObject extends GameObject implements Moveable{

    int velocityX;
    int velocityY;

    // Aliens cannot be controlled


    public MovingGameObject(int xValue, int yValue, int velocityX, int velocityY, Color color) {
        super(xValue, yValue, color);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public int getVelocityX(){
        return velocityX;
    }

    public int getVelocityY(){
        return velocityY;
    }

    public void setVelocityX(int velocityX){
        this.velocityX = velocityX;
    }

    public void setVelocityY(int velocityY){
        this.velocityY = velocityY;
    }

    // To move non controllable objects
    public void move(){
        this.velocityX += velocityX;
        this.velocityY += velocityY;
    }
}
