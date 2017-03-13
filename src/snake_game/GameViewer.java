package snake_game;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.renderer.LineStripRenderer;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import snake_game.Application;
import java.util.ArrayDeque;

import static snake_game.Application.main;

/**
 * Created by martin on 13.03.17.
 */
public class GameViewer extends BasicGameState{

    protected int id;
    protected Application app;

    private static final int ITEMSIZE = 20;

    public GameViewer(int id){
        this.id = id;
        this.app = Application.getApp();
    }

    @Override
    public void init(GameContainer gc, StateBasedGame stbgame) throws SlickException {}

    @Override
    public void update(GameContainer gc, StateBasedGame stbgame, int i) throws SlickException {}

    @Override
    public void render(GameContainer gc, StateBasedGame stbgame, Graphics g) throws SlickException
    {
        //g.drawString("Howdy!", 10, 10);
        //float x = 100;
        //float y = 100;

        SnakeHead snake_head = app.getSnakeHead();
        ArrayDeque<SnakeBody> snakeArray = app.getSnakeArray();
        

        for (SnakeBody element : snakeArray) {
            float x = element.getX();
            float y = element.getY();

            Rectangle rectangle_shape = new Rectangle(x,y,ITEMSIZE,ITEMSIZE);

            g.draw(rectangle_shape);
            g.fill(rectangle_shape);
        }

        float x = snake_head.getX();
        float y = snake_head.getY();

        Rectangle rectangle_shape = new Rectangle(x,y,ITEMSIZE,ITEMSIZE);

        g.draw(rectangle_shape);
        g.fill(rectangle_shape);

    }

    public int getID(){
        return id;
    }


}
