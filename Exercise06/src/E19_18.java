import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class E19_18 {

    public static void main(String[] args) throws IOException {

        Files.readAllLines(Path.of("countries.txt")).stream()
                .map(Country::parse)
                .max(Comparator.comparingDouble(it -> (double) it.getPopulation() / it.getArea()))
                .ifPresent(System.out::println);


    }

    public static Map<String, List<BankAccount>> groupingByName(List<BankAccount> list) {
        return list.stream().collect(Collectors.groupingBy(BankAccount::getName));
    }

}
