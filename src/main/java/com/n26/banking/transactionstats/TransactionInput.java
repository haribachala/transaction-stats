package com.n26.banking.transactionstats;

public class TransactionInput {

    private double amount;
    private long timestamp;


   // used by Framework
    public TransactionInput(){

    }


    // used by Test Case
  public   TransactionInput(double amount, long timestamp){
        this.amount=amount;
       this.timestamp=timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "TransactionInput{" +
                "amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
