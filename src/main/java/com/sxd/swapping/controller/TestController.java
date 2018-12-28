package com.sxd.swapping.controller;


import com.sxd.swapping.base.UniVerResponse;
import com.sxd.swapping.utils.ProjectPath;
import com.xiaoleilu.hutool.io.FileUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
@RequestMapping("/test")
public class TestController {


    public static final String PAKING_DATE_FILE_PATH = "paking_data";

    public static final long MAX_UPLOAD_FILE_SIZE = 100*1024*1024;

    @RequestMapping("/111")
    public void showTest(){
        String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        System.out.println(filePath);
    }


    /**
     * 上传 文件测试
     * @param codeTxt
     * @return
     */
    @RequestMapping(value = "/txtUpload",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    UniVerResponse<String> txtUpload(MultipartFile codeTxt, String fileName){
        UniVerResponse.simplCheckField(codeTxt,"上传文件内容");
        UniVerResponse.simplCheckField(fileName,"上传文件名称");

        UniVerResponse<String> res = new UniVerResponse<>();

        //文件大小限制
        if (MAX_UPLOAD_FILE_SIZE >= codeTxt.getSize()) {
            try {
                InputStream inputStream = codeTxt.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(reader);

                String lineText = null;
                StringBuilder sb = new StringBuilder();


                while ((lineText = br.readLine()) != null){
                    sb.append(lineText);
                }

                String realFileName = writeFile(sb.toString(),fileName);


                res.beTrue(realFileName);
            } catch (IOException e) {
                e.printStackTrace();
                res.beFalse("文件解析失败",UniVerResponse.ERROR_BUSINESS,null);
            }
        }else {
            res.beFalse("文件大小超过限制",UniVerResponse.ERROR_BUSINESS,null);
        }

        return res;
    }

    /**
     * 下载  文件测试
     *
     * 仅用文件名进行下载测试
     * @param fileName
     * @return
     */
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    ResponseEntity<byte[]> download(String fileName){
        String filePath = ProjectPath.getProjectPath(PAKING_DATE_FILE_PATH) + "/" + fileName;

        ResponseEntity<byte[]> responseEntity = null;
        File file = new File(filePath);
        if (file.exists()) {

                HttpHeaders headers = new HttpHeaders();

                try {
                    headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "ISO8859-1"));
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    byte[] x = FileUtil.readBytes(file);

                    responseEntity = new ResponseEntity<byte[]>(x, headers, HttpStatus.CREATED);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

        }else {
            System.out.println("文件不存在");
        }
        return responseEntity;
    }





    /**
     * 写文件到服务器上
     * @param context
     * @param fileName
     * @return
     */
    private String writeFile(String context,String fileName){
        String filePath = getFilePath() + "/" + fileName;
        OutputStreamWriter write = null;
        BufferedWriter writer = null;

        try {
            File f = new File(filePath);

            File parentFile = f.getParentFile();
            if (!parentFile.exists()){
                parentFile.mkdirs();
            }

            if (!f.exists()) {
                f.createNewFile();
            }
            write = new OutputStreamWriter(new FileOutputStream(f));
            writer = new BufferedWriter(write);
            writer.write(context);
            writer.flush();

        } catch (Exception e) {
            fileName = null;
            e.printStackTrace();
        }finally {
            try {
                if(write != null){
                    write.close();
                }
                if(writer != null){
                    writer.close();
                }
            } catch (Exception e2) {

            }
        }

        return fileName;
    }


    /**
     * linux 下项目根路径为   /swapping-data/paking_data
     * windows 下项目根路径为  E:/swapping/paking_data
     * @return
     */
    private String getFilePath(){
        return ProjectPath.getProjectPath(PAKING_DATE_FILE_PATH);
    }
}
