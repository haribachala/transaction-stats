package com.n26.banking.transactionstats;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

import static org.junit.Assert.assertTrue;

public class TimeDiff {

    // Test to verify time Difference
    Logger logger = LoggerFactory.getLogger(TimeDiff.class);

    @Test
    public void test60SecsTimeDiff() {
        long currentTime = Instant.now().toEpochMilli();
        logger.info("Current Time :" + currentTime);
        long currentTimeMinus60secs = Instant.now().minusSeconds(60).toEpochMilli();
        long diff = 60000L;
        assertTrue((currentTime - currentTimeMinus60secs) <= diff);

    }
}
