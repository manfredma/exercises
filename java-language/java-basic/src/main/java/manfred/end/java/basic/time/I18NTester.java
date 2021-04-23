package manfred.end.java.basic.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class I18NTester {
    public static void main(String[] args) throws ParseException {
        String pattern = "dd-MM-yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        System.out.println(simpleDateFormat.format(date));
        pattern = "MM-dd-yyyy";
        simpleDateFormat = new SimpleDateFormat(pattern);
        System.out.println(simpleDateFormat.format(date));
        pattern = "yyyy-MM-dd HH:mm:ss";
        simpleDateFormat = new SimpleDateFormat(pattern);
        System.out.println(simpleDateFormat.format(date));
        pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
        simpleDateFormat = new SimpleDateFormat(pattern);
        System.out.println(simpleDateFormat.format(date));
    }
}