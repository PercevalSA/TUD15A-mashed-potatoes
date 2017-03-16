package snake_game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.text.FieldPosition;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Application extends StateBasedGame {

    // Game state identifiers
    public static final int MAINMENU = 1;
    public static final int GAMEVIEWER = 2;
    public static final int GAMEOVER = 3;

    private static Application instance = null;
    private static AppGameContainer appContainer = null;
    private static MainMenu mainMenu = null;
    private static GameOver gameOver = null;

    private SnakeHead snakeHead = null;
    private ArrayDeque<SnakeBody> snakeArray= new ArrayDeque<>();

    // Application Properties
    public static final int WIDTH   = 640;
    public static final int HEIGHT  = 500;
    public static final int GAMEHEIGHT = 480;
    public static final int FPS     = 60;
    public static final double VERSION = 2.0;

    public static final int ITEMSIZE = 20;


    public static Application getApp() {
        return instance;
    }
    public static AppGameContainer getAppContainer() {
        return appContainer;
    }

    public Application(String appName){
        super(appName);
        resetGame();
    }

    // Initialize game states
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(mainMenu);
        this.addState(GameViewer.getInstance());
        this.addState(gameOver);
        this.enterState(mainMenu.getID());
    }

    public static void main(String[] args){
        try {
            instance = new Application("Potato Snake v" + VERSION);

            // food manager
            FoodManager.getInstance().initializeFoods();
            new Thread(FoodManager.getInstance()).start();

            // display
            mainMenu = new MainMenu(MAINMENU);
            gameOver = new GameOver(GAMEOVER);
            appContainer = new AppGameContainer(instance);
            appContainer.setDisplayMode(WIDTH, HEIGHT, false);
            appContainer.setTargetFrameRate(FPS);
            appContainer.setShowFPS(false);
            appContainer.start();

        } catch(SlickException e) {
            e.printStackTrace();
        }
    }


    protected void resetGame() {

        GameViewer.resetScore();
        FoodManager.getInstance().initializeFoods();
        snakeArray.clear();
        snakeHead = new SnakeHead(WIDTH / 2 , HEIGHT / 2);

        snakeArray.addFirst(new SnakeBody(WIDTH / 2 - 20, HEIGHT / 2));
        snakeArray.addFirst(new SnakeBody(WIDTH / 2 - 20, HEIGHT / 2));
        snakeArray.addFirst(new SnakeBody(WIDTH / 2 - 20, HEIGHT / 2));
        snakeArray.addFirst(new SnakeBody(WIDTH / 2 - 20, HEIGHT / 2));
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

    public static MainMenu getMainMenu() {
        return mainMenu;
    }

    public static GameOver getGameOver() {
        return gameOver;
    }

    public int getFPS() {
        return FPS;
    }

}
