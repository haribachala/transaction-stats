package com.n26.banking.transactionstats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionStatsController {
    @Autowired
    private TransactionStatsService transactionStatsService;

    @RequestMapping("/statistics")
    public ResponseEntity<TransactionVO> getLast60SecsTransactionStats() {
        return ResponseEntity.ok(transactionStatsService.crudResult());
    }
}
