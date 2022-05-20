import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyType2B extends EnemyType2
{
    public EnemyType2B()
    {
        super();
    }

    @Override
    protected void OnEnter(long frame)
    {
        if (frame == 0)
        {    
            setRotation(42);
        }
        else if (frame <= 20)
        {
            move(5);
        }
        else if (frame <= 60)
        {
            move(5);
            turn(2);
        }
        else if (frame <= 80)
        {
            move(5);
            turn(5);
        }
        else if (frame <= 88)
        {
            move(5);
            turn(7);
        }
        else if (frame <= 110)
        {
            move(5);
            turn(2);
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
            else if (frame <= 143)
            {
                move(5);
                turn(5);
            }
            else
            {
                move(5);
                turn(1);
                if (isAtEdge()) 
                    getWorld().removeObject(this);
            }
        }
    }
}
