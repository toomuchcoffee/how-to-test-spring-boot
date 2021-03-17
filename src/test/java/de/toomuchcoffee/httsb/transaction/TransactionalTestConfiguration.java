package de.toomuchcoffee.httsb.transaction;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.mockito.Answers.RETURNS_DEEP_STUBS;

@TestConfiguration
@EnableTransactionManagement
public class TransactionalTestConfiguration {

    @MockBean(answer = RETURNS_DEEP_STUBS)
    private PlatformTransactionManager transactionManager;

    @Bean
    public PlatformTransactionManager transactionManager() {
        return transactionManager;
    }
}
