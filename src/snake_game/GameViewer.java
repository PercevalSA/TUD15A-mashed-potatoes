package snake_game;
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

/**
 * Created by martin on 13.03.17.
 */
public class GameViewer extends BasicGameState{

    protected int id;


    public GameViewer(int id){
        this.id = id;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame stbgame) throws SlickException {}

    @Override
    public void update(GameContainer gc, StateBasedGame stbgame, int i) throws SlickException {}

    @Override
    public void render(GameContainer gc, StateBasedGame stbgame, Graphics g) throws SlickException
    {
        //g.drawString("Howdy!", 10, 10);
        float x = 100;
        float y = 100;
        float w = 20;
        float h = 20;
        Rectangle rectangle_shape = new Rectangle(x,y,w,h);

        g.draw(rectangle_shape);
        g.fill(rectangle_shape);

    }


    public int getID(){
        return id;
    }


}
