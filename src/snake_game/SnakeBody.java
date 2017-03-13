package snake_game;

/**
 * Created by martin on 13.03.17.
 */
public class SnakeBody extends Item {

    public SnakeBody(float x,float y){
        super(x,y);
    }
    
    /*
    Updates the Time of the Body and Informs the Caller if still alive
    @Return Boolean saying if it should exist in this cycle.
     */
    /*
    public boolean updateTime(){
        //SnakeHead snake = Application.getApp().getSnake();
        if( ++time >= snake.getSize()) {
            return false;
        }
        return true;

    }*/

    public void updateBody( float x, float y){
        x_position = x;
        y_position =y;
    }
}