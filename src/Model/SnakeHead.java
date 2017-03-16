package Model;

import Exceptions.BodyCollisionException;
import Exceptions.InvalidMoveException;
import Exceptions.WallCollisionException;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.Deque;

public class SnakeHead extends Item {
    private int previous_direction = 1;

    private Image imgSnakeHeadV, imgSnakeHeadH;
    private boolean imageLoaded = false;

    public void loadImages(){
        try {
            imgSnakeHeadV = new Image("res/snake-v.png");
            imgSnakeHeadH = new Image("res/snake-h.png");
            imageLoaded = true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

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
        //TODO move to the controller
        int GAMEHEIGHT = Application.GAMEHEIGHT;
        int GAMEWIDTH = Application.WIDTH;
        if(x_temp <= 0 - Application.ITEMSIZE / 2|| x_temp + Application.ITEMSIZE / 2 >= GAMEWIDTH || y_temp <= 0 - Application.ITEMSIZE / 2|| y_temp + Application.ITEMSIZE / 2 >= GAMEHEIGHT)
            return true;
        return false;
    }

    public boolean checkBodyCollision(float x_temp, float y_temp) throws BodyCollisionException{
        Deque<SnakeBody> body = SnakeManager.getInstance().getSnakeArray();//TODO move to the controller
        for( SnakeBody b : body) {
            float x = b.getX();
            float y = b.getY();
            if (y_temp == y && x_temp == x) return true;
        }
        return false;
    }

    public void drawItem(Graphics g){
        if(!imageLoaded)
            loadImages();

        Image img = null;
        int direction = getDirection();
        switch (direction) {
            case 0:
                img = imgSnakeHeadV;
                img = img.getFlippedCopy(false, true);
                break;
            case 1:
                img = imgSnakeHeadH;
                break;
            case 2:
                img = imgSnakeHeadV;
                break;
            case 3:
                img = imgSnakeHeadH;
                img = img.getFlippedCopy(true, false);
                break;
        }
        if(img != null)
            img.draw(x_position, y_position,Application.ITEMSIZE,Application.ITEMSIZE);
        else
            super.drawItem(g);
    }
}
