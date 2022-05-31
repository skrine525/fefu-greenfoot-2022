import greenfoot.*;

public class EnemyType2A extends EnemyType2
{
    public EnemyType2A()
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
        else if (frame <= 150)
        {
            turn(-3);
            move(5);
            if (getRotation() < 110 && getRotation() > 25)
                    RandomShoot();
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
                if (getRotation() < 120 && getRotation() > 35)
                    RandomShoot();
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
