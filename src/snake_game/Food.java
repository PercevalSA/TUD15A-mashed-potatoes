package snake_game;

/**
 * Created by martin on 13.03.17.
 */
public class Food extends Item {

    private int value = 0;
    private int growth = 0;

    public Food(float x, float y,int value){
        super(x,y);
        this.value = 2*value;
        this.growth = value;
    }

    public int getValue() {
        return value;
    }

    public int getGrowth(){
        return growth;
    }
}
