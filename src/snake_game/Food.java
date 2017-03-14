package snake_game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Food extends Item {

    private int value = 0;

    public Food(float x, float y,int value){
        super(x,y);
        this.value = 2*value;
    }

    public int getValue() {
        return value;
    }
    public void drawItem(Graphics g, float x, float y){
        g.setColor(Color.red);
        Rectangle shape = new Rectangle(x,y,Application.getITEMSIZE(),Application.getITEMSIZE());
        g.draw(shape);
        g.fill(shape);
    }

}
