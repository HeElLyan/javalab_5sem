package ru.he.consumers;

import ru.he.consumers.direct.DirectEdPackConsumer;
import ru.he.consumers.direct.DirectJobConsumer;
import ru.he.consumers.direct.DirectOpenDebitConsumer;
import ru.he.consumers.direct.DirectStudHolidayConsumer;
import ru.he.consumers.fanout.FanoutCounterConsumer;
import ru.he.consumers.topic.TopicCopiesCounterConsumer;

public class Main {

    public static void main(String[] args) {
        new DirectJobConsumer().run();
        new DirectEdPackConsumer().run();
        new DirectOpenDebitConsumer().run();
        new DirectStudHolidayConsumer().run();
        new TopicCopiesCounterConsumer().run();
        new FanoutCounterConsumer().run();
    }

}
