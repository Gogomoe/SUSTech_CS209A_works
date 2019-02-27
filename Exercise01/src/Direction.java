import java.awt.*;

enum Direction {
    UP, DOWN, LEFT, RIGHT;

    Direction L, R;

    static {
        UP.L = LEFT;
        UP.R = RIGHT;
        LEFT.L = DOWN;
        LEFT.R = UP;
        DOWN.L = RIGHT;
        DOWN.R = LEFT;
        RIGHT.L = UP;
        RIGHT.R = DOWN;
    }

    private static int xAxis = 1;
    private static int yAxis = 1;

    public Point forward(Point p) {
        Point p2 = new Point(p);
        switch (this) {
            case UP:
                p2.y -= yAxis;
                break;
            case DOWN:
                p2.y += yAxis;
                break;
            case LEFT:
                p2.x -= xAxis;
                break;
            case RIGHT:
                p2.x += xAxis;
                break;
        }
        return p2;
    }

    public static void reserveX() {
        xAxis *= -1;
    }

    public static void reserveY() {
        yAxis *= -1;
    }
}