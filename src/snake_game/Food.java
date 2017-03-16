package snake_game;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayDeque;


public class Food extends Item {

    private TasteBehavior tasteBehavior;
    private Image imgApple, imgAppleB;
    private boolean imageLoaded = false;

    public void loadImages(){
        try {
            imgApple = new Image("res/apple.png");
            imgAppleB = new Image("res/apple-b.png");
            imageLoaded = true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

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

    public void eat() throws InvalidSizeException {
        ArrayDeque<SnakeBody> snakeArray = Application.getApp().getSnakeArray();
        int eatValue = tasteBehavior.eat();
        if(eatValue > 0) {
            for(int i = 0; i < eatValue; i++) {
                SnakeBody grow = snakeArray.getLast();
                snakeArray.addLast(new SnakeBody(grow.getX(), grow.getY()));
            }
        }
        else if(eatValue < 0){
            if(eatValue < snakeArray.size())
                for(int i = 0; i < Math.abs(eatValue); i++) {
                    snakeArray.removeLast();
                }
            else
                throw new InvalidSizeException();
        }

        else{
            System.out.println("You ate a neutral apple");
        }
    }

    @Override
    public void drawItem(Graphics g) {
        if(!imageLoaded)
            loadImages();

        Image img = null;

        if (tasteBehavior instanceof TasteBad) {
            img = imgAppleB;
        }
        else
            img = imgApple;

        if(img != null) {
            img.draw(x_position, y_position, Application.ITEMSIZE, Application.ITEMSIZE);
        }
        else
            super.drawItem(g);
    }

    public int getValue() {
        return tasteBehavior.getValue();
    }
}
