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
        Spaceship player = (Spaceship) getOneIntersectingObject(Spaceship.class);
        if(player != null && player.Hit())
            getWorld().removeObject(this);
        else if (isAtEdge())
        {
            getWorld().removeObject(this);
        }
    }
}
