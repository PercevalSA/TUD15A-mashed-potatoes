package Model;

import Viewer.GameViewer;

public class TasteGood implements TasteBehavior {

    @Override
    public int getType() {
        return 5;
    }

    @Override
    public int eat() {
        return 1;
    }

    @Override
    public int getValue() {
        return GameViewer.getSpeedCounter() + 1;
    }
}
