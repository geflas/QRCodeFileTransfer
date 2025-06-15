package com.geflas.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
public class FileUploadController {
    @GetMapping("/upload")
    public String getName(){
        return "<form method=\"POST\" action=\"/upload\" enctype=\"multipart/form-data\">\n" +
                "    <input type=\"file\" name=\"file\" />\n" +
                "    <button type=\"submit\">Upload</button>\n" +
                "</form>";
    }
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            file.transferTo(new File("/tmp/" + fileName));
            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to upload file", HttpStatus.BAD_REQUEST);
        }
    }
}
