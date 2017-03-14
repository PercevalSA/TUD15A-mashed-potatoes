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
    protected int choice = 0;



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
        renderPlayersOptions();
        if (choice > 10) {
            app.enterState(app.getGameView().getID());

            // gc.exit();
        }
    }

    // update-method with all the magic happening in it
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            choice++;
        }
        if (input.isKeyPressed(Input.KEY_UP)) {
            choice--;
        }
    }

    // Returning 'ID' from class 'MainMenu'
    @Override
    public int getID() {
        return id;
    }

    private void renderPlayersOptions() {
        playersOptionsTTF.drawString(100, 100, "toto"+choice);

    }
}
