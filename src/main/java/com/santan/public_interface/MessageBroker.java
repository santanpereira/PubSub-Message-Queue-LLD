package com.santan.public_interface;

import com.santan.model.Message;
import com.santan.model.Topic;
import com.santan.model.TopicSubscriber;
import com.santan.notifier.SubscriberNotifier;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class MessageBroker {
    private final Map<String, Topic> topics;
    private final SubscriberNotifier subscriberNotifier;

    public MessageBroker() {
        this.topics = new HashMap<>();
        this.subscriberNotifier = new SubscriberNotifier();
    }

    public Topic createTopic(String topicName) {
        Topic topic = new Topic(topicName);
        topics.put(topicName, topic);
        return topic;
    }

    public void subscribe(@NonNull final String topicName, @NonNull final Subscriber subscriber) {
        getTopic(topicName).addSubscriber(new TopicSubscriber(subscriber));
    }

    public void unsubscribe(@NonNull final String topicName, @NonNull final Subscriber subscriber) {
        Topic topic = getTopic(topicName);
        TopicSubscriber topicSubscriber = getTopicSubscriber(topic, subscriber);
        topic.removeSubscriber(topicSubscriber);
    }

    public void publish(@NonNull final String topicName, @NonNull final Message message) {
        Topic publishTopic = getTopic(topicName);
        publishTopic.addMessage(message);
        this.subscriberNotifier.broadcast(publishTopic);
    }

    public void resetOffset(@NonNull String topicName, @NonNull Subscriber subscriber, @NonNull Integer offset) {
        Topic topic = getTopic(topicName);
        TopicSubscriber topicSubscriber = getTopicSubscriber(topic, subscriber);
        topicSubscriber.getOffset().set(offset);
        this.subscriberNotifier.broadcastToSubscriber(topic, topicSubscriber);
    }

    private TopicSubscriber getTopicSubscriber(Topic topic, Subscriber subscriber) {
        for(TopicSubscriber topicSubscriber: topic.getTopicSubscribers()) {
            if(topicSubscriber.getSubscriber().equals(subscriber)) {
                return topicSubscriber;
            }
        }

        return null;
    }

    private Topic getTopic(String topicName) {
        return topics.get(topicName);
    }
}
