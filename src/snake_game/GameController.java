package snake_game;
    import org.newdawn.slick.GameContainer;
    import org.newdawn.slick.Input;
    import org.newdawn.slick.SlickException;

    import java.util.ArrayDeque;
    import java.util.ArrayList;
    import java.util.Deque;

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

    //private ArrayList<SnakeBody> snakeArray= new ArrayList<>();
    private ArrayDeque<SnakeBody> snakeArray= new ArrayDeque<>();

    public void updateBodyPosition (){
        SnakeBody last = snakeArray.getLast();
        snakeArray.removeLast();
        SnakeHead snakeHead = Application.getApp().getSnakeHead();
        last.updateBody(snakeHead.getX(),snakeHead.getY());
        snakeArray.addFirst(last);
    }

    protected void wichDirection(GameContainer gc) {

        Input input = gc.getInput();

        if(input.isKeyDown(Input.KEY_UP)) {
            try {
                Application.getApp().getSnakeHead().updateDirection(0);
            }catch (InvalidMoveException e) {
                e.printStackTrace();
            }
        }

        if(input.isKeyDown(Input.KEY_DOWN)) {
            try {
                Application.getApp().getSnakeHead().updateDirection(2);
            } catch (InvalidMoveException e) {
                e.printStackTrace();
            }
        }

        if(input.isKeyDown(Input.KEY_RIGHT)) {
            try {
                Application.getApp().getSnakeHead().updateDirection(1);
            } catch (InvalidMoveException e) {
                e.printStackTrace();
            }
        }

        if(input.isKeyDown(Input.KEY_LEFT)) {
            try {
                Application.getApp().getSnakeHead().updateDirection(3);
            } catch (InvalidMoveException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void update(){
        SnakeHead snakeHead = Application.getApp().getSnakeHead();

    }
}
