package manfred.end.lucene;

import java.util.Arrays;
import java.util.List;

public class TfIdfCal {
    /**
     * tf（term frequency）代表词项频率，要想计算一份文档中某个词的词频，统计该词在整篇文档中出现的次数即可
     * 为了削弱文档长度的影响，需要将词频标准化
     */
    public double tf(List<String> doc, String term) {
        double termFrequency = 0;
        for (String str : doc) {
            if (str.equalsIgnoreCase(term)) {
                termFrequency++;
            }
        }
        return termFrequency / doc.size();
    }

    /**
     * 文档频率用df（document frequency）表示，代表文档集中包含某个词的所有文档数目。
     */
    public int df(List<List<String>> docs, String term) {
        int n = 0;
        if (term != null && !term.isEmpty()) {
            for (List<String> doc : docs) {
                for (String word : doc) {
                    if (term.equalsIgnoreCase(word)) {
                        n++;
                        break;
                    }
                }
            }
        } else {
            System.out.println("term不能为null或者空串");
        }
        return n;
    }

    /**
     * df通常比较大，把它映射到一个较小的取值范围，用逆文档频率（inverse documentfrequency，缩写为idf）来表示
     */
    public double idf(List<List<String>> docs, String term) {
        return Math.log(docs.size() / (double) df(docs, term) + 1);
    }

    /**
     * tf-idf中文称为词频-逆文档频率，用以计算词项对于一个文档集或一个语料库中的一份文件的重要程度。
     * 词项的重要性随着它在文档中出现的次数成正比增加，但同时会随着它在文档集中出现的频率成反比下降。
     */
    public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
        return tf(doc, term) * idf(docs, term);
    }

    public static void main(String[] args) {
        List<String> doc1 = Arrays.asList("人工", "智能", "成为", "互联网", "大会", "焦点");
        List<String> doc2 = Arrays.asList("谷歌", "推出", "开源", "人工", "智能", "系统", "工具");
        List<String> doc3 = Arrays.asList("互联网", "的", "未来", "在", "人工", "智能");
        List<String> doc4 = Arrays.asList("谷歌", "开源", "机器", "学习", "工具");
        List<List<String>> documents = Arrays.asList(doc1, doc2, doc3, doc4);
        TfIdfCal calculator = new TfIdfCal();
        System.out.println("词项频率(谷歌) = " + calculator.tf(doc2, "谷歌"));
        System.out.println("文档频率(谷歌) = " + calculator.df(documents, "谷歌"));
        System.out.println("逆文档频率(谷歌) = " + calculator.idf(documents, "谷歌"));
        double tfidf = calculator.tfIdf(doc2, documents, "谷歌");
        System.out.println("TF-IDF(谷歌) = " + tfidf);
    }
}