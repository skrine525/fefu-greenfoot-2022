public class GameSystem
{
    // Вычисление угла между двумя точками на плоскости
    static double GetAngle(int x1, int y1, int x2, int y2, boolean deg)
    {
        float vX = x2 - x1;
        float vY = y2 - y1;
        double angle = Math.atan2(vY, vX);
        if(deg)
        {
            angle *= 180 / Math.PI;
            if(angle < 0)
                angle += 360;
        }
        else
        {
            if (angle < 0)
                angle += Math.PI;
        }
        return angle;
    }

    // То же самое, что и выше, только возвращает всегда градусы
    static double GetAngle(int x1, int y1, int x2, int y2)
    {
        return GetAngle(x1, y1, x2, y2, true);
    }

    // Вычисление расстояния между двумя точками на плоскости
    static double GetDistance(int x1, int y1, int x2, int y2)
    {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
