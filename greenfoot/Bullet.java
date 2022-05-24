import greenfoot.*;

public class Bullet extends Actor
{
	final int speed = 15;

    public void act(){
        setLocation(getX(),getY() - speed);
        
        EnemyBasic enemy = (EnemyBasic) getOneIntersectingObject(EnemyBasic.class);
        if(enemy != null && enemy.currentState != EnemyBasic.State.Destroy)
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
