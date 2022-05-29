import greenfoot.*;
import java.util.List;

public class EnemyType3 extends EnemyBasic
{   
    private int state;
    private int frame1;
    private List<Bullet> corBullets;            // Список пуль для "вербовки"
    private List<Bullet> capBullets;            // Список пуль для притяжения
    Bullet corBullet;
    Bullet capBullet;
    public EnemyType3()
    {
        super(50);
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
    protected void OnAction(long frame)
    {
        if (frame == 0)
        {
            turnTowards(Greenfoot.getRandomNumber(150)+125,350);
            move(5);
            state = 0;
            frame1 = 0;
        }

        if(state == 0)
        {
            if(getY() < 350)
                move(5);
            else
                state = 1;
        }
        else if(state == 1)
        {
            if(frame1 <= 1200)
            {
                frame1++;
                CorruptBullets();
                CaptureBullets();
                move(2);
                turn(2);
            }
            else
                state = 2;
        }
        else if(state == 2)
        {
            if((getRotation() < 265 || getRotation() > 275))
            {
                move(2);
                turn(2);
            }
            else
                state = 3;
        }
        else
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
    
    private void CorruptBullets()
    {
        corBullets = getObjectsInRange(50, Bullet.class);
        for (int i = 0; i < corBullets.size(); i++)
        {
            corBullet = corBullets.get(i);
            getWorld().addObject(new CorruptedBullet(corBullet.getRotation()), corBullet.getX(), corBullet.getY());
            getWorld().removeObject(corBullet);
        }
    }
    
    private void CaptureBullets()
    {
        capBullets = getObjectsInRange(200, Bullet.class);
        for (int i = 0; i < capBullets.size(); i++)
        {
            if (capBullets.get(i).owner == null)
                capBullets.get(i).owner = this;  
        }
    }
    
    @Override
    protected void OnStay(long frame)
    {
        if(matrixCell != null)
            MoveTo(matrixCell.x, matrixCell.y, 1);
    }
}
