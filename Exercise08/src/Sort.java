import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class Sort {

    private static Random r = new Random();

    public static void main(String[] args) {

        System.out.println("Integer test");
        System.out.println();

        for (int i = 10; i <= 10000; i *= 10) {

            System.out.println("For n = " + i);

            final Integer[] array1 = generateArray(Integer[]::new, r::nextInt, i);
            System.out.printf("bulb sort cost %d ms\n", recordTime(() -> {
                bulbSort(array1, Comparator.comparingInt(a -> (int) a).reversed());
            }));

            final Integer[] array2 = generateArray(Integer[]::new, r::nextInt, i);
            System.out.printf("quick sort cost %d ms\n", recordTime(() -> {
                quickSort(array2, Comparator.comparingInt(a -> (int) a).reversed());
            }));

            System.out.println();
        }

        System.out.println("String test");
        System.out.println();

        for (int i = 10; i <= 10000; i *= 10) {

            System.out.println("For n = " + i);

            final String[] array1 = generateArray(String[]::new, Sort::randomString, i);
            System.out.printf("bulb sort cost %d ms\n", recordTime(() -> {
                bulbSort(array1, String::compareTo);
            }));

            final String[] array2 = generateArray(String[]::new, Sort::randomString, i);
            System.out.printf("quick sort cost %d ms\n", recordTime(() -> {
                quickSort(array2, String::compareTo);
            }));

            System.out.println();
        }
    }

    private static long recordTime(Runnable runnable) {
        Instant start = Instant.now();

        runnable.run();

        Instant end = Instant.now();

        return Duration.between(start, end).toMillis();
    }


    public static <T> void bulbSort(T[] array, Comparator<T> comp) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (comp.compare(array[j], array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    public static <T> void quickSort(T[] array, Comparator<T> comp) {
        quickSort(array, comp, 0, array.length);
    }

    private static <T> void quickSort(T[] array, Comparator<T> comp, int start, int end) {
        if (end - start <= 1) {
            return;
        }
        int index = partition(array, comp, start, end);
        quickSort(array, comp, start, index);
        quickSort(array, comp, index + 1, end);
    }

    private static <T> int partition(T[] array, Comparator<T> comp, int start, int end) {
        T mid = array[start];
        int i = start;
        int j = end - 1;
        while (i < j) {
            while (i < j && comp.compare(mid, array[j]) <= 0) {
                j--;
            }
            swap(array, i, j);
            while (i < j && comp.compare(mid, array[i]) > 0) {
                i++;
            }
            swap(array, i, j);
        }
        return i;
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static <T> T[] generateArray(IntFunction<T[]> generator, Supplier<T> supplier, int size) {
        ArrayList<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(supplier.get());
        }
        return list.toArray(generator);
    }

    private static String randomString() {
        int size = r.nextInt(20) + 5;
        StringBuilder builder = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            builder.append(r.nextInt(26) + 'A');
        }
        return builder.toString();
    }
}
