package com.online.college.common.util;

import java.io.InputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Lists;


/**
 * @author cmazxiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2018/3/19 18:49
 */
public class ImportExcelUtil implements Serializable {

    private final static String excel2003L = ".xls"; // 2003- 版本的excel
    private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel

    /**
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象
     *
     * @param inputStream
     *            输入流
     * @param fileName
     *            文件名
     * @return
     * @throws Exception
     */
    public List<List<Object>> getBankListByExcel(InputStream inputStream, String fileName) throws Exception {
        List<List<Object>> list = null;

        // 创建Excel工作薄
        Workbook workbook = this.getWorkbook(inputStream, fileName);

        if (workbook == null) {
            throw new RuntimeException("创建Excel工作薄为空！");
        }

        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = Lists.newLinkedList();
        // 遍历Excel中所有的sheet
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheet = workbook.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            // 遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                if (j < 1) {
                    continue;
                }
                row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }

                // 遍历所有的列
                List<Object> li = Lists.newArrayList();
                for (int y = sheet.getRow(0).getFirstCellNum(); y < sheet.getRow(0).getPhysicalNumberOfCells(); y++) {

//                     if (getCellValue(row.getCell(0)).equals("end")) {
//                         break;
//                     }

                    cell = row.getCell(y);
                    li.add(this.getCellValue(cell));
                }
                list.add(li);
            }
        }
        inputStream.close();
        return list;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     *
     * @param inputStream
     *            输入流
     * @param fileName
     *            文件名
     * @return
     * @throws Exception
     */
    public Workbook getWorkbook(InputStream inputStream, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            workbook = new HSSFWorkbook(inputStream); // 2003-
        } else if (excel2007U.equals(fileType)) {
            workbook = new XSSFWorkbook(inputStream); // 2007+
        } else {
            throw new RuntimeException("解析的文件格式有误！");
        }
        return workbook;
    }

    /**
     * 描述：对表格中数值进行格式化
     *
     * @param cell
     * @return
     */
    public Object getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        Object value = null;
        DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
        SimpleDateFormat sdfhhmm = new SimpleDateFormat("hh:mm");
        DecimalFormat df2 = new DecimalFormat("0.00"); // 格式化数字

        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_STRING:
            value = cell.getRichStringCellValue().getString();
            break;
        case Cell.CELL_TYPE_NUMERIC:
            if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                value = df.format(cell.getNumericCellValue());
            } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                value = sdf.format(cell.getDateCellValue());
            } else if ("hh:mm".equals(cell.getCellStyle().getDataFormatString())) {
                value = sdfhhmm.format(cell.getDateCellValue());
            }else {
                value = df2.format(cell.getNumericCellValue());
            }
            break;
        case Cell.CELL_TYPE_BOOLEAN:
            value = cell.getBooleanCellValue();
            break;
        case Cell.CELL_TYPE_BLANK:
            value = "";
            break;
        default:
            break;
        }
        return value;
    }

}
