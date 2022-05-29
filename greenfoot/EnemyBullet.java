import greenfoot.*;

public class EnemyBullet extends Actor
{
    public EnemyBullet(int rot)
    {
        setRotation(rot);
    }

    public void act()
    {
        move(5);
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
