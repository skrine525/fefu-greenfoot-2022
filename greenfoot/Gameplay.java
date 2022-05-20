import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Gameplay extends World
{
    private long frame = 0;                                                                      // Переменная количества кадров
    protected EnemyMatrix enemyMatrix;                                                              // Матрица врагов
    protected GreenfootImage backgroundImages[];                                                    // Массив изображений фона
    protected SpawnController spawnController;                                                      // Контроллер спавна

    // Переменная для теста спавна
    private boolean isKeyDown = false;


    // Конструктор мира
    public Gameplay()
    {    
        super(400, 600, 1);                                                                     // Создание мира 400x600
        prepare();                                                                              // Стандартный метод подготовки объектов в мире
        enemyMatrix = new EnemyMatrix(3, 8, 59, 59, 40);                                        // Инициализация матрицы врагов
        spawnController = new SpawnController(this, enemyMatrix);
    }

    public void act(){
        BackgroundAnimation();                                                  // Обрабатывает задний фон мира
        OnSpawn(frame);                                                      // Обрабатывает логику спавна
        spawnController.Act();                                                  // Обрабатывает систему спавна
        enemyMatrix.Act();                                                      // Обрабатывает логику матрицы

        frame++;
    }

    protected void OnSpawn(long frame)
    {
        // Логика тестирования спавна
        if (Greenfoot.isKeyDown("1"))
        {
            if(!isKeyDown){
                spawnController.StartSpawn(SpawnController.SpawnType.Type1Left, 5, 8);
                spawnController.StartSpawn(SpawnController.SpawnType.Type1Right, 5, 8);
                isKeyDown = true;
            }
        }
        else if (Greenfoot.isKeyDown("2"))
        {
            if(!isKeyDown){
                spawnController.StartSpawn(SpawnController.SpawnType.Type2Left, 4, 8);
                spawnController.StartSpawn(SpawnController.SpawnType.Type2Right, 4, 8);
                isKeyDown = true;
            }
        }
        else if (Greenfoot.isKeyDown("3"))
        {
            if(!isKeyDown){
                spawnController.StartSpawn(SpawnController.SpawnType.Type3Left, 4, 8);
                spawnController.StartSpawn(SpawnController.SpawnType.Type3Right, 4, 8);
                isKeyDown = true;
            }
        }
        else
            isKeyDown = false;
    }

    // Стандартный метод подготовки объектов мира
    private void prepare()
    {
        addObject(new Spaceship(), 200, 560);
        LoadBackgroundImages();
    }

    // Обработка анимации фона
    private void BackgroundAnimation(){
        int imageIndex = (int) (frame / 7);
        if(imageIndex < 6)
            setBackground(backgroundImages[imageIndex]);
        else
            frame = 0;
    }

    // Подгрузка изображений фона
    private void LoadBackgroundImages(){
        backgroundImages = new GreenfootImage[6];
        for(int i = 0; i < 6; i++)
        {
            backgroundImages[i] = new GreenfootImage("Background" + (i + 1) + ".png");
        }
    }
}