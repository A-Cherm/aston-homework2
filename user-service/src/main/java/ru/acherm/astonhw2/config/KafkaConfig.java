package ru.acherm.astonhw2.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic userCreatedTopic() {
        return TopicBuilder.name("user-created-event-topic")
                .partitions(3)
                .build();
    }

    @Bean
    public NewTopic userDeletedTopic() {
        return TopicBuilder.name("user-deleted-event-topic")
                .partitions(3)
                .build();
    }
}
