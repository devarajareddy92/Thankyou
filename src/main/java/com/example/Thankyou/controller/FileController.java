package com.example.Thankyou.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.Thankyou.entity.FileDetails;
import com.example.Thankyou.service.FileService;

import java.io.IOException;
import java.util.List;

@Controller

public class FileController {

    @Autowired
    private FileService fileService;
    


    @GetMapping("/")
    public ResponseEntity<Object> showFiles() {
        List<FileDetails> files = fileService.getAllFiles();
        if (files == null || files.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.ok(files);  // 200 OK with files data
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            fileService.saveFile(file);
        }
        return "redirect:/all";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) {
        FileDetails fileDetails = fileService.getFile(id).orElse(null);

        if (fileDetails == null) {
            return ResponseEntity.notFound().build();
        }

        ByteArrayResource resource = new ByteArrayResource(fileDetails.getData());

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileDetails.getFileName())
            .contentType(MediaType.parseMediaType(fileDetails.getFileType()))
            .body(resource);
    }
    @GetMapping("/all")
    public String showUploadForm(Model model) {
        model.addAttribute("fileDetails", new FileDetails());
        model.addAttribute("files", fileService.getAllFiles());
        return "file";
    }
}
