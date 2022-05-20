import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyType1D extends EnemyType1
{
    public EnemyType1D()
    {
        super();
    }

    @Override
    protected void OnEnter(long frame)
    {
        if(frame == 0)
        {
            setRotation(45);
            move(5);
        }
        else if (frame <= 20)
        {
            move(5);
            turn(-1);
        }
        else if (frame <= 47)
        {
            move(5);
            turn(-2);
        }
        else if (frame <= 50)
        {
            move(5);
        }
        else if (frame <= 90)
        {
            move(5);
            turn(-1);
        }
        else if (frame <= 100)
        {
            move(5);
        }
        else if (frame <= 120)
        {
            move(5);
            turn(-2);
        }
        else
        {
            if(matrixCell != null)
            {
                if(getX() != matrixCell.x && getY() != matrixCell.y)
                {
                    RotateTo((int) GameSystem.GetAngle(getX(), getY(), matrixCell.x, matrixCell.y), 3);
                    MoveTo(matrixCell.x, matrixCell.y, 5);
                }
                else if(getRotation() != 90)
                {
                    MoveTo(matrixCell.x, matrixCell.y, 5);
                    RotateTo(90, 5);
                }
                else
                    currentState = State.Stay;
            }
            else
            {
                if (isAtEdge()) 
                    getWorld().removeObject(this);
                move(5);
                turn(2);
            }
        }
    }
}
