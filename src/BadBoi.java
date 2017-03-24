import java.util.ArrayList;

/**
 * Created by michael_hopps on 2/3/17.
 */
public class BadBoi extends Sprite {

    private Sprite target;

    public BadBoi(int x, int y, World world) {
        super(x, y, EAST, world);

        ArrayList<Sprite> sprites = world.getAllSprites();
        target = sprites.get( (int)(Math.random()*sprites.size()) );
        while(target.equals(this) && sprites.size() > 1){
            target = sprites.get( (int)(Math.random()*sprites.size()) );
        }
        setPic("chaser.png", NORTH);
        setSpeed(10);
    }

    @Override
    public void update(){
        int d = getWorld().getDirection(this.getLoc(), target.getLoc());
        setDir(d);

        super.update();
    }

    public Sprite getTarget(){
        return target;
    }
    public void setTarget(Sprite newTarget){
        target = newTarget;
    }

}
