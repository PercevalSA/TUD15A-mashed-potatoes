package snake_game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by martin on 13.03.17.
 */

public class Application extends StateBasedGame {

    private static Application instance = null;
    private static AppGameContainer appContainer = null;
    private static GameViewer gameView = null;
    private SnakeHead snakeHead = null;

    // Application Properties
    private static final int WIDTH   = 640;
    private static final int HEIGHT  = 480;
    private static final int FPS     = 60;
    private static final double VERSION = 1.0;


    public static Application getApp() {
        return instance;
    }
    public static AppGameContainer getAppContainer() {
        return appContainer;
    }

    public Application(String appName){
        super(appName);
        snakeHead = new SnakeHead(WIDTH / 2, HEIGHT / 2);
    }

    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        gameView = new GameViewer(1);
        this.addState(gameView);
        this.enterState(gameView.getID());
    }

    public static void main(String[] args){
        try {
            instance = new Application("Potato Snake v" + VERSION);
            appContainer = new AppGameContainer(instance);
            appContainer.setDisplayMode(WIDTH, HEIGHT, false);
            appContainer.setTargetFrameRate(FPS);
            appContainer.setShowFPS(true);
            appContainer.start();
        } catch(SlickException e) {
            e.printStackTrace();
        }
    }



    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}
