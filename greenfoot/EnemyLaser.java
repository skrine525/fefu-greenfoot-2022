import greenfoot.*;

public class EnemyLaser extends Actor
{
    private int frame = 0;
    private boolean canHit = true;
    public EnemyType4 owner;
    public GreenfootImage laserPointerImage = new GreenfootImage("Laser/Pointer.png");
    private GreenfootImage laserSmallImage = new GreenfootImage("Laser/Small.png");
    private GreenfootImage laserMiddleImage = new GreenfootImage("Laser/Middle.png");

    public EnemyLaser(EnemyType4 newOwner)
    {
        setImage(laserPointerImage);
        owner = newOwner;
        owner.myCurrLaser = this;
        setRotation(owner.getRotation());
    }

    public void act()
    {   
        if (owner != null)
            if (owner.currentState == EnemyBasic.State.Destroy)
                getWorld().removeObject(this);
            else
                setRotation(owner.getRotation() + 90);
        else
        {
        frame++;
        if (frame == 1)
            setImage(laserSmallImage);
        else if (frame == 6)
        {
            setImage(laserMiddleImage);
        }
        else if (frame <= 51)
        {
            Spaceship player = (Spaceship) getOneIntersectingObject(Spaceship.class);
            if(canHit && player != null && player.Hit())
                canHit = false;
        }
        else if (frame <= 102)
        {
            laserMiddleImage.setTransparency(255-(frame-51) * 5);
        }
        else 
            getWorld().removeObject(this);
        }
    }
}
