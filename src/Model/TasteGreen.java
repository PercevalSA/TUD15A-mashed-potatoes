package Model;

import Viewer.GameViewer;

/**
 * Created by john on 16/03/2017.
 */
public class TasteGreen implements TasteBehavior {

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public int eat() {
        return 0;
    }

    @Override
    public int getValue() {
        return 2 * (GameViewer.getSpeedCounter() + 1);
    }
}
