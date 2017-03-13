package snake_game;

/**
 * Created by martin on 13.03.17.
 */
public class Item {

    protected float x_position;
    protected float y_position;

    public Item(float x, float y){
        this.x_position = x;
        this.y_position = y;
    }

    public float getX(){
        return x_position;
    }

    public float getY(){
        return y_position;
    }
}
