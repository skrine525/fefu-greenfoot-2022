import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyMatrix
{
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

    private Cell[][] matrix;
    private int rows, columns, beginX, beginY;
    private long actCount = 0;

    private int animationDeltaX, animationPosX = 0, animationDirX = 1;

    public EnemyMatrix(int rows, int columns, int beginX, int beginY, int distance)
    {
        this.rows = rows;
        this.columns = columns;
        this.beginX = beginX;
        this.beginY = beginY;
        matrix = new Cell[rows][columns];

        animationDeltaX = beginX - 16;

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                int xPos = beginX + distance * j;
                int yPos = beginY + distance * i;
                
                matrix[i][j] = new Cell(xPos, yPos);
            }
        }
    }

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

    public void Act()
    {
        actCount++;

        // Обновление анимации каждый 3 кадр
        if(actCount % 5 == 0)
        {
            if(animationDirX == 1 && animationPosX == animationDeltaX)
                animationDirX = -1;
            else if(animationDirX == -1 && animationPosX == -animationDeltaX)
                animationDirX = 1;

            animationPosX += animationDirX;
            for(int i = 0; i < rows; i++)
            {
                for(int j = 0; j < columns; j++)
                {
                    matrix[i][j].x += animationDirX;
                }
            }
        }
    }
}