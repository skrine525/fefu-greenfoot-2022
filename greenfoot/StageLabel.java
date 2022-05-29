import greenfoot.*;
import java.util.*;

public class StageLabel extends Actor
{
    // Поля, необходимые для работы
    private GreenfootImage stageImage, emptyImage;
    private GreenfootImage[] digitImages;

    public StageLabel()
    {

    	stageImage = new GreenfootImage("Text/Stage.png");		// Подгружаем изображение надписи "Stage"
        LoadDigitImages();   									// Подгружаем изображения цифр

        emptyImage = new GreenfootImage(1, 1);
        setImage(emptyImage);
    }

    public void Show(int number)
    {
        // Создаем из целочисленной переменной массив цифр
        ArrayList<Integer> digits = new ArrayList<Integer>();
        while(number != 0)
        {
        	digits.add(number % 10);
        	number = number / 10;
        }
        Collections.reverse(digits);	// "Переворачиваем" массив

        // Формируем изображение
        int imageWidth = stageImage.getWidth() + digitImages[0].getWidth() * (digits.size() + 1);
        int imageHeight = stageImage.getHeight();
        GreenfootImage image = new GreenfootImage(imageWidth, imageHeight);
        image.drawImage(stageImage, 0, 0);

        // Добавляем цифры на экран из массива
        for(int i = 0; i < digits.size(); i++)
        {
        	//int digitX = stageImage.getWidth() + digitImages[0].getWidth() * (i + 1);
        	int digitX = stageImage.getWidth() + digitImages[0].getWidth() * (i + 1) - 1;
        	image.drawImage(digitImages[digits.get(i)], digitX, 0);
        }
        
        setImage(image);  // Применяем сформированное изображение
    }

    public void Hide()
    {
    	setImage(emptyImage);
    }

    private void LoadDigitImages()
    {
        digitImages = new GreenfootImage[10];
        for(int i = 0; i < 10; i++)
        {
            digitImages[i] = new GreenfootImage(String.format("Digits/%d.png", i));
        }
    }
}
