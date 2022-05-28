import greenfoot.*;

public class HitpointLabel extends Actor
{
    // Поля, необходимые для работы
    private int length, imageWidth;
    private GreenfootImage hpImage;

    public HitpointLabel(int length)
    {
        // Присваивание значения полям
        this.length = length;

        LoadImage();   // Подгружаем изображения цифр
        imageWidth = length * hpImage.getWidth();

        Show(length);
    }

    public void Show(int number)
    {
        // Формируем изображение
        GreenfootImage image = new GreenfootImage(imageWidth, hpImage.getHeight());
        for(int i = 0; i < length; i++)
        {
            if((i + 1) <= number)
            	image.drawImage(hpImage, (length - i - 1) * hpImage.getWidth(), 0);
        }
        
        setImage(image);  // Применяем сформированное изображение
    }

    private void LoadImage()
    {
        hpImage = new GreenfootImage("Spaceship.png");
        hpImage.scale(hpImage.getWidth() / 2, hpImage.getHeight() / 2);
    }
}
