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
        //check if this bug is hitting the sides
        //rotate by -90 or 270

        World w = getWorld();
        if (w.hitBottom(this)) {
            rotateBy(-90);
            getLoc().translate(0, -getSpeed());
        }
        if (w.hitTop(this)) {
            rotateBy(-90);
            getLoc().translate(0, getSpeed());

        }
        if (w.hitLeftSide(this)) {
            rotateBy(-90);
            getLoc().translate(getSpeed(), 0);

        }
        if (w.hitRightSide(this)) {
            rotateBy(-90);
            getLoc().translate(-getSpeed(), 0);

        }
        super.update();
    }


}
