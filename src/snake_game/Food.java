package snake_game;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayDeque;


public class Food extends Item {

    private TasteBehavior tasteBehavior;

    public Food(float x, float y, boolean isGood){
        super(x,y);
        if(isGood){
            tasteBehavior = new TasteGood();
        }
        else{
            tasteBehavior = new TasteBad();
        }
    }

    public Food createGoodFood(float x, float y){
        return new Food(x, y, true);
    }

    public Food createBadFood(float x, float y){
        return new Food(x, y, false);
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
    public void drawItem(Graphics g, float x, float y){
        g.setColor(Color.red);
        Rectangle shape = new Rectangle(x,y,Application.ITEMSIZE,Application.ITEMSIZE);
        g.draw(shape);
        g.fill(shape);
    }

}
