package com.myproject.customer;

import com.myproject.amqp.RabbitMQMessageProducer;
import com.myproject.clients.fraud.FraudCheckResponse;
import com.myproject.clients.fraud.FraudClient;
import com.myproject.clients.notification.NotificationClient;
import com.myproject.clients.notification.NotificationSendRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    public void registerCustomer(CustomerRegistrationRequest request){
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraud(customer.getId());
        if(fraudCheckResponse.isFraud())
            throw new IllegalStateException("Fraudster");

        log.info("Fraud Legit {}",customer.getFirstName());

        log.info("Sending request to save notification to send for email : {}",customer.getEmail());
        NotificationSendRequest notificationSendRequest = new NotificationSendRequest(
                customer.getId(),
                customer.getEmail());

        rabbitMQMessageProducer.publish(notificationSendRequest, "internal.exchange", "internal.notification.routing-key");

        log.info("Notification saved to send :)");
    }


}
