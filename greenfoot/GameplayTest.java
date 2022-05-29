import greenfoot.*;

public class GameplayTest extends Gameplay
{
    // Переменная для теста спавна
    private boolean isKeyDown = false;

    public GameplayTest()
    {
    	super();

    	spawner.chanceEnemyType1 = 0;
    	spawner.chanceEnemyType2 = 0;
    	spawner.chanceEnemyType3 = 0;
    	spawner.chanceEnemyType4 = 100;
    }

    @Override
    protected void OnSpawn(long frame)
    {
    	// Логика тестирования спавна
        if (Greenfoot.isKeyDown("1"))
        {
            if(!isKeyDown){
                spawner.StartSpawn(Spawner.SpawnType.Type1Left, 5, 8, true);
                spawner.StartSpawn(Spawner.SpawnType.Type1Right, 5, 8, true);
                isKeyDown = true;
            }
        }
        else if (Greenfoot.isKeyDown("2"))
        {
            if(!isKeyDown){
                spawner.StartSpawn(Spawner.SpawnType.Type2Left, 4, 8, true);
                spawner.StartSpawn(Spawner.SpawnType.Type2Right, 4, 8, true);
                isKeyDown = true;
            }
        }
        else if (Greenfoot.isKeyDown("3"))
        {
            if(!isKeyDown){
                spawner.StartSpawn(Spawner.SpawnType.Type3Left, 4, 8, true);
                spawner.StartSpawn(Spawner.SpawnType.Type3Right, 4, 8, true);
                isKeyDown = true;
            }
        }
        else
            isKeyDown = false;
    }
}
