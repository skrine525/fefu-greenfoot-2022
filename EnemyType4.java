import greenfoot.*;

public class EnemyType4 extends EnemyBasic
{
    private int frame2 = 0;
    private int direction = -1;
    private int frame1 = 0;
    private int lastX = 0;
    public EnemyLaser myCurrLaser;
    private Spaceship player;
    
    public EnemyType4()
    {
        super(30);
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
    protected void OnAction(long frame)
    {
        if (getY() <= 250 && frame1 == 0)
        {
            CurlyMove();
        }
        else if (frame1 == 0)
        {
            Gameplay gameplay = (Gameplay) getWorld();
            player = gameplay.GetPlayer();
            turnTowards(player.getX(), player.getY());
            frame1++;
            getWorld().addObject(new EnemyLaser(this), getX(), getY());
            myCurrLaser.laserPointerImage.setTransparency(125);
            lastX = getX();
        }
        else if (frame1 <= 140)
        {
            frame1++;
            turnTowards(player.getX(), player.getY());
        }
        else if (frame1 < 185)
        {
            frame1++;
            myCurrLaser.laserPointerImage.setTransparency(125+(frame1-160)*2);
        }
        else if (frame1 == 185)
        {
            frame1++;
            myCurrLaser.owner = null;
        }
        else if (frame1 <= 295)
        {
            frame1++;
        }
        else if (frame1 <= 335)
        {           
            move(5);
            if (lastX < 199)
                turn(-5);
            else
                turn(5);
            frame1++;
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
            {
                currentState = State.Stay;
                frame1 = 0;
                frame2 = 0;
                myCurrLaser = null;
            }
        }
    }
    
    private void CurlyMove()
    {
            frame2++;
            if(frame2 == 10)
            {
                frame2 = 0;
                direction *= -1;
            }
            move(5);
            turn(2*direction);
    }
    
    @Override
    protected void OnStay(long frame)
    {
        if(matrixCell != null)
            MoveTo(matrixCell.x, matrixCell.y, 1);
    }
}
