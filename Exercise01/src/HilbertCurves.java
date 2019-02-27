import java.awt.*;

/**
 * Draw from the bottom left corner
 * The initial direction is up
 *
 * r1 = FRFRF
 * l1 = FLFLF
 *
 * r2 = R  l1 RF r1 LFL r1 FR l1  R
 * l2 = L  r1 LF l1 RFR l1 FL r1  L
 *
 * r3 = R  l2 RF r2 LFL r2 FR l2  R
 * l3 = L  r2 LF l2 RFR l2 FL r2  L
 */
public class HilbertCurves {

    private static Direction direction = Direction.UP;
    private static Point point = new Point();

    public static void main(String[] args) {
        System.out.println("use \"java HilbertCurves <Integer> \" to set the level of HilbertCurves (the default level is 4)");
        System.out.println("use \"java HilbertCurves <Integer> -d \" to disable double buffering");
        int level = 4;
        if (args.length >= 1) {
            level = Integer.parseInt(args[0]);
        }
        boolean enableDoubleBuffering = !(args.length >= 2 && args[1].equals("-d"));
        if (enableDoubleBuffering) {
            StdDraw.enableDoubleBuffering();
        }

        int size = 1;
        for (int i = 1; i < level; i++) {
            size = size * 2 + 1;
        }

        double min = size * -0.1;
        double max = size * 1.1;

        Direction.reserveY();
        StdDraw.setXscale(min, max);
        StdDraw.setYscale(min, max);

        drawRight(level);

        StdDraw.show();
    }

    private static void drawRight(int level) {
        if (level == 1) {
            forward();
            right();
            forward();
            right();
            forward();
            return;
        }
        int next = level - 1;
        right();

        drawLeft(next);
        right();
        forward();
        drawRight(next);
        left();
        forward();
        left();
        drawRight(next);
        forward();
        right();
        drawLeft(next);

        right();
    }

    private static void drawLeft(int level) {
        if (level == 1) {
            forward();
            left();
            forward();
            left();
            forward();
            return;
        }
        int next = level - 1;
        left();

        drawRight(next);
        left();
        forward();
        drawLeft(next);
        right();
        forward();
        right();
        drawLeft(next);
        forward();
        left();
        drawRight(next);

        left();
    }

    private static void forward() {
        Point p2 = direction.forward(point);
        StdDraw.line(point.x, point.y, p2.x, p2.y);
        point = p2;
    }

    private static void left() {
        direction = direction.L;
    }


    private static void right() {
        direction = direction.R;
    }

}
