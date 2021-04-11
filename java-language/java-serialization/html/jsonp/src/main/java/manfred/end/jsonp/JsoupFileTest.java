package manfred.end.jsonp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;

public class JsoupFileTest {
    public static void main(String[] args) throws Exception{

        Document doc = Jsoup.parse(new File(""), "UTF-8");
        System.out.println(doc);
    }
}