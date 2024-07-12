package com.github.ickee953.micros.core.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadFileService {

    void sendFiles(List<MultipartFile> files);

}
