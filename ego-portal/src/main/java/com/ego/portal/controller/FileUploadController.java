package com.ego.portal.controller;

import com.ego.common.result.ImageResult;
import com.ego.portal.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("ar")
public class FileUploadController {
    @Autowired
    private ImageService imageService;

    @RequestMapping("/uploadFile")
    @ResponseBody
    public ImageResult upload(MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        //格式化时间
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd/").format(LocalDateTime.now());
        fileName=date+System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf('.'));
        ImageResult upload = imageService.upload(file.getInputStream(), fileName);
        System.out.println(upload);
        return upload;

    }

}
