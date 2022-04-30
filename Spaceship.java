import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Spaceship extends Actor
{
    int cooldown = 0;
    public void act()
    {
        if (Greenfoot.isKeyDown("left"))
        {
            setLocation(getX()-2,getY());
        }
        else if (Greenfoot.isKeyDown("right"))
        {
            setLocation(getX()+2,getY());
        }
        if (Greenfoot.isKeyDown("z"))
        {
            if (cooldown == 0) 
            {
               //Greenfoot.playSound("pew.wav");
               getWorld().addObject(new Bullet(),getX(), getY()-25); 
               cooldown = 20;
            }
        }
        if (cooldown != 0) 
        {
            cooldown--;
        }
    }
}
