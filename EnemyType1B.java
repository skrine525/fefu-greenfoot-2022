import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyType1B extends EnemyType1
{
    public EnemyType1B()
    {
        super();
    }

    public EnemyType1B(State startingState)
    {
        super(startingState);
    }

    @Override
    protected void OnEnter(long count)
    {
        getWorld().showText(""+count,40,40);
        if (count == 0)
        {    
            setRotation(42);
        }
        else if (count <= 20)
        {
            move(5);
        }
        else if (count <= 60)
        {
            move(5);
            turn(2);
        }
        else if (count <= 80)
        {
            move(5);
            turn(5);
        }
        else if (count <= 88)
        {
            move(5);
            turn(7);
        }
        else if (count <= 110)
        {
            move(5);
            turn(2);
        }
    }
}
