import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public class AppReceive {


    public static void iniciarAppReceive(String QUEUE_NAME, Channel channel) throws Exception {

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] APP Waiting for messages.");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] APP Received '" + message + "'");

            Venda venda = new ObjectMapper().readValue(message, Venda.class);
            try {
                AppSend.appSend(venda.getNomeDaFila(), channel);
            } catch (Exception ex) {
            }
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });

    }


}
