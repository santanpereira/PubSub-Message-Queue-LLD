package com.santan.model;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Topic {
    private final String topicId;
    private final String topicName;
    private final List<Message> messages;
    private final List<TopicSubscriber> topicSubscribers;

    public Topic(@NonNull final String topicName) {
        this.topicId = UUID.randomUUID().toString();
        this.topicName = topicName;
        this.messages = new ArrayList<>();
        this.topicSubscribers = new ArrayList<>();
    }

    public String getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public List<TopicSubscriber> getTopicSubscribers() {
        return Collections.unmodifiableList(topicSubscribers);
    }

    public synchronized void addMessage(final Message message) {
        messages.add(message);
    }

    public void addSubscriber(@NonNull final TopicSubscriber subscriber) {
        topicSubscribers.add(subscriber);
    }

    public void removeSubscriber(@NonNull final TopicSubscriber subscriber) {
        topicSubscribers.remove(subscriber);
    }
}
