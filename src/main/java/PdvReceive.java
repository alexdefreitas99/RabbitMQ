import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public class PdvReceive {

    public static void iniciarPdvReceive(String nomeDaFIla, Channel channel) throws Exception {

        channel.queueDeclare(nomeDaFIla, false, false, false, null);
        System.out.println(" [*] PDV Waiting for messages.");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] PDV Receiveddd '" + message + "'");
        };
        channel.basicConsume(nomeDaFIla, true, deliverCallback, consumerTag -> {
        });
    }
}
