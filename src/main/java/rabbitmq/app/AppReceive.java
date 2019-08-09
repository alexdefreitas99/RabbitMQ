package rabbitmq.app;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DeliverCallback;
import rabbitmq.model.Venda;
import rabbitmq.rabbit.RabbitmqConnection;

public class AppReceive {

    public static String QUEUE_NAME = "queue-fixa";

    public static void main(String[] argv) throws Exception {

        RabbitmqConnection rabbitmqConnection = new RabbitmqConnection();


        rabbitmqConnection.channel().queueDeclare(QUEUE_NAME, false, false, false, null);

        rabbitmqConnection.channel().queuePurge(QUEUE_NAME);

        rabbitmqConnection.channel().basicQos(1);

        System.out.println(" [*] APP Waiting for messages.");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                    .Builder()
                    .correlationId(delivery.getProperties().getCorrelationId())
                    .build();

            try {
                String message = new String(delivery.getBody(), "UTF-8");
                Venda venda = new ObjectMapper().readValue(message, Venda.class);

                System.out.println(" [x] APP Received In string: '" + message + "' In object: " + venda);

                AppSend.appSend(rabbitmqConnection ,delivery, replyProps);
            } catch (Exception e) {
                System.out.println(" [.] " + e.toString());
            }
        };

        rabbitmqConnection.channel().basicConsume(QUEUE_NAME, false, deliverCallback, (consumerTag -> {
        }));

    }


}
