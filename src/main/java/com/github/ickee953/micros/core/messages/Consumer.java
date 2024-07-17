package com.github.ickee953.micros.core.messages;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface Consumer<K, V> {

    void consume(ConsumerRecord<K, V> record);

}
