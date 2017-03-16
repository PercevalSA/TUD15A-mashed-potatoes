package snake_game;

/**
 * Created by john on 14/03/2017.
 */
public class InvalidSizeException extends Exception {
    public InvalidSizeException() {
        super();
        System.out.println("You ate a bad apple");
        Application.getApp().getGameOver().setGameOverMessage("You ate a bad apple");
        Application.getApp().enterState(Application.getApp().getGameOver().getID());
    }
}
