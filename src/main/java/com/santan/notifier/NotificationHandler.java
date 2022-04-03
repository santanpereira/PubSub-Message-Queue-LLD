package com.santan.notifier;

import com.santan.model.Message;
import com.santan.model.Topic;
import com.santan.model.TopicSubscriber;
import lombok.SneakyThrows;

public class NotificationHandler implements Runnable {
    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    public NotificationHandler(Topic topic, TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (topicSubscriber) {
            while(true) {
               int offset = topicSubscriber.getOffset().get();
               while(offset == topic.getMessages().size()) {
                   topicSubscriber.wait();
               }

               Message message = topic.getMessages().get(offset);
               topicSubscriber.getSubscriber().consume(message);

               topicSubscriber.getOffset().compareAndSet(offset, offset+1);
            }
        }
    }

    public void notifySubscriber() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }
}
