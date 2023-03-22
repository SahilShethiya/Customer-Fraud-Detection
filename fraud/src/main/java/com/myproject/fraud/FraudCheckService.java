package com.myproject.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;


    public boolean isFraudCustomer(Integer customerId){
        fraudCheckHistoryRepository.save(FraudCheckHistory
                .builder()
                .isFraud(false)
                .customerId(customerId)
                .createdAt(LocalDateTime.now())
                .build());
        return false;
    }
}
