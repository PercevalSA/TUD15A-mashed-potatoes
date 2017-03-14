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

        for (SnakeBody element : snakeArray) {
            element.drawItem(g,element.getX(),element.getY());
        }

        snake_head.drawItem(g,snake_head.getX(),snake_head.getY());


        Rectangle wall = new Rectangle(1,1,app.WIDTH-1,app.GAMEHEIGHT-1);
        g.setColor(Color.white);
        g.draw(wall);


        //Draw the good apple
        Food foo = FoodManager.getInstance().getGoodApple();
        if (foo != null){
            foo.drawItem(g, foo.getX(), foo.getY());
        }


        // Draw list of other apples
        for(Food food : FoodManager.getInstance().getApples()){

            if (food != null){
                food.drawItem(g, food.getX(), food.getY());
            }
        }

        g.setColor(Color.white);
        g.drawString("FPS: " + app.getAppContainer().getFPS() + "   Score: " + snakeArray.size() + "   Position : (" + snake_head.x_position + ", " + snake_head.y_position + ")"
                , app.WIDTH/5f, app.GAMEHEIGHT);

    }


    public int getID(){
        return id;
    }

}
