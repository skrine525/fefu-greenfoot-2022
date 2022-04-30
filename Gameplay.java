import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Gameplay extends World
{
	private long lastTime, lastUpdateTime;
    
    public Gameplay()
    {    
        super(400, 600, 1); 
        prepare();
        lastTime = System.currentTimeMillis();
    }
    

    private void prepare()
    {
        addObject(new Spaceship(), 200, 560);
    }

    public void act(){
    	float deltaTime = ((float) (System.currentTimeMillis() - lastTime)) / 1000;
    	lastTime = System.currentTimeMillis();
    	if (System.currentTimeMillis() - lastUpdateTime >= 100)
    	{
    		showText(""+((int) (1/deltaTime)), 90, 25);
    		lastUpdateTime = System.currentTimeMillis();
    	}
    }
}
