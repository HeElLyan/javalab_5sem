package ru.he.consumers.direct;

import ru.he.consumers.Consumer;
import ru.he.data.Keys;

import java.util.UUID;

public class DirectOpenDebitConsumer extends Consumer {

    @Override
    protected String getFilename() {
        return "direct_open_debit_" + UUID.randomUUID() + ".pdf";
    }

    @Override
    protected String exchangeName() {
        return Keys.EXCHANGE_NAME_DIRECT_OPEN_DEBIT;
    }

    @Override
    protected String exchangeType() {
        return Keys.EXCHANGE_DIRECT;
    }

    @Override
    protected String exchangeRoutingKey() {
        return "document.original.passport";
    }

    @Override
    protected String queueName() {
        return Keys.EXCHANGE_NAME_DIRECT_OPEN_DEBIT + "_queue";
    }

    @Override
    protected String templateName() {
        return "direct_open_debit.html";
    }
}
