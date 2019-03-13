import java.util.Arrays;
import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

public class Exercise1 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[scanner.nextInt()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }

        String result = Arrays.stream(arr)
                .filter(isEven())
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }

    private static IntPredicate isEven() {
        return it -> it % 2 == 0;
    }

    private static IntPredicate isOdd() {
        return isEven().negate();
    }

    private static IntPredicate isPrime() {
        return it -> {
            for (int i = 2; i < Math.sqrt(it); i++) {
                if (it % i == 0) {
                    return false;
                }
            }
            return true;
        };
    }

    private static IntPredicate biggerThan(int num) {
        return it -> it > num;
    }

}
