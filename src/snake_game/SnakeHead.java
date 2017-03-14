package snake_game;

public class SnakeHead extends Item{
    private int direction = 1;

    public SnakeHead(float x, float y){
        super(x,y);
    }

    public int getDirection(){
        return direction;
    }

    public void updateDirection(int dir) throws InvalidMoveException {
        if(dir%2 == direction%2){
            throw new InvalidMoveException();
        } else {
            direction = dir;
        }

    }

    public void updateCoord(int speed){
        switch(direction) {
            case 0:
                y_position -= speed;
                break;
            case 1:
                x_position += speed;
                break;
            case 2:
                y_position += speed;
                break;
            case 3:
                x_position -= speed;
                break;
        }
    }
}
