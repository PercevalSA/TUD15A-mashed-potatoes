package snake_game;

public class Food extends Item {

    private int value = 0;

    public Food(float x, float y,int value){
        super(x,y);
        this.value = 2*value;
    }

    public int getValue() {
        return value;
    }
}
