package com.n26.banking.transactionstats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * This class is used to persis given input
 */
@Component
public class PersistTransaction {


    private final ConcurrentNavigableMap<Long, List<TransactionInput>> transactions;
    Logger logger = LoggerFactory.getLogger(PersistTransaction.class);

    public PersistTransaction() {
        this.transactions = new ConcurrentSkipListMap<>();
    }

    public synchronized List<TransactionInput> persistTransaction(TransactionInput transactionInput) {
        logger.debug("Persisting Transaction :" + transactionInput);
        List<TransactionInput> transactionList = new ArrayList<>();
        transactionList.add(transactionInput);
        return transactions.put(transactionInput.getTimestamp(), transactionList);


    }

    public ConcurrentNavigableMap<Long, List<TransactionInput>> getPersistTransactions() {
        return transactions;
    }
}
