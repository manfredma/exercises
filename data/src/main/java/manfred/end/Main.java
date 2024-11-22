package manfred.end;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello, World!");
        File file = new File("/Users/maxingfang/Downloads/");
        Set<File> files = listFiles(file);
        files.stream().forEach(System.out::println);
    }

    public static Set<File> listFiles(File dir) {
        Set<File> files = new HashSet<>();
        if (dir == null || !dir.exists()) {
            return files;
        }
        if (dir.isFile()) {
            files.add(dir);
            return files;
        }
        File[] subFiles = dir.listFiles();
        for (File file : subFiles) {
            files.addAll(listFiles(file));
        }
        return files;
    }
}