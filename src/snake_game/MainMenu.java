package snake_game;

import org.newdawn.slick.*;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import java.awt.Font;

public class MainMenu extends BasicGameState {

    // ID we return to class 'Application'
    protected int id;

    private String gameOverMessage;

    private static boolean firstTimeLaunched = true;

    private Font font;
    private TrueTypeFont playersOptionsTTF;

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

            Rectangle frameShadow = new Rectangle(Application.WIDTH * 0.1f + 4,Application.HEIGHT * 0.15f + 4,
                    Application.WIDTH * 0.8f,Application.HEIGHT * 0.25f);
            g.setColor(Color.darkGray);
            g.fill(frameShadow);
            g.draw(frameShadow);

            Rectangle frame = new Rectangle(Application.WIDTH * 0.1f,Application.HEIGHT * 0.15f,
                    Application.WIDTH * 0.8f,Application.HEIGHT * 0.25f);
            g.setColor(Color.decode("#2196F3")); // blue
            g.fill(frame);
            g.draw(frame);

            g.setColor(Color.white);
            g.setFont(new TrueTypeFont(new Font("Comic Sans MS", Font.PLAIN, 50), true));
            g.drawString("SNAKE POTATO", Application.WIDTH * 0.18f, Application.HEIGHT * 0.2f);


            g.setColor(Color.decode("#FF9800")); //orange
            g.setFont(new TrueTypeFont(new Font("Garamon", Font.BOLD, 50), true));
            g.drawString("START", Application.WIDTH * 0.37f, Application.HEIGHT * 0.5f);

            g.setColor(Color.black);
            g.setFont(new TrueTypeFont(new Font("Garamon", Font.PLAIN, 15), true));
            g.drawString("press space", Application.WIDTH * 0.43f, Application.HEIGHT * 0.63f);

            g.setColor(Color.black);
            g.setFont(new TrueTypeFont(new Font("Garamon", Font.PLAIN, 18), true));
            g.drawString("Press Escape to quit", Application.WIDTH * 0.36f, Application.HEIGHT * 0.75f);

            g.setColor(Color.black);
            g.setFont(new TrueTypeFont(new Font("Courier New", Font.PLAIN, 12), true));
            g.drawString("© Made with <3 by the Mashed Potato Team", Application.WIDTH * 0.25f, Application.HEIGHT * 0.95f);
        }
        else{

            Rectangle frameshadow = new Rectangle(Application.WIDTH * 0.1f + 4,Application.HEIGHT * 0.15f + 4,
                    Application.WIDTH * 0.8f,Application.HEIGHT * 0.25f);
            g.setColor(Color.darkGray);
            g.fill(frameshadow);
            g.draw(frameshadow);

            Rectangle frame = new Rectangle(Application.WIDTH * 0.1f,Application.HEIGHT * 0.15f,
                    Application.WIDTH * 0.8f,Application.HEIGHT * 0.25f);
            g.setColor(Color.decode("#4A148C")); //purple
            g.fill(frame);
            g.draw(frame);

            g.setFont(new TrueTypeFont(new Font("Comic Sans MS", Font.BOLD, 50), true));
            //g.setColor(Color.black);
            //g.drawString("GAME OVER", Application.WIDTH * 0.24f + 2, Application.HEIGHT * 0.2f + 4);
            g.setColor(Color.white);
            g.drawString("GAME OVER", Application.WIDTH * 0.24f, Application.HEIGHT * 0.2f);

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

    // update-method with all the magic happening in it
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_SPACE)) {
            Application.getApp().resetGame();
            GameViewer.resetSpeedCounter();
            Application.getApp().enterState(GameViewer.getInstance().getID());
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

    public static boolean getfirstTimeLaunched() {
        return firstTimeLaunched;
    }
}
