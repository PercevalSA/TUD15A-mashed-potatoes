package snake_game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import java.util.Deque;

public class SnakeHead extends Item{
    private int previous_direction = 1;
    Image img;

    public SnakeHead(float x, float y){
        super(x,y,Color.green);
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
        int GAMEHEIGHT = Application.GAMEHEIGHT;
        int GAMEWIDTH = Application.WIDTH;
        if(x_temp <= 0 - Application.ITEMSIZE / 2|| x_temp + Application.ITEMSIZE / 2 >= GAMEWIDTH || y_temp <= 0 - Application.ITEMSIZE / 2|| y_temp + Application.ITEMSIZE / 2 >= GAMEHEIGHT)
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

    public void drawItem(Graphics g){
        Image img = null;
        int direction = getDirection();
        switch (direction) {
            case 0:
                try {
                    img = new Image("res/snake-v.png");
                    img = img.getFlippedCopy(false, true);
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                try {
                    img = new Image("res/snake-h.png");
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    img = new Image("res/snake-v.png");
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                // The image is originally in this direction
                break;
            case 3:
                try {
                    img = new Image("res/snake-h.png");
                    img = img.getFlippedCopy(true, false);
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                break;
        }
        if(img != null)
            img.draw(x_position, y_position,Application.ITEMSIZE,Application.ITEMSIZE);
        else
            super.drawItem(g);
    }
}
