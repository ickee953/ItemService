/**
 * © Panov Vitaly 2024 - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.github.ickee953.micros.items.messages;

import com.github.ickee953.micros.items.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UploadFileConsumer {

    private final ItemRepository itemRepository;

    @KafkaListener(topics = {"file-upload-topic"})
    public void uploaded(ConsumerRecord<String, String> record) {
        log.info("received message : {}", record);
        //todo update file path for returned id
        //UUID id =
        //itemRepository.findById(id).
    }

}
