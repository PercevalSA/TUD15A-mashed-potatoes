package snake_game;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayDeque;


public class Food extends Item {

    private TasteBehavior tasteBehavior;

    public Food(float x, float y, boolean isGood){
        super(x,y, null);
        if(isGood){
            tasteBehavior = new TasteGood();
            color = Color.green;
        }
        else{
            color = Color.red;
            tasteBehavior = new TasteBad();
        }
    }

    public void eat() {
        ArrayDeque<SnakeBody> snakeArray = Application.getApp().getSnakeArray();
        if(tasteBehavior.eat() == 1) {
            SnakeBody grow = snakeArray.getLast();
            snakeArray.addLast(new SnakeBody(grow.getX(), grow.getY()));
        }
        else{
            snakeArray.removeLast();
        }
    }

    @Override
    public void drawItem(Graphics g) {
        Image img = null;
        try {
            if (tasteBehavior instanceof TasteBad) {
                img = new Image("res/apple-b.png");
            }
            else
                img = new Image("res/apple.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        if(img != null) {
            img.draw(x_position, y_position, Application.ITEMSIZE, Application.ITEMSIZE);
        }
        else
            super.drawItem(g);
    }
}
