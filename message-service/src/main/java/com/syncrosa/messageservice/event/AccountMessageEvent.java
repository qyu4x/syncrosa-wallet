package com.syncrosa.messageservice.event;

public record AccountMessageEvent(Long accountNumber, String phoneNumber, String email) {
}
