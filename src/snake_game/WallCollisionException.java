package snake_game;


public class WallCollisionException extends Exception {
    public WallCollisionException(){
        super();
        Application app = Application.getApp();
        app.getMainMenu().setFirstTimeLaunched();
        app.getMainMenu().setGameOverMessage("You hit a wall !");
        app.enterState(app.getMainMenu().getID());
    }
}

