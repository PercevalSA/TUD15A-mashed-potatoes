package simpleslickgame;
        import java.util.logging.Level;
        import java.util.logging.Logger;

        import org.newdawn.slick.*;
        import org.newdawn.slick.geom.Circle;
        import org.newdawn.slick.geom.Shape;
        import org.newdawn.slick.geom.Vector2f;

public class SimpleSlickGame extends    BasicGame
{
    public SimpleSlickGame(String gamename)
    {
        super(gamename);
    }

    private int x = 100;
    private int y = 100;

    @Override
    public void init(GameContainer gc) throws SlickException {}

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

        Input ifinput = gc.getInput();

        if(ifinput.isKeyDown(Input.KEY_UP)) {
            this.y -= 1;
        }

        if(ifinput.isKeyDown(Input.KEY_DOWN)) {
            this.y += 1;
        }

        if(ifinput.isKeyDown(Input.KEY_RIGHT)) {
            this.x += 1;
        }

        if(ifinput.isKeyDown(Input.KEY_LEFT)) {
            this.x -= 1;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.drawString("Howdy!", this.x, this.y);
        g.fill(new Circle(10, 10, 5));
    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
            appgc.setDisplayMode(640, 480, false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}