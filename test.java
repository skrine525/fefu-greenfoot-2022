import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class test extends Actor
{
    /**
     * Act - do whatever the test wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.isKeyDown("e")){
            turn(1);
        }
        else if (Greenfoot.isKeyDown("q")){
            turn(-1);
        }
        else if (Greenfoot.isKeyDown("w")){
            RotateTo(270, 2);
        }
        getWorld().showText(""+getRotation(), 20, 20);
    }

    public void RotateTo(int distRot, int delta){
        distRot = distRot % 360;
        int rot = getRotation();

        if ((distRot >= rot && distRot - rot > 180) || (distRot < rot && rot - distRot < 180))
        	delta = -delta;

        if(Math.abs(distRot - rot) <= Math.abs(delta))
            setRotation(distRot);
        else
            setRotation(rot + delta);
    }
}
