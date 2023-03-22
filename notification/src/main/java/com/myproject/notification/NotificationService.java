package com.myproject.notification;

import com.myproject.clients.notification.NotificationSendRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {

    NotificationRepository notificationRepository;
    public void saveNotificationToSend(NotificationSendRequest notificationSendRequest){
        notificationRepository.save(Notification
                .builder()
                .id(notificationSendRequest.customerID())
                .email(notificationSendRequest.emailID())
                .build());
    }
}
