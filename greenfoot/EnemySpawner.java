import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

class EnemySpawner
{
  private class ClassData
  {
    public Class<?> spawnClass;
    public int chanceStart, chanceEnd;
  }
  
  private int maxChance; // Переменная значения максимального шанса
  private int classChanceStart;   // Начальное значение шанса
  private ClassData[] classes;
  private int currentClassIndex, classCount;
  
  public EnemySpawner(int count, int maxChance)
  {
    this.maxChance = maxChance;
    this.classChanceStart = 0;
    this.currentClassIndex = 0;
    classCount = count;
    this.classes = new ClassData[count];
  }
  
  public void AddEnemyClass(Class<?> enemyClass, int chance)
  {
    if(currentClassIndex < classCount)
    {
      classes[currentClassIndex] = new ClassData();
      classes[currentClassIndex].spawnClass = enemyClass;
      classes[currentClassIndex].chanceStart = classChanceStart;
      classChanceStart = classChanceStart + chance;
      classes[currentClassIndex].chanceEnd = classChanceStart - 1;
      currentClassIndex++;
    }
  }

  public EnemyBasic Spawn()
  {
    int randomNumber = Greenfoot.getRandomNumber(maxChance);
    for(int i = 0; i < classCount; i++)
    {
        if(classes[i].chanceStart <= randomNumber && randomNumber <= classes[i].chanceEnd)
        {
            try
            {
                return (EnemyBasic) classes[i].spawnClass.newInstance();
            }
            catch(InstantiationException e)
            {
                return null;
            }
            catch (IllegalAccessException iae)
            {
                return null;
            }
        }
    }
    return null;
  }
}