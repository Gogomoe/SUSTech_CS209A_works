import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class E19_5 {

    public static void main(String[] args) throws IOException {

        Stream<Path> paths = Stream.of(
                Path.of("a.txt"),
                Path.of("b.txt"),
                Path.of("/dir/a.txt"),
                Path.of("foo.tar.gz"),
                Path.of("/etc/issue")
        );

        System.out.println(toString(paths, 3));

    }

    public static <T> String toString(Stream<T> stream, int n) {
        return stream
                .limit(n)
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }

}
