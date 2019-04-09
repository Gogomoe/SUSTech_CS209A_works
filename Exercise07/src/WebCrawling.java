import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WebCrawling {

    public static void main(String[] args) throws IOException {

        Pattern p = Pattern.compile("href=\"(?<url>.*?)\"");

        String page = args[0];
        URLConnection connection = new URL(page).openConnection();

        String text = new BufferedReader(new InputStreamReader(connection.getInputStream()))
                .lines().collect(Collectors.joining(System.lineSeparator()));

        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.println(m.group("url"));
        }


    }

}