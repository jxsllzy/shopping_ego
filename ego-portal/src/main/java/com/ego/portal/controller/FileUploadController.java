package com.ego.portal.controller;

import com.ego.common.result.FileResult;
import com.ego.portal.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("fileUpload")
public class FileUploadController {
    @Autowired
    private UploadService uploadService;
    @RequestMapping("upload")
    @ResponseBody
    public FileResult upload(MultipartFile file, Model model) throws IOException {
        /*System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());*/
        String fileName=file.getOriginalFilename();
        //格式化时间
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd/").format(LocalDateTime.now());
        fileName=date+System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf('.'));
        return uploadService.upload(file.getInputStream(), fileName);



    }

}
