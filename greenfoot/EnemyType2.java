import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyType2 extends EnemyBasic
{
    private int actionActNumber; 
    private int leftright;
    private boolean isDone;
    private int frame1;
    public EnemyType2()
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
        else if(frame <= 70)
        {
            move(5);
        }
        else if (frame <= 270)
        {
            turn(3);
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
                turn(5);
            }
            else
            {
                move(5);
                if (isAtEdge()) 
                    getWorld().removeObject(this);
            }
        }
    }

    @Override
    protected void OnStay(long frame)
    {
        if(matrixCell != null)
            MoveTo(matrixCell.x, matrixCell.y, 1);
            
        if(frame == 0)
        {
            actionActNumber = 500 + Greenfoot.getRandomNumber(65535) % 501;
        }
        else if(frame >= actionActNumber)
            currentState = State.Action;
    }
    
    @Override
    protected void OnAction(long frame)
    {
        if (frame == 0)
        {
            turnTowards(200,300);
            move(5);
            isDone = false;
            if (getX() <= 199)
                leftright = -1;
            else
                leftright = 1;
        }
        else if (frame <= 30)
        {
            turn(leftright);
            move(6);
        }
        else if (frame <= 72 && !isDone)
        {
            turn(leftright*7);
            move(5);
        }
        else 
        {
            if (!isDone)
            {
                isDone = true;
                turnTowards(Greenfoot.getRandomNumber(339)+30,599);            }
            if (frame1 >= 20)
            {
              leftright *= -1;
              frame1 = 0;
            }
            else 
                frame1 ++;
            move(6);
            turn(leftright*3);
            if (isTouching(Spaceship.class))
            {
                currentState = State.Destroy;
            }
            if (isAtEdge()) 
            {
                getWorld().removeObject(this);
                if(matrixCell != null)
                    matrixCell.enemy = null;
            }
        }
    }
}
