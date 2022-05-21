import greenfoot.*;

public class Bullet extends Actor
{
	final int speed = 15;

    public void act(){
        setLocation(getX(),getY() - speed);
        
        EnemyBasic enemy = (EnemyBasic) getOneIntersectingObject(EnemyBasic.class);
        if(enemy != null)
        {
            getWorld().removeObject(this);
            enemy.Hit();
        }
        else if (isAtEdge())
        {
            getWorld().removeObject(this);
        }
    }
    
}
