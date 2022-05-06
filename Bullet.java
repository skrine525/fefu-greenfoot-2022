import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Bullet extends Actor
{
    public void act(){
        setLocation(getX(),getY() - 8);
        
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
