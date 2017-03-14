package snake_game;

public class BodyCollisionException extends Exception {
    public BodyCollisionException(){
        super();
        System.out.println("You ate yourself");
        Application app = Application.getApp();
        app.getMainMenu().setFirstTimeLaunched();
        app.getMainMenu().setGameOverMessage("You ate yourself !");
        app.enterState(app.getMainMenu().getID());
    }
}

