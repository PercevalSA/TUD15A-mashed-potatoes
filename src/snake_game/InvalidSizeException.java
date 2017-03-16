package snake_game;

public class InvalidSizeException extends Exception {
    public InvalidSizeException() {
        super();
        System.out.println("You ate a bad apple");
        Application.getApp().getGameOver().setGameOverMessage("You ate a bad apple");
        Application.getApp().enterState(Application.getApp().getGameOver().getID());
    }
}
