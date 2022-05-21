import greenfoot.*;

public class Gameplay extends World
{
    private long frame = 0;                                                                      // Переменная количества кадров
    protected EnemyMatrix enemyMatrix;                                                           // Матрица врагов
    protected GreenfootImage backgroundImages[];                                                 // Массив изображений фона
    protected Spawner spawner;                                                                   // Контроллер спавна
    public Spaceship player;                                                                     // Ссылка на игрока
    protected int score = 0;                                                                     // Количество очков

    // Пока без отображения
    protected int lives = 3;

    // Переменная для теста спавна
    private boolean isKeyDown = false;
    
    // Конструктор мира
    public Gameplay()
    {    
        super(400, 600, 1);                                                                     // Создание мира 400x600
        prepare();                                                                              // Стандартный метод подготовки объектов в мире
        ShowScore();                                                                            // Отображаем очки на экране
        enemyMatrix = new EnemyMatrix(3, 8, 59, 59, 40);                                        // Инициализация матрицы врагов
        spawner = new Spawner(this, enemyMatrix);
    }

    public void act(){
        BackgroundAnimation();                                                                  // Обрабатывает задний фон мира
        OnSpawn(frame);                                                                         // Обрабатывает логику спавна
        spawner.Act();                                                                          // Обрабатывает систему спавна
        enemyMatrix.Act();                                                                      // Обрабатывает логику матрицы
        ShowScore();
        frame++;
    }
    
    public Spaceship GetPlayer()
    {
        return player;
    }

    // Если будем выдавать разное кол-во очков за пртивников, ?бонусы? и т.д.
    public void AddScore(int score)
    {
        this.score += score;
    }
    
    // Предположим, отнимать будем за смерть
    public void RemoveScore()
    {
        if (score >= 100)
            score -= 100;
        else 
            score = 0;
    }
    
    public void Hit()
    {
        if (lives >=0)
            lives--;
        //Пока без остановки
    }
    
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

    // Стандартный метод подготовки объектов мира
    private void prepare()
    {
        addObject(player = new Spaceship(), 200, 560);
        LoadBackgroundImages();
    }

    // Обработка анимации фона
    private void BackgroundAnimation(){
        int imageIndex = (int) ((frame % 42) / 7);
        setBackground(backgroundImages[imageIndex]);
    }
    
    // Подгрузка изображений фона
    private void LoadBackgroundImages(){
        backgroundImages = new GreenfootImage[6];
        for(int i = 0; i < 6; i++)
        {
            backgroundImages[i] = new GreenfootImage("Background" + (i + 1) + ".png");
        }
    }
    
    // Отображение очков на экране
    private void ShowScore()
    {
        showText("SCORE: "+score,60,10);
    }
}