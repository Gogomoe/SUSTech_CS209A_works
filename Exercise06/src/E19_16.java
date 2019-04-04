import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class E19_16 {

    public static void main(String[] args) throws IOException {

        Path p = Path.of(args[0]);

        var map = Arrays.stream(Files.readString(p).split("\\s+"))
                .distinct()
                .collect(Collectors.groupingBy(it -> Character.toLowerCase(it.charAt(0))));

        double[] array = IntStream.range((int) 'a', (int) 'z' + 1)
                .mapToObj(it -> map.getOrDefault((char) it, Collections.emptyList()))
                .mapToDouble(E19_16::calcAverageLength)
                .toArray();

        IntStream.range(0, 26).forEach(i ->
                System.out.printf("%c: %.2f\n", 'a' + i, array[i]));
    }

    private static double calcAverageLength(List<String> it) {
        return it.isEmpty() ? 0 : (double) it.stream().mapToInt(String::length).sum() / it.size();
    }

}
