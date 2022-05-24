import greenfoot.*;
import java.util.*;

public class GameplayInf extends Gameplay
{
    private TextObject textObject;

    // Стадия
    private int stageNumber = 1;								// Номер стадии
    private int stageStartFrame = 60;							// Номер кадра, с которого начинается стадия
    private boolean canHandleStage = false;						// Переменная, разрешащая обрабатывать стадию

    // Спавн
    private int spawnCount = 0;									// Количество конвоев, которое осталось заспавнить
    private int spawnCountInStage = 0;							// Общее количество конвоев, которое необходимо заспавнить в стадии
    private int spawnStartFrame = 0;							// Номер кадра, с которого начинается спавно конвоя
    private boolean canSpawn = true;							// Переменная, разрешающая спавн конвоя
    private boolean canAddToMatrix = true;						// Переменная, указывающая на возможность добавление в матрицу
    private boolean isPairedSpawn = false;						// Переменная, указывающая на парность спавна

    // Действие
    private int actionCount = 0;								// Количество врагов, которое осталось перевести в Action
    private int actionStartFrame = 0;							// Номер кадра, с которого начинается Action врагов
    private boolean canAction = false;							// Переменная, разрешающая изменение состояния на Action

    public GameplayInf()
    {
        super();
    }

    @Override
    protected void OnSpawn(long frame)
    {
        if(canHandleStage){
        	if(canAction)
        	{
        		if(frame >= actionStartFrame)
	            {
	            	ArrayList<EnemyBasic> stayingEnemies = new ArrayList<EnemyBasic>();
	            	for(EnemyBasic enemy : enemyMatrix.GetEnemies())
	            	{
	            		if(enemy.currentState == EnemyBasic.State.Stay)
	            			stayingEnemies.add(enemy);
	            	}
	            	
	            	if(stayingEnemies.size() > 0)
	            	{
	            		int enemyIndex = Greenfoot.getRandomNumber(stayingEnemies.size());
	            		stayingEnemies.get(enemyIndex).currentState = EnemyBasic.State.Action;
	            		actionStartFrame = (int) frame + 30;
	            	}

	            	actionCount--;
	            	if(actionCount <= 0)
	            		canAction = false;
	            }
        	}
        	else if(spawnCount <= 0)
        	{
        		boolean enemiesDontExist = true;
        		for(EnemyBasic enemy : enemyMatrix.GetEnemies())
            	{
            		if(enemy != null)
            		{
            			enemiesDontExist = false;
            			break;
            		}
            	}

            	if(enemiesDontExist)
            	{
            		canHandleStage = false;
            		stageNumber++;
            		stageStartFrame = (int) frame + 60;
            	}
        	}
            else if(canSpawn)
            {
            	if(frame >= spawnStartFrame)
            	{
            		if(!SpawnConvoy()){
            			canAction = true;
            			actionStartFrame = (int) frame + 60;
            			actionCount = 3;
            		}
                	canSpawn = false;
            	}
            }
            else
            {
                boolean canStartSpawnConvoy = true;

                int rows = enemyMatrix.getRows();
                int columns = enemyMatrix.getColumns();
                for(int i = 0; i < rows; i++)
                {
                    for(int j = 0; j < columns; j++)
                    {
                        EnemyMatrix.Cell cell = enemyMatrix.getCell(i, j);
                        if(cell.enemy != null && cell.enemy.currentState != EnemyBasic.State.Stay)
                            canStartSpawnConvoy = false;
                    }
                }

                if(canStartSpawnConvoy)
                {
                    canSpawn = true;
                    spawnStartFrame = (int) frame + 20;
                }
            }
        }
        else
        {
            if(frame == stageStartFrame)
            {
                textObject = new TextObject();
                GreenfootImage image = new GreenfootImage(100, 100);
                image.setFont(new Font("Arial", false, false , 20));
                image.setColor(Color.RED);
                image.drawString("Stage " + stageNumber, 1, 50);
                textObject.setImage(image);
                addObject(textObject, getWidth() / 2, getHeight() / 2);
            }
            else if(frame >= stageStartFrame + 120){
                spawnStartFrame = (int) frame + 20;
                spawnCount = 12 + 4 * (stageNumber - 1);
                isPairedSpawn = (Greenfoot.getRandomNumber(2) == 0) ? true : false;
                canHandleStage = true;

                removeObject(textObject);
            }
        }
    }

    private boolean SpawnConvoy()
    {
    	int emptyCellCount = enemyMatrix.GetEmptyCellCount();

        if(isPairedSpawn)
        {
        	if(emptyCellCount >= 8)
        	{
	        	int spawnType = Greenfoot.getRandomNumber(3);
	        	spawnCount -= 2;
		        switch(spawnType)
		        {
		            case 0:
		            spawner.StartSpawn(Spawner.SpawnType.Type1Left, 4, 8, canAddToMatrix);
		            spawner.StartSpawn(Spawner.SpawnType.Type1Right, 4, 8, canAddToMatrix);
		            break;

		            case 1:
		            spawner.StartSpawn(Spawner.SpawnType.Type2Left, 4, 8, canAddToMatrix);
		            spawner.StartSpawn(Spawner.SpawnType.Type2Right, 4, 8, canAddToMatrix);
		            break;
		            case 3:
		            spawner.StartSpawn(Spawner.SpawnType.Type3Left, 4, 8, canAddToMatrix);
		            spawner.StartSpawn(Spawner.SpawnType.Type3Right, 4, 8, canAddToMatrix);
		            break;
		        }

		        return true;
		    }
        }
        else
        {
        	if(emptyCellCount >= 4)
        	{
        		int spawnType = Greenfoot.getRandomNumber(6);
	        	spawnCount--;
		        switch(spawnType)
		        {
		            case 0: spawner.StartSpawn(Spawner.SpawnType.Type1Left, 4, 8, canAddToMatrix); break;
		            case 1: spawner.StartSpawn(Spawner.SpawnType.Type1Right, 4, 8, canAddToMatrix); break;
		            case 2: spawner.StartSpawn(Spawner.SpawnType.Type2Left, 4, 8, canAddToMatrix); break;
		            case 3: spawner.StartSpawn(Spawner.SpawnType.Type2Right, 4, 8, canAddToMatrix); break;
		            case 4: spawner.StartSpawn(Spawner.SpawnType.Type3Left, 4, 8, canAddToMatrix); break;
		            case 5: spawner.StartSpawn(Spawner.SpawnType.Type3Right, 4, 8, canAddToMatrix); break;
		        }

		        return true;
        	}
        }

        return false;
    }
}
