package snake_game;

import org.newdawn.slick.*;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import java.awt.Font;

public class MainMenu extends BasicGameState {

    // ID we return to class 'Application'
    protected int id;
    protected Application app;

    private Font font;
    private TrueTypeFont playersOptionsTTF;

    public MainMenu(int id) {
        this.id = id;
        this.app = Application.getApp();
    }

    // init-method for initializing all resources
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // init font
        font = new Font("Verdana", Font.BOLD, 40);
        playersOptionsTTF = new TrueTypeFont(font, true);
    }

    // render-method for all the things happening on-screen
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        playersOptionsTTF.drawString(100, 100, "Press Space to start :)");
        playersOptionsTTF.drawString(100, 150, "Press Escape to quit");
        playersOptionsTTF.drawString(100, 200, "Score : "+app.getSnakeArray().size());
    }

    // update-method with all the magic happening in it
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_SPACE)) {
            app.enterState(app.getGameView().getID());
        }
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.exit();
        }
    }

    // Returning 'ID' from class 'MainMenu'
    @Override
    public int getID() {
        return id;
    }

}
