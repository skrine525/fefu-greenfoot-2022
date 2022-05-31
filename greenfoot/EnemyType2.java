import greenfoot.*;

public class EnemyType2 extends EnemyBasic
{
    private int actionActNumber; 
    private int direction;
    private Spaceship player;
    private int frame1;
    private int lastX;
    private GreenfootImage enemySprite = new GreenfootImage("Enemy2.png");
    private GreenfootImage emptySprite = new GreenfootImage(2,2);
    private boolean outOfArea = false;
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
    protected void OnStay(long frame)
    {
        if(matrixCell != null)
            MoveTo(matrixCell.x, matrixCell.y, 1);
    }
    
    @Override
    protected void OnAction(long frame)
    {
        if (outOfArea)
        {
            if (frame1 <= 30)
            {
                frame1++;
                if (frame1 == 31)
                {
                    setLocation(399-lastX,0);
                    setImage(enemySprite);
                    setRotation(90);
                }
            }
            else if(getX() != matrixCell.x && getY() != matrixCell.y)
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
                outOfArea = false;
            }
            
        }
        else if (frame == 0)
        {
            turnTowards(200,300);
            move(5);
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
        else if (frame <= 72)
        {
            turn(direction*7);
            move(5);
        }
        else 
        {
            if (getY() <= 400)
            { 
                turnTowards(player.getX(), player.getY());
                move(6);
            }
            else 
                move(6);

            Spaceship.Hitbox spaceshipHitbox = (Spaceship.Hitbox) getOneIntersectingObject(Spaceship.Hitbox.class);
            if (spaceshipHitbox != null && spaceshipHitbox.GetSpaceship().Hit())
                currentState = State.Destroy;
            else if (isAtEdge())
            {
                setImage(emptySprite);
                outOfArea = true;
                lastX = getX();
                frame1 = 0;
            }                
        }
    }
}
