import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyBasic here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyBasic extends Actor
{
	enum State { Stay, Enter, Destroy, Action }												// Перечисление состояний протвника

	private long actCount = 0;												                // Число кадров, необходима для анимаций
    public State currentState;                                                              // Текущее состояние 
	protected State lastState = currentState;								              	// Последнее состояние, необходима для определения изменения состояния

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
        Behavior();																// Поведение противника каждый кадр
    }

    // Сигнал на уничтожение противника
    public void Destroy()
    {
    	getWorld().removeObject(this);
    }

    // Функция плавного поворота на определенный угол с шагом
    public void RotateTo(int distRot, int delta){
        distRot = distRot % 360;
        int rot = getRotation();

        if ((distRot >= rot && distRot - rot > 180) || (distRot < rot && rot - distRot < 180))
            delta = -delta;

        if(Math.abs(distRot - rot) <= Math.abs(delta))
            setRotation(distRot);
        else
            setRotation(rot + delta);
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

    // Поведение "Появление"
    protected void OnEnter(long count)
    {
        // Обработка состояния Enter
    }


    protected void OnDestroy(long count)
    {
        // Обработка состояния Destroy
    }

    protected void OnStay(long count)
    {
        // Обработка состояния Stay
    }

    protected void OnAction(long count)
    {
        // Обработка состояния Action
    }
}
