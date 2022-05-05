import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Gameplay extends World
{
    private long lastSpawnTime;
    boolean generated;

    
    public Gameplay()
    {    
        super(400, 600, 1); 
        prepare();
        generated = false;
    }
    

    private void prepare()
    {
        addObject(new Spaceship(), 200, 560);
        test test = new test();
        addObject(test,294,382);
    }

    public void act(){
        if(System.currentTimeMillis() - lastSpawnTime >= 2000)
        {
            // lastSpawnTime = System.currentTimeMillis();
            // EnemyBasic enemy = (EnemyBasic) new EnemyType1(EnemyBasic.State.Enter);
            // addObject(enemy, 40, 0);

        }

        if (Greenfoot.isKeyDown("space") && !generated)
        {
        	generated = true;
        	for(int i = 0; i < 5; i++)
        	{
        		for(int j = 0; j < 10; j++)
        		{
        			int xPos = 47 + 34 * j;
        			int yPos = 90 + 34 * i;
	            	EnemyBasic enemy = (EnemyBasic) new EnemyType1(EnemyBasic.State.Stay);
	            	addObject(enemy, xPos, yPos);
        		}
        	}
        }
    }
}
