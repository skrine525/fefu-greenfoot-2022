import greenfoot.*;

public class ScoreLabel extends Actor
{
    // Поля, необходимые для работы
    private int length, imageWidth;
    private GreenfootImage images[]; 

    public ScoreLabel(int length)
    {
        // Присваивание значения полям
        this.length = length;

        LoadImages();   // Подгружаем изображения цифр
        imageWidth = length * images[0].getWidth();

        Show(0);
    }

    public void Show(int number)
    {
        // Создаем из целочисленной переменной массив цифр
        int[] digits = new int[length];
        for(int i = (length - 1); i >= 0; i--)
        {
            digits[i] = number % 10;
            number = number / 10;
        }

        // Формируем изображение
        GreenfootImage image = new GreenfootImage(imageWidth, images[0].getHeight());
        for(int i = 0; i < length; i++)
        {
            image.drawImage(images[digits[i]], i * (images[0].getWidth() - 1), 0);
        }
        
        setImage(image);  // Применяем сформированное изображение
    }

    private void LoadImages()
    {
        images = new GreenfootImage[10];
        for(int i = 0; i < 10; i++)
        {
            images[i] = new GreenfootImage(String.format("Digits/%d.png", i));
        }
    }
}
