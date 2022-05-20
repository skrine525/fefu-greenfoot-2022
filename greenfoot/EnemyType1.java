import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyType1 extends EnemyBasic
{
    private int actionActNumber;                                                             // Номер кадра, при котором меняется состояние Stay -> Action
    private boolean IsDone;
    private int cooldown;
    private int LR;
    private int count1;

    public EnemyType1()
    {
        super();
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

        // Тестовый переход в Action
        if(count == 0)
        {
            actionActNumber = 500 + Greenfoot.getRandomNumber(65535) % 501;
        }
        else if(count >= actionActNumber);
            //currentState = State.Action;
    }

    @Override
    protected void OnAction(long count)
    {
        if(count == 0)
        {
            IsDone = false;
            if (getX() <= 199)
            {
                setRotation(60);
                LR = 1;
            }
            else
            {
                setRotation(120);
                LR = -1;
            }
            move(4);
        }
        else if((getY() <= 300) && (!IsDone))
        {
            move(4);
        }
        else if((count1 <= 50) || (!IsDone))
        {     
            if (!IsDone) 
            {
                IsDone = true;
                count1 = 0;
            }
            move(4);
            if (count1 <= 20) 
                Shoot();
            count1++;
            turn(LR * 4);
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
