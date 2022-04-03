package com.santan.notifier;

import com.santan.model.Topic;
import com.santan.model.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SubscriberNotifier {
    final Integer MAX_PUBLISHER_THREADS = 10;
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(MAX_PUBLISHER_THREADS);

    private final Map<String, NotificationHandler> notificationHandlers;

    public SubscriberNotifier() {
        notificationHandlers = new HashMap<>();
    }

    public void broadcast(Topic topic) {
        executor.submit(() -> broadcastToAllSubscriber(topic));
    }

    public void broadcastToSubscriber(Topic topic, TopicSubscriber topicSubscriber) {
        executor.submit(() -> broadcastToSubscriberHelper(topic, topicSubscriber));
    }

    private void broadcastToAllSubscriber(Topic topic) {
        for(TopicSubscriber topicSubscriber : topic.getTopicSubscribers()) {
            broadcastToSubscriberHelper(topic, topicSubscriber);
        }
    }

    private void broadcastToSubscriberHelper(Topic topic, TopicSubscriber topicSubscriber) {
        String subscriberId = topicSubscriber.getSubscriber().getId();

        if(!notificationHandlers.containsKey(subscriberId)) {
            NotificationHandler notificationHandler = new NotificationHandler(topic, topicSubscriber);
            notificationHandlers.put(subscriberId, notificationHandler);
            executor.submit(notificationHandler);
        }

        notificationHandlers.get(subscriberId).notifySubscriber();
    }
}
