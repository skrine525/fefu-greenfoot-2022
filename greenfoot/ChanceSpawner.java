import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ChanceSpawner
{
  private class ClassData
  {
    public Class<?> spawnClass;
    public int chanceStart, chanceEnd;
  }
  
  private int maxChance;
  private int classChanceStart;
  private ClassData[] classes;
  private int currentClassIndex, classCount;
  
  public ChanceSpawner(int count, int maxChance)
  {
    this.maxChance = maxChance;
    classChanceStart = 0;
    currentClassIndex = 0;
    classCount = count;
    classes = new ClassData[count];
  }
  
  public void AddSpawnClass(Class<?> spawnClass, int chance)
  {
    if(currentClassIndex < classCount)
    {
      classes[currentClassIndex] = new ClassData();
      classes[currentClassIndex].spawnClass = spawnClass;
      classes[currentClassIndex].chanceStart = classChanceStart;
      classChanceStart = classChanceStart + chance;
      classes[currentClassIndex].chanceEnd = classChanceStart - 1;
      currentClassIndex++;
    }
  }

  public Object Spawn()
  {
    int randomNumber = Greenfoot.getRandomNumber(maxChance);
    for(int i = 0; i < classCount; i++)
    {
        if(classes[i].chanceStart <= randomNumber && randomNumber <= classes[i].chanceEnd)
        {
            try
            {
                return classes[i].spawnClass.newInstance();
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