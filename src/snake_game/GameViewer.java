package snake_game;

import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.util.ArrayDeque;

public class GameViewer extends BasicGameState{

    private int id;
    private Application app;
    private static GameViewer instance = null;

    private GameViewer(){
        this.id = Application.GAMEVIEWER;
        this.app = Application.getApp();
    }

    public static GameViewer getInstance(){
        if(instance == null){
            instance = new GameViewer();
        }
        return instance;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame stbgame) throws SlickException {}

    @Override
    public void update(GameContainer gc, StateBasedGame stbgame, int i) throws SlickException {
        try {
            GameController.getInstance().updateBodyPosition(gc);
        }catch (WallCollisionException|BodyCollisionException|InvalidSizeException e){

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


            Rectangle body_shape = new Rectangle(body_x,body_y,Application.ITEMSIZE,Application.ITEMSIZE);
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

        img.draw(snake_head.getX(), snake_head.getY(),Application.ITEMSIZE,Application.ITEMSIZE);

        Rectangle wall = new Rectangle(1,1,app.WIDTH-1,app.GAMEHEIGHT-1);
        g.setColor(Color.white);
        g.draw(wall);

        if (food != null){
            Rectangle food_shape = new Rectangle(food.getX(),food.getY(),Application.ITEMSIZE,Application.ITEMSIZE);
            g.setColor(Color.red);
            g.draw(food_shape);
            g.fill(food_shape);
        }

        g.setColor(Color.white);

        g.drawString("FPS: " + app.getAppContainer().getFPS() + "   Score: " + snakeArray.size() + "   Position : (" + snake_head.x_position + ", " + snake_head.y_position + ")"
                , app.WIDTH/5f, app.GAMEHEIGHT);

    }

    public int getID(){
        return id;
    }

}
