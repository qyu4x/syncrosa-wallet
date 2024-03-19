package com.syncrosa.messageservice.function;

import com.syncrosa.messageservice.event.AccountMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<AccountMessageEvent, AccountMessageEvent> email() {
        return accountMessageEvent -> {
            log.info("sending email with the details: {}", accountMessageEvent.toString());
            return accountMessageEvent;
        };
    }

    @Bean
    public Function<AccountMessageEvent, Long> sms() {
        return accountMessageEvent -> {
            log.info("sending sms with the details: {}", accountMessageEvent.toString());
            return accountMessageEvent.accountNumber();
        };
    }
}
