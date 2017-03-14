package snake_game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

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

    public void updateCoord (int speed, int direction) throws WallCollisionException, BodyCollisionException, InvalidMoveException {
        float y_temp = y_position;
        float x_temp = x_position;

        if(direction != previous_direction) {
            updateDirection(direction);
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

    public void drawItem(Graphics g, float x, float y) throws SlickException {
        Image img = new Image("res/SnakeHeadVector.jpg");
        int direction = getDirection();
        img.setCenterOfRotation(0,0);

        switch(direction) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                img = img.getFlippedCopy(true,false);
                break;
        }

        img.draw(x, y,Application.getITEMSIZE(),Application.getITEMSIZE());
    }
}
