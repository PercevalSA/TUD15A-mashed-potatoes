package snake_game;

public class TasteBad implements TasteBehavior {

    @Override
    public int eat() {
        return 1;
    }

    @Override
    public int getValue() {
        return -1;
    }
}
