import greenfoot.*;
import java.util.*;

public class Spawner  
{
    enum SpawnType { Type1Left, Type1Right, Type2Left, Type2Right, Type3Left, Type3Right }

    class SpawnQueue
    {
        public long frameStart;
        public int frameCooldown, count;
        public boolean addToMatrix;
        SpawnType spawnType;
    }

    private World world;
    private EnemyMatrix enemyMatrix;
    private long frame;
    private ArrayList<SpawnQueue> spawnQueue;

    // Константы стандартного шанса появления типа врага
    public static final int DEFAULT_CHANCE_ENEMYTYPE1 = 30;       // EnemyType1
    public static final int DEFAULT_CHANCE_ENEMYTYPE2 = 25;       // EnemyType2
    public static final int DEFAULT_CHANCE_ENEMYTYPE3 = 28;       // EnemyType3
    public static final int DEFAULT_CHANCE_ENEMYTYPE4 = 16;       // EnemyType4
    public static final int DEFAULT_CHANCE_ENEMYTYPE5 = 1;        // EnemyType5


    public int chanceEnemyType1, chanceEnemyType2, chanceEnemyType3, chanceEnemyType4, chanceEnemyType5;
    
    public Spawner(World world, EnemyMatrix enemyMatrix)
    {
        this.world = world;
        this.enemyMatrix = enemyMatrix;
        spawnQueue = new ArrayList<SpawnQueue>();

        chanceEnemyType1 = DEFAULT_CHANCE_ENEMYTYPE1;
        chanceEnemyType2 = DEFAULT_CHANCE_ENEMYTYPE2;
        chanceEnemyType3 = DEFAULT_CHANCE_ENEMYTYPE3;
        chanceEnemyType4 = DEFAULT_CHANCE_ENEMYTYPE4;
        chanceEnemyType5 = DEFAULT_CHANCE_ENEMYTYPE5;
    }

    public void StartSpawn(SpawnType type, int count, int cooldown, boolean addToMatrix)
    {
        SpawnQueue queue = new SpawnQueue();
        queue.frameStart = frame;
        queue.frameCooldown = cooldown;
        queue.count = count;
        queue.spawnType = type;
        queue.addToMatrix = addToMatrix;
        spawnQueue.add(queue);
    }

    public void Act()
    {
        for(SpawnQueue spawn : (ArrayList<SpawnQueue>) spawnQueue.clone())
        {
            if(spawn.count > 0)
            {
                if((frame - spawn.frameStart) % spawn.frameCooldown == 0)
                {
                    switch(spawn.spawnType)
                    {
                        case Type1Left: SpawnType1Left(spawn.addToMatrix); break;
                        case Type1Right: SpawnType1Right(spawn.addToMatrix); break;
                        case Type2Left: SpawnType2Left(spawn.addToMatrix); break;
                        case Type2Right: SpawnType2Right(spawn.addToMatrix); break;
                        case Type3Left: SpawnType3Left(spawn.addToMatrix); break;
                        case Type3Right: SpawnType3Right(spawn.addToMatrix); break;
                    }

                    spawn.count--;
                }
            }
            else
                spawnQueue.remove(spawn);
        }

        frame++;
    }

    private void SpawnType1Left(boolean addToMatrix)
    {
        ChanceSpawner spawner = new ChanceSpawner(5, 100);
        spawner.AddSpawnClass(EnemyType1.class, chanceEnemyType1);
        spawner.AddSpawnClass(EnemyType2.class, chanceEnemyType2);
        spawner.AddSpawnClass(EnemyType3.class, chanceEnemyType3);
        spawner.AddSpawnClass(EnemyType4.class, chanceEnemyType4);
        spawner.AddSpawnClass(EnemyType5.class, chanceEnemyType5);
        EnemyBasic enemy = (EnemyBasic) spawner.Spawn();
        enemy.currentState = EnemyBasic.State.Enter;


        if(addToMatrix)
            enemyMatrix.AddEnemy(enemy);
        world.addObject(enemy, 40, 0);
    }

    private void SpawnType1Right(boolean addToMatrix)
    {
        ChanceSpawner spawner = new ChanceSpawner(5, 100);
        spawner.AddSpawnClass(EnemyType1A.class, chanceEnemyType1);
        spawner.AddSpawnClass(EnemyType2A.class, chanceEnemyType2);
        spawner.AddSpawnClass(EnemyType3A.class, chanceEnemyType3);
        spawner.AddSpawnClass(EnemyType4A.class, chanceEnemyType4);
        spawner.AddSpawnClass(EnemyType5A.class, chanceEnemyType5);
        EnemyBasic enemy = (EnemyBasic) spawner.Spawn();
        enemy.currentState = EnemyBasic.State.Enter;

        if(addToMatrix)
            enemyMatrix.AddEnemy(enemy);
        world.addObject(enemy, 360, 0);
    }

    private void SpawnType2Left(boolean addToMatrix)
    {
        ChanceSpawner spawner = new ChanceSpawner(5, 100);
        spawner.AddSpawnClass(EnemyType1B.class, chanceEnemyType1);
        spawner.AddSpawnClass(EnemyType2B.class, chanceEnemyType2);
        spawner.AddSpawnClass(EnemyType3B.class, chanceEnemyType3);
        spawner.AddSpawnClass(EnemyType4B.class, chanceEnemyType4);
        spawner.AddSpawnClass(EnemyType5B.class, chanceEnemyType5);
        EnemyBasic enemy = (EnemyBasic) spawner.Spawn();
        enemy.currentState = EnemyBasic.State.Enter;

        enemyMatrix.AddEnemy(enemy);
        world.addObject(enemy, 0, 50);
    }

    private void SpawnType2Right(boolean addToMatrix)
    {
        ChanceSpawner spawner = new ChanceSpawner(5, 100);
        spawner.AddSpawnClass(EnemyType1C.class, chanceEnemyType1);
        spawner.AddSpawnClass(EnemyType2C.class, chanceEnemyType2);
        spawner.AddSpawnClass(EnemyType3C.class, chanceEnemyType3);
        spawner.AddSpawnClass(EnemyType4C.class, chanceEnemyType4);
        spawner.AddSpawnClass(EnemyType5C.class, chanceEnemyType5);
        EnemyBasic enemy = (EnemyBasic) spawner.Spawn();
        enemy.currentState = EnemyBasic.State.Enter;

        if(addToMatrix)
            enemyMatrix.AddEnemy(enemy);
        world.addObject(enemy, 399, 50);
    }

    private void SpawnType3Left(boolean addToMatrix)
    {
        ChanceSpawner spawner = new ChanceSpawner(5, 100);
        spawner.AddSpawnClass(EnemyType1D.class, chanceEnemyType1);
        spawner.AddSpawnClass(EnemyType2D.class, chanceEnemyType2);
        spawner.AddSpawnClass(EnemyType3D.class, chanceEnemyType3);
        spawner.AddSpawnClass(EnemyType4D.class, chanceEnemyType4);
        spawner.AddSpawnClass(EnemyType5D.class, chanceEnemyType5);
        EnemyBasic enemy = (EnemyBasic) spawner.Spawn();
        enemy.currentState = EnemyBasic.State.Enter;

        if(addToMatrix)
            enemyMatrix.AddEnemy(enemy);
        world.addObject(enemy, 0, 420);
    }

    private void SpawnType3Right(boolean addToMatrix)
    {
        ChanceSpawner spawner = new ChanceSpawner(5, 100);
        spawner.AddSpawnClass(EnemyType1E.class, chanceEnemyType1);
        spawner.AddSpawnClass(EnemyType2E.class, chanceEnemyType2);
        spawner.AddSpawnClass(EnemyType3E.class, chanceEnemyType3);
        spawner.AddSpawnClass(EnemyType4E.class, chanceEnemyType4);
        spawner.AddSpawnClass(EnemyType5E.class, chanceEnemyType5);
        EnemyBasic enemy = (EnemyBasic) spawner.Spawn();
        enemy.currentState = EnemyBasic.State.Enter;

        if(addToMatrix)
            enemyMatrix.AddEnemy(enemy);
        world.addObject(enemy, 399, 420);
    }
}

class ChanceSpawner {

    private class ClassData {
        public Class <?> spawnClass;
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

    public void AddSpawnClass(Class <?> spawnClass, int chance)
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
                catch (InstantiationException e) {
                    return null;
                }
                catch (IllegalAccessException iae) {
                    return null;
                }
            }
        }
        return null;
    }
}