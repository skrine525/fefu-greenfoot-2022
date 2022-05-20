import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class SpawnController  
{
    enum SpawnType { Type1Left, Type1Right, Type2Left, Type2Right, Type3Left, Type3Right }

    class SpawnQueue
    {
        public long frameStart;
        public int frameCooldown, count;
        SpawnType spawnType;
    }

    private World world;
    private EnemyMatrix enemyMatrix;
    private long frame;
    private ArrayList<SpawnQueue> spawnQueue;

    // Константы стандартного шанса появления типа врага
    public final int DEFAULT_CHANCE_ENEMYTYPE1 = 50;       // EnemyType1
    public final int DEFAULT_CHANCE_ENEMYTYPE2 = 30;       // EnemyType2
    public final int DEFAULT_CHANCE_ENEMYTYPE3 = 18;       // EnemyType3
    public final int DEFAULT_CHANCE_ENEMYTYPE4 = 2;        // EnemyType4


    public int chanceEnemyType1, chanceEnemyType2, chanceEnemyType3, chanceEnemyType4;
    
    public SpawnController(World world, EnemyMatrix enemyMatrix)
    {
        this.world = world;
        this.enemyMatrix = enemyMatrix;
        spawnQueue = new ArrayList<SpawnQueue>();

        chanceEnemyType1 = DEFAULT_CHANCE_ENEMYTYPE1;
        chanceEnemyType2 = DEFAULT_CHANCE_ENEMYTYPE2;
        chanceEnemyType3 = DEFAULT_CHANCE_ENEMYTYPE3;
        chanceEnemyType4 = DEFAULT_CHANCE_ENEMYTYPE4;
    }

    public void StartSpawn(SpawnType type, int count, int cooldown)
    {
        SpawnQueue queue = new SpawnQueue();
        queue.frameStart = frame;
        queue.frameCooldown = cooldown;
        queue.count = count;
        queue.spawnType = type;
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
                        case Type1Left: SpawnType1Left(); break;
                        case Type1Right: SpawnType1Right(); break;
                        case Type2Left: SpawnType2Left(); break;
                        case Type2Right: SpawnType2Right(); break;
                        case Type3Left: SpawnType3Left(); break;
                        case Type3Right: SpawnType3Right(); break;
                    }

                    spawn.count--;
                }
            }
            else
                spawnQueue.remove(spawn);
        }

        frame++;
    }

    private void SpawnType1Left()
    {
        ChanceSpawner spawner = new ChanceSpawner(4, 100);
        spawner.AddSpawnClass(EnemyType1.class, chanceEnemyType1);
        spawner.AddSpawnClass(EnemyType2.class, chanceEnemyType2);
        spawner.AddSpawnClass(EnemyType3.class, chanceEnemyType3);
        spawner.AddSpawnClass(EnemyType4.class, chanceEnemyType4);
        EnemyBasic enemy = (EnemyBasic) spawner.Spawn();
        enemy.currentState = EnemyBasic.State.Enter;

        enemyMatrix.AddEnemy(enemy);
        world.addObject(enemy, 40, 0);
    }

    private void SpawnType1Right()
    {
        ChanceSpawner spawner = new ChanceSpawner(4, 100);
        spawner.AddSpawnClass(EnemyType1A.class, chanceEnemyType1);
        spawner.AddSpawnClass(EnemyType2A.class, chanceEnemyType2);
        spawner.AddSpawnClass(EnemyType3A.class, chanceEnemyType3);
        spawner.AddSpawnClass(EnemyType4A.class, chanceEnemyType4);
        EnemyBasic enemy = (EnemyBasic) spawner.Spawn();
        enemy.currentState = EnemyBasic.State.Enter;

        enemyMatrix.AddEnemy(enemy);
        world.addObject(enemy, 360, 0);
    }

    private void SpawnType2Left()
    {
        ChanceSpawner spawner = new ChanceSpawner(4, 100);
        spawner.AddSpawnClass(EnemyType1B.class, chanceEnemyType1);
        spawner.AddSpawnClass(EnemyType2B.class, chanceEnemyType2);
        spawner.AddSpawnClass(EnemyType3B.class, chanceEnemyType2);
        spawner.AddSpawnClass(EnemyType4B.class, chanceEnemyType4);
        EnemyBasic enemy = (EnemyBasic) spawner.Spawn();
        enemy.currentState = EnemyBasic.State.Enter;

        enemyMatrix.AddEnemy(enemy);
        world.addObject(enemy, 0, 50);
    }

    private void SpawnType2Right()
    {
        ChanceSpawner spawner = new ChanceSpawner(4, 100);
        spawner.AddSpawnClass(EnemyType1C.class, chanceEnemyType1);
        spawner.AddSpawnClass(EnemyType2C.class, chanceEnemyType2);
        spawner.AddSpawnClass(EnemyType3C.class, chanceEnemyType3);
        spawner.AddSpawnClass(EnemyType4C.class, chanceEnemyType4);
        EnemyBasic enemy = (EnemyBasic) spawner.Spawn();
        enemy.currentState = EnemyBasic.State.Enter;

        enemyMatrix.AddEnemy(enemy);
        world.addObject(enemy, 399, 50);
    }

    private void SpawnType3Left()
    {
        ChanceSpawner spawner = new ChanceSpawner(4, 100);
        spawner.AddSpawnClass(EnemyType1D.class, chanceEnemyType1);
        spawner.AddSpawnClass(EnemyType2D.class, chanceEnemyType2);
        spawner.AddSpawnClass(EnemyType3D.class, chanceEnemyType3);
        spawner.AddSpawnClass(EnemyType4D.class, chanceEnemyType4);
        EnemyBasic enemy = (EnemyBasic) spawner.Spawn();
        enemy.currentState = EnemyBasic.State.Enter;

        enemyMatrix.AddEnemy(enemy);
        world.addObject(enemy, 0, 420);
    }

    private void SpawnType3Right()
    {
        ChanceSpawner spawner = new ChanceSpawner(4, 100);
        spawner.AddSpawnClass(EnemyType1E.class, chanceEnemyType1);
        spawner.AddSpawnClass(EnemyType2E.class, chanceEnemyType2);
        spawner.AddSpawnClass(EnemyType3E.class, chanceEnemyType3);
        spawner.AddSpawnClass(EnemyType4E.class, chanceEnemyType4);
        EnemyBasic enemy = (EnemyBasic) spawner.Spawn();
        enemy.currentState = EnemyBasic.State.Enter;

        enemyMatrix.AddEnemy(enemy);
        world.addObject(enemy, 399, 420);
    }
}