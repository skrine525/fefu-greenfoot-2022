import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Gameplay extends World
{
    private int spawnCooldown = 0;																// Переменная интервала между спавнами
    private int spawnType = 0;																	// Перменная вида созданного спавна
    private int spawnCount = 0;																	// Переменная количества итераций спавна

    private EnemyMatrix enemyMatrix;															// Матрица врагов

    // Конструктор мира
    public Gameplay()
    {    
        super(400, 600, 1);																		// Создание мира 400x600
        prepare();																				// Стандартный метод подготовки объектов в мире
        enemyMatrix = new EnemyMatrix(47, 47);													// Инициализация матрицы врагов
    }

    // Стандартный метод подготовки объектов мира
    private void prepare()
    {
        addObject(new Spaceship(), 200, 560);
        EnemyTest enemyTest = new EnemyTest();
        addObject(enemyTest,344,473);
    }

    // Занимается инициализированным спавном
    private void Spawn()
    {
    	if(spawnCooldown <= 0)
        {
        	if(spawnCount > 0)
        	{
        		if(spawnType == 1)
        		{

        			EnemyBasic enemy1 = (EnemyBasic) new EnemyType1(EnemyBasic.State.Enter);
        			EnemyBasic enemy2 = (EnemyBasic) new EnemyType1A(EnemyBasic.State.Enter);
        			enemyMatrix.AddEnemy(enemy1);
        			enemyMatrix.AddEnemy(enemy2);
        			addObject(enemy1, 40, 0);
        			addObject(enemy2, 360, 0);
        			spawnCooldown = 5;
        			spawnCount--;
        		}
        		else
        		{
        			spawnCooldown = 0;
        			spawnType = 0;
        			spawnCount = 0;
        		}
        	}
        	else
        	{
        		spawnCooldown = 0;
        		spawnType = 0;
        	}
        }
        else
        	spawnCooldown--;
    }

    //  Инициализирует спавн определенного вида
    public void CreateSpawn(int type, int count)
    {
    	if(spawnType == 0)
    	{
    		spawnType = type;
    		spawnCount = count;
    	}
    }

    public void act(){
        Spawn();																// Обрабатывает инициализированный спавн
        SpawnTest();															// Тестовый запуск спавна через клавиатуру
    }

    public void SpawnTest()
    {
    	if (Greenfoot.isKeyDown("1"))
        {
        	CreateSpawn(1, 5);
        }
    }
}