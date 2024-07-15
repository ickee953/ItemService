package com.github.ickee953.micros.items.config;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.DefaultSslBundleRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.web.multipart.MultipartFile;

@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, byte[]> producerFactory(KafkaProperties properties) {
        return new DefaultKafkaProducerFactory<>(properties.buildProducerProperties(
                new DefaultSslBundleRegistry()
        ));
    }

    @Bean
    public KafkaTemplate<String, byte[]> kafkaTemplate(ProducerFactory<String, byte[]> factory) {
        return new KafkaTemplate<>(factory);
    }

}
