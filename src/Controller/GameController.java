package Controller;

    import Exceptions.BodyCollisionException;
    import Exceptions.InvalidSizeException;
    import Exceptions.WallCollisionException;
    import Model.*;
    import Viewer.GameViewer;
    import org.newdawn.slick.GameContainer;
    import org.newdawn.slick.Input;
    import java.util.ArrayDeque;
    import java.util.ArrayList;

/**
 * main game controller
 * Manage input keys to the game and snake direction
 */

public class GameController {

    public int fps = 0;
    private int direction = 1;

    private static GameController instance = null;
    private static Score score = null;


    private GameController() {
        score = new Score();
    }

    public static GameController getInstance() {
        if(instance == null) {
            instance = new GameController();
        }
        return instance;
    }

/*
 * Directions (clockwise order):
 * Up : 0
 * Right : 1
 * Down : 2
 * Left : 3
 */

    public void updateBodyPosition(GameContainer gc,int sleepFrameRate) throws WallCollisionException, BodyCollisionException, InvalidSizeException {

        //Get the snakeHead for updates
        SnakeHead snakeHead = SnakeManager.getInstance().getSnakeHead();
        whichDirection(gc);

        if (++fps % sleepFrameRate == 0) {
            fps = 0;

            //Get the input from keyboard
            float temp_x = snakeHead.getX();
            float temp_y = snakeHead.getY();

            snakeHead.updateCoord(20, direction);

            //Get the body of the snake
            ArrayDeque<SnakeBody> snakeArray = SnakeManager.getInstance().getSnakeArray();
            if (snakeArray.size() != 0) {
                SnakeBody last = snakeArray.getLast();
                snakeArray.removeLast();
                last.updateBody(temp_x, temp_y);
                snakeArray.addFirst(last);
                SnakeManager.getInstance().setSnakeArray(snakeArray);
            } else
                throw new InvalidSizeException();

            if (checkFoodCollision()) {
                FoodManager.getInstance().getGoodApple().eat();
                FoodManager.getInstance().mooveGoodApple();
            }


            Food food = checkBadFoodCollision();
            if (food != null) {

                food.eat();

                try {
                    ArrayList<Food> foodArray = FoodManager.getInstance().getApples();
                    foodArray.remove(food);
                    FoodManager.getInstance().setApples(foodArray);
                } catch (Exception e) {
                    System.out.println("Error while trying to remove a bad apple from the list");
                }

            }
        }
    }

    private boolean checkFoodCollision() {
        Food food = FoodManager.getInstance().getGoodApple();
        if(food != null) {
            SnakeHead snakeHead = SnakeManager.getInstance().getSnakeHead();

            if (snakeHead.getX() == food.getX() && snakeHead.getY() == food.getY()){
                setScore(food);
                System.out.println("You ate the FOOOOOOOOD");
                return true;
            }
        }
        return false;
    }


    private Food checkBadFoodCollision() {
        ArrayList<Food> foodArray = FoodManager.getInstance().getApples();
        if(foodArray != null) {
            SnakeHead snakeHead = SnakeManager.getInstance().getSnakeHead();
            for(Food food : foodArray){
                if (snakeHead.getX() == food.getX() && snakeHead.getY() == food.getY()){
                    setScore(food);
                    System.out.println("You ate the BAAAAAAAD FOOOOOOD");
                    return food;
                }
            }
        }
        return null;
    }

    protected void whichDirection(GameContainer gc) {

        Input input = gc.getInput();

        if(input.isKeyDown(Input.KEY_UP)) {
            direction = 0;
        }

        if(input.isKeyDown(Input.KEY_DOWN)) {
            direction = 2;
        }

        if(input.isKeyDown(Input.KEY_RIGHT)) {
            direction = 1;
        }

        if(input.isKeyDown(Input.KEY_LEFT)) {
            direction = 3;
        }
    }

    public static int getScore() {
        return score.getScore();
    }

    public static void setScore(Object obj) {
        score.update(obj);
    }

    public static void resetScore() {
        try {
            score.reset();
        } catch (Exception e){
            score = new Score();
        }
    }
}
