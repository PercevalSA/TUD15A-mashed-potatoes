package snake_game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;

public abstract class Item {
    protected Color color;
    protected float x_position;
    protected float y_position;

    public Item(float x, float y){
        this.x_position = x;
        this.y_position = y;
    }

    public float getX(){
        return x_position;
    }

    public float getY(){
        return y_position;
    }

    public void setX(float x_position){
        this.x_position = x_position;
    }

    public void setY(float y_position){
        this.y_position = y_position;
    }


    public Color getCol(){
        return color;
    }
}
