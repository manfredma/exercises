package template;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author manfred on 2021/4/18.
 */
public class AccountPayableModel {

    LocalDate endDate;

    private final List<AccountPayableEntry> datas = new ArrayList<>();

    public AccountPayableModel(File file) throws IOException {
        Path path = Paths.get(file.toURI());
        try {
            List<String> allLines = Files.readAllLines(path, Charset.forName("GB2312"));
            int begin = 0;
            for (int i = 0; i < allLines.size(); i++) {
                if (allLines.get(i).startsWith("截止日期:")) {
                    endDate = LocalDate.parse(allLines.get(i).substring(5), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                }
                if (allLines.get(i).startsWith("分公司")) {
                    begin = i + 1;
                    break;
                }
            }
            for (int i = begin; i < allLines.size(); i++) {
                datas.add(new AccountPayableEntry(allLines.get(i)));
            }
        } catch (Exception exception) {
            throw new IOException("解析文件" + file.getPath() + " 出现问题！", exception);
        }
    }

    @Override
    public String toString() {
        return "template.AccountPayableModel{" +
                "datas=" + datas +
                '}';
    }

    static class AccountPayableEntry {
        // 分公司
        private final String company;
        // 供应商
        private final String vendor;
        // 总金额
        private final Double amount;
        // 科目
        private final String subject;
        // 科目描述
        private final String subjectDesc;
        // 一个月以内
        private final Double lastNowToOneMonth;
        // 1至3个月
        private final Double lastOneToThreeMonth;
        // 4至6个月
        private final Double lastFourToSixMonth;
        // 7至12个月
        private final Double lastSevenToTwelveMonth;
        // 1至2年
        private final Double lastOneToTwoYear;
        // 2至3年
        private final Double lastTwoToThreeYear;
        // 3年以上
        private final Double lastBeforeThreeYear;

        public AccountPayableEntry(String data) {
            String[] strings = data.split("\t");
            this.company = strings[0].trim();
            this.vendor = strings[1].trim();
            this.amount = Double.parseDouble(strings[2].trim());
            this.subject = strings[3].trim();
            this.subjectDesc = strings[4].trim();
            this.lastNowToOneMonth = Double.parseDouble(strings[5]);
            this.lastOneToThreeMonth = Double.parseDouble(strings[6]);
            this.lastFourToSixMonth = Double.parseDouble(strings[7]);
            this.lastSevenToTwelveMonth = Double.parseDouble(strings[8]);
            this.lastOneToTwoYear = Double.parseDouble(strings[9]);
            this.lastTwoToThreeYear = Double.parseDouble(strings[10]);
            this.lastBeforeThreeYear = Double.parseDouble(strings[11]);
        }

        @Override
        public String toString() {
            return "AccountPayableEntry{" +
                    "company='" + company + '\'' +
                    ", vender='" + vendor + '\'' +
                    ", amount=" + amount +
                    ", subject='" + subject + '\'' +
                    ", subjectDesc='" + subjectDesc + '\'' +
                    ", lastNowToOneMonth=" + lastNowToOneMonth +
                    ", lastOneToThreeMonth=" + lastOneToThreeMonth +
                    ", lastFourToSixMonth=" + lastFourToSixMonth +
                    ", lastSevenToTwelveMonth=" + lastSevenToTwelveMonth +
                    ", lastOneToTwoYear=" + lastOneToTwoYear +
                    ", lastTwoToThreeYear=" + lastTwoToThreeYear +
                    ", lastBeforeThreeYear=" + lastBeforeThreeYear +
                    '}';
        }
    }
}
