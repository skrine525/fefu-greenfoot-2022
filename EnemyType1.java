import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyType1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyType1 extends EnemyBasic
{
    public EnemyType1(State startingState)
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
    	else if(count <= 50)
    	{
    		move(5);
    	}
    	else if (count <= 130)
    	{
    		turn(3);
    		move(4);
    	}
    	else if(getRotation() != 90)
    		RotateTo(90, 4);
    	else
    		currentState = State.Stay;
    }
}
