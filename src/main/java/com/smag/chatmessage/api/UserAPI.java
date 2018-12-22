package com.smag.chatmessage.api;

import com.smag.chatmessage.services.StorageService;
import com.smag.chatmessage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;



@RestController
public class UserAPI {
    @Autowired
    UserService userService;

    @Autowired
    StorageService storageService;


    @GetMapping(value="/user/{id}")
    private ResponseEntity getUserByIdUser(@PathVariable("id")  String id){
        return new ResponseEntity(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/user/{id}/editProfile")
    public ResponseEntity handleFileUpload(@PathVariable(value = "id") String id,@RequestParam("file") MultipartFile file) {
        String message=null;
        try {
            message =userService.editProfile(id,file);
            return new ResponseEntity(message,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(message,HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/user/{id}/profile")
    public ResponseEntity downloadFile(@PathVariable("id") String id, HttpServletRequest request) {
        // Load file as Resource
        String filename=null;
        Resource resource=null;
        String contentType = null;

        try {
            filename = userService.findById(id).getProfile();
            resource = storageService.loadFileAsResource(filename);
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception ex) {
            ex.printStackTrace();
         return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
