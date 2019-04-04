import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class E19_4 {

    public static void main(String[] args) throws IOException {
        Path p = Path.of(args[0]);

        Arrays.stream(Files.readString(p).split("\\s+"))
                .filter(it -> it.length() <= 4)
                .distinct()
                .forEach(System.out::println);
    }

}
