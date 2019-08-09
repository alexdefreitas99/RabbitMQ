package rabbitmq.app;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Delivery;
import rabbitmq.rabbit.RabbitmqConnection;

public class AppSend {

    public static String response = "Pode finalizar a venda";

    public static void appSend(RabbitmqConnection rabbitmqConnection, Delivery delivery, AMQP.BasicProperties replyProps) throws Exception {

        System.out.println("[x] Publishing");
        rabbitmqConnection.channel().basicPublish("", delivery.getProperties().getReplyTo(), replyProps, response.getBytes());
        rabbitmqConnection.channel().basicAck(delivery.getEnvelope().getDeliveryTag(), false);

    }


}
