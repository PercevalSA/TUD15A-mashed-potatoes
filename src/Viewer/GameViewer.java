package Viewer;

import Controller.GameController;
import Exceptions.BodyCollisionException;
import Exceptions.InvalidSizeException;
import Exceptions.WallCollisionException;
import Model.*;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayDeque;

public class GameViewer extends BasicGameState{

    protected int id;

    //array with different frame rates whit which we updateBodyPosition
    private int[] speedFrameRates = new int[] {11, 8, 6, 5, 4, 3, 2};
    private int[] speedMillisecRates = new int[speedFrameRates.length];

    //alternative way to change the speed - array contains milliseconds
    private int[] speedTimesCheck = new int[] {180, 166, 83 , 75, 65, 55, 45};
    private static int speedCounter = 0;
    private int totalNumberOfFrames;
    private static GameViewer instance = null;
    private static Score score = null;

    private GameViewer(){
        this.id = Application.GAMEVIEWER;
        totalNumberOfFrames = 0;
        //transform frame rates to the milliseconds - used to set sleeping time of the thread
        for (int i=0; i<speedFrameRates.length; i++){
            speedMillisecRates[i]  = (int)((1.0/Application.getApp().getFPS()) * speedFrameRates[i] * 1000);
        }
        score = new Score();
    }

    public static GameViewer getInstance(){
        if(instance == null){
            instance = new GameViewer();
        }
        return instance;
    }

    public static int getScore() {
        return score.getScore();
    }

    public static void setScore(Object obj) {
        score.update(obj);
    }

    @Override
    public void init(GameContainer gc, StateBasedGame stbgame) throws SlickException {}

    @Override
    public void update(GameContainer gc, StateBasedGame stbgame, int i) throws SlickException {
        Input input = gc.getInput();
        int state = Application.getApp().getCurrentStateId();
        if (input.isKeyPressed(Input.KEY_SPACE) && state == Application.GAMEVIEWER){
            Application.getApp().enterState(Application.getPause().getID());
        }
        //control speed change rate
        if(++totalNumberOfFrames % 720 == 0 && speedCounter < speedFrameRates.length) {
            if (speedCounter != (speedFrameRates.length-1)) {
                speedCounter++;
            }
            totalNumberOfFrames = 0;
        }
        try {
            GameController.getInstance().updateBodyPosition(gc, speedFrameRates[speedCounter]);
        }catch (WallCollisionException | BodyCollisionException | InvalidSizeException e){

        }
    }


    @Override
    public void render(GameContainer gc, StateBasedGame stbgame, Graphics g) throws SlickException {

        Rectangle background = new Rectangle(0,0,Application.WIDTH,Application.HEIGHT);
        g.setColor(Color.decode("#F5F5F5"));
        g.draw(background);
        g.fill(background);

        SnakeHead snake_head = SnakeManager.getInstance().getSnakeHead();
        ArrayDeque<SnakeBody> snakeArray = SnakeManager.getInstance().getSnakeArray();

        for (SnakeBody element : snakeArray) {
            element.drawItem(g);
        }

        snake_head.drawItem(g);


        Rectangle wall = new Rectangle(1,1,Application.WIDTH-1,Application.GAMEHEIGHT-1);
        g.setColor(Color.darkGray);

        g.draw(wall);


        //Draw the good apple
        Food foo = FoodManager.getInstance().getGoodApple();
        if (foo != null){
            foo.drawItem(g);
        }

        // Draw list of other apples
        for(Food food : FoodManager.getInstance().getApples()){

            if (food != null){
                food.drawItem(g);
            }
        }


        g.setColor(Color.darkGray);
        g.drawString("FPS: " + Application.getApp().getAppContainer().getFPS()
                        + "  |  Speed Level: " + speedCounter
                        + "  |  Score: " + getScore()
                        + "  |  Snake size : " + snakeArray.size()
                , Application.WIDTH/16f, Application.GAMEHEIGHT);

    }


    public int getID(){
        return id;
    }

    public static void resetSpeedCounter(){
        speedCounter = 0;
    }

    public static int getSpeedCounter() {
        return speedCounter;
    }

    public static void resetScore() {
        if(score!= null) {
            score.reset();
        }
    }

}