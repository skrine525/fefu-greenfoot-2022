import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class test extends Actor
{
	private boolean pressed = false;
    /**
     * Act - do whatever the test wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.isKeyDown("w")){
        	if(!pressed)
        	{
        		move(1);
        		pressed = true;
        	}
        }
        else if (Greenfoot.isKeyDown("s")){
            if(!pressed)
        	{
        		move(-1);
        		pressed = true;
        	}
        }
        else if (Greenfoot.isKeyDown("a")){
            if(!pressed)
        	{
        		turn(-1);
        		pressed = true;
        	}
        }
        else if (Greenfoot.isKeyDown("d")){
            if(!pressed)
        	{
        		turn(1);
        		pressed = true;
        	}
        }
        else
        	pressed = false;

        getWorld().showText("x=" + getX() + " y=" + getY() + " r=" + getRotation() , 100, 20);
    }

    public test()
    {
    	setRotation(45);
    }
}
