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


            Rectangle frameShadow = new Rectangle(Application.WIDTH * 0.24f + 4,Application.HEIGHT * 0.4f + 4,
                    Application.WIDTH * 0.52f,Application.HEIGHT * 0.25f);
            g.setColor(Color.darkGray);
            g.fill(frameShadow);
            g.draw(frameShadow);

            Rectangle frame = new Rectangle(Application.WIDTH * 0.24f,Application.HEIGHT * 0.4f,
                    Application.WIDTH * 0.52f,Application.HEIGHT * 0.25f);
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

    public void print(Graphics g, String message, float x, float y, Color color, String fontName, int fontStyle, int fontSize){
        g.setColor(color);
        g.setFont(new TrueTypeFont(new Font(fontName, fontStyle, fontSize), true));
        g.drawString(message, x, y);
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

    public static boolean getfirstTimeLaunched() {
        return firstTimeLaunched;
    }
}
