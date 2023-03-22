package com.myproject.notification.rabbitmq;

import com.myproject.clients.notification.NotificationSendRequest;
import com.myproject.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;
    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationSendRequest notificationSendRequest){
      log.info("Consumed {} from queue",notificationSendRequest);
      notificationService.saveNotificationToSend(notificationSendRequest);
    }
}
