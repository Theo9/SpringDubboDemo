package com.zsc.test;

import com.alibaba.fastjson.JSONObject;
import com.zsc.service.ExcelOperate;
import org.apache.commons.lang3.StringUtils;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:ShaochaoZhao
 * @Description:
 * @Date:Create in 15:02 2019/5/22
 * @Modified By:
 **/
public class IfcertUserInfoTest extends BaseTest {
    @Autowired
    private ExcelOperate excelOperate;

    private static final Logger logger = LoggerFactory.getLogger(ExcelTest.class);

    @Test
    public void migrateQYCredit() throws IOException, InvalidFormatException {
        File file = new File("E:\\company\\5-23.xlsx");
        if (file.exists()) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet("Sheet1");

            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
            Sheet result = hssfWorkbook.createSheet("result");
            int rows = sheet.getLastRowNum() + 1;

            Row row = null;
            JSONObject param = new JSONObject();

            int errorCount = 0;
            Row resRow = null;
            for (int j = 1; j < rows; j++) {
                System.out.println("第"+j+"条数据");
                row = sheet.getRow(j);
                if (null == row) {
                    return;
                }
                if(null == row.getCell(1) || StringUtils.isBlank(row.getCell(1).toString())){
                    resRow = result.createRow(errorCount);
                    resRow.createCell(0).setCellValue(row.getCell(1).toString());
                    resRow.createCell(1).setCellValue("资产信息缺失");
                    continue;
                }
                if(null == row.getCell(2) || "0".equals(row.getCell(2).toString()) || StringUtils.isBlank(row.getCell(2).toString())){
                    resRow = result.createRow(errorCount);
                    resRow.createCell(0).setCellValue(row.getCell(1).toString());
                    resRow.createCell(1).setCellValue("省份信息缺失");
                    continue;
                }
                if(null == row.getCell(3) || "0".equals(row.getCell(3).toString()) || "1900-01-00".equals(row.getCell(3).toString()) || StringUtils.isBlank(row.getCell(3).toString())){
                    resRow = result.createRow(errorCount);
                    resRow.createCell(0).setCellValue(row.getCell(1).toString());
                    resRow.createCell(1).setCellValue("注册地址信息缺失");
                    continue;
                }
                if(null == row.getCell(4) || "0".equals(row.getCell(4).toString()) || StringUtils.isBlank(row.getCell(4).toString())){
                    resRow = result.createRow(errorCount);
                    resRow.createCell(0).setCellValue(row.getCell(1).toString());
                    resRow.createCell(1).setCellValue("注册时间信息缺失");
                    continue;
                }
                param.put("companyId", row.getCell(0).toString());
                param.put("userFund", row.getCell(1).toString());
                param.put("userProvince", row.getCell(2).toString());
                param.put("userAddress", row.getCell(3).toString());
                param.put("registerDate", row.getCell(4).toString());
                param.put("industry", row.getCell(5).toString());
                param.put("logout", (new BigDecimal(row.getCell(6).toString()).byteValue()));
                String code = excelOperate.saveCompanyRegisterInfo(param);
                resRow = result.createRow(errorCount);
                resRow.createCell(0).setCellValue(row.getCell(2).toString());
                resRow.createCell(1).setCellValue(code);
                errorCount++;
            }

            File resFile = new File("E:\\company\\dateResult.xls");
            FileOutputStream xlsStream = new FileOutputStream(resFile);
            hssfWorkbook.write(xlsStream);
            logger.info("run over");
        } else {
            logger.error("can not find file");
        }
    }

    @Test
    public void retryUserInfo(){

        String str ="2134";
        String[] ids = str.split(",");
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        for (String id : ids) {
            executorService.execute(() -> {
                excelOperate.userInfo(Integer.valueOf(id));
            });
        }

        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkUserId(){
        List<String> userIds = new ArrayList <>();
        userIds.add("3fb6933b-98ab-494d-b570-a1cd07f94808");
        userIds.add("449a8dc7-236b-41e7-9c61-a2207d06f4e5");
        userIds.add("493a5006-259c-42ca-9ae9-57cfc822ea2f");
        userIds.add("4962f718-72c3-4cac-8420-1c7dc737765a");
        userIds.add("4cdd0ab1-ef72-445a-a9ff-a7f09b77f207");
        userIds.add("4d393aeb-2057-44c8-9d61-83107dc04618");
        userIds.add("4d4e412d-8e93-4345-bbb3-115a4ffea122");
        userIds.add("4eeba828-2e41-4d3d-a17b-cb720642ca0c");
        userIds.add("4ffba172-a6db-4241-b761-3459bada7185");
        userIds.add("5277f559-557b-46e3-8191-eb4bc27f543b");
        userIds.add("583ffc8c-a3f3-44a8-a6ab-5e30b762cf86");
        userIds.add("5eedf451-7235-4955-8e7b-c9dbc0ccc09d");
        userIds.add("5f8abcd1-c01f-4243-8441-0e82aa17d392");
        userIds.add("60353d42-a9b1-4ff1-bdfe-3dde08299b0e");
        userIds.add("603ce8c3-6fe2-450e-bf12-b6e1ec257e90");
        userIds.add("61a890f2-a302-43bd-8f62-cca6eb04c8ae");
        userIds.add("61f172cd-e1a9-444d-b681-6f4402fcb74e");
        userIds.add("6247d764-8162-4b07-ae64-f0e2cc226641");
        userIds.add("634c9a34-cade-4c19-8ee8-f4f40da95973");
        userIds.add("671a447e-9612-48dd-86d1-42e2ebef6663");
        userIds.add("674b75cc-48b3-4fb2-a0f8-aefe7d292961");
        userIds.add("68032ab9-de3f-4664-a657-7e242dddc705");
        userIds.add("6b9a6d7d-51e7-497b-8bc0-c6a499b4ed4e");
        userIds.add("6d2cdabc-51b0-408c-8d12-6d2e3abba87a");
        userIds.add("6df83b78-2cce-4c57-9504-a0c3ef8ffb2f");
        userIds.add("6e7f8029-9987-4899-86ef-fb58d9030b18");
        userIds.add("6ed85b04-68a7-4bc6-9060-6f1980b14ff2");
        userIds.add("6f477763-972e-46ff-8a84-a826fafe178a");
        userIds.add("70159025-3036-4c9d-af01-661f8f0cd8a2");
        userIds.add("70d29efd-a69e-4730-92c1-92d07be8f4f5");
        userIds.add("7153322b-ec0d-4845-bce8-094eb7e0f793");
        userIds.add("72272071-c3dc-454f-978c-53cdaaf6d46c");
        userIds.add("72335587-16f0-497a-9c05-55f29e8cb3b3");
        userIds.add("75453f9b-87e3-4791-b725-0455186a1ad0");
        userIds.add("76609ab0-d2ad-419d-88dd-26b4f046d508");
        userIds.add("7878dc4c-cde2-4eca-98d4-714537f0c312");
        userIds.add("7a50113c-094d-48a7-84c3-250e5c2e8b16");
        userIds.add("7a65b20f-7bb2-4377-aedc-cf309b5390b8");
        userIds.add("7e49de0d-e2b9-4c88-9d1b-f46ca8a182b6");
        userIds.add("7e8e8a84-bca0-47d7-b2cd-3ac305f040dc");
        userIds.add("7ee929ef-7d24-4697-a971-0d146f7f7678");
        userIds.add("8105a0fd-0b0b-4343-8e7e-39bb44ae911a");
        userIds.add("82818750-5576-4c50-8d3a-9ca0af1ba5c5");
        userIds.add("82faa6ec-d5de-4590-bdd3-59ac902556de");
        userIds.add("83be9c9e-a056-4821-841f-15b3046c5ecb");
        userIds.add("8413c08d-fcc8-4b50-a97b-a577b5467716");
        userIds.add("8518e5c7-c18b-4334-8145-549cab75db3c");
        userIds.add("89a1633f-e24a-4cd4-a2f9-f3961eda1674");
        userIds.add("8be8f5ce-912e-4917-8d99-48ded6c9b651");
        userIds.add("8bfc0609-7aef-4cf2-bd00-0311d8657d13");
        userIds.add("8f6f6233-45c2-4179-b98e-2cb3102468fb");
        userIds.add("908eeec0-8ff1-4bb2-80a2-692fc0869b6d");
        userIds.add("919e2808-14d0-41a1-864f-7e4293e27aa9");
        userIds.add("91cf3f77-3b92-469d-8f16-bc9572d247de");
        userIds.add("92541f84-8b90-49ea-bc5f-d00158c22b77");
        userIds.add("994d806e-a992-4b35-9a93-3589d333f9f5");
        userIds.add("9afa44eb-e29f-41b1-9bae-7de655ae2865");
        userIds.add("9c004693-0b60-42de-b9ec-07b694b21e31");
        userIds.add("9e7913e4-3c52-4519-be10-721e0002a72c");
        userIds.add("9efac311-aaca-4536-93d1-2e093563af2c");
        userIds.add("9f4d1c0c-602a-4d1b-b143-9cd08370e1b6");
        userIds.add("a0705e64-dbbf-4e2b-8673-1e5389008598");
        userIds.add("a0a0d3d8-4fb1-45ae-b39f-b110e50efb42");
        userIds.add("a2d9ac8f-8a22-4cb7-9095-764794c9e5cf");
        userIds.add("a3ab422d-9464-4a68-a1f3-42e929e4a41e");
        userIds.add("aadb0047-c78b-4572-a119-34d68413d78d");
        userIds.add("add22048-dc1e-4522-af4c-a5cb34a511fd");
        userIds.add("b25a7b7d-cf7e-40fa-952f-b1e7f3a0c422");
        userIds.add("b2a0543e-71f2-4522-834c-ac02605c1cef");
        userIds.add("b45b8ec8-8e96-4544-b49c-26176b65995c");
        userIds.add("b875f238-7016-4ef4-97af-7c73afaa339b");
        userIds.add("b9232293-a9e4-4efa-9f22-c4aedec1632c");
        userIds.add("b9a61244-3248-427d-b383-1d2edde60810");
        userIds.add("baf93d21-8f30-47af-abd7-64ad1932d21d");
        userIds.add("bceb845c-2157-44d5-b8e5-5ee869d8b054");
        userIds.add("bee20078-4d4c-458b-bfa4-fb75ed2d75b3");
        userIds.add("bef1d96a-13c1-4cee-9169-0c3a2a065ce0");
        userIds.add("bf0ac3fe-4d76-4f8b-980e-f4f34e7eb3cb");
        userIds.add("c00eb652-00e6-4711-bb90-ad282916e288");
        userIds.add("c0c016c1-2456-4389-b001-3959cc73b14c");
        userIds.add("c363ade9-8099-4987-a816-f02cd0671de3");
        userIds.add("c3a53a09-629a-48d0-b8be-7aee26cac6f2");
        userIds.add("c44d941f-253a-4861-98a2-745da97a7043");
        userIds.add("c509cc03-d9db-4a5c-ae28-14190d992ef9");
        userIds.add("c900cbf0-a82f-41c3-be86-01c5d2b3eed0");
        userIds.add("c979fc7e-c986-458d-b86b-382fb0187c2d");
        userIds.add("cf099774-1a44-4f0c-aca5-a9a0c8f92d45");
        userIds.add("d1d3e3b7-e506-41f8-af4c-69f5289a30a6");
        userIds.add("d3be9c27-09bc-49b5-81d4-214bdb5f5b4d");
        userIds.add("db8448fe-c1e6-4a19-a866-dd268ae22bd6");
        userIds.add("e2efa7d8-815e-483f-9b6b-c57fd086811f");
        userIds.add("e3b43530-bcef-44a2-9d92-b92653a028ca");
        userIds.add("e3d5fd97-43a0-48ec-9512-185e5277e0c8");
        userIds.add("e65f5472-8cb0-44e2-ae95-63fde5559bcf");
        userIds.add("e7b2eb2a-3706-4ee9-bf50-973012455352");
        userIds.add("e7cbe446-64f7-46b4-b1e5-6bec73a89e32");
        userIds.add("edd009ab-d201-43f3-aacc-c0c0e25ed97f");
        userIds.add("eea29f18-22cc-473b-a1f8-68153d194e26");
        userIds.add("f480d139-ce08-43f9-b262-9283e19c07c7");
        userIds.add("f703cea7-249c-4577-9d52-4fe70c014ae4");
        userIds.add("f7b022b8-8e29-4f34-88d8-c54436cd7cfd");
        userIds.add("fbdf6269-6974-479e-bf85-4fbc04696321");
        userIds.add("01edaa6b-3f31-40f7-beb2-d7ae4848fa07");
        userIds.add("12b0f0a8-19b1-4572-903c-0d0dca5a5e00");
        userIds.add("150bd32b-4a97-4333-ae9e-c7041d5b37b9");
        userIds.add("193ddf16-13d2-4a32-8844-c5e2b07ff9ff");
        userIds.add("1d916a7f-409d-4ba0-b22f-17be207be502");
        userIds.add("1de3ae4f-db9b-4402-b453-1fa9edf15ddd");
        userIds.add("22d3ad34-4d7c-4b26-b8a8-c3e40d6475c9");
        userIds.add("23ddf930-4d65-435a-b054-6a9fab32441b");
        userIds.add("2b0ecada-e0f7-4e2c-8835-ffe81c7af579");
        userIds.add("2e28c7d3-fcd5-4b0f-b2e4-08d5d91f012f");
        userIds.add("2fd29432-3c49-42c6-b670-17e4c6be988a");
        userIds.add("32d29f77-28d5-4448-a297-4d013a898956");
        userIds.add("37693ff8-a851-49be-9cd1-7f95377df630");
        userIds.add("3fd3d936-6f84-4ce5-8198-65e54bcf2d92");
        userIds.add("4406bef3-dc1a-4583-a932-846db7b78b0a");
        userIds.add("44df184a-8a15-44f3-ab42-c1a8d805a050");
        userIds.add("4d6b555b-542a-4201-a0a0-7caaf61e3705");
        userIds.add("4da473df-518f-4d27-9513-6646dc70eea9");
        userIds.add("56b3df43-1352-4027-82e0-c86f17654961");
        userIds.add("5c489f4d-31bb-4bca-8afe-8a3d198294d5");
        userIds.add("5e22c94c-6784-41d1-8cac-439b301ad185");
        userIds.add("63a8b5e9-635b-4d9e-948e-f8df82c702b3");
        userIds.add("65dc64fd-69c8-487f-8c17-5a92c6e099aa");
        userIds.add("67b433d1-ebd4-4a67-ae46-cdf8010985fd");
        userIds.add("6d632f20-14f0-42e2-a2de-8e66e95cb4ae");
        userIds.add("6eca785c-75ca-44d4-80fc-b6a17066fc59");
        userIds.add("6f0af04a-7103-432c-964e-abcd956f94cd");
        userIds.add("79b3f80e-d389-44fa-887e-94c18af1bf7f");
        userIds.add("7afb43bc-c504-4941-a69b-59ce43beb001");
        userIds.add("7e5337d0-2567-40eb-87c8-c992d8e660a4");
        userIds.add("83331bfd-d989-46fb-8c1b-efadc054f156");
        userIds.add("8397ebb4-16bf-478f-a83f-38657e19dc15");
        userIds.add("90c78334-4cd3-48b3-b49b-a66ac5696b98");
        userIds.add("9802340a-bea1-4649-a4ba-509474498bf6");
        userIds.add("988747f3-84d6-4694-a498-32b7d271d2e5");
        userIds.add("9f8eea52-f1d8-4510-b5d1-aa14ff442cc8");
        userIds.add("a71b5146-0922-4264-bbe7-582108f3c9d5");
        userIds.add("a84660aa-f4b5-4e85-b4e1-0fb71a877c25");
        userIds.add("aacd8d08-401c-4608-bf6e-444c5a41a713");
        userIds.add("ab3d0d7d-6881-481c-95cb-45565b1269f7");
        userIds.add("b1fdb3b3-c5c2-48b7-8971-abc93ec5d6de");
        userIds.add("b4e16662-c0b8-4292-9e5e-71a08ff54e7b");
        userIds.add("b8ad173c-dd43-4432-9b1d-e022221c12d2");
        userIds.add("b9903d69-2c3f-4c28-b54d-468e020328c3");
        userIds.add("ba82b2c9-244e-4050-bb47-5e3b2237bf2b");
        userIds.add("bbc818db-8b9e-4eb0-ace0-4f220392347c");
        userIds.add("bcb86577-908f-4333-b281-135120320b9a");
        userIds.add("c0ae6b37-75d3-4422-8a74-25d9a7959172");
        userIds.add("c18fbb1f-633b-459a-9b98-edcefb18f281");
        userIds.add("c1d73319-8e22-4e8f-8cfd-a04a7ff652e3");
        userIds.add("c378af57-162e-452d-85f9-4d624715a114");
        userIds.add("c4164c11-ed6b-4dfd-b8b6-95a9a06adafa");
        userIds.add("c90f8431-9de0-4d7d-984f-ecf44f000f8c");
        userIds.add("c9b01cce-4fd2-4ce2-8d9c-6e02a67f52a2");
        userIds.add("cbf5a01f-1803-4246-9bae-45ce4fb70ea9");
        userIds.add("ce3739d8-ec7a-42b2-9153-a02f9485c6dc");
        userIds.add("cf40da51-642c-48d2-80e6-549a80a0c999");
        userIds.add("d2324288-b812-449f-984c-13a161873742");
        userIds.add("d71ddc4c-d522-4824-a8bb-1b06813a9f7f");
        userIds.add("ec9eb69f-ffd1-4884-9300-f5ced1b6f312");
        userIds.add("ed150a8a-9e7d-4400-99b2-12140364120d");
        userIds.add("efe9389c-f1f5-485e-a513-4c6a7eb06d43");
        for(int i =0;i<userIds.size();i++){
            System.out.println(i+"-----------"+userIds.get(i));
            excelOperate.checkUserById(userIds.get(i));
        }

    }
}
