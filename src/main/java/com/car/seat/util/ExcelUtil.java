package com.car.seat.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Excel 工具类
 */
@Slf4j
public class ExcelUtil {

    /**
     * 读取Excel
     *
     * @param is
     * @param filename
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static List<String[]> readExcel(InputStream is, String filename) throws IOException, FileNotFoundException {
        return readExcel(is, filename, 1);
    }

    /**
     * 读取Excel
     *
     * @param is
     * @param filename
     * @param startRow
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static List<String[]> readExcel(InputStream is, String filename, int startRow) throws IOException, FileNotFoundException {

        Workbook xssfWorkbook = null;
        if (filename.endsWith(".xls")) {
            xssfWorkbook = new HSSFWorkbook(is);
        } else if (filename.endsWith(".xlsx")) {
            xssfWorkbook = new XSSFWorkbook(is);
        }
        Sheet xssfSheet = xssfWorkbook.getSheetAt(0);
        if (xssfSheet == null) {
            return null;
        }
        ArrayList<String[]> list = new ArrayList<>();
        int lastRowNum = xssfSheet.getLastRowNum();
        for (int rowNum = startRow; rowNum <= lastRowNum; rowNum++) {
            if (xssfSheet.getRow(rowNum) != null) {
                Row xssfRow = xssfSheet.getRow(rowNum);
                short firstCellNum = xssfRow.getFirstCellNum();
                short lastCellNum = xssfRow.getLastCellNum();
                if (firstCellNum != lastCellNum) {
                    String[] values = new String[lastCellNum];
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell xssfCell = xssfRow.getCell(cellNum);
                        if (xssfCell == null) {
                            values[cellNum] = "";
                        } else {
                            values[cellNum] = getExcelValue(xssfCell);
                        }
                    }
                    list.add(values);
                }
            }
        }
        return list;
    }


    private static String getExcelValue(Cell cell) {
        String result = new String();
        switch (cell.getCellTypeEnum()) {
            case NUMERIC:// 数字类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double value = cell.getNumericCellValue();
                    Date date = DateUtil.getJavaDate(value);
                    result = sdf.format(date);
                } else {
                    double value = cell.getNumericCellValue();
                    CellStyle style = cell.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    // 单元格设置成常规
                    if (temp.equals("General")) {
                        format.applyPattern("#");
                    }
                    result = format.format(value);
                }
                break;
            case STRING:// String类型
                result = cell.getRichStringCellValue().toString();
                break;
            case BLANK:
                result = "";
                break;
            default:
                result = "";
                break;
        }
        return result;
    }
}

