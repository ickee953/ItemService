/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.core.messages;

import com.github.ickee953.micros.core.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class UploadFileKafkaService implements UploadFileService {

    private final KafkaTemplate<String, List<MultipartFile>> sender;

    @Override
    public void sendFiles(List<MultipartFile> files) {

    }

    @Override
    public void confirm(UUID id) {

    }
}
