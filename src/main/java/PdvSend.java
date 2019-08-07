import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

public class PdvSend {

    public static String QUEUE = "queue-fixa";

    public static void main(String[] argv) throws Exception {
        Venda venda = new Venda();
        System.out.println("Digite o nome da fila de resposta: ");

        venda.setNomeDaFila(new Scanner(System.in).nextLine());
        venda.setId(1);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        createQueue(venda, channel);

    }

    public static void createQueue(Venda venda, Channel channel) throws Exception {
        channel.queueDeclare(QUEUE, false, false, false, null);
        channel.basicPublish("", QUEUE, null, mapperToByte(venda));
        System.out.println(" [x] PDV Sent '" + venda + "'");
        AppReceive.iniciarAppReceive(QUEUE, channel);


    }

    public static byte[] mapperToByte(Venda venda) throws Exception{
        return new ObjectMapper().writeValueAsBytes(venda);
    }


}

