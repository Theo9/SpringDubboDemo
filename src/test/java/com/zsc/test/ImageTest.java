package com.zsc.test;

import org.apache.poi.util.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author:ShaochaoZhao
 * @Description:
 * @Date:Create in 10:39 2019/2/11
 * @Modified By:
 **/
public class ImageTest {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\130419\\Desktop\\image\\cc.png");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile =new MockMultipartFile("file", file.getName(), "text/plain", input);
        try {
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            if (image != null) {//如果image=null 表示上传的不是图片格式
                System.out.println(image.getWidth());//获取图片宽度，单位px
                System.out.println(image.getHeight());//获取图片高度，单位px
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
