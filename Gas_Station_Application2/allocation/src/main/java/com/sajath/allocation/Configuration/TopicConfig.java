package com.sajath.allocation.Configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

   @Bean
    public NewTopic mainTopic(){
        return TopicBuilder.name("mainTopic")
                .build();
    }
    @Bean
    public NewTopic secondTopic(){
        return TopicBuilder.name("secondTopic")
                .build();
    }

    @Bean
    public NewTopic thirdTopic(){
        return TopicBuilder.name("thirdTopic")
                .build();
    }
}
