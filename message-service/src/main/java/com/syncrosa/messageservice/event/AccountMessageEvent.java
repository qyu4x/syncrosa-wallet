package com.syncrosa.messageservice.event;

public record AccountMessageEvent(Long accountNumber, String name, String phoneNumber, String email) {
}
