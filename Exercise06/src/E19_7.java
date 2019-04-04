import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.UnaryOperator;

public class E19_7 {

    public static void main(String[] args) throws IOException {

        Path p = Path.of(args[0]);

        Arrays.stream(Files.readString(p).split("\\s+"))
                .filter(it -> it.length() > 2)
                .map(acronym)
                .forEach(System.out::println);

    }

    private static final UnaryOperator<String> acronym = it ->
            it.charAt(0) + "..." + it.charAt(it.length() - 1);

}
