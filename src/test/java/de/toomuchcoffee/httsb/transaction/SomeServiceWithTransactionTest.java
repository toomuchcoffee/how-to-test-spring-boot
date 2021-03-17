package de.toomuchcoffee.httsb.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.PlatformTransactionManager;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = {
        SomeServiceWithTransaction.class,
        TransactionalTestConfiguration.class
})
class SomeServiceWithTransactionTest {

    @Autowired
    private SomeServiceWithTransaction service;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @MockBean
    private SubServiceA subServiceA;

    @MockBean
    private SubServiceB subServiceB;

    @Test
    void shouldRollback() {
        doAnswer(invocationOnMock -> {
            throw new RuntimeException("BOOM!");
        }).when(subServiceB).doB();

        try {
            service.doStuff();
        } catch (Exception ignored) {
        }

        verify(subServiceA).doA();
        verify(subServiceB).doB();
        verify(transactionManager, atLeastOnce()).rollback(any());
        verify(transactionManager, never()).commit(any());
    }

    @Test
    void shouldCommit() {
        service.doStuff();

        verify(subServiceA).doA();
        verify(subServiceB).doB();
        verify(transactionManager, never()).rollback(any());
        verify(transactionManager).commit(any());
    }
}