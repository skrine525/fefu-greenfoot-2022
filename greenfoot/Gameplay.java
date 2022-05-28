import greenfoot.*;

public class Gameplay extends World
{
    private long frame = 0;                                                                      // Переменная количества кадров
    protected EnemyMatrix enemyMatrix;                                                           // Матрица врагов
    protected GreenfootImage backgroundImages[];                                                 // Массив изображений фона
    protected Spawner spawner;                                                                   // Контроллер спавна
    public Spaceship player;                                                                     // Ссылка на игрока
    protected int score = 0;                                                                     // Количество очков
    private ScoreLabel scoreLabel;                                                               // Метка счетчика очков
    private HitpointLabel hitpointLabel;                                                         // Метка жизней игрока
    
    // Конструктор мира
    public Gameplay()
    {    
        super(400, 600, 1);                                                                     // Создание мира 400x600
        prepare();                                                                              // Стандартный метод подготовки объектов в мире

        // Очередь отрисовки объектов
        setPaintOrder(ScoreLabel.class, HitpointLabel.class);

        LoadBackgroundImages();                                                                 // Подгружаем изображения фона
        setBackground(backgroundImages[0]);                                                     // Устанавливаем фон мира
        enemyMatrix = new EnemyMatrix(3, 8, 59, 59, 40);                                        // Инициализация матрицы врагов
        spawner = new Spawner(this, enemyMatrix);                                               // Инициализация спавнера

        scoreLabel = new ScoreLabel(8);                                                         // Инициализируем метку очков
        addObject(scoreLabel, 50, 10);                                                          // Добавляем метку очков в мир
        ShowScore();                                                                            // Отображаем очки на экране

        hitpointLabel = new HitpointLabel(Spaceship.hitpointsMax);                              // Инициализируем метку жизней
        addObject(hitpointLabel, 359, 10);                                                      // Добавляем метку жизней в мир
    }

    public void act(){
        BackgroundAnimation();                                                                  // Обрабатывает задний фон мира
        OnSpawn(frame);                                                                         // Обрабатывает логику спавна
        spawner.Act();                                                                          // Обрабатывает систему спавна
        enemyMatrix.Act();                                                                      // Обрабатывает логику матрицы
        ShowScore();
        frame++;
    }
    
    // Возвращает объект игрока
    public Spaceship GetPlayer()
    {
        return player;
    }

    // Если будем выдавать разное кол-во очков за противников, ?бонусы? и т.д.
    public void AddScore(int score)
    {
        this.score += score;
    }

    // Выводит на экран количество очков пользователя
    public void ShowPlayerHP(int hp)
    {
        hitpointLabel.Show(hp);
    }
    
    // Предположим, отнимать будем за смерть
    public void RemoveScore()
    {
        if (score >= 100)
            score -= 100;
        else 
            score = 0;
    }
    
    protected void OnSpawn(long frame)
    {
        // Обработка логики спавна каждый кадр
    }

    // Стандартный метод подготовки объектов мира
    private void prepare()
    {
        addObject(player = new Spaceship(), 200, 560);
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
            backgroundImages[i] = new GreenfootImage("Background/Background" + (i + 1) + ".png");
        }
    }
    
    // Отображение очков на экране
    private void ShowScore()
    {
       scoreLabel.Show(score);
    }
}