import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyType1A extends EnemyType1
{
    public EnemyType1A()
    {
        super();
    }

    @Override
    protected void OnEnter(long frame)
    {
        if(frame == 0)
        {
            setRotation(135);
            move(5);
        }
        else if(frame <= 70)
        {
            move(5);
        }
        else if (frame <= 270)
        {
            turn(-3);
            move(5);
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
            else if (frame <= 283)
            {
                move(5);
                turn(-5);
            }
            else
            {
                move(5);
                if (isAtEdge()) 
                    getWorld().removeObject(this);
            }
        }
    }
}
