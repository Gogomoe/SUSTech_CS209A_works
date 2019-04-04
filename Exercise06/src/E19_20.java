import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class E19_20 {

    public static void main(String[] args) {

        System.out.println(findAdjacentDuplicate(
                Stream.of("a", "b", "c", "c", "b", "a", "a")
        ));

    }

    public static List<String> findAdjacentDuplicate(Stream<String> stream) {
        final String[] last = {null};
        return stream.filter(it -> {
            if (last[0] == null) {
                last[0] = it;
                return true;
            }
            boolean equal = last[0].equals(it);
            last[0] = it;
            return equal;
        }).collect(Collectors.toList());
    }

}
