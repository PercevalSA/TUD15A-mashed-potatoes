package snake_game;

import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.util.ArrayDeque;

public class GameViewer extends BasicGameState{

    protected int id;
    //array with different frame rates whit which we updateBodyPosition
    private int[] speedFrameRates = new int[] {10, 6, 5, 3, 2, 1};
    private int[] speedMillisecRates = new int[speedFrameRates.length];
    //alternative way to change the speed - array contains milliseconds
    private int[] speedTimesCheck = new int[] {180, 166, 83 , 75, 65, 55, 45};
    private static int speedCounter = 0;
    private int totalNumberOfFrames;
    private static GameViewer instance = null;

    private GameViewer(){
        this.id = Application.GAMEVIEWER;
        totalNumberOfFrames = 0;
        //transform frame rates to the milliseconds - used to set sleeping time of the thread
        for (int i=0; i<speedFrameRates.length; i++){
            speedMillisecRates[i]  = (int)((1.0/Application.getApp().getFPS()) * speedFrameRates[i] * 1000);
        }
    }

    public static GameViewer getInstance(){
        if(instance == null){
            instance = new GameViewer();
        }
        return instance;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame stbgame) throws SlickException {}

    @Override
    public void update(GameContainer gc, StateBasedGame stbgame, int i) throws SlickException {
        //control speed change rate
        if(++totalNumberOfFrames % 90 == 0 && speedCounter < speedFrameRates.length) {
            if (speedCounter != (speedFrameRates.length-1)) {speedCounter++;}
            totalNumberOfFrames = 0;
        }
        try {
            //GameController.getInstance().updateBodyPosition(gc, speedTimesCheck[speedCounter]);
            GameController.getInstance().updateBodyPosition(gc, speedMillisecRates[speedCounter]);
        }catch (WallCollisionException|BodyCollisionException|InvalidSizeException e){

        }
    }


    @Override
    public void render(GameContainer gc, StateBasedGame stbgame, Graphics g) throws SlickException {

        SnakeHead snake_head = Application.getApp().getSnakeHead();
        ArrayDeque<SnakeBody> snakeArray = Application.getApp().getSnakeArray();

        for (SnakeBody element : snakeArray) {
            element.drawItem(g);
        }

        snake_head.drawItem(g);


        Rectangle wall = new Rectangle(1,1,Application.WIDTH-1,Application.GAMEHEIGHT-1);
        g.setColor(Color.white);
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

        g.setColor(Color.white);
        g.drawString("FPS:" + Application.getApp().getAppContainer().getFPS() + "  Speed Level:" + speedCounter + "  Score:" + snakeArray.size() + "  Position:(" + snake_head.x_position + ", " + snake_head.y_position + ")"
                , Application.WIDTH/5f, Application.GAMEHEIGHT);

    }


    public int getID(){
        return id;
    }

    public static void resetSpeedCounter(){
        speedCounter = 0;
    }

}
