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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelTest extends BaseTest{

    private static final Logger logger = LoggerFactory.getLogger(ExcelTest.class);
    @Autowired
    private ExcelOperate excelOperate;
    @Test
    public void migrateQYCredit() throws IOException, InvalidFormatException {
        File file = new File("E:\\file\\09.xlsx");
        if(file.exists()){
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet("Sheet1");

            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
            Sheet result = hssfWorkbook.createSheet("result");
            int rows = sheet.getLastRowNum()+1;

            Row row = null;
            JSONObject param = new JSONObject();

            int errorCount = 0;
            Row resRow = null;
            for(int j=1;j<rows;j++){
                row = sheet.getRow(j);
                if(null == row){
                    return;
                }
                if(null!=row.getCell(1)){
                    param.put("name",row.getCell(1).toString());
                    System.out.println(row.getCell(1).toString());
                }
                if(null!=row.getCell(2)){
                    param.put("identity",row.getCell(2).toString());
                    System.out.println(row.getCell(2).toString());
                }
                param.put("cdTotalAmount",row.getCell(3).toString());
                param.put("fcdTotalAmount",row.getCell(4).toString());
                String code = excelOperate.migrateQYCreditData(param);
                resRow = result.createRow(errorCount);
                resRow.createCell(0).setCellValue(row.getCell(2).toString());
                resRow.createCell(1).setCellValue(code);
                errorCount++;
            }

            File resFile = new File("E:\\file\\dateResult.xls");
            FileOutputStream xlsStream = new FileOutputStream(resFile);
            hssfWorkbook.write(xlsStream);
            logger.info("run over");
        }else{
            logger.error("can not find file");
        }
    }
}
