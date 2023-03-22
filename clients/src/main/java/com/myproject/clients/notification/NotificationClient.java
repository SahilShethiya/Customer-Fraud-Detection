package com.myproject.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        value = "notification",
        path = "api/v1/notification/send"
)
public interface NotificationClient {
    @PostMapping
    void sendNotification(@RequestBody NotificationSendRequest notificationSendRequest);
}
