import greenfoot.*;

public class PressSpaceLabel extends Actor
{
	private double t = 0;

	public PressSpaceLabel()
	{
		setImage("Text/PressSpace.png");
	}

    public void act()
    {
    	int transparency = 155 + (int) (100 * Math.cos(t));
    	getImage().setTransparency(transparency);

        t += Math.PI / 50;
    }
}
