package snake_game;
    import org.newdawn.slick.GameContainer;
    import org.newdawn.slick.Input;
    import java.util.ArrayDeque;

/**
 * main game controller
 * Manage input keys to the game and snake direction
 */

public class GameController {

    /*
     * Directions (clockwise order):
     * Up : 0
     * Right : 1
     * Down : 2
     * Left : 3
     */

    public void updateBodyPosition(GameContainer gc) {
        //Get the input from keyboard
        whichDirection(gc);

        //Get the body of the snake
        ArrayDeque<SnakeBody> snakeArray = Application.getApp().getSnakeArray();
        SnakeBody last = snakeArray.getLast();
        snakeArray.removeLast();
        SnakeHead snakeHead = Application.getApp().getSnakeHead();
        last.updateBody(snakeHead.getX() - 20,snakeHead.getY());
        snakeArray.addFirst(last);
        Application.getApp().setSnakeArray(snakeArray);
        snakeHead.updateCoord(1);
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
