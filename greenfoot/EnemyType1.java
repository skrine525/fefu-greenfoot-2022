import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyType1 extends EnemyBasic
{
    private int actionActNumber;                                                             // Номер кадра, при котором меняется состояние Stay -> Action
    private boolean isDone;
    private int cooldown;
    private int leftright;
    private int frame1;

    public EnemyType1()
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

        // Тестовый переход в Action
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
        if(frame == 0)
        {
            isDone = false;
            if (getX() <= 199)
            {
                setRotation(60);
                leftright = 1;
            }
            else
            {
                setRotation(120);
                leftright = -1;
            }
            move(4);
        }
        else if((getY() <= 300) && (!isDone))
        {
            move(4);
        }
        else if((frame1 <= 50) || (!isDone))
        {     
            if (!isDone) 
            {
                isDone = true;
                frame1 = 0;
            }
            move(4);
            if (frame1 <= 20) 
                Shoot();
            frame1++;
            turn(leftright * 4);
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
        }
    }

    private void Shoot()
    {
       if (cooldown == 0)
       {
           getWorld().addObject(new EnemyBullet(getRotation()), getX(), getY());     
       }
       Cool();
    }

    private void Cool()
    {
       if (cooldown != 0){
            cooldown--;
        } 
       else cooldown = 5;
    }
}
