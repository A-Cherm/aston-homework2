package ru.acherm.astonhw2.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.acherm.astonhw2.event.UserCreatedEvent;
import ru.acherm.astonhw2.event.UserDeletedEvent;

@Component
public class UserEventHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "user-created-event-topic")
    public void handleCreated(UserCreatedEvent event) {
        logger.info("Получил событие создания пользователя с id = {}, name = {}",
                event.getId(), event.getName());
    }

    @KafkaListener(topics = "user-deleted-event-topic")
    public void handleDeleted(UserDeletedEvent event) {
        logger.info("Получил событие удаления пользователя с id = {}, email = {}",
                event.getId(), event.getEmail());
    }
}
