import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Gameplay extends World
{
    private int spawnCooldown = 0;                                                                // Переменная интервала между спавнами
    private int spawnType = 0;                                                                    // Перменная вида созданного спавна
    private int spawnCount = 0;                                                                    // Переменная количества итераций спавна
    private long animCount = 0;                                                                    //Переменная для анимации фона
    private EnemyMatrix enemyMatrix;                                                            // Матрица врагов
    public static Gameplay instance;  
    private GreenfootImage backImages[];
    // Синглтон мира
    // Конструктор мира
    public Gameplay()
    {    
        super(400, 600, 1);                                                                        // Создание мира 400x600
        prepare();                                                                              // Стандартный метод подготовки объектов в мире
        enemyMatrix = new EnemyMatrix(3, 8, 59, 59, 40);                                        // Инициализация матрицы врагов
        instance = this;
    }

    // Стандартный метод подготовки объектов мира
    private void prepare()
    {
        addObject(new Spaceship(), 200, 560);
        BackLoad();
        setBackground("Background1.png");
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
                    EnemySpawner spawner = new EnemySpawner(4, 100);
                    spawner.AddEnemyClass(EnemyType1.class, 35);
                    spawner.AddEnemyClass(EnemyType2.class, 35);
                    spawner.AddEnemyClass(EnemyType3.class, 20);
                    spawner.AddEnemyClass(EnemyType4.class, 10);
                    EnemyBasic enemy1 = spawner.Spawn();
                    enemy1.currentState = EnemyBasic.State.Enter;

                    spawner = new EnemySpawner(4, 100);
                    spawner.AddEnemyClass(EnemyType1A.class, 35);
                    spawner.AddEnemyClass(EnemyType2A.class, 35);
                    spawner.AddEnemyClass(EnemyType3A.class, 20);
                    spawner.AddEnemyClass(EnemyType4A.class, 10);
                    EnemyBasic enemy2 = spawner.Spawn();
                    enemy2.currentState = EnemyBasic.State.Enter;

                    enemyMatrix.AddEnemy(enemy1);
                    enemyMatrix.AddEnemy(enemy2);
                    addObject(enemy1, 40, 0);
                    addObject(enemy2, 360, 0);
                    spawnCooldown = 5;
                    spawnCount--;
                }
                else if(spawnType == 2)
                {
                    EnemySpawner spawner = new EnemySpawner(4, 100);
                    spawner.AddEnemyClass(EnemyType1B.class, 35);
                    spawner.AddEnemyClass(EnemyType2B.class, 35);
                    spawner.AddEnemyClass(EnemyType3B.class, 20);
                    spawner.AddEnemyClass(EnemyType4B.class, 10);
                    EnemyBasic enemy1 = spawner.Spawn();
                    enemy1.currentState = EnemyBasic.State.Enter;

                    spawner = new EnemySpawner(4, 100);
                    spawner.AddEnemyClass(EnemyType1C.class, 35);
                    spawner.AddEnemyClass(EnemyType2C.class, 35);
                    spawner.AddEnemyClass(EnemyType3C.class, 20);
                    spawner.AddEnemyClass(EnemyType4C.class, 10);
                    EnemyBasic enemy2 = spawner.Spawn();
                    enemy2.currentState = EnemyBasic.State.Enter;

                    enemyMatrix.AddEnemy(enemy1);
                    enemyMatrix.AddEnemy(enemy2);
                    addObject(enemy1, 0, 50);
                    addObject(enemy2, 399, 50);
                    spawnCooldown = 5;
                    spawnCount--;
                }
                else if(spawnType == 3)
                {
                    EnemySpawner spawner = new EnemySpawner(4, 100);
                    spawner.AddEnemyClass(EnemyType1D.class, 35);
                    spawner.AddEnemyClass(EnemyType2D.class, 35);
                    spawner.AddEnemyClass(EnemyType3D.class, 20);
                    spawner.AddEnemyClass(EnemyType4D.class, 10);
                    EnemyBasic enemy1 = spawner.Spawn();
                    enemy1.currentState = EnemyBasic.State.Enter;

                    spawner = new EnemySpawner(4, 100);
                    spawner.AddEnemyClass(EnemyType1E.class, 35);
                    spawner.AddEnemyClass(EnemyType2E.class, 35);
                    spawner.AddEnemyClass(EnemyType3E.class, 20);
                    spawner.AddEnemyClass(EnemyType4E.class, 10);
                    EnemyBasic enemy2 = spawner.Spawn();
                    enemy2.currentState = EnemyBasic.State.Enter;

                     enemyMatrix.AddEnemy(enemy1);
                     enemyMatrix.AddEnemy(enemy2);
                     addObject(enemy1, 0, 420);
                     addObject(enemy2, 399, 420);
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
        Spawn();                                                                // Обрабатывает инициализированный спавн
        SpawnTest();                                                            // Тестовый запуск спавна через клавиатуру
        enemyMatrix.Act();                                                      // Обрабатывает логику матрицы
        BackAnimation();
    }
    public void BackAnimation(){
        int imageIndex = (int) (animCount / 7);
        animCount++;
        if(imageIndex < 6)
            setBackground(backImages[imageIndex]);
        else animCount = 0;
    }
    public void BackLoad(){
        backImages = new GreenfootImage[6];
        for(int i = 0; i < 6; i++)
        {
            backImages[i] = new GreenfootImage("Background" + (i + 1) + ".png");
        }
    }
    public void SpawnTest()
    {
        if (Greenfoot.isKeyDown("1"))
        {
            CreateSpawn(1, 5);
        }
        else if (Greenfoot.isKeyDown("2"))
        {
            CreateSpawn(2, 4);
        }
        else if (Greenfoot.isKeyDown("3"))
        {
            CreateSpawn(3, 4);
        }
    }
}