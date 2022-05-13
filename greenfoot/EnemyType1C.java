import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyType1C extends EnemyType1
{
    public EnemyType1C()
    {
        super();
    }

    public EnemyType1C(State startingState)
    {
        super(startingState);
    }

    @Override
    protected void OnEnter(long count)
    {
        if (count == 0)
        {    
            setRotation(138);
        }
        else if (count <= 20)
        {
            move(5);
        }
        else if (count <= 60)
        {
            move(5);
            turn(-2);
        }
        else if (count <= 80)
        {
            move(5);
            turn(-5);
        }
        else if (count <= 88)
        {
            move(5);
            turn(-7);
        }
        else if (count <= 110)
        {
            move(5);
            turn(-2);
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
