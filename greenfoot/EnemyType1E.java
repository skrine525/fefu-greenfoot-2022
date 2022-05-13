import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyType1E extends EnemyType1
{
    public EnemyType1E()
    {
        super();
    }

    public EnemyType1E(State startingState)
    {
        super(startingState);
    }

    @Override
    protected void OnEnter(long count)
    {
        if(count == 0)
        {
            setRotation(135);
            move(5);
        }
        else if (count <= 20)
        {
            move(5);
            turn(1);
        }
        else if (count <= 47)
        {
            move(5);
            turn(2);
        }
        else if (count <= 50)
        {
            move(5);
        }
        else if (count <= 90)
        {
            move(5);
            turn(1);
        }
        else if (count <= 100)
        {
            move(5);
        }
        else if (count <= 120)
        {
            move(5);
            turn(2);
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
}