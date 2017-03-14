package snake_game;

import java.util.Deque;

public class SnakeHead extends Item{
    private int previous_direction = 1;

    public SnakeHead(float x, float y){
        super(x,y);
    }

    public int getDirection() {
        return previous_direction;
    }

    public void updateDirection(int dir) throws InvalidMoveException {
        if(dir%2 == previous_direction%2){
            throw new InvalidMoveException();
        } else {
            previous_direction = dir;
        }

    }

    public void updateCoord (int speed, int direction) throws WallCollisionException, BodyCollisionException {
        float y_temp = y_position;
        float x_temp = x_position;

        if(direction != previous_direction) {
            try {
                updateDirection(direction);
            }catch(InvalidMoveException e){
                direction = previous_direction;
            }
        }

        switch(direction) {
            case 0:
                y_temp -= speed;
                break;
            case 1:
                x_temp += speed;
                break;
            case 2:
                y_temp += speed;
                break;
            case 3:
                x_temp -= speed;
                break;
        }

        if(checkWallCollision(x_temp, y_temp))
            throw new WallCollisionException();

        if( checkBodyCollision(x_temp,y_temp))
            throw new BodyCollisionException();
        x_position = x_temp;
        y_position = y_temp;
    }

    private boolean checkWallCollision(float x_temp, float y_temp) {
        int GAMEHEIGHT = Application.getGAMEHEIGHT();
        int GAMEWIDTH = Application.getWIDTH();
        if(x_temp <= 0 - Application.getITEMSIZE() / 2|| x_temp + Application.getITEMSIZE() / 2 >= GAMEWIDTH || y_temp <= 0 - Application.getITEMSIZE() / 2|| y_temp + Application.getITEMSIZE() / 2 >= GAMEHEIGHT)
            return true;
        return false;
    }

    public boolean checkBodyCollision(float x_temp, float y_temp) throws BodyCollisionException{
        Deque<SnakeBody> body = Application.getApp().getSnakeArray();
        for( SnakeBody b : body) {
            float x = b.getX();
            float y = b.getY();
            if (y_temp == y && x_temp == x) return true;
        }
        return false;
    }
}
