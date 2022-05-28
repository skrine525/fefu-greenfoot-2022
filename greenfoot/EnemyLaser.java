import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyLaser extends Actor
{
    private int frame = 0;
    GreenfootImage laserS = new GreenfootImage("lasersmall.png");
    GreenfootImage laserM = new GreenfootImage("lasermiddle.png");
    EnemyLaser(int rot)
    {
        setImage(laserS);
        setRotation(rot);
    }
    public void act()
    {
        frame++;
        if (frame == 5)
        {
            setImage(laserM);
        }
        else if (frame <= 50)
        {
            if (isTouching(Spaceship.class))
            {
                // ХП пока нет
            }
        }
        else if (frame <= 150)
        {
            laserM.setTransparency(100-(frame-50));
        }
        else 
            getWorld().removeObject(this);
    }
}
