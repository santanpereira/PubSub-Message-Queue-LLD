package com.santan.public_interface;

import com.santan.model.Message;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class Subscriber {
    private String topicName;
    private final String id;
    private final MessageBroker messageBroker;

    public Subscriber(MessageBroker messageBroker) {
        this.id = UUID.randomUUID().toString();
        this.messageBroker = messageBroker;
    }

    public void subscribe(String topicName) {
        this.topicName = topicName;
        this.messageBroker.subscribe(topicName, this);
    }

    public void unsubscribe() {
        this.messageBroker.unsubscribe(this.topicName, this);
    }

    public void resetOffset(@NonNull Subscriber subscriber, @NonNull Integer offset) {
        this.messageBroker.resetOffset(this.topicName,this, offset);
    }

    public void consume(Message message) {

        System.out.println("Topic "+ this.topicName + " message received : " + message);
    }
}
