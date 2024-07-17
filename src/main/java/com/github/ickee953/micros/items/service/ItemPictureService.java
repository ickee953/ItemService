package com.github.ickee953.micros.items.service;

import com.github.ickee953.micros.core.messages.AbstractKafkaProducer;
import com.github.ickee953.micros.core.messages.Consumer;
import com.github.ickee953.micros.items.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ItemPictureService extends AbstractKafkaProducer<String, byte[]> implements Consumer<String, String> {

    private final ItemRepository itemRepository;

    @Value("${app.kafka.topic.file-upload}")
    private String fileUploadTopic;

    public ItemPictureService(ItemRepository itemRepository, KafkaTemplate<String, byte[]> kafkaTemplate) {
        super(kafkaTemplate);
        this.itemRepository = itemRepository;
    }

    @Override
    public String getTopic() {
        return fileUploadTopic;
    }

    @Override
    @KafkaListener(topics = {"${app.kafka.topic.file-uploaded}"})
    @Transactional
    public void consume(ConsumerRecord<String, String> record) {
        log.info("received message : {}", record);
        itemRepository.findById(UUID.fromString(record.key())).orElseThrow()
                .setTitlePic(record.value());
    }
}
