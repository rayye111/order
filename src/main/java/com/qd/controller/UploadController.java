package com.qd.controller;


import com.qd.common.result.ResultUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class UploadController {

    @Value("${web.upload-path}")
    private String path;


    @RequestMapping("/upload")
    @ResponseBody
    public Object upload(@RequestParam("file") MultipartFile file) {
        String realPath = path + "/" + file.getOriginalFilename();
        File dir = new File(realPath);
        // 判断文件父目录是否存在
        if (!dir.getParentFile().exists()) {
            dir.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dir); // 保存文件
            return ResultUtils.returnDataSuccess(file.getOriginalFilename());
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtils.returnFail("图片上传失败");
    }


    /**
     * 显示图片
     * @param fileName
     * @param suffix
     * @param response
     */
    @RequestMapping("/showPic/{fileName}.{suffix}")
    public void showPicture(@PathVariable("fileName") String fileName,
                            @PathVariable("suffix") String suffix,
                            HttpServletResponse response) {
        File imgFile = new File(path +"/"+ fileName + "." + suffix);
        responseFile(response, imgFile);
    }




    private void responseFile(HttpServletResponse response, File imgFile) {
        try (InputStream is = new FileInputStream(imgFile);
             OutputStream os = response.getOutputStream();) {
            byte[] buffer = new byte[1024];
            while (is.read(buffer) != -1) {
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}