import java.awt.*;

/**
 * Created by dmitry_ilin on 3/21/17.
 */

// bad bois bad bois whacha gonna do? whachha gonna do when they come for you
public class BadBoi extends Sprite{

    public BadBoi(int x, int y, int dir, World world){
        super(x, y, dir, world);
        setPic("BadBoi.png", NORTH);
    }

    @Override
    public void update(){
        //check if this bug is hitting the sides.
        //rotate by -90 or 270

        World w = getWorld();
        if(w.hitBottom(this)){
            rotateBy(-90);
            getLoc().translate(0, -getSpeed());
        }
        if(w.hitTop(this)){
            rotateBy(-90);
            getLoc().translate(0, getSpeed());

        }
        if(w.hitLeftSide(this)){
            rotateBy(-90);
            getLoc().translate(getSpeed(), 0);

        }
        if(w.hitRightSide(this)){
            rotateBy(-90);
            getLoc().translate(-getSpeed(), 0);

        }
        super.update();


    }




}

