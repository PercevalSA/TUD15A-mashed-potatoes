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
        snakeHead = new SnakeHead(Application.WIDTH / 2 , Application.HEIGHT / 2);

        snakeArray.addFirst(new SnakeBody(Application.WIDTH / 2 - 20, Application.HEIGHT / 2));
        snakeArray.addFirst(new SnakeBody(Application.WIDTH / 2 - 20, Application.HEIGHT / 2));
        snakeArray.addFirst(new SnakeBody(Application.WIDTH / 2 - 20, Application.HEIGHT / 2));
        snakeArray.addFirst(new SnakeBody(Application.WIDTH / 2 - 20, Application.HEIGHT / 2));
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
