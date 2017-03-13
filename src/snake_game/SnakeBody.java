package snake_game;

/**
 * Created by martin on 13.03.17.
 */
public class SnakeBody extends Item {

    public SnakeBody(float x,float y){
        super(x,y);
    }

    public void updateBody( float x, float y){
        x_position = x;
        y_position =y;
    }
}