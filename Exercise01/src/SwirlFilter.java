import static java.lang.Math.*;

public class SwirlFilter {

    public static void main(String[] args) {

        Picture source = new Picture(args[0]);

        int width = source.width();
        int height = source.height();
        int ci = height / 2;
        int cj = width / 2;

        Picture target = new Picture(width, height);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double theta = PI / 256 * sqrt(pow(i - ci, 2) + pow(j - cj, 2));
                double cos = cos(theta);
                double sin = sin(theta);
                double ti = (i - ci) * cos - (j - cj) * sin + ci;
                double tj = (i - ci) * sin + (j - cj) * cos + cj;
                int tjj = (int) round(min(max(tj, 0), width - 1));
                int tii = (int) round(min(max(ti, 0), height - 1));
                target.set(tjj, tii, source.get(j, i));
            }
        }

        target.show();
    }

}
