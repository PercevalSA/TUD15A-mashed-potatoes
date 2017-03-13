package snake_game;

/**
 * Created by martin on 13.03.17.
 */
public class SnakeHead extends Item{
    private int size = 3;
    private int direction = 0;

    public SnakeHead(float x, float y){
        super(x,y);
    }

    public int getSize(){
        return size;
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
}
