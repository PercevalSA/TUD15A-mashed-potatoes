package Model;

import java.util.ArrayDeque;

/**
 * Created by john on 16/03/2017.
 */
public class SnakeManager {


    private SnakeHead snakeHead = null;
    private ArrayDeque<SnakeBody> snakeArray= new ArrayDeque<>();
    private static SnakeManager uniqueInstance = new SnakeManager();

    public static SnakeManager getInstance(){
        return uniqueInstance;
    }

    public void initializeSnake(){
        snakeArray.clear();
        int snake_x = ((int)Application.WIDTH / (2*Application.ITEMSIZE)) * Application.ITEMSIZE ;
        int snake_y = ((int)Application.HEIGHT / (2*Application.ITEMSIZE)) * Application.ITEMSIZE;
        snakeHead = new SnakeHead(snake_x, snake_y);

        snakeArray.addFirst(new SnakeBody(snake_x - Application.ITEMSIZE, snake_y));
        snakeArray.addFirst(new SnakeBody(snake_x - Application.ITEMSIZE, snake_y));
        snakeArray.addFirst(new SnakeBody(snake_x - Application.ITEMSIZE, snake_y));
        snakeArray.addFirst(new SnakeBody(snake_x - Application.ITEMSIZE, snake_y));
    }

    public SnakeHead getSnakeHead() {
        return snakeHead;
    }

    public ArrayDeque<SnakeBody> getSnakeArray() {
        return snakeArray;
    }

    public void setSnakeArray(ArrayDeque<SnakeBody> snakeArray) {
        this.snakeArray = snakeArray;
    }

}
