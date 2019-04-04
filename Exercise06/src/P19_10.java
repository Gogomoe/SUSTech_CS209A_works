import java.io.IOException;
import java.util.AbstractCollection;
import java.util.HashSet;
import java.util.Set;

public class P19_10 {

    public static void main(String[] args) throws IOException {

        Set<String> actors = Movies.readMovies("movies.txt").stream()
                .map(Movie::getActors)
                .collect(HashSet::new, AbstractCollection::addAll, AbstractCollection::addAll);

        System.out.println(actors);

    }

}