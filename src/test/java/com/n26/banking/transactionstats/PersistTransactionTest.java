package com.n26.banking.transactionstats;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.acl.LastOwnerException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import  static org.junit.Assert.*;
public class PersistTransactionTest {

    ConcurrentNavigableMap<Long, List<TransactionInput>> testTransactions=null;
    @Before
    public void setUp() throws Exception {
         testTransactions =new ConcurrentSkipListMap<>();
         testTransactions.put(1509349242127L, Arrays.asList(new TransactionInput(100.00,1509349242127L)));
    }
    @After
    public void tearDown() throws Exception {
        testTransactions.clear();
    }
    @Test
    public void persistTransaction() throws Exception {
        assertTrue(testTransactions.size()==1);

    }
    @Test
    public void getPersistTransactions() throws Exception {
        assertTrue(testTransactions.containsKey(1509349242127L));
    }

    public Long test60SecsTimeDiff() {
         return Instant.now().minusSeconds(60).toEpochMilli();

    }


}