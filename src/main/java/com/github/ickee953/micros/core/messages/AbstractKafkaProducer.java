package com.github.ickee953.micros.core.messages;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@Slf4j
public abstract class AbstractKafkaProducer<K, V> implements Producer<K, V> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    @Override
    public void send(K key, V value) {
        try {
            kafkaTemplate.send(getTopic(), key, value ).get();
        } catch (InterruptedException | ExecutionException e) {
            log.error(String.format("Kafka send message error in topic: %s with key: %s",
                    getTopic(), key));
        }
    }

    public abstract String getTopic();
}
