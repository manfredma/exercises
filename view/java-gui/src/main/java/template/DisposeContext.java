package template;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * @author manfred on 2021/4/18.
 */
class DisposeContext {
    /**
     * 当前选择的文件夹
     */
    File selectDirectory;

    /**
     * exportFinishedClaimListFile 文件
     */
    File exportFinishedClaimListFile;

    /**
     * 应付帐款报表文件
     */
    File accountPayableFile;

    /**
     * 供应商往来情况核对表文件
     */
    File currentCheckFile;

    /**
     * 华盛报账单对应发票信息
     */
    File invoiceFile;

    /**
     * 模板文件
     */
    File templateFile;

    boolean extractSuccess;


    /**
     * exportFinishedClaimListFile 文件
     */
    Workbook exportFinishedClaim;

    /**
     * 应付帐款报表文件
     */
    AccountPayableModel accountPayable;

    /**
     * 供应商往来情况核对表文件
     */
    CurrentCheckModel currentCheck;

    /**
     * 华盛报账单对应发票信息
     */
    Workbook invoice;

    /**
     * 模板文件
     */
    XSSFWorkbook template;

    List<InputStream> tempInputStreams = new ArrayList<>();


    Map<String, VendorResultContext> vendorContext = new HashMap<>();

    LocalDate endDate;

    DecimalFormat format = new DecimalFormat("#0.00");


    public void initXSSFWorkbook() throws IOException {
        exportFinishedClaim = openHWorkbook(exportFinishedClaimListFile);
        if (exportFinishedClaim == null) {
            exportFinishedClaim = openWorkbook(exportFinishedClaimListFile);
        }
        accountPayable = new AccountPayableModel(accountPayableFile);
        endDate = accountPayable.endDate;
        currentCheck = new CurrentCheckModel(currentCheckFile);
        invoice = openWorkbook(invoiceFile);
        if (invoice == null) {
            invoice = openHWorkbook(invoiceFile);
        }
        checkWorkbookInit();
    }

    public void saveResult() throws IOException {
        template = openWorkbook(templateFile);
        displayTemplateExcel();
        vendorContext.forEach((vendorName, vendorContext) -> {
            template = openWorkbook(templateFile);
//        System.out.println(accountPayable);
//        System.out.println(currentCheck);

            try {
                String fileName = selectDirectory.getPath() + "/供应商对账单" + endDate.getMonthValue() + "月_"
                        + vendorName + ".xlsx";
                FileOutputStream fileOut = new FileOutputStream(fileName);
                // 设置静态信息
                Sheet checkSheet = template.getSheetAt(0);
                Row row = checkSheet.getRow(1);
                Cell cell = row.getCell(1);
                cell.setCellValue(cell.getStringCellValue().replace("{0}", vendorName));
                cell = row.getCell(7);
                String value = cell.getStringCellValue();
                cell.setCellValue(
                        value.replace("{0}", endDate.getYear() + "")
                                .replace("{1}", endDate.getMonthValue() + ""));

                cell = row.getCell(8);
                value = cell.getStringCellValue();
                cell.setCellValue(
                        value.replace("{0}", endDate.getYear() + "")
                                .replace("{1}", endDate.getMonthValue() + "")
                                .replace("{2}", endDate.getDayOfMonth() + ""));

                row = checkSheet.getRow(31);
                cell = row.getCell(1);
                value = cell.getStringCellValue();
                cell.setCellValue(
                        value.replace("{0}", endDate.getYear() + "")
                                .replace("{1}", endDate.getMonthValue() + "")
                                .replace("{2}", endDate.getDayOfMonth() + "")
                                .replace("{3}", format.format(vendorContext.欠款)));
                // 设置差异调节信息
                Sheet tidySheet = template.getSheetAt(1);
                row = tidySheet.getRow(2);
                row.getCell(1).setCellValue(vendorName);
                cell = row.getCell(3);
                value = cell.getStringCellValue();
                cell.setCellValue(
                        value.replace("{0}", endDate.getYear() + "")
                                .replace("{1}", endDate.getMonthValue() + "")
                                .replace("{2}", endDate.getDayOfMonth() + ""));

                row.getCell(8).setCellValue(vendorContext.供应商编号);

                template.write(fileOut);
                fileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }

    private void checkWorkbookInit() throws IOException {
        try {
            Objects.requireNonNull(exportFinishedClaim, exportFinishedClaimListFile.getName() + " 格式不正确");
            Objects.requireNonNull(accountPayable, accountPayableFile.getName() + " 格式不正确");
            Objects.requireNonNull(currentCheck, currentCheckFile.getName() + " 格式不正确");
            Objects.requireNonNull(invoice, invoiceFile.getName() + " 格式不正确");
        } catch (Exception exception) {
            throw new IOException(exception.getMessage());
        }
    }

    private XSSFWorkbook openWorkbook(File file) {
        try {
            FileInputStream excelFis = new FileInputStream(file);
            BufferedInputStream excelBis = new BufferedInputStream(excelFis);
            tempInputStreams.add(excelFis);
            tempInputStreams.add(excelBis);
            return new XSSFWorkbook(excelBis);
        } catch (Exception exception) {
            System.err.println(file.getPath() + "不是新版本的Excel文件！");
            return null;
        }

    }

    private void displayTemplateExcel() {
        for (Sheet excelSheet : template) {
            System.out.println("Sheet: " + excelSheet.getSheetName() + ", lastRowNum = " + excelSheet.getLastRowNum());
            for (int rowNum = 0; rowNum < excelSheet.getLastRowNum(); rowNum++) {
                Row excelRow = excelSheet.getRow(rowNum);
                System.out.println("rowNum = " + rowNum);
                for (int cellNum = 0; cellNum < excelRow.getLastCellNum(); cellNum++) {
                    Cell cell = excelRow.getCell(cellNum);
                    System.out.print("[" + cellNum + "=" + cell + ", type=" + (cell != null ? cell.getCellType() : null) + "] ");
                }
                System.out.println();
            }
        }
    }

    private HSSFWorkbook openHWorkbook(File file) {
        try {
            FileInputStream excelFis = new FileInputStream(file);
            BufferedInputStream excelBis = new BufferedInputStream(excelFis);
            tempInputStreams.add(excelFis);
            tempInputStreams.add(excelBis);
            return new HSSFWorkbook(excelBis);
        } catch (Exception exception) {
            System.err.println(file.getPath() + "不是老版本的Excel文件！");
            return null;
        }
    }

    public void closeXSSFWorkbook() {
        tempInputStreams.forEach(a -> {
            try {
                a.close();
            } catch (IOException ioException) {
                // do nothing
            }
        });
        tempInputStreams.clear();
    }

    public boolean isExtractSuccess() {
        return extractSuccess;
    }


    public void initVendorContext() {
        VendorResultContext vendorResultContext = new VendorResultContext();
        vendorResultContext.name = "苹果公司";
        vendorResultContext.欠款 = 1000.324;
        vendorResultContext.供应商编号 = "1231423";
        vendorContext.put("苹果公司", vendorResultContext);
    }
}
