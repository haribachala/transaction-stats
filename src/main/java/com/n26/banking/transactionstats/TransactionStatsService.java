package com.n26.banking.transactionstats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope("prototype")
public class TransactionStatsService {
    @Autowired
    PersistTransaction persistTransaction;
    @Autowired
    TransactionVO transactionVO;

    public TransactionVO crudResult() {

        Logger logger = LoggerFactory.getLogger(TransactionVO.class);
        logger.info("Total Transactions ::" + persistTransaction.getPersistTransactions());
        logger.info("Transactions in last 60 secs::" + persistTransaction.getPersistTransactions().tailMap(last60Secs()).values().size());
        transactionVO.setTotalNumberOfTransactions(persistTransaction.getPersistTransactions().size());
        transactionVO.setLastOneMinuteTransactions(persistTransaction.getPersistTransactions().tailMap(last60Secs()).values().size());

        if (persistTransaction.getPersistTransactions().size() <= 0 || persistTransaction.getPersistTransactions().tailMap(last60Secs()).size() <= 0) {
            TransactionVO transVO = new TransactionVO();
            transVO.setTotalNumberOfTransactions(persistTransaction.getPersistTransactions().size());
            return transVO;
        }

        List<TransactionInput> last60secTransaction = persistTransaction.getPersistTransactions().tailMap(last60Secs())
                .values().stream().flatMap(transactionInputs -> transactionInputs.stream()).collect(Collectors.toList());
        return transactionVO.crudStats(last60secTransaction, persistTransaction.getPersistTransactions().size());

    }

    private long last60Secs() {
        return Instant.now().minusSeconds(60).toEpochMilli();
    }

}
