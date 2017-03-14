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

    private String gameOverMessage;

    private static boolean firstTimeLaunched = true;

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
        font = new Font("Verdana", Font.PLAIN, 20);
        playersOptionsTTF = new TrueTypeFont(font, true);
    }

    // render-method for all the things happening on-screen
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        if(firstTimeLaunched) {
            playersOptionsTTF.drawString(100, 100, "Press Space to start :)");
            playersOptionsTTF.drawString(100, 150, "Press Escape to quit");
        }
        else{
            playersOptionsTTF.drawString(100, 100, "Game Over");
            playersOptionsTTF.drawString(100, 150, gameOverMessage);
            playersOptionsTTF.drawString(100, 200, "Score : " + app.getSnakeArray().size());
        }
    }

    // update-method with all the magic happening in it
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_SPACE)) {
            // TODO : Reset snake position and score
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

    public static void setFirstTimeLaunched() {
        MainMenu.firstTimeLaunched = false;
    }

    public void setGameOverMessage(String gameOverMessage) {
        this.gameOverMessage = gameOverMessage;
    }
}
