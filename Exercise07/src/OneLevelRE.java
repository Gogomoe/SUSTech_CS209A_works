import java.util.regex.Pattern;

public class OneLevelRE {

    public static void main(String[] args) {

        System.out.println("(a.*b)*|(b.*a)* : " + isOneLevel("(a.*b)*|(b.*a)*"));
        System.out.println("(b(a|b)b)* : " + isOneLevel("(b(a|b)b)*"));

    }

    private static final Pattern p = Pattern.compile("[^()]*((\\([^(]*?\\))([^()]*))*[^()]*");

    private static boolean isOneLevel(String str) {
        return p.matcher(str).matches();
    }

}
