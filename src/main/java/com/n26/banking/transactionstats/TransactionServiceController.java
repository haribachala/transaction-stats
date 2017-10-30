package com.n26.banking.transactionstats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@ResponseBody
@EnableAutoConfiguration
@RequestMapping("/transactionService")
public class TransactionServiceController {
    @Autowired
    private PersistTransaction persistTransactionService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, path = "/transactions")
    public ResponseEntity<String> saveTransaction(@RequestBody TransactionInput transactionInput) throws Exception {

        ResponseEntity<String> validationStatus = validateTransactionInput(transactionInput);
        if (validationStatus.getStatusCode().value() == 202)
            persistTransactionService.persistTransaction(transactionInput);
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).contentType(org.springframework.http.MediaType.APPLICATION_JSON).body("Invalid Input, Amount and TimeStamp should be greater than zero");
        return ResponseEntity.status(HttpStatus.CREATED).contentType(org.springframework.http.MediaType.APPLICATION_JSON).body("Request Success");
    }

    private ResponseEntity<String> validateTransactionInput(TransactionInput transactionInput) throws Exception {
        if (transactionInput.getAmount() <= 0 || transactionInput.getTimestamp() <= 0)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).contentType(org.springframework.http.MediaType.APPLICATION_JSON).body("Invalid Input, Amount and TimeStamp should be greater than zero");
        return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(org.springframework.http.MediaType.APPLICATION_JSON).body("");

    }

    public void setPersistTransactionService(PersistTransaction persistTransactionService) {
        this.persistTransactionService = persistTransactionService;
    }

}
