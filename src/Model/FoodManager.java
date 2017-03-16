package Model;

import java.util.ArrayList;
import java.util.Random;

public class FoodManager implements Runnable{

    private static final int MAXAPPLES = 6;
    private Food goodApple;
    private ArrayList<Food> apples = new ArrayList<>();

    private static FoodManager uniqueInstance = new FoodManager();

    private FoodManager(){
    }

    public static FoodManager getInstance(){
        return uniqueInstance;
    }

    public synchronized Food getGoodApple() {
        return goodApple;
    }

    public synchronized void setGoodApple(Food goodApple) {
        this.goodApple = goodApple;
    }

    public synchronized ArrayList<Food> getApples() {
        return apples;
    }

    public synchronized void setApples(ArrayList<Food> apples) {
        this.apples = apples;
    }

    public synchronized void mooveGoodApple(){
        Random rand = new Random();
        float x_position = rand.nextFloat()*(Application.WIDTH- 2 * Application.ITEMSIZE) + Application.ITEMSIZE;
        float y_position = rand.nextFloat()*(Application.GAMEHEIGHT- 2 * Application.ITEMSIZE) + Application.ITEMSIZE;

        goodApple.setX(x_position);
        goodApple.setY(y_position);
    }

    public Food createGoodFood(){
        Random rand = new Random();
        float x_position = rand.nextFloat()*(Application.WIDTH- 2 * Application.ITEMSIZE) + Application.ITEMSIZE;
        float y_position = rand.nextFloat()*(Application.GAMEHEIGHT- 2 * Application.ITEMSIZE) + Application.ITEMSIZE;

        return new Food(x_position, y_position, true);
    }

    public Food createBadFood(){
        Random rand = new Random();
        float x_position = rand.nextFloat()*(Application.WIDTH- 2 * Application.ITEMSIZE) + Application.ITEMSIZE;
        float y_position = rand.nextFloat()*(Application.GAMEHEIGHT- 2 * Application.ITEMSIZE) + Application.ITEMSIZE;

        return new Food(x_position, y_position, false);
    }

    public void initializeFoods(){
        goodApple = createGoodFood();
        apples.clear();
        apples.add(createBadFood());
        apples.add(createBadFood());
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

            if (apples.size() < MAXAPPLES && Application.getApp().getCurrentStateId() == Application.GAMEVIEWER)
                apples.add(createBadFood());
        }
    }
}
