import greenfoot.*;

public class EnemyBasic extends Actor
{
    enum State { Stay, Enter, Destroy, Action }                                              // Перечисление состояний протвника

    private long frame = 0;                                                                  // Число кадров, необходима для анимаций
    public State currentState;                                                               // Текущее состояние
    private State lastState = currentState;                                                  // Последнее состояние, необходима для определения изменения состояния
    protected EnemyMatrix.Cell matrixCell;                                                   // Ячейка в матрице
    private GreenfootImage blastImages[];                                                    // Массив изображений для анимации взрыва
    private int pointsForHit;

    // Константы
    private static final int BLAST_IMAGE_COUNT = 4;                                          // Количество кадров в анимации взрыва
    private static final int BLAST_ANIMATION_SPEED = 5;                                      // Скорость смены кадров анимации взрыва

    // Конструктор противника
    public EnemyBasic()
    {
        LoadBlastImages();
        currentState = State.Stay;
        pointsForHit = 0;
    }

    public EnemyBasic(int pointsForHit)
    {
        LoadBlastImages();
        currentState = State.Stay;
        this.pointsForHit = pointsForHit;
    }

    // Устанавливает ячейку матрицы для врага
    public void SetCell(EnemyMatrix.Cell connectingCell)
    {
        matrixCell = connectingCell;
    }

    // Обычный act
    public void act()
    {
        Behavior();                                                                             // Поведение противника каждый кадр
    }

    // Функция плавного поворота на определенный угол с шагом
    public void RotateTo(int distRot, int delta)
    {
        distRot = distRot % 360;
        int rot = getRotation();

        if ((distRot >= rot && distRot - rot > 180) || (distRot < rot && rot - distRot < 180))
            delta = -delta;

        if(Math.abs(distRot - rot) <= Math.abs(delta))
            setRotation(distRot);
        else
            setRotation(rot + delta);
    }

    // Функции движения к заданной точке каждый вызов с шагом
    public void MoveTo(int distX, int distY, int delta)
    {
        if(GameSystem.GetDistance(getX(), getY(), distX, distY) <= delta)
            setLocation(distX, distY);
        else
        {
            double vX = distX - getX();
            double vY = distY - getY();
            double angle = Math.atan2(vY, vX);

            vX = Math.cos(angle) * delta;
            vY = Math.sin(angle) * delta;

            int x = getX() + (int) (Math.signum(vX) * Math.round(Math.abs(vX)));
            int y = getY() + (int) (Math.signum(vY) * Math.round(Math.abs(vY)));

            if(GameSystem.GetDistance(x, y, distX, distY) <= delta)
                setLocation(distX, distY);
            else
                setLocation(x, y);
        }
    }

    public void Destroy()
    {
        getWorld().removeObject(this);
        if(matrixCell != null)
            matrixCell.enemy = null;
    }

    public void Hit()
    {
        ((Gameplay) getWorld()).AddScore(pointsForHit);
        currentState = State.Destroy;
    }

    // Основное поведение противника
    private void Behavior()
    {
        if(currentState != lastState)
        {
            frame = 0;
            lastState = currentState;
        }
        else
            frame++;

        switch(currentState)
        {
            case Stay: OnStay(frame); break;
            case Enter: OnEnter(frame); break;
            case Action: OnAction(frame); break;
            case Destroy: OnDestroy(frame); break;
        }
    }

    // Обработка состояния Enter каждый кадр
    protected void OnEnter(long frame)
    {
        // Да-да, он без кода. Необходима переопределять в субклассах
    }

    // Обработка состояния Destroy каждый кадр
    protected void OnDestroy(long frame)
    {
        if(frame == 0)
            Greenfoot.playSound("EnemyBlast.wav");

        int imageIndex = (int) (frame / (BLAST_IMAGE_COUNT + 1));
        if(imageIndex < BLAST_IMAGE_COUNT)
            setImage(blastImages[imageIndex]);
        else
            Destroy();
    }

    // Обработка состояния Stay каждый кадр
    protected void OnStay(long frame)
    {
        // Да-да, он без кода. Необходима переопределять в субклассах
    } 

    // Обработка состояния Action каждый кадр
    protected void OnAction(long frame)
    {
        // Да-да, он без кода. Необходима переопределять в субклассах

        // Для теста
        if(frame <= 100)
        {
            move(5);
        }
        else{
            currentState = State.Stay;
            setRotation(90);
            setLocation(matrixCell.x, matrixCell.y);
        }
    }

    // Подгружает изображения взрыва в массив
    private void LoadBlastImages()
    {
        blastImages = new GreenfootImage[BLAST_IMAGE_COUNT];
        for(int i = 0; i < BLAST_IMAGE_COUNT; i++)
        {
            blastImages[i] = new GreenfootImage("EnemyBlast/Blast" + (i + 1) + ".png");
        }
    }

    // Играемся с процентами
    public void RandomShoot()
    {
        if (Greenfoot.getRandomNumber(1000) <= 5)
        {
            Greenfoot.playSound("EnemyShoot.wav");
            getWorld().addObject(new EnemyBullet(getRotation(), 5), getX(), getY());
        }
    }
}
