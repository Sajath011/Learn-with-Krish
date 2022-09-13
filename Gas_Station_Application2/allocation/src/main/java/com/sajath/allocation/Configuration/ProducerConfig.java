package com.sajath.allocation.Configuration;

import com.sajath.allocation.Entity.CheckAllocationHistory;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerConfig {

    @Value("localhost:9092")
    private String bootstrapServers;


    public Map<String, Object> producersConfig(){
        Map<String, Object> abc = new HashMap<>();
        abc.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        abc.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        abc.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return abc;
    }


    @Bean
    public ProducerFactory<String, CheckAllocationHistory> producersFactory(){
        return new DefaultKafkaProducerFactory<>(producersConfig());
    }


    @Bean
    public KafkaTemplate<String, CheckAllocationHistory> KafkaTemplates(
            ProducerFactory<String, CheckAllocationHistory> producerFactory
    ) {
        return new KafkaTemplate<>(producerFactory);
    }

}
