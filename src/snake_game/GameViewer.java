package snake_game;

import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.util.ArrayDeque;

public class GameViewer extends BasicGameState{

    protected int id;
    protected Application app;
    private int[] speedTimes = new int[] {10, 5 , 3};
    private int[] delays = new int[speedTimes.length];
    private int[] speedTimesCheck = new int[] {200, 166, 83 , 65, 45};
    private int speedCounter = 0;
    private int totalNumberOfFrames;

    public GameViewer(int id){
        this.id = id;
        this.app = Application.getApp();
        totalNumberOfFrames = 0;
        for (int i=0; i<speedTimes.length; i++){
            delays[i]  = (int)((1.0/app.getFPS()) * speedTimes[speedCounter] * 1000);
        }
    }

    @Override
    public void init(GameContainer gc, StateBasedGame stbgame) throws SlickException {}

    @Override
    public void update(GameContainer gc, StateBasedGame stbgame, int i) throws SlickException {
        if(++totalNumberOfFrames % 60 == 0 && speedCounter < speedTimesCheck.length) {
            if (speedCounter != (speedTimesCheck.length-1)) {speedCounter++;}
            totalNumberOfFrames = 0;
        }
        try {
            Application.getApp().getGameController().updateBodyPosition(gc, speedTimesCheck[speedCounter]);
            //Application.getApp().getGameController().updateBodyPosition(gc, delays[speedCounter]);
        }catch (WallCollisionException|BodyCollisionException e){

        }
    }


    @Override
    public void render(GameContainer gc, StateBasedGame stbgame, Graphics g) throws SlickException {

        SnakeHead snake_head = app.getSnakeHead();
        ArrayDeque<SnakeBody> snakeArray = app.getSnakeArray();
        Food food = app.getFood();

        g.setColor(Color.blue);
        for (SnakeBody element : snakeArray) {
            float body_x = element.getX();
            float body_y = element.getY();


            Rectangle body_shape = new Rectangle(body_x,body_y,Application.getITEMSIZE(),Application.getITEMSIZE());
            g.draw(body_shape);
            g.fill(body_shape);
        }

        Image img = new Image("res/SnakeHeadVector.jpg");
        int direction = snake_head.getDirection();
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

        img.draw(snake_head.getX(), snake_head.getY(),Application.getITEMSIZE(),Application.getITEMSIZE());

        Rectangle wall = new Rectangle(1,1,app.getWIDTH()-1,app.getGAMEHEIGHT()-1);
        g.setColor(Color.white);
        g.draw(wall);

        if (food != null){
            Rectangle food_shape = new Rectangle(food.getX(),food.getY(),Application.getITEMSIZE(),Application.getITEMSIZE());
            g.setColor(Color.red);
            g.draw(food_shape);
            g.fill(food_shape);
        }

        g.setColor(Color.white);

        g.drawString("FPS:" + app.getAppContainer().getFPS() + "  Speed Level:" + speedCounter + "  Score:" + snakeArray.size() + "  Position:(" + snake_head.x_position + ", " + snake_head.y_position + ")"
                , app.getWIDTH()/5f, app.getGAMEHEIGHT());

    }

    public int getID(){
        return id;
    }

    public void resetSpeedCounter(){
        speedCounter = 0;
    }

}
