package snake_game;
    import org.jcp.xml.dsig.internal.dom.Utils;
    import org.newdawn.slick.GameContainer;
    import org.newdawn.slick.Input;
    import java.util.ArrayDeque;
    import java.util.ArrayList;
    import java.util.Random;

/**
 * main game controller
 * Manage input keys to the game and snake direction
 */

public class GameController {

    public int fps = 0;
    private int direction = 1;
    private int delay = 5;

    private static GameController instance = null;

    private GameController() {}

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

    public void updateBodyPosition(GameContainer gc,int sleepTime) throws WallCollisionException, BodyCollisionException, InvalidSizeException {

        //Get the snakeHead for updates
        SnakeHead snakeHead = Application.getApp().getSnakeHead();
        whichDirection(gc);

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            //Get the input from keyboard
            float temp_x = snakeHead.getX();
            float temp_y = snakeHead.getY();

            snakeHead.updateCoord(20, direction);

            //Get the body of the snake
            ArrayDeque<SnakeBody> snakeArray = Application.getApp().getSnakeArray();
            if(snakeArray.size() != 0) {
                SnakeBody last = snakeArray.getLast();
                snakeArray.removeLast();
                last.updateBody(temp_x, temp_y);
                snakeArray.addFirst(last);
                Application.getApp().setSnakeArray(snakeArray);
            }
            else
                throw new InvalidSizeException();

            if(checkFoodCollision()){
                FoodManager.getInstance().getGoodApple().eat();
                FoodManager.getInstance().mooveGoodApple();
            }


            Food food = checkBadFoodCollision();
            if (food != null) {

                food.eat();

                try{
                    ArrayList<Food> foodArray = FoodManager.getInstance().getApples();
                    foodArray.remove(food);
                    FoodManager.getInstance().setApples(foodArray);
                }catch (Exception e){
                    System.out.println("Error while trying to remove a bad apple from the list");
                }

            }
        }

    private boolean checkFoodCollision() {
        Food food = FoodManager.getInstance().getGoodApple();
        if(food != null) {
            SnakeHead snakeHead = Application.getApp().getSnakeHead();
            float x_snake = snakeHead.x_position + Application.ITEMSIZE / 2;
            float y_snake = snakeHead.y_position + Application.ITEMSIZE / 2;

            float x_food = food.x_position;
            float y_food = food.y_position;

            if ((x_snake >= x_food - (Application.ITEMSIZE / 2))
                    && (x_snake <= x_food + 1.5 * Application.ITEMSIZE)
                    && (y_snake >= y_food - 0.5 * Application.ITEMSIZE)
                    && (y_snake <= y_food + 1.5 * Application.ITEMSIZE)
                    ) {
                System.out.println("You ate the FOOOOOOOOD");
                return true;
            }
        }
        return false;
    }


    private Food checkBadFoodCollision() {
        ArrayList<Food> foodArray = FoodManager.getInstance().getApples();
        if(foodArray != null) {
            SnakeHead snakeHead = Application.getApp().getSnakeHead();
            float x_snake = snakeHead.x_position + Application.ITEMSIZE / 2;
            float y_snake = snakeHead.y_position + Application.ITEMSIZE / 2;

            for(Food food : foodArray){
                float x_food = food.x_position;
                float y_food = food.y_position;

                if ((x_snake >= x_food - (Application.ITEMSIZE / 2))
                        && (x_snake <= x_food + 1.5 * Application.ITEMSIZE)
                        && (y_snake >= y_food - 0.5 * Application.ITEMSIZE)
                        && (y_snake <= y_food + 1.5 * Application.ITEMSIZE)
                        ) {
                    System.out.println("You ate the FOOOOOOOOD");
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
}
