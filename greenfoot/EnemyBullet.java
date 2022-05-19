import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyBullet extends Actor
{
    EnemyBullet(int Rot)
    {
        setRotation(Rot);
    }
    public void act()
    {
        move(5);
        if(isTouching(Spaceship.class))
        {
            getWorld().removeObject(this);
            
        }
        else if (isAtEdge())
        {
            getWorld().removeObject(this);
        }
    }
}
