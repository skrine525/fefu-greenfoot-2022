import greenfoot.*;

public class PressSpaceLabel extends Actor
{
    private double t = 0;
    private boolean isPressed = false;
    private Menu menu;

    public PressSpaceLabel(Menu menu, GreenfootImage image)
    {
        setImage(image);
        this.menu = menu;
    }

    public void act()
    {
        int transparency = 155 + (int) (100 * Math.cos(t));
        getImage().setTransparency(transparency);

        t += Math.PI / 50;

        if(!isPressed && Greenfoot.isKeyDown("space"))
            menu.PressSpace();
    }
}
