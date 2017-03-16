package snake_game;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;

public class GameOver extends BasicGameState{

    protected int id;
    private String gameOverMessage;

    private java.awt.Font font;
    private TrueTypeFont playersOptionsTTF;

    private boolean pressed = false;
    private final float gameOverX = Application.WIDTH * 0.1f;
    private final float gameOverY = Application.HEIGHT * 0.15f;
    private final float gameOverWidth = Application.WIDTH * 0.8f;
    private final float gameOverHeight = Application.HEIGHT * 0.25f;

    public GameOver(int id) {

        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        font = new java.awt.Font("Verdana", java.awt.Font.PLAIN, 20);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

        Rectangle background = new Rectangle(0,0,Application.WIDTH,Application.HEIGHT);
        g.setColor(Color.white);
        g.draw(background);
        g.fill(background);

        Rectangle frameshadow = new Rectangle(gameOverX + 4 ,gameOverY + 4,gameOverWidth, gameOverHeight);
        g.setColor(Color.darkGray);
        g.fill(frameshadow);
        g.draw(frameshadow);

        Rectangle frame = new Rectangle(gameOverX + (pressed?4:0) ,gameOverY + (pressed?4:0), gameOverWidth, gameOverHeight);
        g.setColor(Color.decode("#4A148C")); //purple
        g.fill(frame);
        g.draw(frame);

        g.setFont(new TrueTypeFont(new java.awt.Font("Comic Sans MS", java.awt.Font.BOLD, 50), true));
        g.setColor(Color.white);
        g.drawString("GAME OVER", Application.WIDTH * 0.26f, Application.HEIGHT * 0.2f);

        g.setColor(Color.decode("#AD1457"));
        g.setFont(new TrueTypeFont(new java.awt.Font("Garamon", java.awt.Font.PLAIN, 35), true));
        g.drawString(gameOverMessage, Application.WIDTH * 0.3f, Application.HEIGHT * 0.48f);

        g.setColor(Color.black);
        g.setFont(new TrueTypeFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 20), true));
        g.drawString("Score      : " + GameViewer.getScore(), Application.WIDTH * 0.35f, Application.HEIGHT * 0.68f);

        g.setColor(Color.black);
        g.setFont(new TrueTypeFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 20), true));
        g.drawString("Snake size : " + Application.getApp().getSnakeArray().size(), Application.WIDTH * 0.35f, Application.HEIGHT * 0.73f);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {}

    public void setGameOverMessage(String gameOverMessage) {
        this.gameOverMessage = gameOverMessage;
    }

    // Key Listener
    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        int state = Application.getApp().getCurrentStateId();

        if(key == Input.KEY_SPACE && state == Application.GAMEOVER) {
            pressed = true;
        }
        if (key == Input.KEY_ESCAPE && state == Application.GAMEOVER) {
            Application.getAppContainer().exit();
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        super.keyReleased(key, c);
        int state = Application.getApp().getCurrentStateId();

        if(key == Input.KEY_SPACE && state == Application.GAMEOVER) {
            pressed = false;
            Application.getApp().resetGame();
            GameViewer.resetSpeedCounter();
            Application.getApp().enterState(GameViewer.getInstance().getID());
        }

    }

    @Override
    public void mousePressed(int button, int x, int y) {
        super.mousePressed(button, x, y);
        int state = Application.getApp().getCurrentStateId();

        if(button == 0  && state == Application.GAMEOVER &&
            x >= gameOverX && x <= gameOverX + gameOverWidth &&
            y >= gameOverY && y <= gameOverY + gameOverHeight) {
            pressed = true;
        }
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        super.mouseReleased(button, x, y);
        int state = Application.getApp().getCurrentStateId();

        if(button == 0 && state == Application.GAMEOVER &&
            x >= gameOverX && x <= gameOverX + gameOverWidth &&
            y >= gameOverY && y <= gameOverY + gameOverHeight) {
            pressed = false;
            Application.getApp().resetGame();
            GameViewer.resetSpeedCounter();
            Application.getApp().enterState(GameViewer.getInstance().getID());
        }
    }
}
