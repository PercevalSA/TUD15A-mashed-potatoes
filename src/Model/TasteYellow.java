package Model;

/**
 * Created by john on 16/03/2017.
 */
public class TasteYellow implements TasteBehavior {

    @Override
    public int getType() {
        return 3;
    }

    @Override
    public int eat() {
        return 0;
    }

    @Override
    public int getValue() {
        return 5;
    }
}
