package manfred.end.jsonp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class JsoupHtmlTest {
    public static void main(String[] args) {
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "\t<title>测试网页</title>\n" +
                "\t<link rel=\"stylesheet\" href=\"\">\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<p>这是p标签的内容</p>\n" +
                "\t<a href=\"http://blog.beifengtz.com\" title=\"beifengtz's blog\">beifeng blog</a>\n" +
                "</body>\n" +
                "</html>";
        Document document = Jsoup.parse(html);  //  将字符串解析成Document对象
        System.out.println(document);

    }
}