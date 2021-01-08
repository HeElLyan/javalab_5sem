package ru.he.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.he.data.Keys;
import ru.he.models.Request;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static void main(String[] args) {

        while (true) {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(Keys.HOST_NAME);

            List<Request> requests = Keys.requests;

            System.out.println("Enter the number of what you need:");
            for (int i = 0; i < requests.size(); i++) {
                System.out.printf("%s %s%n", i + 1, requests.get(i).getName());
            }

            Scanner scanner = new Scanner(System.in);
            int num = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the number of your " + requests.get(num - 1).getDocumentType());
            String line = scanner.nextLine();
            String routingKey = requests.get(num - 1).getDocumentCode();

            try (Connection connection = connectionFactory.newConnection();
                 Channel channel = connection.createChannel()) {
                channel.basicPublish(Keys.EXCHANGE_NAME_FANOUT, routingKey, null, line.getBytes(StandardCharsets.UTF_8));
                channel.basicPublish(Keys.EXCHANGE_NAME_DIRECT_ED_PACK, routingKey, null, line.getBytes(StandardCharsets.UTF_8));
                channel.basicPublish(Keys.EXCHANGE_NAME_DIRECT_JOB, routingKey, null, line.getBytes(StandardCharsets.UTF_8));
                channel.basicPublish(Keys.EXCHANGE_NAME_DIRECT_OPEN_DEBIT, routingKey, null, line.getBytes(StandardCharsets.UTF_8));
                channel.basicPublish(Keys.EXCHANGE_NAME_DIRECT_STUD_HOLIDAY, routingKey, null, line.getBytes(StandardCharsets.UTF_8));
                channel.basicPublish(Keys.EXCHANGE_NAME_TOPIC, routingKey, null, line.getBytes(StandardCharsets.UTF_8));
            } catch (IOException | TimeoutException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }


}
