import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class DragonCurves {

    private static Direction direction = Direction.RIGHT;
    private static Point point = new Point(0, 0);
    private static List<Line2D.Float> lines = new ArrayList<>();

    private static double minX;
    private static double maxX;
    private static double minY;
    private static double maxY;

    public static void main(String[] args) {
        System.out.println("use \"java DragonCurves <Integer> \" to set the level of DragonCurves (the default level is 16)");
        System.out.println("use \"java DragonCurves <Integer> -d \" to disable double buffering");
        int level = 16;
        if (args.length >= 1) {
            level = Integer.parseInt(args[0]);
        }
        boolean enableDoubleBuffering = !(args.length >= 2 && args[1].equals("-d"));
        if (enableDoubleBuffering) {
            StdDraw.enableDoubleBuffering();
        }

        dragon(level);
        calculateCanvasSize();
        StdDraw.setXscale(minX, maxX);
        StdDraw.setYscale(minY, maxY);

        lines.forEach(it -> {
            StdDraw.line(it.x1, it.y1, it.x2, it.y2);
        });
        StdDraw.show();
    }

    private static void calculateCanvasSize() {
        double size = max(maxX - minX, maxY - minY) * 1.2;
        double centerX = (minX + maxX) / 2;
        double centerY = (minY + maxY) / 2;
        minX = centerX - size / 2;
        maxX = centerX + size / 2;
        minY = centerY + size / 2;
        maxY = centerY - size / 2;
    }

    private static void dragon(int i) {
        if (i == 1) {
            forward();
            return;
        }
        dragon(i - 1);
        left();
        nogard(i - 1);
    }


    private static void nogard(int i) {
        if (i == 1) {
            forward();
            return;
        }
        dragon(i - 1);
        right();
        nogard(i - 1);
    }

    private static void left() {
        direction = direction.L;
    }

    private static void right() {
        direction = direction.R;
    }

    private static void forward() {
        Point p2 = direction.forward(point);
        minX = min(p2.x, minX);
        maxX = max(p2.x, maxX);
        minY = min(p2.y, minY);
        maxY = max(p2.y, maxY);
        lines.add(new Line2D.Float(point.x, point.y, p2.x, p2.y));
        point = p2;
    }
}
