package com.n26.banking.transactionstats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@EnableAutoConfiguration
public class TransactionService {
    @Autowired
    private PersistTransaction persistTransactionService;

    @RequestMapping("/transactions")
    public ResponseEntity saveTransaction(@RequestBody TransactionInput transactionInput) throws Exception {
        validateTransactionInput(transactionInput);
        persistTransactionService.persistTransaction(transactionInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private void validateTransactionInput(TransactionInput transactionInput) throws Exception {
        if (transactionInput.getAmount() <= 0 || transactionInput.getTimestamp() <= 0)
            throw new IllegalArgumentException("Invalid Input, Amount and TimeStamp should be > zero");
    }

    public void setPersistTransactionService(PersistTransaction persistTransactionService) {
        this.persistTransactionService = persistTransactionService;
    }

}
