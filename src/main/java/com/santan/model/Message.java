package com.santan.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.Date;
import java.util.UUID;

@Getter
public class Message {
    private final String message;
    private final String messageId;
    private final Date created;

    public Message(@NonNull String message) {
        this.messageId = UUID.randomUUID().toString();
        this.message = message;
        this.created = new Date();
    }

    @Override
    public String toString() {
        return "{ message: " + message + ", messageId: " + messageId + ", created:" + created + "}";
    }
}
