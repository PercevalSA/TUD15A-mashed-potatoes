package Model;

import Viewer.GameViewer;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import Viewer.GameOver;
import Viewer.MainMenu;
import Viewer.Pause;


public class Application extends StateBasedGame {

    // Game state identifiers
    public static final int MAINMENU = 1;
    public static final int GAMEVIEWER = 2;
    public static final int GAMEOVER = 3;
    public static final int PAUSE = 4;

    private static Application instance = null;
    private static AppGameContainer appContainer = null;

    private static GameViewer gameViewer = null;
    private static MainMenu mainMenu = null;
    private static GameOver gameOver = null;
    private static Pause pause = null;

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
    }

    // Initialize game states
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(mainMenu);
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
            appContainer = new AppGameContainer(instance);
            appContainer.setDisplayMode(WIDTH, HEIGHT, false);
            appContainer.setTargetFrameRate(FPS);
            appContainer.setShowFPS(false);
            appContainer.start();

        } catch(SlickException e) {
            e.printStackTrace();
        }
    }

    public static GameViewer getGameViewer() {
        return gameViewer;
    }

    public static void setGameViewer(GameViewer gameViewer) {
        Application.gameViewer = gameViewer;
    }

    public static Pause getPause() {
        return pause;
    }

    public int getFPS() {
        return FPS;
    }

    public  int getCurrentStateId(){
        return getCurrentStateID();
    }

}
