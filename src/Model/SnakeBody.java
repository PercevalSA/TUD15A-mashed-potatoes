package Model;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;


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
            img.draw(x_position, y_position, Application.ITEMSIZE,Application.ITEMSIZE);
        else
            super.drawItem(g);
    }
}