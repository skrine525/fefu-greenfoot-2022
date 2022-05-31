import greenfoot.*;
import java.util.*;

public class GameplayInf extends Gameplay
{
    // Стадия
    private int stageNumber = 1;								// Номер стадии
    private int stageStartFrame = 60;							// Номер кадра, с которого начинается стадия
    private boolean canHandleStage = false;						// Переменная, разрешащая обрабатывать стадию

    // Спавн
    private int spawnCount = 0;									// Количество конвоев, которое осталось заспавнить
    private int spawnCountMax = 0;								// Количество конвоев, которое необходимо заспавнить
    private int spawnCountToDoAction = 0;						// Количество конвоев, которое осталось заспавнить
    private int spawnStartFrame = 0;							// Номер кадра, с которого начинается спавн конвоя
    private boolean canSpawn = true;							// Переменная, разрешающая спавн конвоя
    private boolean canAddToMatrix = true;						// Переменная, указывающая на возможность добавление в матрицу
    private boolean isPairedSpawn = false;						// Переменная, указывающая на парность спавна

    // Действие
    private int actionEnymyCount = 0;							// Количество врагов, которое осталось перевести в Action
    private int actionStartFrame = 0;							// Номер кадра, с которого начинается Action врагов
    private boolean canAction = false;							// Переменная, разрешающая изменение состояния на Action
    private boolean actionDisableForEnemyType3 = false;         // Переменная, отключающая возможность действия для EnemyType3

    // Прочее
    private StageLabel stageLabel;                              // Метка номера стадии

    // Константы
    private static final int ACTION_COUNT_IN_STAGE_MIN = 5;		// Константа минимального числа Action в стадии
    private static final int ACTION_COUNT_IN_STAGE_MAX = 10;	// Константа максимального числа Action в стадии

    public GameplayInf()
    {
        super();

        stageLabel = new StageLabel();                                                          // Инициализируем метку номера стадии
        addObject(stageLabel, 199, getHeight() / 2);                                            // Добавляем метку номера стадии в мир
    }

    @Override
    protected void OnSpawn(long frame)
    {
        if(canHandleStage){
        	if(canAction)
        	{
                EnemyMatrix.SortedEnemiesByState sortedEnemies = enemyMatrix.GetSortedEnemiesByState();

        		ArrayList<EnemyBasic> actionableEnemies = new ArrayList<EnemyBasic>();
                for(EnemyBasic enemy : sortedEnemies.GetEnemiesInStay())
                {
                    if(!actionDisableForEnemyType3 || !(enemy instanceof EnemyType3))
                        actionableEnemies.add(enemy);
                }

            	if(actionableEnemies.size() > 0)
            	{
            		if(frame >= actionStartFrame)
		            {
		            	
	            		int enemyIndex = Greenfoot.getRandomNumber(actionableEnemies.size());
	            		actionableEnemies.get(enemyIndex).currentState = EnemyBasic.State.Action;
	            		actionStartFrame = (int) frame + 30;

		            	actionEnymyCount--;
		            	if(actionEnymyCount <= 0)
		            		canAction = false;
		            }
            	}
            	else
            		canAction = false;
        	}
            else if(canSpawn)
            {
                if(GetPlayer().IsHit())
                    spawnStartFrame = (int) frame + 20;
                else if(frame >= spawnStartFrame)
                {
                    if(SpawnConvoy())
                        spawnCountToDoAction--; // Уменьшаем на единицу каждый спавн
                    else
                        DoAction(frame, 3, false);
                    canSpawn = false;
                }
            }
        	else if(spawnCount <= 0)
        	{
                if(!GetPlayer().IsHit())
                {
                    EnemyMatrix.SortedEnemiesByState sortedEnemies = enemyMatrix.GetSortedEnemiesByState();
                    if(sortedEnemies.GetEnemiesCount() == 0)
                    {
                        canHandleStage = false;
                        stageNumber++;
                        stageStartFrame = (int) frame + 60;
                        SetBackgroundSoundVolume(25);                           // Уменьшаем громкость фоновой музыки
                    }
                    else
                    {
                        if(sortedEnemies.GetEnemiesInAction().size() > 0)
                        {
                            boolean canDoAction = true;
                            for(EnemyBasic enemy : sortedEnemies.GetEnemiesInAction())
                            {
                                if(!(enemy instanceof EnemyType3))
                                {
                                    canDoAction = false;
                                    break;
                                }
                            }

                            if(canDoAction)
                            {
                                DoAction(frame, 1, true);
                            }
                        }
                        else if(sortedEnemies.GetEnemiesInStay().size() == sortedEnemies.GetEnemiesCount())
                            DoAction(frame, 3, false);
                    }   
                }
        	}
            else
            {
                if(!GetPlayer().IsHit())
                {
                    EnemyMatrix.SortedEnemiesByState sortedEnemies = enemyMatrix.GetSortedEnemiesByState();

                    if(sortedEnemies.GetEnemiesInAction().size() > 0)
                    {
                        boolean canDoAction = true;
                        for(EnemyBasic enemy : sortedEnemies.GetEnemiesInAction())
                        {
                            if(!(enemy instanceof EnemyType3))
                            {
                                canDoAction = false;
                                break;
                            }
                        }

                        if(canDoAction)
                        {
                            DoAction(frame, 1, true);
                        }
                    }
                    else if(sortedEnemies.GetEnemiesInStay().size() == sortedEnemies.GetEnemiesCount())
                    {
                        if(spawnCountToDoAction <= 0)
                        {
                            DoAction(frame, 3, false);
                            spawnCountToDoAction = ACTION_COUNT_IN_STAGE_MIN + Greenfoot.getRandomNumber(65536) % (ACTION_COUNT_IN_STAGE_MAX - ACTION_COUNT_IN_STAGE_MIN + 1);
                        }
                        else
                        {
                            canSpawn = true;
                            spawnStartFrame = (int) frame + 20;
                            isPairedSpawn = (Greenfoot.getRandomNumber(2) == 0) ? true : false;
                        }
                    }
                }
            }
        }
        else
        {
            if(frame == stageStartFrame)
            {
            	stageLabel.Show(stageNumber);							// Отображаем метку стадии
                Greenfoot.playSound("Stage.wav");                       // Проигрываем звук начала стадии
            }
            else if(frame >= stageStartFrame + 120){
                spawnStartFrame = (int) frame + 20;
                spawnCountMax = 12 + 4 * (stageNumber - 1);
                spawnCount = spawnCountMax;
                spawnCountToDoAction = ACTION_COUNT_IN_STAGE_MIN + Greenfoot.getRandomNumber(65536) % (ACTION_COUNT_IN_STAGE_MAX - ACTION_COUNT_IN_STAGE_MIN + 1);
                isPairedSpawn = (Greenfoot.getRandomNumber(2) == 0) ? true : false;
                canHandleStage = true;

                stageLabel.Hide();										// Скрываем метку стадии

                // Включаем фоновую музыку
                SetBackgroundSoundVolume(40);
                PlayBackgroundSound();
            }
        }

        // Для тестирования
    	showText(String.valueOf(spawnCountToDoAction), getWidth() - 20, getHeight() - 20);
        showText(String.valueOf(spawnCount), 20, getHeight() - 20);
    }

    // Спавнит рандомный конвой
    private boolean SpawnConvoy()
    {
    	int emptyCellCount = enemyMatrix.GetEmptyCellCount();

        if(isPairedSpawn && spawnCount >= 2)
        {
        	if(emptyCellCount >= 16)
        	{
	        	int spawnType = Greenfoot.getRandomNumber(3);
	        	spawnCount -= 2;
		        switch(spawnType)
		        {
		            case 0:
		            spawner.StartSpawn(Spawner.SpawnType.Type1Left, 8, 8, canAddToMatrix);
		            spawner.StartSpawn(Spawner.SpawnType.Type1Right, 8, 8, canAddToMatrix);
		            break;

		            case 1:
		            spawner.StartSpawn(Spawner.SpawnType.Type2Left, 8, 8, canAddToMatrix);
		            spawner.StartSpawn(Spawner.SpawnType.Type2Right, 8, 8, canAddToMatrix);
		            break;

		            case 2:
		            spawner.StartSpawn(Spawner.SpawnType.Type3Left, 8, 8, canAddToMatrix);
		            spawner.StartSpawn(Spawner.SpawnType.Type3Right, 8, 8, canAddToMatrix);
		            break;
		        }

		        return true;
		    }
        }
        else
        {
        	if(emptyCellCount >= 8)
        	{
        		int spawnType = Greenfoot.getRandomNumber(6);
	        	spawnCount--;
		        switch(spawnType)
		        {
		            case 0: spawner.StartSpawn(Spawner.SpawnType.Type1Left, 8, 8, canAddToMatrix); break;
		            case 1: spawner.StartSpawn(Spawner.SpawnType.Type1Right, 8, 8, canAddToMatrix); break;
		            case 2: spawner.StartSpawn(Spawner.SpawnType.Type2Left, 8, 8, canAddToMatrix); break;
		            case 3: spawner.StartSpawn(Spawner.SpawnType.Type2Right, 8, 8, canAddToMatrix); break;
		            case 4: spawner.StartSpawn(Spawner.SpawnType.Type3Left, 8, 8, canAddToMatrix); break;
		            case 5: spawner.StartSpawn(Spawner.SpawnType.Type3Right, 8, 8, canAddToMatrix); break;
		        }

		        return true;
        	}
        }

        return false;
    }

    // Запускает Action
    private void DoAction(long frame, int count, boolean disableEnemyType3)
    {
        canAction = true;
        actionStartFrame = (int) frame + 60;
        actionEnymyCount = count;
        actionDisableForEnemyType3 = disableEnemyType3;
    }
}
