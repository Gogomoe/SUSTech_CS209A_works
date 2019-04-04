import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class E19_13 {

    public static void main(String[] args) throws IOException {

        Path p = Path.of(args[0]);

        Arrays.stream(Files.readString(p).split("\\s+"))
                .distinct()
                .max(Comparator.comparingInt(E19_13::vowel))
                .ifPresent(System.out::println);

    }

    public static final Set<Integer> vowels = new HashSet<>(Arrays.asList(
            (int) 'a', (int) 'e', (int) 'i', (int) 'o', (int) 'u',
            (int) 'A', (int) 'E', (int) 'I', (int) 'O', (int) 'U'));


    private static int vowel(String str) {
        return (int) str.codePoints().filter(vowels::contains).count();
    }


}
