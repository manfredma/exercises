package manfred.end.lucene;

import java.io.IOException;
import java.io.StringReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class StdAnalyzer {
    private static String strCh = "中华人民共和国简称中国，是一个有13亿人口的国家";
    private static String strEn = "Dogs can not achieve a place，eyes can reach; ";

    public static void main(String[] args) throws IOException {
        System.out.println("StandardAnalyzer对中文分词：");
        stdAnalyzer(strCh);
        System.out.println("StandardAnalyzer对英文分词：");
        stdAnalyzer(strEn);
    }

    public static void stdAnalyzer(String str) throws IOException {
        Analyzer analyzer = null;
        analyzer = new StandardAnalyzer();
        StringReader reader = new StringReader(str);
        TokenStream toStream = analyzer.tokenStream(str, reader);
        toStream.reset();
        CharTermAttribute teAttribute =
                toStream.getAttribute(CharTermAttribute.class);
        System.out.println("分词结果：");
        while (toStream.incrementToken()) {
            System.out.print(teAttribute.toString() + "|");
        }
        System.out.println("\n");
        analyzer.close();
    }
}