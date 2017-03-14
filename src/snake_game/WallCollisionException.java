package snake_game;


public class WallCollisionException extends Exception {
    public WallCollisionException(){
        super();
        Application app = Application.getApp();
        app.enterState(app.getMainMenu().getID());
    }
}

