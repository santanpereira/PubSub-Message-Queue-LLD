package com.santan.public_interface;

import com.santan.model.Message;
import com.santan.model.Topic;
import lombok.NonNull;

public class Publisher {
    private String topicName;
    private final MessageBroker messageBroker;

    public Publisher(@NonNull MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    public Publisher(@NonNull MessageBroker messageBroker, @NonNull String topicName) {
        this.messageBroker = messageBroker;
        this.topicName = topicName;
    }

    public void publishTopic(@NonNull String topicName) {
        this.topicName = topicName;
    }

    public void publish(@NonNull Message message) {
        this.messageBroker.publish(this.topicName, message);
    }
}
