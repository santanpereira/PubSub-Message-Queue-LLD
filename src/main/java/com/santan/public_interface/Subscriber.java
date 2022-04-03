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

    public Subscriber(@NonNull MessageBroker messageBroker) {
        this.id = UUID.randomUUID().toString();
        this.messageBroker = messageBroker;
    }

    public Subscriber(@NonNull MessageBroker messageBroker,@NonNull String topicName) {
        this.id = UUID.randomUUID().toString();
        this.messageBroker = messageBroker;
        subscribeToTopic(topicName);
    }

    public void subscribe(@NonNull String topicName) {
        subscribeToTopic(topicName);
    }

    private void subscribeToTopic(@NonNull String topicName) {
        this.topicName = topicName;
        this.messageBroker.subscribe(topicName, this);
    }

    public void unsubscribe() {
        this.messageBroker.unsubscribe(this.topicName, this);
    }

    public void resetOffset(@NonNull Integer offset) {
        this.messageBroker.resetOffset(this.topicName,this, offset);
    }

    public void consume(@NonNull Message message) {
        System.out.println("subscriber : "+ getId().substring(0, 4) + " Topic "+ getTopicName() + " message received : " + message);
    }
}
