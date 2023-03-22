package com.myproject.notification;

import com.myproject.clients.notification.NotificationSendRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/notification/send")
@AllArgsConstructor
public class NotificationController {

    final NotificationService notificationService;

    @PostMapping
    public void sendNotification(@RequestBody NotificationSendRequest notificationSendRequest){
            log.info("Notification to send for customer email ID :",notificationSendRequest.emailID());
            notificationService.saveNotificationToSend(notificationSendRequest);

    }
}
