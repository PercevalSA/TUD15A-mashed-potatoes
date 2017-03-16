package snake_game;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.*;


public class SnakeBody extends Item {

    private Image imgSnakeScale;
    private boolean imageLoaded = false;


    public void loadImages(){
        try {
            imgSnakeScale = new Image("res/snake_scale.png");
            imageLoaded = true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public SnakeBody(float x,float y){
        super(x,y, Color.blue);
    }

    /*
    Updates the Time of the Body and Informs the Caller if still alive
    @Return Boolean saying if it should exist in this cycle.
     */
    /*
    public boolean updateTime(){
        //SnakeHead snake = Application.getApp().getSnake();
        if( ++time >= snake.getSize()) {
            return false;
        }
        return true;

    }*/

    public void updateBody( float x, float y){
        x_position = x;
        y_position = y;
    }

    @Override
    public void drawItem(Graphics g) {
        if(!imageLoaded)
            loadImages();

        Image img = imgSnakeScale;
        
        if(img != null)
            img.draw(x_position, y_position,Application.ITEMSIZE,Application.ITEMSIZE);
        else
            super.drawItem(g);
    }
}