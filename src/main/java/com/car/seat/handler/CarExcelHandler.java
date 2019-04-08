package com.car.seat.handler;

import com.car.seat.util.ExcelUtil;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * car excel handler
 */
public class CarExcelHandler {

    public void handleExcel(String fileName){

        String json = "";
        Map<String, String> seatsMap = Maps.newHashMap();


//        String fileName = "car_excel.xlsx";
        File file = new File(fileName);
        try {
            InputStream is = new FileInputStream(file);
            List<String[]> dataList = ExcelUtil.readExcel(is, fileName);

            for (String[] array : dataList) {
                // 车型
                String brand = array[0];

                // 厂家

                // 英文

                // 价格区间 下

                // 价格区间 上

                // seats
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
