package snake_game;

public class BodyCollisionException extends Exception {
    public BodyCollisionException() {
        super();
        Application app = Application.getApp();
        app.enterState(app.getMainMenu().getID());
    }
}