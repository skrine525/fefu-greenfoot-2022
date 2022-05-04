import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Gameplay extends World
{
	private long lastSpawnTime;

    
    public Gameplay()
    {    
        super(400, 600, 1); 
        prepare();
    }
    

    private void prepare()
    {
        addObject(new Spaceship(), 200, 560);
    }

    public void act(){
    	if(System.currentTimeMillis() - lastSpawnTime >= 2000)
    	{
    		lastSpawnTime = System.currentTimeMillis();
    		EnemyBasic enemy = (EnemyBasic) new EnemyType1(EnemyBasic.State.Enter);
    		addObject(enemy, 40, 0);
    	}
    }
}
