package com.santan;

import com.santan.model.Message;
import com.santan.public_interface.MessageBroker;
import com.santan.public_interface.Publisher;
import com.santan.public_interface.Subscriber;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MessageBroker messageBroker = new MessageBroker();
        messageBroker.createTopic("one");

        Publisher pub1 = new Publisher(messageBroker, "one");
        Subscriber sub1 = new Subscriber(messageBroker, "one");

        pub1.publish(new Message("Hello"));
        pub1.publish(new Message("World"));
        pub1.publish(new Message("how"));
        pub1.publish(new Message("are"));
        pub1.publish(new Message("you"));

        Subscriber sub2 = new Subscriber(messageBroker, "one");
        sub1.resetOffset(3);
    }
}
