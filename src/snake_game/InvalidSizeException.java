package snake_game;

/**
 * Created by john on 14/03/2017.
 */
public class InvalidSizeException extends Exception {
    public InvalidSizeException() {
        super();
        Application app = Application.getApp();
        MainMenu.setFirstTimeLaunched();
        Application.getMainMenu().setGameOverMessage("You ate a bad apple");
        app.enterState(Application.getMainMenu().getID());
    }
}
