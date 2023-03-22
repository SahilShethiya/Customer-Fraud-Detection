package com.myproject.fraud;

import com.myproject.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud/check")
@AllArgsConstructor
public class FraudController {
    private FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraud(@PathVariable("customerId") Integer customerID){
        boolean isFraudCustomer = fraudCheckService.isFraudCustomer(customerID);

        log.info("Fraud check request for customer {}",customerID);
        return new FraudCheckResponse(isFraudCustomer);
    }
}
