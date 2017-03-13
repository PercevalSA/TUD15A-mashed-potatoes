package snake_game;

/**
 * Created by martin on 13.03.17.
 */
public class Application {
    public static Application instance;

    public static Application getApp() {
        return instance;
    }

    public static void main(){
        instance = new Application();
        
    }
}
