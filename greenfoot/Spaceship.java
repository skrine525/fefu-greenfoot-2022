import greenfoot.*;

public class Spaceship extends Actor {
    private GreenfootImage[] blastImages;                               // Изображения взрыва
    private GreenfootImage spaceshipImage;                              // Изображение корабля
    private boolean isDestroyed = false;                                // Уничтожен ли
    private int shootCooldown = 0;                                      // Количество кадров до следующего выстрела
    private int hitpoints = 3;                                          // Количество жизней
    private int moveSpeed = 3;                                          // Горизонатльная скорость
    private int animationFrame = 0;                                     // Количество кадров для обработки анимации взрыва
    private int animationStopFrame = 0;                                 // Номер кадра, с которого заканчивается анимация взрыва

    // Константы
    private static final int BLAST_IMAGE_COUNT = 6;                     // Количество изображений взрыва
    private static final int BLAST_ANIMATION_SPEED = 5;                 // Скорость смены кадров анимации взрыва
    public static final int HITPOINT_MAX = 3;                           // Максимальный ХП

    public Spaceship()
    {
        super();

        spaceshipImage = getImage();            // Запоминаем стандартное изображение корабля
        LoadBlastImages();                      // Подгружаем изображения взрыва
    }

    public void act()
    {
        ShootCooldown();
        BlastAnimation();
        UserInput();
    }

    // Наносение урона
    public boolean Hit()
    {
        if(isDestroyed)
            return false;
        else
        {
            hitpoints = Math.max(hitpoints - 1, 0);
            isDestroyed = true;
            ((Gameplay) getWorld()).ShowPlayerHP(hitpoints);
            return true;
        }
    }

    // Движение корабля
    public void Move(int deltaX)
    {
        if(!isDestroyed)
        {
            int x = getX() + deltaX;
            if(25 < x && x < 375)
                setLocation(x, getY());
        }
    }

    // Выстрел
    protected void Shoot() {
        if(!isDestroyed && shootCooldown == 0)
        {
            Greenfoot.playSound("Pew.wav");
            getWorld().addObject(new Bullet(), getX(), getY() - 25);
            shootCooldown = 20;
        }
    }

    // Анимация взрыва
    private void BlastAnimation()
    {
        if(isDestroyed)
        {
            int imageIndex = (int) (animationFrame / (BLAST_ANIMATION_SPEED));
            if(imageIndex < BLAST_IMAGE_COUNT)
                setImage(blastImages[imageIndex]);
            else
            {
                if(animationStopFrame == 0)
                    animationStopFrame = animationFrame + 120;
                else if(animationFrame == animationStopFrame)
                {
                    if(hitpoints > 0)
                    {
                        animationStopFrame = 0;
                        isDestroyed = false;
                        animationFrame = -1;

                        setImage(spaceshipImage);
                        setLocation(199, getY());
                    }
                    else
                    {
                        Gameplay gameplay = (Gameplay) getWorld();
                        gameplay.StopGame();
                    }
                }
            }

            animationFrame++;
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