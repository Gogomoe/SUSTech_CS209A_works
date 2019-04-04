import java.util.Currency;

public class E19_6 {

    public static void main(String[] args) {

        Currency.getAvailableCurrencies().stream()
                .map(Currency::getDisplayName)
                .sorted()
                .forEach(System.out::println);
    }

}
