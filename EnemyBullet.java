import greenfoot.*;

public class EnemyBullet extends Actor
{
    private int speed;
    public EnemyBullet(int rot, int newSpeed)
    {
        speed = newSpeed;
        setRotation(rot);
    }

    public void act()
    {
        move(speed);

        Spaceship.Hitbox spaceshipHitbox = (Spaceship.Hitbox) getOneIntersectingObject(Spaceship.Hitbox.class);
        if(spaceshipHitbox != null && spaceshipHitbox.GetSpaceship().Hit())
            getWorld().removeObject(this);
        else if (isAtEdge())
        {
            getWorld().removeObject(this);
        }
    }
}
