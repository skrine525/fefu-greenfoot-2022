import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyTest extends EnemyBasic
{

    public void act()
    {
        if(Greenfoot.isKeyDown("space"))
        {
        	MouseInfo m = Greenfoot.getMouseInfo();
        	if(m != null){
		        MoveTo(m.getX(), m.getY(), 3);
		        RotateTo((int) GameSystem.GetAngle(getX(), getY(), m.getX(), m.getY()), 5);
		    }
    		getWorld().showText(""+getX() + " " + getY(), 50, 580);
        }
    }
}
