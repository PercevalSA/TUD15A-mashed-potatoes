package Model;

/**
 * Created by john on 16/03/2017.
 */
public class TastePurple implements TasteBehavior {

    @Override
    public int getType() {
        return 4;
    }

    @Override
    public int eat() {
        return 5;
    }

    @Override
    public int getValue() {
        return 0;
    }
}
