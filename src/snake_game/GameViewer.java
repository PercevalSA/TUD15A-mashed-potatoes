package snake_game;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.util.ArrayDeque;

public class GameViewer extends BasicGameState{

    protected int id;
    protected Application app;

    public GameViewer(int id){
        this.id = id;
        this.app = Application.getApp();
    }

    @Override
    public void init(GameContainer gc, StateBasedGame stbgame) throws SlickException {}

    @Override
    public void update(GameContainer gc, StateBasedGame stbgame, int i) throws SlickException {
        try {
            Application.getApp().getGameController().updateBodyPosition(gc);
        }catch (WallCollisionException e){

        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stbgame, Graphics g) throws SlickException {



        SnakeHead snake_head = app.getSnakeHead();
        ArrayDeque<SnakeBody> snakeArray = app.getSnakeArray();

        g.setColor(Color.blue);
        for (SnakeBody element : snakeArray) {
            float body_x = element.getX();
            float body_y = element.getY();


            Rectangle rectangle_shape = new Rectangle(body_x,body_y,Application.getITEMSIZE(),Application.getITEMSIZE());

            g.draw(rectangle_shape);
            g.fill(rectangle_shape);
        }
//
//        Image img = new Image("res/SnakeHeadVector.jpg");
//        int direction = snake_head.getDirection();
//
//        switch(direction) {
//            case 0:
//                img = img.getFlippedCopy(false,false);
//                break;
//            case 1:
//                img = img.getFlippedCopy(true,false);
//                break;
//            case 2:
//                img = img.getFlippedCopy(false,true);
//                break;
//            case 3:
//                img = img.getFlippedCopy(true,true);
//                break;
//        }
//
//
//        img.draw(snake_head.getX(), snake_head.getY());



        Rectangle rectangle_shape = new Rectangle(snake_head.getX(),snake_head.getY(),Application.getITEMSIZE(),Application.getITEMSIZE());

        g.setColor(Color.white);
        g.draw(rectangle_shape);
        g.fill(rectangle_shape);

        Rectangle wall = new Rectangle(1,1,app.getWIDTH()-1,app.getGAMEHEIGHT()-1);
        g.setColor(Color.white);
        g.draw(wall);

        g.drawString("Score: " + snakeArray.size(), app.getWIDTH()/2.3f, app.getGAMEHEIGHT());




    }

    public int getID(){
        return id;
    }


}
