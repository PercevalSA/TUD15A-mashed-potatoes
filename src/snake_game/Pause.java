package snake_game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by jovanovic on 3/16/2017.
 */
public class Pause extends BasicGameState {

    protected int id;

    public Pause(int id){
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        GameViewer.getInstance().render(gc, sbg, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_SPACE)) {
            //Application.getApp().resetGame();
            //GameViewer.resetSpeedCounter();
            Application.getApp().enterState(GameViewer.getInstance().getID());
        }
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.exit();
        }
    }
}
