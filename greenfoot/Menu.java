import greenfoot.*;

public class Menu extends World
{
    private ScoreLabel scoreLabel;
    private PressSpaceLabel pressSpaceLabel;
    private GreenfootSound gameOverSound;
    private byte state;

    public Menu()
    {    
        super(400, 600, 1);

        state = 0;
        setBackground("Menu/Control.png");

        pressSpaceLabel = new PressSpaceLabel(this);
        addObject(pressSpaceLabel, 199, 350);
    }

    public Menu(int score)
    {
        super(400, 600, 1);

        state = 1;
        setBackground("Menu/Lastpic.png");

        scoreLabel = new ScoreLabel(12);
        scoreLabel.Show(score);
        addObject(scoreLabel, 307, 581);

        gameOverSound = new GreenfootSound("GameOver.wav");
        gameOverSound.setVolume(40);
        gameOverSound.play();

        pressSpaceLabel = new PressSpaceLabel(this);
        addObject(pressSpaceLabel, 199, 350);
    }

    public void PressSpace()
    {
        if(gameOverSound != null && gameOverSound.isPlaying())
            gameOverSound.stop();
        GameplayInf gameplay = new GameplayInf();
        Greenfoot.setWorld(gameplay);
    }
}
