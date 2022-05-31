import greenfoot.*;
public class Meds extends Actor
{
	private static final int SPEED = 5;					// Скорость падения

    public void act()
    {
        setLocation(getX(), getY() + SPEED);

        Spaceship.Hitbox spaceshipHitbox = (Spaceship.Hitbox) getOneIntersectingObject(Spaceship.Hitbox.class);
        if(spaceshipHitbox != null)
        {
        	spaceshipHitbox.GetSpaceship().Heal();
        	Greenfoot.playSound("MedsOk.wav");
            getWorld().removeObject(this);
        }
        else if(isTouching(EnemyBasic.class))
        {
        	Greenfoot.playSound("MedsFail.wav");
        	getWorld().removeObject(this);
        }
        else if (isAtEdge())
        {
            getWorld().removeObject(this);
        }
    }
}
