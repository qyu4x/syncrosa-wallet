package com.syncrosa.usersservice.event;

public record AccountMessageEvent(Long accountNumber, String name, String phoneNumber, String email) {
}
