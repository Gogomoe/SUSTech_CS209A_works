import java.io.File;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;

public class FilesManager {


    public static void main(String[] args) {

        try {

            Path p = Path.of(args[0]);
            Consumer<Path> task = getTask(Integer.parseInt(args[1]));

            if (p.toFile().exists()) {
                task.accept(p);
            } else {
                System.out.println("file not exist");
            }

        } catch (Exception ignored) {
            System.out.println("input error");
        }

    }

    private static List<Consumer<Path>> tasks = Arrays.asList(
            FilesManager::filesCount, FilesManager::totalSize,
            FilesManager::findBigFiles, FilesManager::categorize
    );

    private static Consumer<Path> getTask(int taskNo) {
        return tasks.get(taskNo - 1);
    }

    private static void filesCount(Path p) {
        System.out.println(fileCount(p.toFile()));
    }

    private static int fileCount(File file) {
        if (file.isDirectory()) {
            return Arrays.stream(Objects.requireNonNull(file.listFiles()))
                    .mapToInt(FilesManager::fileCount)
                    .sum();
        }
        return 1;
    }

    private static void totalSize(Path p) {
        System.out.printf("%.0fk", fileSize(p.toFile()) / 1024.0);
    }

    private static long fileSize(File file) {
        if (file.isDirectory()) {
            return Arrays.stream(Objects.requireNonNull(file.listFiles()))
                    .mapToLong(FilesManager::fileSize)
                    .sum();
        }
        return file.length();
    }

    private static void findBigFiles(Path p) {
        findBigFiles(p.toFile());
    }

    private static void findBigFiles(File file) {
        if (file.isDirectory()) {
            Arrays.stream(Objects.requireNonNull(file.listFiles()))
                    .forEach(FilesManager::findBigFiles);
            return;
        }
        if (file.length() > 1000_000) {
            System.out.println(file.toPath());
        }
    }

    private static void categorize(Path p) {
        Map<String, Integer> category = new TreeMap<>();
        categorize(category, p.toFile());
        category.forEach((key, value) -> System.out.printf("%s,%d\n", key, value));
    }

    private static void categorize(Map<String, Integer> category, File file) {
        if (file.isDirectory()) {
            Arrays.stream(Objects.requireNonNull(file.listFiles()))
                    .forEach(f -> categorize(category, f));
            return;
        }
        String name = file.getName();
        String postfix = "";
        int i = name.lastIndexOf('.');
        if (i != -1) {
            postfix = name.substring(i);
        }
        category.putIfAbsent(postfix, 0);
        int count = category.get(postfix);
        category.put(postfix, count + 1);

    }
}
