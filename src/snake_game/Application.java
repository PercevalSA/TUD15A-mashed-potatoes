package snake_game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import java.util.ArrayDeque;

public class Application extends StateBasedGame {

    // Game state identifiers
    public static final int MAINMENU = 1;
    public static final int GAMEVIEWER = 2;

    private static Application instance = null;
    private static AppGameContainer appContainer = null;
    private static MainMenu mainMenu = null;
    private static GameViewer gameView = null;
    private static GameController gameController = null;
    private SnakeHead snakeHead = null;
    private Food food = null;

    private ArrayDeque<SnakeBody> snakeArray= new ArrayDeque<>();

    // Application Properties
    private static final int WIDTH   = 640;
    private static final int HEIGHT  = 500;
    private static final int GAMEHEIGHT = 480;
    private static final int FPS     = 60;
    private static final double VERSION = 1.0;
    private static final int ITEMSIZE = 20;
    private static final int BODYSIZE = 15;


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
        snakeArray.addFirst(new SnakeBody(WIDTH / 2 - 20, HEIGHT / 2));
        snakeArray.addFirst(new SnakeBody(WIDTH / 2 - 20, HEIGHT / 2));
        snakeArray.addFirst(new SnakeBody(WIDTH / 2 - 20, HEIGHT / 2));
        snakeArray.addFirst(new SnakeBody(WIDTH / 2 - 20, HEIGHT / 2));
        snakeArray.addFirst(new SnakeBody(WIDTH / 2 - 20, HEIGHT / 2));
        snakeArray.addFirst(new SnakeBody(WIDTH / 2 - 20, HEIGHT / 2));
        snakeArray.addFirst(new SnakeBody(WIDTH / 2 - 20, HEIGHT / 2));
        snakeArray.addFirst(new SnakeBody(WIDTH / 2 - 20, HEIGHT / 2));
        snakeArray.addFirst(new SnakeBody(WIDTH / 2 - 20, HEIGHT / 2));

    }

    // Initialize game states
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(mainMenu);
        this.addState(gameView);
        this.enterState(mainMenu.getID());
    }

    public static void main(String[] args){
        try {
            instance = new Application("Potato Snake v" + VERSION);
            mainMenu = new MainMenu(MAINMENU);
            gameView = new GameViewer(GAMEVIEWER);
            appContainer = new AppGameContainer(instance);
            appContainer.setDisplayMode(WIDTH, HEIGHT, false);
            appContainer.setTargetFrameRate(FPS);
            appContainer.setShowFPS(true);
            appContainer.start();
            gameController.createFoodItem();
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

    public static int getITEMSIZE() {
        return ITEMSIZE;
    }

    public static int getGAMEHEIGHT() {
        return GAMEHEIGHT;
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

    public static MainMenu getMainMenu() {
        return mainMenu;
    }

    public static GameViewer getGameView() {
        return gameView;
    }

    public void setFood(Food food) { this.food = food;}

    public Food getFood() {
        return food;
    }
}
