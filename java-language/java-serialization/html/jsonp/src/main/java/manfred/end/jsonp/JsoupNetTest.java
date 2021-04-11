package manfred.end.jsonp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * @Author beifengtz
 * @Site www.beifengtz.com
 * @Date Created in 19:09 2019/2/2
 * @Description:
 */
public class JsoupNetTest {
    public static void main(String[] args) throws Exception{
        Document document = Jsoup.connect("http://blog.beifengtz.com/").get();
        System.out.println(document);
    }
}