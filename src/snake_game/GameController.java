package snake_game;
    import org.newdawn.slick.GameContainer;
    import org.newdawn.slick.Input;
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

    protected void wichDirection(GameContainer gc) {

        Input input = gc.getInput();

        if(input.isKeyDown(Input.KEY_UP)) {
            try {
                getSnake().updateDirection(0);
            } catch(Exception e) {
                System.out.println("Invalid Direction");
            }
        }

        if(input.isKeyDown(Input.KEY_DOWN)) {
            try {
                getSnake().updateDirection(2);
            } catch(Exception e) {
                System.out.println("Invalid Direction");
            }
        }

        if(input.isKeyDown(Input.KEY_RIGHT)) {
            try {
                getSnake().updateDirection(1);
            } catch(Exception e) {
                System.out.println("Invalid Direction");
            }
        }

        if(input.isKeyDown(Input.KEY_LEFT)) {
            try {
                getSnake().updateDirection(3);
            } catch(Exception e) {
                System.out.println("Invalid Direction");
            }
        }
    }
}
