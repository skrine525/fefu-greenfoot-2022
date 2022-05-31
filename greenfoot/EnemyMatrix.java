import greenfoot.*;
import java.util.*;

public class EnemyMatrix
{
    // Реализаця ячейки матрицы
    public class Cell{
        private int startX, startY;
        public int x, y;
        public EnemyBasic enemy;

        public Cell(int x, int y)
        {
            this.x = x;
            this.y = y;
            startX = x;
            startY = y;
        }

        public int GetStartX()
        {
            return startX;
        }

        public int GetStartY()
        {
            return startY;
        }
    }

    // Списки врагов в матрицу по состоянию
    public class SortedEnemiesByState
    {
        private EnemyMatrix matrix;
        private ArrayList<EnemyBasic> stay_list, enter_list, action_list, destroy_list;
        private int enemiesCount;

        public SortedEnemiesByState(EnemyMatrix matrix)
        {
            this.matrix = matrix;
            enemiesCount = 0;
            stay_list = new ArrayList<EnemyBasic>();
            enter_list = new ArrayList<EnemyBasic>();
            action_list = new ArrayList<EnemyBasic>();
            destroy_list = new ArrayList<EnemyBasic>();

            int rows = matrix.getRows();
            int columns = matrix.getColumns();
            for(int i = 0; i < rows; i++)
            {
                for(int j = 0; j < columns; j++)
                {
                    EnemyMatrix.Cell cell = matrix.getCell(i, j);
                    if(cell.enemy != null)
                    {
                        switch(cell.enemy.currentState)
                        {
                            case Stay: stay_list.add(cell.enemy); break;
                            case Enter: enter_list.add(cell.enemy); break;
                            case Action: action_list.add(cell.enemy); break;
                            case Destroy: destroy_list.add(cell.enemy); break;
                        }
                        enemiesCount++;
                    }
                }
            }
        }

        public ArrayList<EnemyBasic> GetEnemiesInStay()
        {
            return stay_list;
        }

        public ArrayList<EnemyBasic> GetEnemiesInEnter()
        {
            return enter_list;
        }

        public ArrayList<EnemyBasic> GetEnemiesInAction()
        {
            return action_list;
        }

        public ArrayList<EnemyBasic> GetEnemiesInDestroy()
        {
            return destroy_list;
        }

        public int GetEnemiesCount()
        {
            return enemiesCount;
        }
    }

    private Cell[][] matrix;
    private int rows, columns, beginX, beginY;
    private long frame = 0;
    private int animationType, animationDelta, animationPos, animationDir;

    // Конструктор EnemyMatrix
    public EnemyMatrix(int rows, int columns, int beginX, int beginY, int distance)
    {
        this.rows = rows;
        this.columns = columns;
        this.beginX = beginX;
        this.beginY = beginY;
        matrix = new Cell[rows][columns];

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                int xPos = beginX + distance * j;
                int yPos = beginY + distance * i;
                
                matrix[i][j] = new Cell(xPos, yPos);
            }
        }

        animationType = 1;
        animationDelta = beginX - 16;
        animationDir = 1;
        animationPos = 0;
    }

    // Возвращает количество пустых ячеек  матрицу
    public int GetEmptyCellCount()
    {
        int count = 0;
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                if(matrix[i][j].enemy == null)
                    count++;
            }
        }
        return count;
    }

    // Возвращает список врагов
    public ArrayList<EnemyBasic> GetEnemies()
    {
        ArrayList<EnemyBasic> enemies = new ArrayList<EnemyBasic>();
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                if(matrix[i][j].enemy != null)
                    enemies.add(matrix[i][j].enemy);
            }
        }
        return enemies;
    }

    // Возвращает объект отсортированных врагов по состоянию
    public SortedEnemiesByState GetSortedEnemiesByState()
    {
        return new SortedEnemiesByState(this);
    }

    // Возвращает ячейку
    public Cell getCell(int row, int column)
    {
        return matrix[row][column];
    }

    // Возвращает количество строк
    public int getRows()
    {
        return rows;
    }

    // Возвращает количество столбцов
    public int getColumns()
    {
        return columns;
    }

    // Добавляет врага в матрицу, если есть место
    public void AddEnemy(EnemyBasic enemy)
    {
        int count = GetEmptyCellCount();
        if(count > 0)
        {
            boolean isAdding = true;
            while(isAdding)
            {
                int i = Greenfoot.getRandomNumber(rows);
                int j = Greenfoot.getRandomNumber(columns);
                if(matrix[i][j].enemy == null)
                {
                    matrix[i][j].enemy = enemy;
                    enemy.SetCell(matrix[i][j]);
                    isAdding = false;
                }
            }
        }
    }

    // Обработка матрицы каждый кадр
    public void Act()
    {
        switch(animationType)
        {
            case 1: Animation1(); break;
            case 2: Animation2(); break;
        }

        frame++;
    }

    // Анимация перемещения по горизонтали
    private void Animation1()
    {
        // Обновление анимации каждый 5 кадр
        if(frame % 5 == 0)
        {
            if(animationDir == 1 && animationPos == animationDelta)
                animationDir = -1;
            else if(animationDir == -1 && animationPos == -animationDelta)
                animationDir = 1;
            animationPos += animationDir;

            for(int i = 0; i < rows; i++)
            {
                for(int j = 0; j < columns; j++)
                {
                    matrix[i][j].x += animationDir;
                }
            }

            if(animationPos == 0 && Greenfoot.getRandomNumber(100) % 2 == 0)
            {
                animationType = 2;
                animationDir = 1;
                animationDelta = beginX - 44;
            }
        }
    }

    // Анимация "Гармошки"
    private void Animation2()
    {
        // Обновление анимации каждый 5 кадр
        if(frame % 5 == 0)
        {
            if(animationDir == 1 && animationPos == animationDelta)
                animationDir = -1;
            else if(animationDir == -1 && animationPos == 0)
                animationDir = 1;
            animationPos += animationDir;

            int halfColumns = columns / 2;
            for(int i = 0; i < rows; i++)
            {
                for(int j = 0; j < columns; j++)
                {
                    if(j < halfColumns)
                        matrix[i][j].x -= animationDir * (i + 1);
                    else
                        matrix[i][j].x += animationDir * (i + 1);
                    matrix[i][j].y += animationDir * (i + 1);
                }
            }

            if(animationPos == 0 && Greenfoot.getRandomNumber(100) % 2 == 0)
            {
                animationType = 1;
                animationDir = 1;
                animationDelta = beginX - 16;
            }
        }
    }
}