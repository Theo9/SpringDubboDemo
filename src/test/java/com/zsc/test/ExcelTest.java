package com.zsc.test;


import com.alibaba.fastjson.JSONObject;
import com.zsc.service.ExcelOperate;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelTest extends BaseTest{
    @Autowired
    private ExcelOperate excelOperate;

    @Test
    public void migrateQYCredit() throws IOException, InvalidFormatException {
        File file = new File("");
        if(file.exists()){
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet("data");

            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
            Sheet result = hssfWorkbook.createSheet("result");
            int rows = sheet.getLastRowNum()+1;

            Row row = null;
            JSONObject param = new JSONObject();

            int errorCount = 0;
            Row resRow = null;
            for(int j=0;j<rows;j++){
                row = sheet.getRow(j);
                if(null != row){

                }
                param.put("companyName",row.getCell(0).toString());
                param.put("cdTotalAmount",row.getCell(1));
                param.put("fcdTotalAmount",row.getCell(2));
                String code = excelOperate.migrateQYCreditData(param);
                if(!"10000".equals(code)){
                    resRow = result.createRow(errorCount);
                    resRow.createCell(0).setCellValue(row.getCell(0).toString());
                    resRow.createCell(2).setCellValue(code);
                    errorCount++;
                }
            }

            File resFile = new File("/opt/dateResult.xls");
            FileOutputStream xlsStream = new FileOutputStream(resFile);
            workbook.write(xlsStream);

        }else{

        }
    }
}
