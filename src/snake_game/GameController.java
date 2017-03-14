package snake_game;
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

    /*
     * Directions (clockwise order):
     * Up : 0
     * Right : 1
     * Down : 2
     * Left : 3
     */

    public void updateBodyPosition(GameContainer gc) throws WallCollisionException, BodyCollisionException, InvalidSizeException {
        //Get the snakeHead for updates
        SnakeHead snakeHead = Application.getApp().getSnakeHead();
        whichDirection(gc);

        if( ++fps % delay == 0) {
            fps=0;

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

                Application.getApp().getFood().eat();
                
                Random rand = new Random();
                float x_position = rand.nextFloat()*(Application.WIDTH- 2 * Application.ITEMSIZE) + Application.ITEMSIZE;
                float y_position = rand.nextFloat()*(Application.GAMEHEIGHT- 2 * Application.ITEMSIZE) + Application.ITEMSIZE;
                Application.getApp().getFood().setX(x_position);
                Application.getApp().getFood().setY(y_position);
            }

            Food food = checkBadFoodCollision();
            if (food != null) {

                food.eat();

                try{
                    ArrayList<Food> foodArray = Application.getApp().getFoodArray();
                    foodArray.remove(food);
                    Application.getApp().setFoodArray(foodArray);
                }catch (Exception e){
                    System.out.println("Error while trying to remove a bad apple from the list");
                }

            }
        }
    }

    private boolean checkFoodCollision() {
        Food food = Application.getApp().getFood();
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

    //TODO: Shift to Factory
    public void createFoodItem(){
        Random rand = new Random();
        float x_position = rand.nextFloat()*(Application.WIDTH- 2 * Application.ITEMSIZE) + Application.ITEMSIZE;
        float y_position = rand.nextFloat()*(Application.GAMEHEIGHT- 2 * Application.ITEMSIZE) + Application.ITEMSIZE;

        //Create one Good apple and a list of bad apples
        ArrayList<Food> foods = new ArrayList<>();
        Food food = new Food(x_position, y_position, true);
        Application.getApp().setFood(food);

        x_position = rand.nextFloat()*(Application.WIDTH- 2 * Application.ITEMSIZE) + Application.ITEMSIZE;
        y_position = rand.nextFloat()*(Application.GAMEHEIGHT- 2 * Application.ITEMSIZE) + Application.ITEMSIZE;
        foods.add(new Food(x_position, y_position, false));

        x_position = rand.nextFloat()*(Application.WIDTH- 2 * Application.ITEMSIZE) + Application.ITEMSIZE;
        y_position = rand.nextFloat()*(Application.GAMEHEIGHT- 2 * Application.ITEMSIZE) + Application.ITEMSIZE;
        foods.add(new Food(x_position, y_position, false));
        Application.getApp().setFoodArray(foods);


    }

    private Food checkBadFoodCollision() {
        ArrayList<Food> foodArray = Application.getApp().getFoodArray();
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
