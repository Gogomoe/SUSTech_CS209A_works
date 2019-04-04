import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class E19_14 {

    public static void main(String[] args) throws IOException {

        Path p = Path.of(args[0]);

        ArrayList<String> list = Arrays.stream(Files.readString(p).split("\\s+"))
                .collect(Collectors.toCollection(ArrayList::new));

        list.parallelStream()
                .distinct()
                .filter(it -> it.length() >= 5)
                .filter(E19_14::palindrome)
                .findAny()
                .ifPresent(System.out::println);
    }

    private static boolean palindrome(String it) {
        for (int i = 0; i < it.length() / 2; i++) {
            if (it.charAt(0) != it.charAt(it.length() - 1 - i))
                return false;
        }
        return true;
    }

}
