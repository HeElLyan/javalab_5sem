package ru.he.consumers.direct;

import ru.he.consumers.Consumer;
import ru.he.data.Keys;

import java.util.UUID;

public class DirectStudHolidayConsumer extends Consumer {

    @Override
    protected String getFilename() {
        return "direct_stud_holiday_" + UUID.randomUUID() + ".pdf";
    }

    @Override
    protected String exchangeName() {
        return Keys.EXCHANGE_NAME_DIRECT_STUD_HOLIDAY;
    }

    @Override
    protected String exchangeType() {
        return Keys.EXCHANGE_DIRECT;
    }

    @Override
    protected String exchangeRoutingKey() {
        return "document.original.studentTicket";
    }

    @Override
    protected String queueName() {
        return Keys.EXCHANGE_NAME_DIRECT_STUD_HOLIDAY + "_queue";
    }

    @Override
    protected String templateName() {
        return "direct_stud_holiday.html";
    }
}
