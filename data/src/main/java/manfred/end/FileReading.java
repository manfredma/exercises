package manfred.end;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReading {
    public static void main(String[] args) throws IOException {
        Files.lines(Paths.get("./framework/spring/spring-boot/pom.xml")).forEach(System.out::println);

        String text = FileUtils.readFileToString(new File("./framework/spring/spring-boot/pom.xml"), "UTF-8");
        System.out.println(text);
    }
}
