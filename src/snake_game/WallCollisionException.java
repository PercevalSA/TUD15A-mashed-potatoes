package snake_game;


public class WallCollisionException extends Exception {
    public WallCollisionException(){
        super();
        System.out.println("You collided with a wall");
        Application app = Application.getApp();
        app.enterState(app.getMainMenu().getID());
    }
}
