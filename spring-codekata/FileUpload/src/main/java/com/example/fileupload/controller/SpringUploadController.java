package com.example.fileupload.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/spring")
public class SpringUploadController {

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFile(){
        return "upload-form";
    }

    @GetMapping("/upload2")
    public String newFile2(){
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFile(
            @RequestParam String itemName,
            @RequestParam(value = "file2") MultipartFile file,
            HttpServletRequest request
    ) throws IOException {
        log.info("request={}",request);
        log.info("itemName={}",itemName);
        log.info("multipartFile={}",file);
        if(!file.isEmpty()){
            String fullPath = fileDir + file.getOriginalFilename();
            log.info("파일 저장={}",fullPath);
            file.transferTo(new File(fullPath));
        }
        return "upload-form";
    }


    @PostMapping("/upload2")
    public String saveFile2(@ModelAttribute RequestDto requestDto,HttpServletRequest request) throws IOException {
        log.info("request={}",request);
        log.info("itemName={}",requestDto.itemName);
        log.info("multipartFile={}",requestDto.getFile());
        if(!requestDto.getFile().isEmpty()){
            String fullPath = fileDir + requestDto.getFile().getOriginalFilename();
            log.info("파일 저장={}",fullPath);
            requestDto.getFile().transferTo(new File(fullPath));
        }
        return "upload-form";
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class RequestDto{
        String itemName;
        MultipartFile file;
    }
}
