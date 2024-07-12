package com.github.ickee953.micros.core.messages;

import com.github.ickee953.micros.core.service.UploadFileService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileKafkaProducer implements UploadFileService {


    @Override
    public void sendFiles(List<MultipartFile> files) {

    }
}
