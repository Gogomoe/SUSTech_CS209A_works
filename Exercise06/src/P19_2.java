import java.util.stream.IntStream;

public class P19_2 {

    public static void main(String[] args) {

        generate().limit(10).forEach(System.out::println);

    }

    private static IntStream generate() {
        return IntStream.iterate(0, i -> i + 1)
                .map(it -> it * it)
                .filter(P19_2::palindrome);
    }

    private static boolean palindrome(int it) {
        int length = (int) Math.ceil(Math.log10(it + 0.1));
        for (int i = 0; i < length / 2; i++) {
            int a = getNum(it, i, length);
            int b = getNum(it, length - 1 - i, length);
            if (a != b) {
                return false;
            }
        }
        return true;
    }

    private static int getNum(int num, int i, int length) {
        return num / pow10(length - i - 1) % 10;
    }

    private static int pow10(int i) {
        return (int) Math.pow(10, i);
    }

}
