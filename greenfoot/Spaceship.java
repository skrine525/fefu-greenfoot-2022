import greenfoot.*;

public class Spaceship extends Actor
{
    private int shootCooldown = 0;                                                    // Количество кадров до следующего выстрела
    private int hitpoints = 3;                                                        // Количество жизней
    
    public static final int hitpointsMax = 3;

    public Spaceship()
    {
      super();
    }

    public void act()
    {
       Shoot();
       Control();
    }

    public void Hit()
    {
      hitpoints = Math.max(hitpoints - 1, 0);
      ((Gameplay) getWorld()).ShowPlayerHP(hitpoints);
    }

    private void Shoot(){
       if (Greenfoot.isKeyDown("z"))
       {
            if (shootCooldown == 0)
            {
               //Greenfoot.playSound("pew.wav");
               getWorld().addObject(new Bullet(), getX(), getY() - 25);
            }
        }   
       Cooldown();
    }

    private void Cooldown(){
       if (shootCooldown != 0)
       {
            shootCooldown--;
        } 
       else shootCooldown = 10;
    }

    public void Control(){
       if (Greenfoot.isKeyDown("left"))
       {
            if (getX() > 25)
            setLocation(getX() - 3, getY());
        }
        else if (Greenfoot.isKeyDown("right"))
        {
            if (getX() < 375)
            setLocation(getX() + 3, getY());
        } 
    }
}
