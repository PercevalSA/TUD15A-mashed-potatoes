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
            //Get the body of the snake
            ArrayDeque<SnakeBody> snakeArray = Application.getApp().getSnakeArray();
            SnakeBody last = snakeArray.getLast();
            snakeArray.removeLast();
            last.updateBody(snakeHead.getX(), snakeHead.getY());
            snakeArray.addFirst(last);
            Application.getApp().setSnakeArray(snakeArray);
            snakeHead.updateCoord(10);
        }
    }

    public void createFoodItem(){
        Random rand = new Random();
         float x_position = rand.nextInt(Application.getWIDTH()- 2 * Application.getITEMSIZE()) + Application.getITEMSIZE();
         float y_position = rand.nextInt(Application.getGAMEHEIGHT()- 2 * Application.getITEMSIZE()) + Application.getITEMSIZE();
         Application.getApp().setFood(new Food(x_position, y_position, 5));

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
