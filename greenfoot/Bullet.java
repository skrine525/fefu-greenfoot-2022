import greenfoot.*;

public class Bullet extends Actor
{
    public EnemyType3 corruptOwner;            // Определяет, кто притягивает пулю
    private boolean canCorrupt = true;          // Определяет возможность завербовать пулю

    // Константы
    final static int SPEED = 10;        // Скорость полета

    public Bullet()
    {
        setRotation(270);

        if(Greenfoot.getRandomNumber(100) < 10)
            canCorrupt = false;
        else
            canCorrupt = true;
    }

    public void act()
    {
        Movement();
        CheckIntersects();
    }

    public boolean CanCorrupt()
    {
        return canCorrupt;
    }

    private void CheckIntersects()
    {
        EnemyBasic enemy = (EnemyBasic) getOneIntersectingObject(EnemyBasic.class);

        if(enemy != null && enemy.currentState != EnemyBasic.State.Destroy)
        {
            getWorld().removeObject(this);
            enemy.Hit();
        }
        else if (isAtEdge())
        {
            getWorld().removeObject(this);
        }
    }

    private void Movement()
    {
        if (corruptOwner != null && !corruptOwner.onManeuver)
            corruptOwner = null;

        if (corruptOwner == null)
            move(SPEED);
        else 
        {
            // Необходимо исключение, потому что corruptOwner может быть не в мире
            try
            {
                int angle = (int) GameSystem.GetAngle(getX(), getY(), corruptOwner.getX(), corruptOwner.getY());
                move(5);
                RotateTo(angle, 2);
            }
            catch(IllegalStateException e)
            {
                corruptOwner = null;
                move(SPEED);
            }
        }
    }

    private void RotateTo(int distRot, int delta)
    {
        distRot = distRot % 360;
        int rot = getRotation();

        if ((distRot >= rot && distRot - rot > 180) || (distRot < rot && rot - distRot < 180))
            delta = -delta;

        if(Math.abs(distRot - rot) <= Math.abs(delta))
            setRotation(distRot);
        else
            setRotation(rot + delta);
    }
}
