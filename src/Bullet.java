import java.util.ArrayList;

/**
 * Created by jared_poulsen on 3/28/17.
 */
public class Bullet extends Sprite {

    private Sprite target;
    private int counter;

    public Bullet(int x, int y, World world, Sprite target) {
        super(x, y, EAST, world);

        ArrayList<Sprite> sprites = world.getAllSprites();
        this.target = target;
        while(target.equals(this) && sprites.size() > 1){
            target = sprites.get( (int)(Math.random()*sprites.size()) );
        }
        setPic("fire.png",NORTH);
        setSpeed((int)(Math.random() * 8) + 5);
        counter=0;
    }

    @Override
    public void update(){
        counter++;
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
    public int getCounter(){
        return counter;
    }
}
