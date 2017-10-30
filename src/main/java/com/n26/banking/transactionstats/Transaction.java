package com.n26.banking.transactionstats;

import org.springframework.stereotype.Component;

@Component
public interface Transaction {

    boolean persistTransaction(double amount, long epochTimeStamp);
}
