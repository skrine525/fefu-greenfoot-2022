import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Gameplay extends World
{
    private int spawnCooldown = 0;                                                                // Переменная интервала между спавнами
    private int spawnType = 0;                                                                    // Перменная вида созданного спавна
    private int spawnCount = 0;                                                                    // Переменная количества итераций спавна

    private EnemyMatrix enemyMatrix;                                                            // Матрица врагов
    public static Gameplay instance;                                                            // Синглтон мира

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
                    EnemyBasic enemy1, enemy2;

                    if(Greenfoot.getRandomNumber(100) <= 49)
                        enemy1 = (EnemyBasic) new EnemyType1(EnemyBasic.State.Enter);
                    else
                        enemy1 = (EnemyBasic) new EnemyType2(EnemyBasic.State.Enter);

                    if(Greenfoot.getRandomNumber(100) <= 49)
                        enemy2 = (EnemyBasic) new EnemyType1A(EnemyBasic.State.Enter);
                    else
                        enemy2 = (EnemyBasic) new EnemyType2A(EnemyBasic.State.Enter);


                    enemyMatrix.AddEnemy(enemy1);
                    enemyMatrix.AddEnemy(enemy2);
                    addObject(enemy1, 40, 0);
                    addObject(enemy2, 360, 0);
                    spawnCooldown = 5;
                    spawnCount--;
                }
                else if(spawnType == 2)
                {
                    EnemyBasic enemy1, enemy2;
                    
                    if(Greenfoot.getRandomNumber(100) <= 49)
                        enemy1 = (EnemyBasic) new EnemyType1B(EnemyBasic.State.Enter);
                    else
                        enemy1 = (EnemyBasic) new EnemyType2B(EnemyBasic.State.Enter);

                    if(Greenfoot.getRandomNumber(100) <= 49)
                        enemy2 = (EnemyBasic) new EnemyType1C(EnemyBasic.State.Enter);
                    else
                        enemy2 = (EnemyBasic) new EnemyType2C(EnemyBasic.State.Enter);

                    enemyMatrix.AddEnemy(enemy1);
                    enemyMatrix.AddEnemy(enemy2);
                    addObject(enemy1, 0, 50);
                    addObject(enemy2, 399, 50);
                    spawnCooldown = 5;
                    spawnCount--;
                }
                else if(spawnType == 3)
                {
                    EnemyBasic enemy1, enemy2;
                    
                    if(Greenfoot.getRandomNumber(100) <= 49)
                        enemy1 = (EnemyBasic) new EnemyType1D(EnemyBasic.State.Enter);
                    else
                        enemy1 = (EnemyBasic) new EnemyType2D(EnemyBasic.State.Enter);

                    if(Greenfoot.getRandomNumber(100) <= 49)
                        enemy2 = (EnemyBasic) new EnemyType1E(EnemyBasic.State.Enter);
                    else
                        enemy2 = (EnemyBasic) new EnemyType2E(EnemyBasic.State.Enter);

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