package template;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author manfred on 2021/4/18.
 */
public class CurrentCheckModel {
    private List<CurrentCheckEntry> datas = new ArrayList<>();

    public CurrentCheckModel(File file) throws IOException {
        Document doc = Jsoup.parse(file, "GB2312");
        for (Element table : doc.select("table")) {
            for (Element row : table.select("tr")) {
                Elements tds = row.select("td");
                if (tds.get(0).text().startsWith("订单OU")) {
                    // 跳过表头
                    continue;
                }
                datas.add(new CurrentCheckEntry(tds));
            }
        }
    }

    static class CurrentCheckEntry {

        // <td WIDTH = 200>订单OU</td>
        private final String orderOu;
        // <td WIDTH = 200>供应商编号</td>
        private final String vendorId;
        // <td WIDTH = 200>供应商名称</td>
        private final String vendorName;
        // <td WIDTH = 200>合同编号</td>
        private final String contractId;
        // <td WIDTH = 200>协议流水号</td>
        private final String agreementNo;
        // <td WIDTH = 200>采购订单编号</td>
        private final String purchaseOrderNo;
        // <td WIDTH = 200>付款条件</td>
        private final String paymentCondition;
        // <td WIDTH = 200>币种</td>
        private final String currency;
        // <td WIDTH = 200>物料编码</td>
        private final String materialCode;
        // <td WIDTH = 200>物料说明</td>
        private final String materialDesc;
        // <td WIDTH = 200>核心ERP接收单编号</td>
        private final String erpNo;
        // <td WIDTH = 200>易拓接收单编号</td>
        private final String yiTuoNo;
        // <td WIDTH = 200>事务处理类型</td>
        private final String transactionType;
        // <td WIDTH = 200>事务处理日期</td>
        private final String transactionDisposeDate;
        // <td WIDTH = 200>目的组织</td>
        private final String targetOrg;
        // <td WIDTH = 200>数量</td>
        private final String num;
        // <td WIDTH = 200>单价</td>
        private final String unitPrice;
        // <td WIDTH = 200>金额</td>
        private final String amount;
        // <td WIDTH = 200>项目编号</td>
        private final String projectNo;
        // <td WIDTH = 200>项目名称</td>
        private final String projectName;
        // <td WIDTH = 200>已开票数量</td>
        private final String invoiceNum;
        // <td WIDTH = 200>发票编号</td>
        private final String invoiceCode;
        // <td WIDTH = 200>开票日期</td>
        private final String invoiceDate;
        // <td WIDTH = 200>匹配金额</td>
        private final String matchAmount;
        // <td WIDTH = 200>发票总含税金额</td>
        private final String invoiceTotalAmount;
        // <td WIDTH = 200>价差调整金额</td>
        private final String priceTidyAmount;
        // <td WIDTH = 200>价差调整数量</td>
        private final String priceTidyNum;

        public CurrentCheckEntry(Elements tableRow) {
            // <td WIDTH = 200>订单OU</td>
            orderOu = tableRow.get(0).text();
            // <td WIDTH = 200>供应商编号</td>
            vendorId = tableRow.get(1).text();
            // <td WIDTH = 200>供应商名称</td>
            vendorName = tableRow.get(2).text();
            // <td WIDTH = 200>合同编号</td>
            contractId = tableRow.get(3).text();
            // <td WIDTH = 200>协议流水号</td>
            agreementNo = tableRow.get(4).text();
            // <td WIDTH = 200>采购订单编号</td>
            purchaseOrderNo = tableRow.get(5).text();
            // <td WIDTH = 200>付款条件</td>
            paymentCondition = tableRow.get(6).text();
            // <td WIDTH = 200>币种</td>
            currency = tableRow.get(7).text();
            // <td WIDTH = 200>物料编码</td>
            materialCode = tableRow.get(8).text();
            // <td WIDTH = 200>物料说明</td>
            materialDesc = tableRow.get(9).text();
            // <td WIDTH = 200>核心ERP接收单编号</td>
            erpNo = tableRow.get(10).text();
            // <td WIDTH = 200>易拓接收单编号</td>
            yiTuoNo = tableRow.get(11).text();
            // <td WIDTH = 200>事务处理类型</td>
            transactionType = tableRow.get(12).text();
            // <td WIDTH = 200>事务处理日期</td>
            transactionDisposeDate = tableRow.get(13).text();
            // <td WIDTH = 200>目的组织</td>
            targetOrg = tableRow.get(14).text();
            // <td WIDTH = 200>数量</td>
            num = tableRow.get(15).text();
            // <td WIDTH = 200>单价</td>
            unitPrice = tableRow.get(16).text();
            // <td WIDTH = 200>金额</td>
            amount = tableRow.get(17).text();
            // <td WIDTH = 200>项目编号</td>
            projectNo = tableRow.get(18).text();
            // <td WIDTH = 200>项目名称</td>
            projectName = tableRow.get(19).text();
            // <td WIDTH = 200>已开票数量</td>
            invoiceNum = tableRow.get(20).text();
            // <td WIDTH = 200>发票编号</td>
            invoiceCode = tableRow.get(21).text();
            // <td WIDTH = 200>开票日期</td>
            invoiceDate = tableRow.get(22).text();
            // <td WIDTH = 200>匹配金额</td>
            matchAmount = tableRow.get(23).text();
            // <td WIDTH = 200>发票总含税金额</td>
            invoiceTotalAmount = tableRow.get(24).text();
            // <td WIDTH = 200>价差调整金额</td>
            priceTidyAmount = tableRow.get(25).text();
            // <td WIDTH = 200>价差调整数量</td>
            priceTidyNum = tableRow.get(26).text();
        }

        @Override
        public String toString() {
            return "CurrentCheckEntry{" +
                    "orderOu='" + orderOu + '\'' +
                    ", vendorId='" + vendorId + '\'' +
                    ", vendorName='" + vendorName + '\'' +
                    ", contractId='" + contractId + '\'' +
                    ", agreementNo='" + agreementNo + '\'' +
                    ", purchaseOrderNo='" + purchaseOrderNo + '\'' +
                    ", paymentCondition='" + paymentCondition + '\'' +
                    ", currency='" + currency + '\'' +
                    ", materialCode='" + materialCode + '\'' +
                    ", materialDesc='" + materialDesc + '\'' +
                    ", erpNo='" + erpNo + '\'' +
                    ", yiTuoNo='" + yiTuoNo + '\'' +
                    ", transactionType='" + transactionType + '\'' +
                    ", transactionDisposeDate='" + transactionDisposeDate + '\'' +
                    ", targetOrg='" + targetOrg + '\'' +
                    ", num='" + num + '\'' +
                    ", unitPrice='" + unitPrice + '\'' +
                    ", amount='" + amount + '\'' +
                    ", projectNo='" + projectNo + '\'' +
                    ", projectName='" + projectName + '\'' +
                    ", invoiceNum='" + invoiceNum + '\'' +
                    ", invoiceCode='" + invoiceCode + '\'' +
                    ", invoiceDate='" + invoiceDate + '\'' +
                    ", matchAmount='" + matchAmount + '\'' +
                    ", invoiceTotalAmount='" + invoiceTotalAmount + '\'' +
                    ", priceTidyAmount='" + priceTidyAmount + '\'' +
                    ", priceTidyNum='" + priceTidyNum + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CurrentCheckModel{" +
                "datas=" + datas +
                '}';
    }
}
