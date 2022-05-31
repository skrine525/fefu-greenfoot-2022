import greenfoot.*;

public class EnemyType1 extends EnemyBasic
{
    private boolean isDone;
    private int cooldown;
    private int direction;
    private int shootingFrame;

    public EnemyType1()
    {
        super(10);
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
        else if (frame <= 150)
        {
            turn(3);
            move(5);
            if (getRotation() > 100 && getRotation() < 155)
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
                turn(5);
                if (getRotation() > 80 && getRotation() < 145)
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

    @Override
    protected void OnStay(long frame)
    {
        if(matrixCell != null)
            MoveTo(matrixCell.x, matrixCell.y, 1);
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
                direction = 1;
            }
            else
            {
                setRotation(120);
                direction = -1;
            }
            move(4);
        }
        else if((getY() <= 300) && (!isDone))
        {
            move(4);
        }
        else if((shootingFrame <= 50) || (!isDone))
        {     
            if (!isDone) 
            {
                isDone = true;
                shootingFrame = 0;
            }
            move(4);
            if (shootingFrame <= 20)
                Shoot();
            shootingFrame++;
            turn(direction * 4);
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
           getWorld().addObject(new EnemyBullet(getRotation(),5), getX(), getY());     
           Greenfoot.playSound("EnemyShoot.wav");
       }
       Cooldown();
    }

    private void Cooldown()
    {
        if(cooldown != 0)
            cooldown--; 
        else
            cooldown = 7;
    }
}
