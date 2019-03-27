package com.mko.test.controller;

import com.mko.test.entity.MessageProperties;
import com.mko.test.entity.ResponseResult;
import com.mko.test.service.IStatusMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * @program: testdemo
 * @description:
 * @author: yuxz
 * @create: 2019-03-25 17:31
 **/
@RestController
@RequestMapping("/upload1")
public class ImageCutController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageCutController.class);
    @Autowired
    private MessageProperties config;

    //返回路径
    @RequestMapping(value = "/cutImage", method = RequestMethod.POST)
    public ResponseResult cutImage(@RequestParam(value = "file") MultipartFile file) {

        ResponseResult result=new ResponseResult();

        try {
            //判断file是否为空
            if(file==null){
                result.setCode(IStatusMessage.SystemStatus.PARAM_ERROR.getCode());
                return result;
            }

            //将MultipartFile转换为file型
            File toFile = null;
            if(file.equals("")||file.getSize()<=0){
                file = null;
            }else {
                InputStream ins = null;
                ins = file.getInputStream();
                toFile = new File(file.getOriginalFilename());
                inputStreamToFile(ins, toFile);
                ins.close();
            }
            //读取图片
            BufferedImage srcImage=ImageIO.read(toFile);

            List<Object> FF=new ArrayList<>();
            BufferedImage image1=srcImage.getSubimage(130,140,775,174);
            FF.add(image1);
            BufferedImage image2=srcImage.getSubimage(100,420,140,130);
            FF.add(image2);
            BufferedImage image3=srcImage.getSubimage(420,420,140,130);
            FF.add(image3);
            BufferedImage image4=srcImage.getSubimage(720,420,140,130);
            FF.add(image4);
            BufferedImage image5=srcImage.getSubimage(100,590,280,180);
            FF.add(image5);
            BufferedImage image6=srcImage.getSubimage(420,590,280,180);
            FF.add(image6);
            BufferedImage image7=srcImage.getSubimage(720,590,280,180);
            FF.add(image7);
            BufferedImage image8=srcImage.getSubimage(100,870,380,340);
            FF.add(image8);
            BufferedImage image9=srcImage.getSubimage(500,870,380,340);
            FF.add(image9);


            Map<String,Object> resultFile=new HashMap<>();
                //循环截图
                 for(int i=0;i<FF.size();i++){

                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                // 获得文件类型
                String fileType = file.getContentType();
                // 获得文件后缀名称
                String imageName = fileType.substring(fileType.indexOf("/") + 1);
                // 原名称
                String oldFileName = file.getOriginalFilename();
                //新名称
                String newFileName = uuid + "." + imageName;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String basedir = sdf.format(new Date());
                String newUUID = UUID.randomUUID().toString().replaceAll("-", "");
                newFileName = newUUID + "." + imageName;
                //图片路径与名字
                String path = config.getUpPath() + "/" + basedir + "/" + newUUID + "." + imageName;
                // 如果目录不存在则创建目录
                File outputfile = new File(path);
                if (!outputfile .exists()) {
                    outputfile .mkdirs();
                }
                //返回路径
                BufferedImage test= (BufferedImage) FF.get(i);
                ImageIO.write(test, "jpeg", outputfile);
                     resultFile.put("image"+(i+1),outputfile);
            }
            result.setData1(resultFile);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(">>>>>>图片上传异常，e={}", e.getMessage());
            result.setCode(IStatusMessage.SystemStatus.ERROR.getCode());
            result.setMessage(IStatusMessage.FILE_UPLOAD_ERROR);
        }
        return result;
    }


    //返回类型
    @RequestMapping(value = "/cutImages", method = RequestMethod.POST)
    public ResponseResult cutImages(@RequestParam(value = "file") MultipartFile file) {

        ResponseResult result=new ResponseResult();
        try {
            //判断file是否为空
            if(file.isEmpty()){
                result.setCode(IStatusMessage.SystemStatus.PARAM_ERROR.getCode());
                return result;
            }
            //将MultipartFile转换为file型
            File toFile = null;
            if(file.equals("")||file.getSize()<=0){
                file = null;
            }else {
                InputStream ins = null;
                ins = file.getInputStream();
                toFile = new File(file.getOriginalFilename());
                inputStreamToFile(ins, toFile);
                ins.close();
            }

            //装截图数据装进list
            List<Object> FF=new ArrayList<>();
            //读取图片
            BufferedImage srcImage=ImageIO.read(toFile);
            BufferedImage image1=srcImage.getSubimage(130,140,775,174);
            FF.add(image1);
            BufferedImage image2=srcImage.getSubimage(100,420,140,130);
            FF.add(image2);
            BufferedImage image3=srcImage.getSubimage(420,420,140,130);
            FF.add(image3);
            BufferedImage image4=srcImage.getSubimage(720,420,140,130);
            FF.add(image4);
            BufferedImage image5=srcImage.getSubimage(100,590,280,180);
            FF.add(image5);
            BufferedImage image6=srcImage.getSubimage(420,590,280,180);
            FF.add(image6);
            BufferedImage image7=srcImage.getSubimage(720,590,280,180);
            FF.add(image7);
            BufferedImage image8=srcImage.getSubimage(100,870,380,340);
            FF.add(image8);
            BufferedImage image9=srcImage.getSubimage(500,870,380,340);
            FF.add(image9);
            //循环截图
            List<Object> lsitByte=new ArrayList<>();
            //装截图数据装进list
            Map<String,Object> resultByte=new HashMap<>();
            for(int i=0;i<FF.size();i++){

                BufferedImage test= (BufferedImage) FF.get(i);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(test, "jpeg", os);
                os.flush();
                byte [] b=os.toByteArray();
                os.close();
//                result1.put("image"+(i+1),b);
                lsitByte.add(b);
                System.out.println(b);
            }
            System.out.println(lsitByte);
            resultByte.put("lsitByte",lsitByte);
            result.setData1(resultByte);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(">>>>>>图片上传异常，e={}", e.getMessage());
            result.setCode(IStatusMessage.SystemStatus.ERROR.getCode());
            result.setMessage(IStatusMessage.FILE_UPLOAD_ERROR);
        }
        return result;
    }

//MultipartFile 转file方法
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void inputStreamfile(File file) {
////        FileInputStream fis = null;
////        try {
////            OutputStream os = new FileOutputStream(file);
////            fis = new FileInputStream(file);
////            byte[] b = new byte[fis.available()];
////            fis.read(b);
////            os.write(b);
////            os.flush();
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            if (fis != null) {
////                try {
////                    fis.close();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        }
////    }
}
