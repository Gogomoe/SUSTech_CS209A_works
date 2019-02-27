import java.awt.*;

import static java.awt.Color.BLACK;
import static java.lang.Math.*;

public class RotationFilter {

    public static void main(String[] args) {

        Picture source = new Picture(args[0]);
        double theta = Double.parseDouble(args[1]);

        int width = source.width();
        int height = source.height();
        int ci = height / 2;
        int cj = width / 2;
        double cos = cos(toRadians(theta));
        double sin = sin(toRadians(theta));

        Picture target = new Picture(width, height);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double ti = (i - ci) * cos - (j - cj) * sin + ci;
                double tj = (i - ci) * sin + (j - cj) * cos + cj;
                int tjj = (int) round(min(max(tj, 0), width - 1));
                int tii = (int) round(min(max(ti, 0), height - 1));
                target.set(tjj, tii, source.get(j, i));
            }
        }

        // 旋转丢的像素都用左边一个代替
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width - 2; j++) {
                Color c1 = target.get(j, i);
                Color c2 = target.get(j + 1, i);
                Color c3 = target.get(j + 2, i);
                if (!c1.equals(BLACK) && !c3.equals(BLACK) && c2.equals(BLACK)) {
                    target.set(j + 1, i, c1);
                }
            }
        }

        target.show();
    }

}
