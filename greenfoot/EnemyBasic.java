import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyBasic extends Actor
{
	enum State { Stay, Enter, Destroy, Action }												// Перечисление состояний протвника

	private long actCount = 0;												                // Число кадров, необходима для анимаций
    public State currentState;                                                              // Текущее состояние
	private State lastState = currentState;								              		// Последнее состояние, необходима для определения изменения состояния
    protected EnemyMatrix.Cell cell;

    private GreenfootImage blastImages[];

	// Конструктор противника
	public EnemyBasic(){
		currentState = State.Stay;
		LoadBlastImages();
	}

	// Расширенный конструктор противника
    public EnemyBasic(State startingState)
    {
    	currentState = startingState;
    	LoadBlastImages();
    }

    // Устанавливает ячейку матрицы для врага
    public void SetCell(EnemyMatrix.Cell connectingCell)
    {
        cell = connectingCell;
    }

    // Обычный act
    public void act()
    {
        Behavior();																             // Поведение противника каждый кадр
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
        if(cell != null)
            cell.enemy = null;
    }

    public void Hit()
    {
        currentState = State.Destroy;
    }

    // Основное поведение противника
    private void Behavior()
    {
    	if(currentState != lastState)
    	{
    		actCount = 0;
    		lastState = currentState;
    	}
    	else
    		actCount++;

    	switch(currentState)
    	{
            case Stay: OnStay(actCount); break;
    		case Enter: OnEnter(actCount); break;
            case Action: OnAction(actCount); break;
            case Destroy: OnDestroy(actCount); break;
    	}
    }

    // Подгружает изображения взрыва в массив
    private void LoadBlastImages()
    {
    	blastImages = new GreenfootImage[4];
    	for(int i = 0; i < 4; i++)
    	{
    		blastImages[i] = new GreenfootImage("Blast" + (i + 1) + ".png");
    	}
    }

    // Обработка состояния Enter каждый кадр
    protected void OnEnter(long count)
    {
        // Да-да, он без кода. Необходима переопределять в субклассах
    }

    // Обработка состояния Destroy каждый кадр
    protected void OnDestroy(long count)
    {
    	// Вау, уже с кодом. Можно переопределять в субклассах, но зачем этом ¯\_(ツ)_/¯

    	int imageIndex = (int) (count / 5);
    	if(imageIndex < 4)
    		setImage(blastImages[imageIndex]);
    	else
        	Destroy();
    }

    // Обработка состояния Stay каждый кадр
    protected void OnStay(long count)
    {
        // Да-да, он без кода. Необходима переопределять в субклассах
    } 

    // Обработка состояния Action каждый кадр
    protected void OnAction(long count)
    {
        // Да-да, он без кода. Необходима переопределять в субклассах
    }
}
