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
    private long frame = 0;
    private int animationType, animationDelta, animationPos, animationDir;

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