package snake_game;

import org.newdawn.slick.Color;

/**
 * Created by john on 14/03/2017.
 */
public class TasteBad implements TasteBehavior {

    public static final Color taste_color = Color.red;

    @Override
    public int eat() {
        return -1;
    }
}
