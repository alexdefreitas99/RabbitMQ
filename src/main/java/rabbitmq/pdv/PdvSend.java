package rabbitmq.pdv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import rabbitmq.model.Venda;
import rabbitmq.rabbit.RabbitmqConnection;

import java.util.UUID;


public class PdvSend {

    public static String QUEUE_NAME = "queue-fixa";

    public static void main(String[] argv) throws Exception {

        RabbitmqConnection connection = new RabbitmqConnection();

        final String corrId = UUID.randomUUID().toString();
        Venda venda = Venda.builder().clienteNome("Alex").id(1).numeroPedido(1).build();
        byte[] objectInByte = new ObjectMapper().writeValueAsBytes(venda);

        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(connection.channel().queueDeclare().getQueue())
                .build();

        System.out.println("Publishing venda in " + QUEUE_NAME);

        connection.channel().basicPublish("", QUEUE_NAME, props, objectInByte);

        PdvReceive.iniciarPdvReceive(QUEUE_NAME, corrId);


//        connection.channel().queueDeclare(QUEUE_NAME, false, false, false, null);
//        connection.channel().basicPublish("", QUEUE_NAME, null, objectInByte);
//        System.out.println(" [x] PDV Sent '" + venda + "'");

    }

}

