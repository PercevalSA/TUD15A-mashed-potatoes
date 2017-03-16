package snake_game;

import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import java.awt.Font;
import java.awt.event.*;

public class MainMenu extends BasicGameState implements KeyListener, MouseListener {

    // ID we return to class 'Application'
    protected int id;

    private static boolean firstTimeLaunched = true;
    private String gameOverMessage;
    private Font font;
    private TrueTypeFont playersOptionsTTF;
    private boolean pressed = false;
    private final float buttonX = Application.WIDTH * 0.24f;
    private final float buttonY = Application.HEIGHT * 0.4f;
    private final float buttonWidth = Application.WIDTH * 0.52f;
    private final float buttonHeight = Application.HEIGHT * 0.25f;

    private final float gameOverX = Application.WIDTH * 0.1f;
    private final float gameOverY = Application.HEIGHT * 0.15f;
    private final float gameOverWidth = Application.WIDTH * 0.8f;
    private final float gameOverHeight = Application.HEIGHT * 0.25f;

    public MainMenu(int id) {
        this.id = id;
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

        Rectangle background = new Rectangle(0,0,Application.WIDTH,Application.HEIGHT);
        g.setColor(Color.white);
        g.draw(background);
        g.fill(background);

        if(firstTimeLaunched) {

            Rectangle frameShadow = new Rectangle(buttonX + 4,buttonY + 4, buttonWidth, buttonHeight);
            g.setColor(Color.darkGray);
            g.fill(frameShadow);
            g.draw(frameShadow);

            Rectangle frame = new Rectangle(buttonX + (pressed?4:0) , buttonY + (pressed?4:0), buttonWidth, buttonHeight);
            g.setColor(Color.decode("#2196F3")); // blue
            g.fill(frame);
            g.draw(frame);

            g.setColor(Color.decode("#D50000")); //orange
            g.setFont(new TrueTypeFont(new Font("Comic Sans MS", Font.PLAIN, 60), true));
            g.drawString("SNEAKY SNAKE", Application.WIDTH * 0.14f, Application.HEIGHT * 0.11f);


            g.setColor(Color.white);
            g.setFont(new TrueTypeFont(new Font("Garamon", Font.BOLD, 60), true));
            g.drawString("START", Application.WIDTH * 0.35f, Application.HEIGHT * 0.432f);

            g.setFont(new TrueTypeFont(new Font("Courier New", Font.PLAIN, 30), true));
            g.drawString("Press SPACE", Application.WIDTH * 0.35f, Application.HEIGHT * 0.562f);

            g.setColor(Color.decode("#00897B"));
            g.setFont(new TrueTypeFont(new Font("Garamon", Font.PLAIN, 18), true));
            g.drawString("Press Escape to quit", Application.WIDTH * 0.36f, Application.HEIGHT * 0.8f);

            g.setColor(Color.black);
            g.setFont(new TrueTypeFont(new Font("Courier New", Font.PLAIN, 12), true));
            g.drawString("© Made with <3 by the Mashed Potato Team", Application.WIDTH * 0.27f, Application.HEIGHT * 0.95f);
        }
        else{

            Rectangle frameshadow = new Rectangle(gameOverX + 4 ,gameOverY + 4,gameOverWidth, gameOverHeight);
            g.setColor(Color.darkGray);
            g.fill(frameshadow);
            g.draw(frameshadow);

            Rectangle frame = new Rectangle(gameOverX + (pressed?4:0) ,gameOverY + (pressed?4:0), gameOverWidth, gameOverHeight);
            g.setColor(Color.decode("#4A148C")); //purple
            g.fill(frame);
            g.draw(frame);

            g.setFont(new TrueTypeFont(new Font("Comic Sans MS", Font.BOLD, 50), true));
            g.setColor(Color.white);
            g.drawString("GAME OVER", Application.WIDTH * 0.26f, Application.HEIGHT * 0.2f);

            g.setColor(Color.decode("#AD1457"));
            g.setFont(new TrueTypeFont(new Font("Garamon", Font.PLAIN, 35), true));
            g.drawString(gameOverMessage, Application.WIDTH * 0.3f, Application.HEIGHT * 0.48f);

            g.setColor(Color.black);
            g.setFont(new TrueTypeFont(new Font("Courier New", Font.PLAIN, 20), true));
            g.drawString("Score      : " + GameViewer.getScore(), Application.WIDTH * 0.35f, Application.HEIGHT * 0.68f);

            g.setColor(Color.black);
            g.setFont(new TrueTypeFont(new Font("Courier New", Font.PLAIN, 20), true));
            g.drawString("Snake size : " + Application.getApp().getSnakeArray().size(), Application.WIDTH * 0.35f, Application.HEIGHT * 0.73f);

        }
    }

    // Key Listener

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if(key == Input.KEY_SPACE) {
            pressed = true;
        }
        if (key == Input.KEY_ESCAPE) {
            Application.getAppContainer().exit();
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        super.keyReleased(key, c);
        if(key == Input.KEY_SPACE) {
            pressed = false;
            Application.getApp().resetGame();
            GameViewer.resetSpeedCounter();
            Application.getApp().enterState(GameViewer.getInstance().getID());
        }

    }

    @Override
    public void mousePressed(int button, int x, int y) {
        super.mousePressed(button, x, y);

        if(button == 0 &&
           x >= buttonX && x <= buttonX + buttonWidth &&
           y >= buttonY && y <= buttonY + buttonHeight) {
            pressed = true;
        }
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        super.mouseReleased(button, x, y);
        if(button == 0 &&
                x >= buttonX && x <= buttonX + buttonWidth &&
                y >= buttonY && y <= buttonY + buttonHeight) {
            pressed = false;
            Application.getApp().resetGame();
            GameViewer.resetSpeedCounter();
            Application.getApp().enterState(GameViewer.getInstance().getID());
        }
    }

    // update-method with all the magic happening in it
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException  {}

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

    public static boolean getfirstTimeLaunched() {
        return firstTimeLaunched;
    }
}
