import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class P19_11 {

    public static void main(String[] args) throws IOException {


        Map<String, Integer> m = Movies.readMovies("movies.txt").stream()
                .map(it -> it.getActors().stream().collect(HashMap<String, Integer>::new,
                        (map, actor) -> map.put(actor, 1),
                        HashMap::putAll))
                .collect(HashMap::new,
                        (map, actors) -> actors.keySet().forEach(actor -> {
                            map.putIfAbsent(actor, 0);
                            map.put(actor, map.get(actor) + 1);
                        }),
                        (map1, map2) -> map2.keySet().forEach(actor -> {
                            map1.putIfAbsent(actor, 0);
                            map1.put(actor, map2.get(actor));
                        }));

        new ArrayList<>(m.entrySet()).stream()
                .sorted(Comparator.comparingInt(Map.Entry<String, Integer>::getValue).reversed())
                .limit(100)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);

    }

}