package snake_game;

public class Score implements Observer {

    private int score = 0;

    public int getScore() {
        return score;
    }

    public void update(Object colider) {
        Food food = (Food) colider;
        score += food.getValue();
    }

    public void reset() {
        score = 0;
    }


}

