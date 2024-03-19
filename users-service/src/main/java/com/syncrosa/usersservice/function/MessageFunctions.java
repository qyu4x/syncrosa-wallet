package com.syncrosa.usersservice.function;

import com.syncrosa.usersservice.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class MessageFunctions {

    private Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Consumer<Long> receiveUpdatedMessageStatus(IAccountService accountService) {
        return accountNumber -> {
            log.info("Updating communication status for the account number: {}", accountNumber);
            accountService.updateEventStatus(accountNumber);
        };
    }

}
