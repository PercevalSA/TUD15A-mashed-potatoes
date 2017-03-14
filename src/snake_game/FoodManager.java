package snake_game;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by john on 14/03/2017.
 */
public class FoodManager implements Runnable{

    private static final int MAXAPPLES = 6;
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

    @Override
    public void run() {
        while(true) {
            Random rand = new Random();
            int time = rand.nextInt(10) + 5;
            try {
                Thread.sleep(time * 1000);
            } catch (InterruptedException e) {
                System.out.println("Error while trying to put the thread to sleep");
            }

            if(apples.size() < MAXAPPLES) {
                float x_position = rand.nextFloat() * (Application.WIDTH - 2 * Application.ITEMSIZE) + Application.ITEMSIZE;
                float y_position = rand.nextFloat() * (Application.GAMEHEIGHT - 2 * Application.ITEMSIZE) + Application.ITEMSIZE;
                apples.add(new Food(x_position, y_position, false));
            }
        }
    }
}
