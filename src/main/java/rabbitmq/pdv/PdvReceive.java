package rabbitmq.pdv;

import rabbitmq.rabbit.RabbitmqConnection;

public class PdvReceive {

    public static void iniciarPdvReceive(String QUEUE_NAME, String corrId) throws Exception {

        RabbitmqConnection connection = new RabbitmqConnection();

        connection.channel().basicConsume(QUEUE_NAME, true, (consumerTag, delivery) -> {
            System.out.println(" [x] receive initialized ");
            System.out.println(new String(delivery.getBody()));
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {

            }
        }, consumerTag -> {
        });


//        channel.queueDeclare(nomeDaFIla, false, false, false, null);
//        System.out.println(" [*] PDV Waiting for messages.");
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
//            System.out.println(" [x] PDV Receiveddd '" + message + "'");
//        };
//        channel.basicConsume(nomeDaFIla, true, deliverCallback, consumerTag -> {
//        });
    }
}
