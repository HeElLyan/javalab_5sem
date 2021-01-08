package ru.he.consumers.direct;

import ru.he.consumers.Consumer;
import ru.he.data.Keys;

import java.util.UUID;

public class DirectEdPackConsumer extends Consumer {

    @Override
    protected String getFilename() {
        return "direct_ed_pack_" + UUID.randomUUID() + ".pdf";
    }

    @Override
    protected String exchangeName() {
        return Keys.EXCHANGE_NAME_DIRECT_ED_PACK;
    }

    @Override
    protected String exchangeType() {
        return Keys.EXCHANGE_DIRECT;
    }

    @Override
    protected String exchangeRoutingKey() {
        return "document.copy.studentTicket";
    }

    @Override
    protected String queueName() {
        return Keys.EXCHANGE_NAME_DIRECT_ED_PACK + "_queue";
    }

    @Override
    protected String templateName() {
        return "direct_ed_pack.html";
    }

}
