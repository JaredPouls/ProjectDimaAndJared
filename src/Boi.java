import java.awt.*;
import java.util.ArrayList;
/**
 * Created by dmitry_ilin on 3/21/17.
 */
public class Boi extends Sprite {

    public Boi(int x, int y, int dir, World world){
        super(x, y, dir, world);
        setPic("Boi.png", NORTH);
        setLoc(new Point(100,100));
    }

    @Override
    public void update() {
        super.update();
    }


}
