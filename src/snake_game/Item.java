package snake_game;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public abstract class Item {
    protected Color color;
    protected float x_position;
    protected float y_position;

    public Item(float x, float y, Color color){
        this.x_position = x;
        this.y_position = y;
        this.color = color;
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

    public void drawItem(Graphics g){
        g.setColor(color);
        Rectangle shape = new Rectangle(x_position,y_position,Application.ITEMSIZE,Application.ITEMSIZE);
        g.draw(shape);
        g.fill(shape);
    }
}
