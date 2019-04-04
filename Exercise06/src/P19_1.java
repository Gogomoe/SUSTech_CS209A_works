import java.util.Random;

public class P19_1 {

    public static void main(String[] args) {

        Random r = new Random();

        int n = 10;
        while (true) {

            long start = System.currentTimeMillis();
            r.ints()
                    .filter(it -> it % 2 == 0)
                    .limit(n)
                    .count();
            long mid = System.currentTimeMillis();

            r.ints().parallel()
                    .filter(it -> it % 2 == 0)
                    .limit(n)
                    .count();
            long end = System.currentTimeMillis();

            if (end - mid < mid - start) {
                System.out.println(n);
                break;
            }

            n *= 10;
        }

    }

}
