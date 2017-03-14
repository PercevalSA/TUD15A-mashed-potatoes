package snake_game;

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
}
