import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class E19_17 {

    public static void main(String[] args) throws IOException {

        List<BankAccount> list = Arrays.asList(
                new BankAccount("A", 5),
                new BankAccount("A", 10),
                new BankAccount("B", 5),
                new BankAccount("C", 5));

        groupingByName(list)
                .forEach((key, value) -> System.out.println(key + ": " + value.toString()));

    }

    public static Map<String, List<BankAccount>> groupingByName(List<BankAccount> list) {
        return list.stream().collect(Collectors.groupingBy(BankAccount::getName));
    }


}
