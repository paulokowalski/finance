package com.kowalski.finance.config.kafka;

import com.kowalski.finance.config.kafka.event.CompraRealizadaEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    private String bootStrapServers = "localhost:9092";

    @Bean
    public ConsumerFactory<String, CompraRealizadaEvent> compraRealizadaEventConsumerFactory() {
        ErrorHandlingDeserializer<CompraRealizadaEvent> errorHandlingDeserializer = new ErrorHandlingDeserializer<>(new JsonDeserializer<>(CompraRealizadaEvent.class));
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), errorHandlingDeserializer);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, CompraRealizadaEvent>> compraRealizadaEventDTOFactory(ConsumerFactory<String, CompraRealizadaEvent> consumerFactory){
        ConcurrentKafkaListenerContainerFactory<String, CompraRealizadaEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}