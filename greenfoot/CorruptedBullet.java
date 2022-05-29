import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CorruptedBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CorruptedBullet extends Actor
{
    private int fallingDir;
    public CorruptedBullet(int rot)
    {
        fallingDir = Greenfoot.getRandomNumber(91)+45;
        setRotation(rot);
    }

    public void act()
    {
        if ((getRotation() < fallingDir-3) || (getRotation() > fallingDir+3))
        {
            move(4);
            if (fallingDir >= 90)
                turn(-4);
            else
                turn(4);
        }
        else
            move(6);
        Spaceship player = (Spaceship) getOneIntersectingObject(Spaceship.class);
        if(player != null)
        {
            player.Hit();
            getWorld().removeObject(this);
        }
        else if (isAtEdge())
        {
            getWorld().removeObject(this);
        }
    }
}