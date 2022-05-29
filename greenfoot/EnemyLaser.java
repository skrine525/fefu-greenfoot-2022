import greenfoot.*;

public class EnemyLaser extends Actor
{
    private int frame = 0;
    private boolean canHit = true;
    private GreenfootImage laserSmallImage = new GreenfootImage("Laser/Small.png");
    private GreenfootImage laserMiddleImage = new GreenfootImage("Laser/Middle.png");

    public EnemyLaser(int rot)
    {
        setImage(laserSmallImage);
        setRotation(rot);
    }

    public void act()
    {
        frame++;
        if (frame == 5)
        {
            setImage(laserMiddleImage);
        }
        else if (frame <= 50)
        {
            Spaceship player = (Spaceship) getOneIntersectingObject(Spaceship.class);
            if(canHit && player != null)
            {
                player.Hit();
                canHit = false;
            }
        }
        else if (frame <= 150)
        {
            laserMiddleImage.setTransparency(100-(frame-50));
        }
        else 
            getWorld().removeObject(this);
    }
}
