import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class WordCount {

    static class Data {
        char c;
        int index;
        int count;
        long encode;
        String encodeStr;

        Data(char c, int index) {
            this.c = c;
            this.index = index;
            this.count = 0;

            byte[] bytes = Character.toString(c).getBytes(targetCharset);
            StringBuilder e = new StringBuilder();
            for (byte b : bytes) {
                e.append(String.format("%02X", b));
            }
            encodeStr = e.toString();
            encode = Long.parseLong(encodeStr, 16);
        }
    }

    private static Map<Character, Data> map = new HashMap<>();

    private static Charset targetCharset;

    public static void main(String[] args) throws IOException {
        Path path = new File(args[0]).toPath();
        String targetFilename = args[2] + "_Dict_From_" + args[0];
        Charset originCharset = Charset.forName(args[1]);
        targetCharset = Charset.forName(args[2]);

        String sortKey = args[3];

        String text = Files.readString(path, originCharset);
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            final char c = chars[i];
            final int index = i;
            if (c < 0x4e00 || c > 0x9fa5) {
                continue;
            }
            map.computeIfAbsent(c, it -> new Data(it, index));
            map.get(c).count++;
        }

        List<Data> list = new ArrayList<>(map.values());
        Comparator<Data> comparator = getComparator(sortKey);
        list.sort(comparator);

        BufferedWriter writer = new BufferedWriter(new FileWriter(targetFilename, Charset.forName("utf-8")));
        for (Data it : list) {
            String str = it.c + "," + it.encodeStr + "," + it.count;
            writer.write(str);
            writer.newLine();
        }
        writer.close();

    }

    private static Comparator<Data> getComparator(String sortKey) {
        switch (sortKey) {
            case "char":
                return Comparator.comparingInt(it -> it.c);
            case "code":
                return Comparator.comparingLong(it -> it.encode);
            case "count":
                return Comparator.comparingInt(it -> -it.count);
            default:
                throw new RuntimeException("please input correct sort key");
        }
    }
}
