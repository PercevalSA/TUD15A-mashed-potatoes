package snake_game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import java.util.ArrayDeque;

public class Application extends StateBasedGame {

    private static Application instance = null;
    private static AppGameContainer appContainer = null;
    private static GameViewer gameView = null;
    private static final int GAMEVIEWER_ID = 1;
    private static GameController gameController = null;
    private SnakeHead snakeHead = null;

    private ArrayDeque<SnakeBody> snakeArray= new ArrayDeque<>();

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
        snakeHead = new SnakeHead(WIDTH / 2 , HEIGHT / 2);
        gameController = new GameController();
        snakeArray.addFirst(new SnakeBody(WIDTH / 2 - 20, HEIGHT / 2));
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(gameView);
        this.enterState(gameView.getID());
    }

    public static void main(String[] args){
        try {
            instance = new Application("Potato Snake v" + VERSION);
            gameView = new GameViewer(GAMEVIEWER_ID);
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

    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    public ArrayDeque<SnakeBody> getSnakeArray() {
        return snakeArray;
    }

    public void setSnakeArray(ArrayDeque<SnakeBody> snakeArray) {
        this.snakeArray = snakeArray;
    }

    public static GameController getGameController() {
        return gameController;
    }
}
