package snake_game;

/**
 * Created by martin on 13.03.17.
 */
public class SnakeHead extends Item{
    private int direction = 0;

    public SnakeHead(float x, float y){
        super(x,y);
    }

    public int getDirection(){
        return direction;
    }

    public void updateDirection(int a) throws InvalidMoveException {
        if( a%2 == direction%2){
            throw new InvalidMoveException();
        }
        direction = a;
    }

    public void updateCoord(int speed){
        if( direction > 2){
            speed = -speed;
        }
        if(direction%2 == 0){
            x_position += speed;
        }else{
            y_position += speed;
        }
    }
}
