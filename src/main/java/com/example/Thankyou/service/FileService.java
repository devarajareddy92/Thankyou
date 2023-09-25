package com.example.Thankyou.service;



import org.springframework.web.multipart.MultipartFile;

import com.example.Thankyou.entity.FileDetails;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FileService {
    FileDetails saveFile(MultipartFile file) throws IOException;
    Optional<FileDetails> getFile(Long id);
    List<FileDetails> getAllFiles();
}

