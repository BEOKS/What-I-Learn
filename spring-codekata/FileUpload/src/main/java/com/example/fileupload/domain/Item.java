package com.example.fileupload.domain;

import lombok.Data;

import java.util.List;

@Data
public class Item {
    private Long id;
    private String itemName;
    private UploadFile uploadFile;
    private List<UploadFile> imageFiles;
}