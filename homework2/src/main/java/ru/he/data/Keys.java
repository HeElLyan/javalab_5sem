package ru.he.data;

import ru.he.models.Request;

import java.util.Arrays;
import java.util.List;

public class Keys {

    public final static String EXCHANGE_NAME_DIRECT_JOB = "documents_direct_job";
    public final static String EXCHANGE_NAME_DIRECT_ED_PACK = "documents_direct_ed_pack";
    public final static String EXCHANGE_NAME_DIRECT_OPEN_DEBIT = "documents_direct_open_debit";
    public final static String EXCHANGE_NAME_DIRECT_STUD_HOLIDAY = "documents_direct_stud_holiday";

    public final static String EXCHANGE_NAME_FANOUT = "documents_fanout";
    public final static String EXCHANGE_NAME_TOPIC = "documents_topic";

    public final static String EXCHANGE_FANOUT = "fanout";
    public final static String EXCHANGE_DIRECT = "direct";
    public final static String EXCHANGE_TOPIC = "topic";

    public final static String HOST_NAME = "localhost";

    public final static List<Request> requests = Arrays.asList(
            new Request("Get a job", "number of passport", "document.copy.passport"),
            new Request("Get a student education pack", "number of student ticket", "document.copy.studentTicket"),
            new Request("Open debit account", "number of original passport", "document.original.passport"),
            new Request("Get a student holiday", "number of original student ticket", "document.original.studentTicket")
    );

}
