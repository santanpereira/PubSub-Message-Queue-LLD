package com.santan.public_interface;

import com.santan.model.Message;
import com.santan.model.Topic;

public class Publisher {
    private String topicName;
    private final MessageBroker messageBroker;

    public Publisher(MessageBroker messageBroker, String topicName) {
        this.messageBroker = messageBroker;
        this.topicName = topicName;
    }

    public void publish(Message message) {
        this.messageBroker.publish(this.topicName, message);
    }
}
