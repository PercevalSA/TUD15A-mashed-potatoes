package Model;

public class TasteBlue implements TasteBehavior {

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public int eat() {
        return 1;
    }

    @Override
    public int getValue() {
        return -1;
    }
}
