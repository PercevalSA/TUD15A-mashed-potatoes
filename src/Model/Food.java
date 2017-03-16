package Model;


import Exceptions.InvalidSizeException;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.ArrayDeque;
import java.util.Random;


public class Food extends Item {

    private TasteBehavior tasteBehavior;
    private Image imgApple, imgAppleB, imgAppleG, imgAppleP, imgAppleY;
    private boolean imageLoaded = false;

    public void loadImages(){
        try {
            imgApple = new Image("res/apple.png");
            imgAppleB = new Image("res/apple-b.png");
            imgAppleP = new Image("res/apple-p.png");
            imgAppleY = new Image("res/apple-y.png");
            imgAppleG = new Image("res/apple-g.png");
            imageLoaded = true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Food(float x, float y, boolean isGood){
        super(x,y, null);
        if(isGood){
            tasteBehavior = new TasteGood();
        }
        else{
            Random rand = new Random();
            int type = rand.nextInt(4) + 1;
            switch (type){
                case 1:
                    tasteBehavior = new TasteGreen();
                    break;
                case 2:
                    tasteBehavior = new TasteBlue();
                    break;
                case 3:
                    tasteBehavior = new TasteYellow();
                    break;
                case 4:
                    tasteBehavior = new TastePurple();
                    break;
            }
        }
    }

    public void eat() throws InvalidSizeException {
        ArrayDeque<SnakeBody> snakeArray = SnakeManager.getInstance().getSnakeArray();
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

        switch(tasteBehavior.getType()){
            case 1:
                img = imgAppleG;
                break;
            case 2:
                img = imgAppleB;
                break;
            case 3:
                img = imgAppleY;
                break;
            case 4:
                img = imgAppleP;
                break;
            case 5:
                img = imgApple;
                break;

        }

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
