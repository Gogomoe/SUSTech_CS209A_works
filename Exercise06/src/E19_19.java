import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class E19_19 {

    public static void main(String[] args) {
        System.out.println(findPos1("Hello World", 'o'));
        System.out.println(findPos2("Hello World", 'o'));
    }

    public static List<Integer> findPos1(String str, char code) {
        final int[] i = {-1};
        return str.codePoints()
                .map(c -> {
                    i[0]++;
                    return c == code ? i[0] : -1;
                })
                .filter(it -> it != -1)
                .boxed()
                .collect(Collectors.toList());

    }

    public static List<Integer> findPos2(String str, char code) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == code) {
                list.add(i);
            }
        }
        return list;
    }

}
