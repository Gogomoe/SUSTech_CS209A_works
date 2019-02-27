import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class CharsetConvertor {

    public static void main(String[] args) throws IOException {
        Path path = new File(args[0]).toPath();
        String targetFilename = args[2] + "_" + args[0];
        Charset originCharset = Charset.forName(args[1]);
        Charset targetCharset = Charset.forName(args[2]);

        byte[] bytes = Files.readString(path, originCharset).getBytes(targetCharset);

        String[] hexArray = new String[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            hexArray[i] = String.format("%02X", bytes[i]);
        }

        String str = String.join(" ", hexArray);

        BufferedWriter writer = new BufferedWriter(new FileWriter(targetFilename));
        writer.write(str);
        writer.close();
    }

}
