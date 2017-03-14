package snake_game;

/**
 * Created by john on 14/03/2017.
 */
public class WallCollisionException extends Exception {
    public WallCollisionException(){
        super();
        System.out.println("You collided with a wall");
        //TODO : Enter new state : Game over with score (size of the snake)
    }
}
