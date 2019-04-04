import java.util.Optional;
import java.util.Random;

public class E19_10 {

    public static void main(String[] args) {

        Random r = new Random();
        r.ints(2, 100)
                .limit(20)
                .mapToObj(it -> it + ": " + smallestProperDivisor(it))
                .forEach(System.out::println);

    }

    public static Optional<Integer> smallestProperDivisor(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

}
