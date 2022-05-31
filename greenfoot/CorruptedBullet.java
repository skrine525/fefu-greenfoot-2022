import greenfoot.*;

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

        Spaceship.Hitbox spaceshipHitbox = (Spaceship.Hitbox) getOneIntersectingObject(Spaceship.Hitbox.class);
        if(spaceshipHitbox != null && spaceshipHitbox.GetSpaceship().Hit())
            getWorld().removeObject(this);
        else if (isAtEdge())
        {
            getWorld().removeObject(this);
        }
    }
}
