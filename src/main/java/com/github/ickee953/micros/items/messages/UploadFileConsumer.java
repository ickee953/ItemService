/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.items.messages;

import com.github.ickee953.micros.items.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UploadFileConsumer {

    private final ItemRepository itemRepository;

    @KafkaListener(topics = {"${app.kafka.topic.file-uploaded}"})
    @Transactional
    public void uploaded(ConsumerRecord<String, String> record) {
        log.info("received message : {}", record);
        itemRepository.findById(UUID.fromString(record.key())).orElseThrow()
                .setTitlePic(record.value());
    }

}
