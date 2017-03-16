package Viewer;

import Model.Application;
import Viewer.GameViewer;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Pause extends BasicGameState {

    protected int id;

    private final float pauseMenuX = Application.WIDTH * 0.1f;
    private final float pauseMenuY = Application.HEIGHT * 0.35f;
    private final float pauseMenuWidth = Application.WIDTH * 0.8f;
    private final float pauseMenuHeight = Application.HEIGHT * 0.25f;

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

        Rectangle background = new Rectangle(0, 0, Application.WIDTH, Application.HEIGHT);
        Color gray = Color.gray;
        gray.a = 0.5f; // alpha transparency
        g.setColor(gray);
        g.draw(background);
        g.fill(background);

        Rectangle frameshadow = new Rectangle(pauseMenuX + 4 ,pauseMenuY + 4,pauseMenuWidth, pauseMenuHeight);
        Color darkGray = Color.darkGray;
        darkGray.a = 0.4f;
        g.setColor(darkGray);
        g.fill(frameshadow);
        g.draw(frameshadow);

        Rectangle frame = new Rectangle(pauseMenuX,pauseMenuY, pauseMenuWidth, pauseMenuHeight);
        Color red = Color.decode("#F44336");
        red.a = 0.75f;
        g.setColor(red);
        g.fill(frame);
        g.draw(frame);

        g.setFont(new TrueTypeFont(new java.awt.Font("Comic Sans MS", java.awt.Font.BOLD, 50), true));
        g.setColor(Color.white);
        g.drawString("PAUSE", Application.WIDTH * 0.375f, Application.HEIGHT * 0.4f);


    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        int state = Application.getApp().getCurrentStateId();

        if (input.isKeyPressed(Input.KEY_SPACE) && state == Application.PAUSE) {
            Application.getApp().enterState(GameViewer.getInstance().getID());
        }
        if (input.isKeyPressed(Input.KEY_ESCAPE) && state == Application.PAUSE) {
            gc.exit();
        }
    }
}
