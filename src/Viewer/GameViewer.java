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

public class GameViewer extends BasicGameState implements KeyListener {

    protected int id;

    //array with different frame rates whit which we updateBodyPosition
    private int[] speedFrameRates = new int[] {11, 8, 6, 5, 4, 3, 2};
    private static int speedCounter = 0;
    private int totalNumberOfFrames;

    public GameViewer(int id) {
        this.id = id;
        totalNumberOfFrames = 0;

        this.resetGame();
        
    }

   public void resetGame() {
        GameViewer.resetScore();
        GameViewer.resetSpeedCounter();
        FoodManager.getInstance().initializeFoods();
        SnakeManager.getInstance().initializeSnake();
    }


    @Override
    public void init(GameContainer gc, StateBasedGame stbgame) throws SlickException {}

    @Override
    public void update(GameContainer gc, StateBasedGame stbgame, int i) throws SlickException {
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

        // infos toolbar
        g.setColor(Color.black);
        g.drawString("FPS: " + Application.getApp().getAppContainer().getFPS()
                        + "  |  Speed Level: " + (speedCounter + 1)
                        + "  |  Score: " + GameController.getScore()
                        + "  |  Snake size : " + snakeArray.size()
                , Application.WIDTH/16f, Application.GAMEHEIGHT);

    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        int state = Application.getApp().getCurrentStateId();

        if(key == Input.KEY_SPACE && state == Application.GAMEVIEWER) {
            Pause pause = new Pause(Application.PAUSE);
            Application.getApp().addState(pause);
            Application.getApp().enterState(pause.getID());

        }
    }

    public int getID() {
        return id;
    }

    public static void resetSpeedCounter() {
        speedCounter = 0;
    }

    public static int getSpeedCounter() {
        return speedCounter;
    }

    public static void resetScore() {
        GameController.resetScore();
    }

}
