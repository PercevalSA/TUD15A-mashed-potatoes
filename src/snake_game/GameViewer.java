package snake_game;

import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.util.ArrayDeque;

public class GameViewer extends BasicGameState{

    protected int id;
    protected Application app;
    private int[] speedTimes = new int[] {10, 5 , 3};
    private int[] delays = new int[speedTimes.length];
    private int[] speedTimesCheck = new int[] {200, 166, 83 , 65, 45};
    private static int speedCounter = 0;
    private int totalNumberOfFrames;
    private static GameViewer instance = null;

    private GameViewer(){
        this.id = Application.GAMEVIEWER;
        this.app = Application.getApp();
        totalNumberOfFrames = 0;
        for (int i=0; i<speedTimes.length; i++){
            delays[i]  = (int)((1.0/app.getFPS()) * speedTimes[speedCounter] * 1000);
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
        if(++totalNumberOfFrames % 60 == 0 && speedCounter < speedTimesCheck.length) {
            if (speedCounter != (speedTimesCheck.length-1)) {speedCounter++;}
            totalNumberOfFrames = 0;
        }
        try {
            GameController.getInstance().updateBodyPosition(gc, speedTimesCheck[speedCounter]);
            //Application.getApp().getGameController().updateBodyPosition(gc, delays[speedCounter]);
        }catch (WallCollisionException|BodyCollisionException|InvalidSizeException e){

        }
    }


    @Override
    public void render(GameContainer gc, StateBasedGame stbgame, Graphics g) throws SlickException {

        SnakeHead snake_head = app.getSnakeHead();
        ArrayDeque<SnakeBody> snakeArray = app.getSnakeArray();

        for (SnakeBody element : snakeArray) {
            element.drawItem(g);
        }

        snake_head.drawItem(g);


        Rectangle wall = new Rectangle(1,1,app.WIDTH-1,app.GAMEHEIGHT-1);
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
        g.drawString("FPS:" + app.getAppContainer().getFPS() + "  Speed Level:" + speedCounter + "  Score:" + snakeArray.size() + "  Position:(" + snake_head.x_position + ", " + snake_head.y_position + ")"
                , app.WIDTH/5f, app.GAMEHEIGHT);

    }


    public int getID(){
        return id;
    }

    public static void resetSpeedCounter(){
        speedCounter = 0;
    }

}
