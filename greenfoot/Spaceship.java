import greenfoot.*;

public class Spaceship extends Actor
{
    private int cooldown = 0;
    public void act(){
       Shoot();
       Control();
    }
    private void Shoot(){
       if (Greenfoot.isKeyDown("z")){
            if (cooldown == 0){
               //Greenfoot.playSound("pew.wav");
               getWorld().addObject(new Bullet(), getX(), getY() - 25); 
               
            }
        }   
       Cooldown();
    }
    private void Cooldown(){
       if (cooldown != 0){
            cooldown--;
        } 
       else cooldown = 10;
    }
    public void Control(){
       if (Greenfoot.isKeyDown("left")){
            if (getX() > 25)
            setLocation(getX() - 2, getY());
        }
        else if (Greenfoot.isKeyDown("right")){
            if (getX() < 375)
            setLocation(getX() + 2, getY());
        } 
    }
}
