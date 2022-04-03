package com.santan.model;

import com.santan.public_interface.Subscriber;
import lombok.Getter;
import lombok.NonNull;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class TopicSubscriber {
    private final AtomicInteger offset;
    private final Subscriber subscriber;

    public TopicSubscriber(@NonNull Subscriber subscriber) {
        this.subscriber = subscriber;
        this.offset = new AtomicInteger(0);
    }
}
