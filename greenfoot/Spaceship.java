import greenfoot.*;

public class Spaceship extends Actor {
    // Класс хитбокса
    public class Hitbox extends Actor
    {
        private Spaceship spaceship;                                                    // Ссылка на Spaceship

        // Константы
        private static final int HITBOX_WIDTH = 39;                                     // Ширина хитбокса
        private static final int HITBOX_HEIGHT = 32;                                    // Высота хитбокса
        private static final int XPOS_OFFSET = 0;                                       // Смещение хитбокса по оси X относительно центра Spacehip'а
        private static final int YPOS_OFFSET = 8;                                       // Смещение хитбокса по оси Y относительно центра Spacehip'а

        // Конструктор
        public Hitbox(Spaceship spaceship)
        {
            this.spaceship = spaceship;
            GreenfootImage image = new GreenfootImage(HITBOX_WIDTH, HITBOX_HEIGHT);
            setImage(image);

            // Для теста
            // image.setColor(Color.RED);
            // image.fillRect(0, 0, image.getWidth() - 1, image.getHeight() - 1);
            // image.setTransparency(100);
        }

        // Возвращает ссылку на Spaceship
        public Spaceship GetSpaceship()
        {
            return spaceship;
        }

        // Перемещает Hitbox к Spaceship'у
        public void MoveToSpaceship()
        {
            if(spaceship != null)
                setLocation(spaceship.getX() + XPOS_OFFSET, spaceship.getY() + YPOS_OFFSET);
        }
    }

    private GreenfootImage[] blastImages;                               // Изображения взрыва
    private GreenfootImage spaceshipImage;                              // Изображение корабля
    private Spaceship.Hitbox hitbox;                                    // Хитбокс игрока
    private boolean isHit = false;                                      // Поврежден ли
    private int shootCooldown = 0;                                      // Количество кадров до следующего выстрела
    private int hitpoints = 3;                                          // Количество жизней
    private int moveSpeed = 3;                                          // Горизонатльная скорость
    private int blastAnimationFrame = 0;                                // Количество кадров для обработки анимации взрыва
    private int blastAnimationStopFrame = 0;                            // Номер кадра, с которого заканчивается анимация взрыва
    private int invincibilityFrame = 0;                                 // Количество кадров, которое дает непобедимость игроку
    private double hitAnimationNumber = 0;                              // Переменная для анимации мигания через косинус

    // Константы
    private static final int BLAST_IMAGE_COUNT = 6;                     // Количество изображений взрыва
    private static final int BLAST_ANIMATION_SPEED = 5;                 // Скорость смены кадров анимации взрыва
    private static final int INVINCIBILITY_FRAME_COUNT = 240;           // Количество кадров, при котором игрок остается непобедимым после возрождения
    public static final int HITPOINT_MAX = 3;                           // Максимальный ХП

    public Spaceship(World world, int x, int y)
    {
        super();

        spaceshipImage = getImage();                // Запоминаем стандартное изображение корабля
        LoadBlastImages();                          // Подгружаем изображения взрыва

        world.addObject(this, x, y);                // Добавляем игрока в мир
        hitbox = new Spaceship.Hitbox(this);        // Инициализируем хитбокс
        world.addObject(hitbox, getX(), getY());    // Добавляем хитбокс в мир
        hitbox.MoveToSpaceship();                   // Применяем смещение хитбокса
    }

    public void act()
    {
        UserInput();                                // Обработка пользовательского ввода
        ShootCooldown();                            // Просчёт КД между выстрелами
        HitProccess();                              // Обрабатывание урона по игроку
        hitbox.MoveToSpaceship();                   // Перемещение хитбокса вслед за игроком
    }

    // Наносение урона
    public boolean Hit()
    {
        if(isHit)
            return false;
        else
        {
            hitpoints = Math.max(hitpoints - 1, 0);
            ((Gameplay) getWorld()).ShowPlayerHP(hitpoints);

            isHit = true;
            invincibilityFrame = INVINCIBILITY_FRAME_COUNT;
            Greenfoot.playSound("Blast.wav");

            return true;
        }
    }

    // Движение корабля
    public void Move(int deltaX)
    {
        if(hitpoints > 0)
        {
            int x = getX() + deltaX;
            if(25 < x && x < 375)
                setLocation(x, getY());
        }
    }

    // Getter переменной isHit
    public boolean IsHit()
    {
        return isHit;
    }

    // Выстрел
    protected void Shoot() {
        if(!isHit && shootCooldown == 0)
        {
            Greenfoot.playSound("SpaceshipShoot.wav");
            getWorld().addObject(new Bullet(), getX(), getY() - 25);
            shootCooldown = 20;
        }
    }

    // Обрабатывает состояние, когда по игроку проходит урон
    private void HitProccess()
    {
        if(isHit)
        {
            if(hitpoints > 0)
            {
                if(invincibilityFrame > 0)
                {
                    invincibilityFrame--;

                    // Меняем прозрачность изображения для эффекта мигания
                    int transparency = 155 + (int) (100 * Math.cos(hitAnimationNumber));
                    hitAnimationNumber += Math.PI / 25;
                    getImage().setTransparency(transparency);
                }
                else
                {
                    isHit = false;
                    hitAnimationNumber = 0;
                    getImage().setTransparency(255);
                }
            }
            else
            {
                if(blastAnimationFrame == 0)
                    Greenfoot.playSound("Blast.wav");

                int imageIndex = (int) (blastAnimationFrame / (BLAST_ANIMATION_SPEED));
                if(imageIndex < BLAST_IMAGE_COUNT)
                    setImage(blastImages[imageIndex]);
                else
                {
                    if(blastAnimationStopFrame == 0)
                        blastAnimationStopFrame = blastAnimationFrame + 120;
                    else if(blastAnimationFrame == blastAnimationStopFrame)
                    {
                        Gameplay gameplay = (Gameplay) getWorld();
                        gameplay.StopGame();
                    }
                }

                blastAnimationFrame++;
            }
        }
    }

    // Просчитывание КД
    private void ShootCooldown()
    {
        if(shootCooldown > 0)
            shootCooldown--;
    }

    // Пользовательское управление
    private void UserInput() {
        if (Greenfoot.isKeyDown("left"))
            Move(-moveSpeed);
        else if (Greenfoot.isKeyDown("right"))
            Move(moveSpeed);

        if(Greenfoot.isKeyDown("z"))
            Shoot();
    }

    // Подгружает изображения взрыва в массив
    private void LoadBlastImages()
    {
        blastImages = new GreenfootImage[BLAST_IMAGE_COUNT];
        for(int i = 0; i < BLAST_IMAGE_COUNT; i++)
        {
            blastImages[i] = new GreenfootImage("SpaceshipBlast/Blast" + (i + 1) + ".png");
        }
    }
}