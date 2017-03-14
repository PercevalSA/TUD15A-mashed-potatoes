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
        }catch (WallCollisionException|BodyCollisionException e){

        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stbgame, Graphics g) throws SlickException {

        SnakeHead snake_head = app.getSnakeHead();
        ArrayDeque<SnakeBody> snakeArray = app.getSnakeArray();
        Food food = app.getFood();

        for (SnakeBody element : snakeArray) {
            element.drawItem(g,element.getX(),element.getY());
        }

        snake_head.drawItem(g,snake_head.getX(),snake_head.getY());

        Rectangle wall = new Rectangle(1,1,app.getWIDTH()-1,app.getGAMEHEIGHT()-1);
        g.setColor(Color.white);
        g.draw(wall);


        if (food != null){
            food.drawItem(g,food.getX(),food.getY());
        }

        g.setColor(Color.white);
        g.drawString("FPS: " + app.getAppContainer().getFPS() + "   Score: " + snakeArray.size() + "   Position : (" + snake_head.x_position + ", " + snake_head.y_position + ")"
                , app.getWIDTH()/5f, app.getGAMEHEIGHT());

    }


    public int getID(){
        return id;
    }

}
