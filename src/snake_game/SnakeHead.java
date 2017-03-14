package snake_game;

public class SnakeHead extends Item{
    private int direction = 1;

    public SnakeHead(float x, float y){
        super(x,y);
    }

    public int getDirection(){
        return direction;
    }

    public void updateDirection(int dir) throws InvalidMoveException {
        if(dir%2 == direction%2){
            throw new InvalidMoveException();
        } else {
            direction = dir;
        }

    }

    public void updateCoord (int speed) throws WallCollisionException {
        float y_temp = y_position;
        float x_temp = x_position;

        switch(direction) {
            case 0:
                y_temp -= speed;
                break;
            case 1:
                x_temp += speed;
                break;
            case 2:
                y_temp += speed;
                break;
            case 3:
                x_temp -= speed;
                break;
        }

        if(checkWallCollision(x_temp, y_temp))
            throw new WallCollisionException();

        x_position = x_temp;
        y_position = y_temp;
    }

    private boolean checkWallCollision(float x_temp, float y_temp) {
        int GAMEHEIGHT = Application.getHEIGHT();
        int GAMEWIDTH = Application.getWIDTH();
        if(x_temp <= 0 || x_temp + GameViewer.ITEMSIZE >= GAMEWIDTH || y_temp <= 0 || y_temp + GameViewer.ITEMSIZE >= GAMEHEIGHT)
            return true;
        return false;
    }
}
