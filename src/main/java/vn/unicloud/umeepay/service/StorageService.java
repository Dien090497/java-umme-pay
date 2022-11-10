package vn.unicloud.umeepay.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class StorageService {

    public InputStream downloadFile(String objectName) {
        if (objectName == null) {
            return null;
        }
        return null;
    }

    public String uploadFile(MultipartFile file) {
        if (file == null) {
            return null;
        }
        return "URL";
    }

}
