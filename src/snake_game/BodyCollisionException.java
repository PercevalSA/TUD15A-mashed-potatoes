package snake_game;

/**
 * Created by tsepol on 14-03-2017.
 */
public class BodyCollisionException extends Exception {
    public BodyCollisionException(){
        super();
        System.out.println("You ate yourself");
        Application app = Application.getApp();
        app.getMainMenu().setFirstTimeLaunched();
        app.enterState(app.getMainMenu().getID());
    }
}
