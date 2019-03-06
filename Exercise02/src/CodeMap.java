import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CodeMap {

    private static Map<String, Set<String>> map = new HashMap<>();
    private static Set<String> emptySet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("use \"java CodeMap 26337 4325 6476 <and more>\" to list words");
            return;
        }

        Files.lines(Paths.get("words.txt"))
                .forEach(CodeMap::parseWords);

        Arrays.stream(args).forEach(it -> {
            if (it.matches("[2-9]*")) {
                Set<String> words = map.getOrDefault(it, emptySet);
                System.out.println(it + " : " + words);
            } else {
                System.out.println(it + " : invalid, code should match [2-9]*");
            }
        });


    }

    private static void parseWords(String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = letterToNumber(Character.toLowerCase(chars[i]));
        }
        String number = new String(chars);
        map.computeIfAbsent(number, it -> new HashSet<>()).add(word);
    }

    private static final Map<Character, Character> charMap = new HashMap<>();

    static {
        charMap.put('a', '2');
        charMap.put('b', '2');
        charMap.put('c', '2');
        charMap.put('d', '3');
        charMap.put('e', '3');
        charMap.put('f', '3');
        charMap.put('g', '4');
        charMap.put('h', '4');
        charMap.put('i', '4');
        charMap.put('j', '5');
        charMap.put('k', '5');
        charMap.put('l', '5');
        charMap.put('m', '6');
        charMap.put('n', '6');
        charMap.put('o', '6');
        charMap.put('p', '7');
        charMap.put('q', '7');
        charMap.put('r', '7');
        charMap.put('s', '7');
        charMap.put('t', '8');
        charMap.put('u', '8');
        charMap.put('v', '8');
        charMap.put('w', '9');
        charMap.put('x', '9');
        charMap.put('y', '9');
        charMap.put('z', '9');
    }

    private static char letterToNumber(char letter) {
        return charMap.get(letter);
    }

}
