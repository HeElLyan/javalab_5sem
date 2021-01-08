package ru.he.consumers.direct;

import ru.he.consumers.Consumer;
import ru.he.data.Keys;

import java.util.UUID;

public class DirectJobConsumer extends Consumer {

    @Override
    protected String getFilename() {
        return "direct_job_" + UUID.randomUUID() + ".pdf";
    }

    @Override
    protected String exchangeName() {
        return Keys.EXCHANGE_NAME_DIRECT_JOB;
    }

    @Override
    protected String exchangeType() {
        return Keys.EXCHANGE_DIRECT;
    }

    @Override
    protected String exchangeRoutingKey() {
        return "document.copy.passport";
    }

    @Override
    protected String queueName() {
        return Keys.EXCHANGE_NAME_DIRECT_JOB + "_queue";
    }

    @Override
    protected String templateName() {
        return "direct_job.html";
    }


}
