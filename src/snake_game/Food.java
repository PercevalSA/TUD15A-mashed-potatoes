package snake_game;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayDeque;
import java.util.Random;


public class Food extends Item {

    private TasteBehavior tasteBehavior;
    private float random_num;

    public Food(float x, float y, boolean isGood){
        super(x,y, null);
        if(isGood){
            tasteBehavior = new TasteGood();
            color = Color.green;
        }
        else{
            color = Color.red;
            Random rand = new Random();
            random_num = rand.nextFloat();
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

    public void drawItem(Graphics g){


        if (tasteBehavior.eat() == -1){
            Image img = null;

            try {
                if (random_num>0.66f) {
                    img = new Image("res/GeertWilders2.jpeg");
                }else if(random_num<0.33f){
                    img = new Image("res/marinelepen.jpg");
                }else{
                    img = new Image("res/FraukePetry.jpg");
                }
            } catch (SlickException e) {
                e.printStackTrace();
            }
            img.draw(x_position, y_position,Application.ITEMSIZE,Application.ITEMSIZE);
        }else{
            super.drawItem(g);
        }



    }

}
