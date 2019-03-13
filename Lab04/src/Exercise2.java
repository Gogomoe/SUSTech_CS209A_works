import java.util.Arrays;
import java.util.Scanner;

public class Exercise2 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[scanner.nextInt()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }

        int result = Arrays.stream(arr)
                .reduce(0, (a, b) -> a + b * b);

        System.out.println(result);

    }


}
