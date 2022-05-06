import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyType2 extends EnemyBasic
{
    public EnemyType2()
	{
		super();
	}

	public EnemyType2(State startingState)
    {
    	super(startingState);
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
    }
}
