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
        imageWidth = length * (hpImage.getWidth() + 1);

        Show(length);
    }

    public void Show(int number)
    {
        // Формируем изображение
        GreenfootImage image = new GreenfootImage(imageWidth, hpImage.getHeight());
        for(int i = 0; i < length; i++)
        {
            if((i + 1) <= number)
            	image.drawImage(hpImage, (length - i - 1) * (hpImage.getWidth() + 1), 0);
        }
        
        setImage(image);  // Применяем сформированное изображение
    }

    private void LoadImage()
    {
        hpImage = new GreenfootImage("Meds.png");
        hpImage.scale((int) (hpImage.getWidth() * 1.5), (int) (hpImage.getHeight() * 1.5));
    }
}
