package com.n26.banking.transactionstats;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionVO implements Serializable {


    private static final long serialVersionUID = -4522492652289616631L;
    private long totalNumberOfTransactions;
    private long lastOneMinuteTransactions;
    private double sumOfLast60secsTransactions;
    private double avgOfLast60SecsTransactions;
    private double minOfLast60SecsTransaction;
    private double maxOfLast60SecsTransactions;

    public double getSumOfLast60secsTransactions() {
        return sumOfLast60secsTransactions;
    }

    public void setSumOfLast60secsTransactions(double sumOfLast60secsTransactions) {
        this.sumOfLast60secsTransactions = sumOfLast60secsTransactions;
    }

    public double getAvgOfLast60SecsTransactions() {
        return avgOfLast60SecsTransactions;
    }

    public void setAvgOfLast60SecsTransactions(double avgOfLast60SecsTransactions) {
        this.avgOfLast60SecsTransactions = avgOfLast60SecsTransactions;
    }

    public double getMinOfLast60SecsTransaction() {
        return minOfLast60SecsTransaction;
    }

    public void setMinOfLast60SecsTransaction(double minOfLast60SecsTransaction) {
        this.minOfLast60SecsTransaction = minOfLast60SecsTransaction;
    }

    public double getMaxOfLast60SecsTransactions() {
        return maxOfLast60SecsTransactions;
    }

    public void setMaxOfLast60SecsTransactions(double maxOfLast60SecsTransactions) {
        this.maxOfLast60SecsTransactions = maxOfLast60SecsTransactions;
    }

    public long getLastOneMinuteTransactions() {
        return lastOneMinuteTransactions;
    }

    public void setLastOneMinuteTransactions(long lastOneMinuteTransactions) {
        this.lastOneMinuteTransactions = lastOneMinuteTransactions;
    }

    public long getTotalNumberOfTransactions() {
        return totalNumberOfTransactions;
    }

    public void setTotalNumberOfTransactions(long totalNumberOfTransactions) {
        this.totalNumberOfTransactions = totalNumberOfTransactions;
    }

    public TransactionVO crudStats(List<TransactionInput> transactionList, long totalNumberOfTransactions) {

        DoubleSummaryStatistics statistics = transactionList.parallelStream().collect(Collectors.summarizingDouble(TransactionInput::getAmount));
        this.totalNumberOfTransactions = totalNumberOfTransactions;
        this.lastOneMinuteTransactions = statistics.getCount();
        this.minOfLast60SecsTransaction = statistics.getMin();
        this.maxOfLast60SecsTransactions = statistics.getMax();
        this.avgOfLast60SecsTransactions = statistics.getAverage();
        this.sumOfLast60secsTransactions = statistics.getSum();

        return this;
    }
}
