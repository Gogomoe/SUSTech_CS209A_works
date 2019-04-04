import java.util.stream.IntStream;

public class E19_11 {

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);

        IntStream.range(1, n + 1)
                .map(it -> it * it)
                .mapToObj(String::valueOf)
                .filter(E19_11::palindrome)
                .forEach(System.out::println);

    }

    private static boolean palindrome(String it) {
        for (int i = 0; i < it.length() / 2; i++) {
            if (it.charAt(0) != it.charAt(it.length() - 1 - i))
                return false;
        }
        return true;
    }


}
