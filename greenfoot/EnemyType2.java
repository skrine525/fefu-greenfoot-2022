import greenfoot.*;

public class EnemyType2 extends EnemyBasic
{
    private int actionActNumber; 
    private int direction;
    private boolean isDone;
    private int frame1;
    private Spaceship player;

    public EnemyType2()
    {
        super(20);
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
    }
    
    @Override
    protected void OnAction(long frame)
    {
        if (frame == 0)
        {
            turnTowards(200,300);
            move(5);
            isDone = false;
            Gameplay gameplay = (Gameplay) getWorld();
            player = gameplay.GetPlayer();
            if (getX() <= 199)
                direction = -1;
            else
                direction = 1;
        }
        else if (frame <= 30)
        {
            turn(direction);
            move(6);
        }
        else if (frame <= 72 && !isDone)
        {
            turn(direction*7);
            move(5);
        }
        else 
        {
            if (!isDone)
            {
                isDone = true; 
            }
            if (getY() <= 400)
            { 
                turnTowards(player.getX(), player.getY());
                move(6);
            }
            else 
                move(6);

            Spaceship player = (Spaceship) getOneIntersectingObject(Spaceship.class);
            if (player != null)
            {
                currentState = State.Destroy;
                player.Hit();
            }
            else if (isAtEdge()) 
                Destroy();
        }
    }
}
