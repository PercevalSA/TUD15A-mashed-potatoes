package snake_game;

import java.util.ArrayList;

/**
 * Created by john on 14/03/2017.
 */
public class FoodManager {

    private Food goodApple;
    private ArrayList<Food> apples;

    private static FoodManager uniqueInstance = new FoodManager();

    private FoodManager(){

    }

    public static FoodManager getInstance(){
        return uniqueInstance;
    }

    public Food getGoodApple() {
        return goodApple;
    }

    public void setGoodApple(Food goodApple) {
        this.goodApple = goodApple;
    }

    public ArrayList<Food> getApples() {
        return apples;
    }

    public void setApples(ArrayList<Food> apples) {
        this.apples = apples;
    }
}
