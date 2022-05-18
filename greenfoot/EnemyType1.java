import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyType1 extends EnemyBasic
{
    private int actionActNumber;                                                             // Номер кадра, при котором меняется состояние Stay -> Action

    public EnemyType1()
	{
		super();
	}

    @Override
    protected void OnEnter(long count)
    {
    	if(count == 0)
    	{
    		setRotation(45);
    		move(5);
    	}
    	else if(count <= 70)
    	{
    		move(5);
    	}
    	else if (count <= 270)
    	{
    		turn(3);
    		move(5);
    	}
    	else
    	{
    		if(cell != null)
    		{
    			if(getX() != cell.x && getY() != cell.y)
    			{
    				RotateTo((int) GameSystem.GetAngle(getX(), getY(), cell.x, cell.y), 3);
    				MoveTo(cell.x, cell.y, 5);
    			}
    			else if(getRotation() != 90)
    			{
    				MoveTo(cell.x, cell.y, 5);
    				RotateTo(90, 5);
    			}
    			else
    				currentState = State.Stay;
    		}
    	}
    }

    @Override
    protected void OnStay(long count)
    {
    	if(cell != null)
    		MoveTo(cell.x, cell.y, 1);

        // Тестовый переход в Action
        if(count == 0)
        {
            actionActNumber = 500 + Greenfoot.getRandomNumber(65535) % 501;
        }
        else if(count >= actionActNumber)
            currentState = State.Action;
    }

    @Override
    protected void OnAction(long count)
    {
        if(count < 60)
            move(3);
        else
        {
            if(cell != null)
            {
                setLocation(cell.x, cell.y);
                currentState = State.Stay;
            }
        }
    }
}
