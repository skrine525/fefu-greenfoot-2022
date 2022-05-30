import greenfoot.*;

public class Bullet extends Actor
{
    public EnemyType3 owner;            // Определяет, кто притягивает пулю

    // Константы
    final static int SPEED = 10;        // Скорость полета

    public Bullet()
    {
        setRotation(270);
    }

    public void act()
    {
        if (owner != null && owner.currentState != EnemyBasic.State.Action)
            owner = null;

        if (owner == null)
            move(SPEED);
        else 
        {
            move(5);
            RotateTo((int) GameSystem.GetAngle(getX(), getY(), owner.getX(), owner.getY()), 2);
        }
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
