import java.util.Random;

import static java.lang.Math.*;

public class GlassFilter {

    public static void main(String[] args) {

        Picture source = new Picture(args[0]);
        Random random = new Random();

        int width = source.width();
        int height = source.height();

        Picture target = new Picture(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int xmin = min(max(x - 5, 0), width - 1);
                int xmax = min(max(x + 5, 0), width - 1);
                int ymin = min(max(y - 5, 0), height - 1);
                int ymax = min(max(y + 5, 0), height - 1);
                int tx = random.nextInt(xmax - xmin) + xmin;
                int ty = random.nextInt(ymax - ymin) + ymin;
                target.set(x, y, source.get(tx, ty));
            }
        }

        target.show();
    }

}
