package nl.StijveHark.Game;

import java.awt.*;

// A game object that will be drawn on the board.
// Therefore it needs to implement the interface Drawable
public abstract class GameObject implements Drawable{
    int xCoordinateValue;
    int yCoordinateValue;
    Color color;
    boolean isCollided; // isCollided or hasCollided? Which moment in time is used to check if 2 objects touch each other

    public GameObject(){};

    public GameObject(int xValue,int yValue, Color color){
        this.xCoordinateValue = xValue;
        this.yCoordinateValue = yValue;
        this.color = color;
    }

    // Now it is forced on Rectangle, maybe more abstract for future releases and use Shape?
    public abstract Rectangle getBounds();

    // Gets the value of X of the object
    public int getXCoordinateValue(){
        return xCoordinateValue;
    }

    // Gets the value of X of the object
    public int getYCoordinateValue(){
        return yCoordinateValue;
    }

    public Color getColor(){
        return color;
    }

    public void setXCoordinateValue(int xValue){
        this.xCoordinateValue = xValue;
    }

    public void setYCoordinateValue(int yValue){
        this.yCoordinateValue = yValue;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public boolean isColliding(GameObject otherGameObject){
        /*
            Determines whether or not this Rectangle and the specified Rectangle intersect. Two rectangles intersect if their intersection is nonempty.
            Params: r – the specified Rectangle
            Returns: true if the specified Rectangle and this Rectangle intersect; false otherwise.
        */
        isCollided = otherGameObject.getBounds().intersects(this.getBounds());
        return  isCollided;
    }
}
