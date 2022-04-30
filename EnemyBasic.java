import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyBasic here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyBasic extends Actor
{
	enum State { Stay, Emersion }												// Перечисление состояний протвника

	protected long actCount = 0;												// Число кадров, необходима для анимаций
	public State currentState = State.Stay;										// Текущее состояние
	protected State lastState = currentState;									// Последнее состояние, необходима для определения изменения состояния

	// Конструктор противника
	public EnemyBasic(){
		currentState = State.Stay;
	}

	// Расширенный конструктор противника
    public EnemyBasic(State startingState)
    {
    	currentState = startingState;
    }

    // Обычный act
    public void act()
    {
    	getWorld().showText(""+getRotation(), 20, 20);
        Behavior();																// Поведение противника каждый кадр
    }

    // Сигнал на уничтожение противника
    public void Destroy()
    {
    	getWorld().removeObject(this);
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
    		case Emersion: OnEmersion(); break;
    	}
    }

    // Поведение "Появление"
    protected void OnEmersion()
    {
    	if(actCount == 0)
    	{
    		setRotation(45);
    		move(5);
    	}
    	else if(actCount <= 50)
    	{
    		move(5);
    	}
    	else if (actCount <= 130)
    	{
    		turn(3);
    		move(4);
    	}
    	else if(getRotation() != 90)
    		Rotate(90, 4);
    	else
    		currentState = State.Stay;
    }

    // Функция плавного поворота на определенный угол с шагом
    protected void Rotate(int distRot, int delta){
    	distRot = distRot % 360;
    	int rot = getRotation();
    	if((distRot - rot > 180) || (Math.signum(distRot - rot) == -1))
    		delta = -delta;
    	if(Math.abs(distRot - rot) <= Math.abs(delta))
    		setRotation(distRot);
    	else
    		setRotation(rot + delta);
    }
}
