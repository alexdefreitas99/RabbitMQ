import com.rabbitmq.client.Channel;

public class AppSend {

    public static String response = "Pode finalizar a venda";


    public static void appSend(String nomeDaFila, Channel channel) throws Exception {

        try {
            channel.queueDeclare(nomeDaFila, false, false, false, null);
            channel.basicPublish("", nomeDaFila, null, response.getBytes());

            System.out.println(" [x] APP Sent to fila " + nomeDaFila + " '" + response + "'");

            PdvReceive.iniciarPdvReceive(nomeDaFila, channel);


        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


}
