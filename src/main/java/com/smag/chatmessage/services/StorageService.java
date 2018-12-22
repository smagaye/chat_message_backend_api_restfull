package com.smag.chatmessage.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.smag.chatmessage.helper.GenerateCode;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get("upload_dir_images");

    public void init() {
        try {
            if(!Files.isReadable(rootLocation))
                Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

    public static String getExtension(String filename) {
        return filename.substring(filename.indexOf("."), filename.length());
    }

    private String nameFormatter(String originalFilename) {
        return GenerateCode.clefUTC("IMGP").concat(getExtension(originalFilename));
    }

    public String store(MultipartFile file) {
        try {
            String newFilename=nameFormatter(file.getOriginalFilename());
            Files.copy(file.getInputStream(), this.rootLocation.resolve(newFilename));
            return newFilename;
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public Resource loadFileAsResource(String filename) {
        try {
            Path filePath = Paths.get(rootLocation.toAbsolutePath().toString().concat(File.separator).concat(filename));
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void deleteFile(String filename) {
        try {
            FileUtils.forceDelete(Paths.get(rootLocation.toAbsolutePath().toString().concat(File.separator).concat(filename)).toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

}