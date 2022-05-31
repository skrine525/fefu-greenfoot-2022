import greenfoot.*;

public class Gameplay extends World
{
    protected EnemyMatrix enemyMatrix;                                                           // Матрица врагов
    protected GreenfootImage backgroundImages[];                                                 // Массив изображений фона
    protected Spawner spawner;                                                                   // Контроллер спавна
    protected int score = 0;                                                                     // Количество очков
    protected ScoreLabel scoreLabel;                                                             // Метка счетчика очков
    protected HitpointLabel hitpointLabel;                                                       // Метка жизней игрока
    private Spaceship player;                                                                    // Ссылка на игрока
    private long frame = 0;                                                                      // Переменная количества кадров
    private GreenfootSound backgroundSound;                                                      // Объект фоновой музыки

    // Константы
    private static int BACKGROUND_IMAGE_COUNT = 75;                                              // Количество изображений заднего фона
    
    // Конструктор мира
    public Gameplay()
    {    
        super(400, 600, 1);                                                                      // Создание мира 400x600

        // Очередь отрисовки объектов
        setPaintOrder(ScoreLabel.class, HitpointLabel.class, StageLabel.class, Spaceship.class, EnemyBasic.class, Meds.class);

        LoadBackgroundImages();                                                                 // Подгружаем изображения фона
        setBackground(backgroundImages[0]);                                                     // Устанавливаем фон мира
        enemyMatrix = new EnemyMatrix(3, 8, 59, 59, 40);                                        // Инициализация матрицы врагов
        spawner = new Spawner(this, enemyMatrix);                                               // Инициализация спавнера

        backgroundSound = new GreenfootSound("BackgroundSound.wav");                            // Инициализируем объект фоновой музыки

        scoreLabel = new ScoreLabel(8);                                                         // Инициализируем метку очков
        addObject(scoreLabel, scoreLabel.getImage().getWidth() / 2, 10);                                                          // Добавляем метку очков в мир
        scoreLabel.Show(score);                                                                 // Отображаем очки на экране

        hitpointLabel = new HitpointLabel(Spaceship.HITPOINT_MAX);                              // Инициализируем метку жизней
        addObject(hitpointLabel, getWidth() - hitpointLabel.getImage().getWidth() / 2, 10);     // Добавляем метку жизней в мир

        player = new Spaceship(this, 199, 560);                                                 // Инициализируем игрока
    }

    public void act(){
        BackgroundAnimation();                                                                  // Обрабатывает задний фон мира
        OnSpawn(frame);                                                                         // Обрабатывает логику спавна
        spawner.Act();                                                                          // Обрабатывает систему спавна
        enemyMatrix.Act();                                                                      // Обрабатывает логику матрицы
        frame++;                                                                                // Увеичиваем переменную счетчика кадров
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
        scoreLabel.Show(this.score);
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

    // Останавливает игру
    public void StopGame()
    {
        Menu menu = new Menu(score);
        Greenfoot.setWorld(menu);
        StopBackgroundSound();
    }

    // Включает фоновую музыку
    public void PlayBackgroundSound()
    {
        if(!backgroundSound.isPlaying())
            backgroundSound.playLoop();
    }

    // Выключает фоновую музыку
    public void StopBackgroundSound()
    {
        if(backgroundSound.isPlaying())
            backgroundSound.stop();
    }

    // Устанавливает громкость фоновой музыки
    public void SetBackgroundSoundVolume(int volume)
    {
        backgroundSound.setVolume(volume);
    }
    
    protected void OnSpawn(long frame)
    {
        // Обработка логики спавна каждый кадр
    }

    // Обработка анимации фона
    private void BackgroundAnimation(){
        int imageIndex = (int) (frame % BACKGROUND_IMAGE_COUNT);
        setBackground(backgroundImages[imageIndex]);
    }
    
    // Подгрузка изображений фона
    private void LoadBackgroundImages(){
        backgroundImages = new GreenfootImage[BACKGROUND_IMAGE_COUNT];
        for(int i = 0; i < BACKGROUND_IMAGE_COUNT; i++)
        {
            backgroundImages[i] = new GreenfootImage("Background/Background" + (i + 1) + ".png");
            setBackground(backgroundImages[i]);
        }
    }
}