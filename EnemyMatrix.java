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

    public EnemyMatrix(int beginX, int beginY)
    {
        matrix = new Cell[5][10];
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                int xPos = beginX + 34 * j;
                int yPos = beginY + 34 * i;
                
                matrix[i][j] = new Cell(xPos, yPos);
            }
        }
    }

    public int GetEmptyCellCount()
    {
        int count = 0;
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 10; j++)
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
                int i = Greenfoot.getRandomNumber(5);
                int j = Greenfoot.getRandomNumber(10);
                if(matrix[i][j].enemy == null)
                {
                    matrix[i][j].enemy = enemy;
                    enemy.SetCell(matrix[i][j]);
                    isAdding = false;
                }
            }
        }
    }

    public void moveCells(int deltaX, int deltaY)
    {
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                matrix[i][j].x += deltaX;
                matrix[i][j].y += deltaY;
            }
        }
    }
}