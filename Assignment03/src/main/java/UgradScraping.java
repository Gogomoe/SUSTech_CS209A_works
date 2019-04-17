import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class UgradScraping {

    final private static String url = "http://www.cs.princeton.edu/people/ugrad";

    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect(url).get();
        Elements studentList = doc.select("#block-system-main .people-ugrad-col li");

        studentList.stream()
                .map(Student::new)
                .peek(System.out::println)
                .collect(Collectors.groupingBy(it -> it.year))
                .entrySet().stream()
                .map(it -> new Pair<>(it.getKey(), it.getValue().size()))
                .sorted(Comparator.comparing(it -> it.left))
                .forEachOrdered(it -> System.out.printf("Graduating year:%s, Student number:%d\n", it.left, it.right));
    }

    private static class Student {
        String firstname;
        String lastname;
        String year;

        Student(Element e) {
            String text = e.select("a").isEmpty() ?
                    e.ownText() : e.select("a").get(0).text();
            firstname = text.split(",")[0].trim();
            lastname = text.split(",")[1].trim();
            year = e.select(".person-ugrad-year").text().substring(1);
        }

        @Override
        public String toString() {
            return String.format("%s %s, %s", firstname, lastname, year);
        }
    }

    private static class Pair<L, R> {
        L left;
        R right;

        Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }
    }

}
