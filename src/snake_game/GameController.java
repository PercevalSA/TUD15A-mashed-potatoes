package snake_game;
    import org.newdawn.slick.GameContainer;
    import org.newdawn.slick.Input;
    import java.util.ArrayDeque;
    import java.util.Random;

/**
 * main game controller
 * Manage input keys to the game and snake direction
 */

public class GameController {
    public int fps = 0;
    /*
     * Directions (clockwise order):
     * Up : 0
     * Right : 1
     * Down : 2
     * Left : 3
     */


    public void updateBodyPosition(GameContainer gc) throws WallCollisionException {

        //Get the input from keyboard
        whichDirection(gc);

        //Get the snakeHead for updates
        SnakeHead snakeHead = Application.getApp().getSnakeHead();
        if( ++fps % 10 == 0) {
            fps=0;
            //Update the body of the snake
            ArrayDeque<SnakeBody> snakeArray = Application.getApp().getSnakeArray();
            SnakeBody last = snakeArray.getLast();
            snakeArray.removeLast();
            last.updateBody(snakeHead.getX(), snakeHead.getY());
            snakeArray.addFirst(last);
            Application.getApp().setSnakeArray(snakeArray);

            //Update position of the head
            snakeHead.updateCoord(10);

            checkFoodCollision();
        }
    }

    public void createFoodItem(){
        Random rand = new Random();
         float x_position = rand.nextInt(Application.getWIDTH()- 2 * Application.getITEMSIZE()) + Application.getITEMSIZE();
         float y_position = rand.nextInt(Application.getGAMEHEIGHT()- 2 * Application.getITEMSIZE()) + Application.getITEMSIZE();
         Application.getApp().setFood(new Food(x_position, y_position, 5));

    }

    private boolean checkFoodCollision() {
        Food foo = Application.getApp().getFood();
        if(foo != null) {
            SnakeHead snakeHead = Application.getApp().getSnakeHead();
            float x_snake = snakeHead.x_position + Application.getITEMSIZE() / 2;
            float y_snake = snakeHead.y_position + Application.getITEMSIZE() / 2;
            float x_food = foo.x_position;
            float y_food = foo.y_position;

            if ((x_snake >= x_food - (Application.getITEMSIZE() / 2))
                    && (x_snake <= x_food + 1.5 * Application.getITEMSIZE())
                    && (y_snake <= y_food - 0.5 * Application.getITEMSIZE())
                    && (y_snake <= y_food + 1.5 * Application.getITEMSIZE())
                    ) {
                System.out.println("You ate the FOOOOOOOOD");
                return true;
            }
        }
        return false;
    }

    protected void whichDirection(GameContainer gc) {

        Input input = gc.getInput();

        if(input.isKeyDown(Input.KEY_UP)) {
            try {
                Application.getApp().getSnakeHead().updateDirection(0);
            }catch (InvalidMoveException e) {}
        }

        if(input.isKeyDown(Input.KEY_DOWN)) {
            try {
                Application.getApp().getSnakeHead().updateDirection(2);
            } catch (InvalidMoveException e) {}
        }

        if(input.isKeyDown(Input.KEY_RIGHT)) {
            try {
                Application.getApp().getSnakeHead().updateDirection(1);
            } catch (InvalidMoveException e) {}
        }

        if(input.isKeyDown(Input.KEY_LEFT)) {
            try {
                Application.getApp().getSnakeHead().updateDirection(3);
            } catch (InvalidMoveException e) {}
        }
    }
    
    public void update(){
        SnakeHead snakeHead = Application.getApp().getSnakeHead();

    }
}
