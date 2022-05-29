import greenfoot.*;

public class Menu extends World
{
	private ScoreLabel scoreLabel;
	private PressSpaceLabel pressSpaceLabel;
	private GreenfootSound gameOverSound;

    public Menu()
    {    
        super(400, 600, 1); 
    }

    public Menu(int score)
    {
    	super(400, 600, 1);

    	setBackground("Menu/Lastpic.png");

    	scoreLabel = new ScoreLabel(12);
    	scoreLabel.Show(score);
    	addObject(scoreLabel, 307, 581);

    	gameOverSound = new GreenfootSound("GameOver.wav");
    	gameOverSound.setVolume(50);
    	gameOverSound.play();

    	pressSpaceLabel = new PressSpaceLabel();
    	addObject(pressSpaceLabel, 199, 350);
    }
}
