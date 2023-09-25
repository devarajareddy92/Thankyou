package com.example.Thankyou.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Thankyou.entity.FileDetails;
import com.example.Thankyou.repository.FileRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public FileDetails saveFile(MultipartFile file) throws IOException {
        FileDetails fileDetails = new FileDetails();
        fileDetails.setFileName(file.getOriginalFilename());
        fileDetails.setFileType(file.getContentType());
        fileDetails.setData(file.getBytes());
        return fileRepository.save(fileDetails);
    }

    @Override
    public Optional<FileDetails> getFile(Long id) {
        return fileRepository.findById(id);
    }

    @Override
    public List<FileDetails> getAllFiles() {
        return fileRepository.findAll();
    }
}

