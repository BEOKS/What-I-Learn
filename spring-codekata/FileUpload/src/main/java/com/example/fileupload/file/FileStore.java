package com.example.fileupload.file;

import com.example.fileupload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;

    public String getFulPath(String filename){
        return fileDir+filename;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        ArrayList<UploadFile> storeFileList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            storeFileList.add(storeFile(multipartFile));
        }
        return storeFileList;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(multipartFile);
        multipartFile.transferTo(new File(getFulPath(storeFileName)));
        return new UploadFile(originalFilename,storeFileName);
    }

    private String createStoreFileName(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        assert originalFilename != null;
        String ext = extractExtensionName(originalFilename);
        return uuid + "." + ext;
    }

    private static String extractExtensionName(String originalFilename) {
        int pos= originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

}
