import greenfoot.*;

public class EnemyType4 extends EnemyBasic
{
    private int frame1 = 0;
    private int direction = -1;
    private int frame2 = 0;
    private Spaceship player;
    
    public EnemyType4()
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
        if (getY() <= 250)
        {
            CurlyMove();
        }
        else if (frame1 == 0)
        {
            Gameplay gameplay = (Gameplay) getWorld();
            player = gameplay.GetPlayer();
            turnTowards(player.getX(), player.getY());
            frame1++;
        }
        else if (frame1 <= 140)
        {
            frame1++;
            turnTowards(player.getX(), player.getY());
        }
        else if (frame1 < 155)
        {
            frame1++;
        }
        else if (frame1 == 155)
        {
            getWorld().addObject(new EnemyLaser(getRotation()+90), getX(), getY());
            frame1++;
        }
        else if (frame1 <= 305)
        {
            frame1++;
        }
        else
        {           
            CurlyMove();
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
