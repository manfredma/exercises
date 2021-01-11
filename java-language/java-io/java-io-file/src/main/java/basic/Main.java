package basic;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

/**
 * @author manfred on 2019/9/29.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        new Main().modifyTime("C:/Users/maxin/Desktop/mxf");
    }

    private void modifyTime(String fileName) throws Exception {
        File fileToChange = new File(fileName);
        if (fileToChange.isDirectory()) {
            String[] files = fileToChange.list();
            for (int i = 0; i < files.length; i++) {
                modifyTime(fileName + "/" + files[i]);
            }
        } else {

            BasicFileAttributes attr = Files.readAttributes(fileToChange.toPath(), BasicFileAttributes.class);

            System.out.println("creationTime: " + attr.creationTime());
            System.out.println("lastAccessTime: " + attr.lastAccessTime());
            System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
            Date filetime = new Date(fileToChange.lastModified());
            System.out.println(filetime.toString());
            System.out.println(fileToChange.setLastModified(attr.creationTime().toMillis()));
            filetime = new Date(fileToChange.lastModified());
            System.out.println(filetime.toString());
        }
    }
}
