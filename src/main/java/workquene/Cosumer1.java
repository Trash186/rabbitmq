package workquene;

import com.rabbitmq.client.*;
import helloworld.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author 萧润东
 */
public class Cosumer1
{
    public static void main(String[] args) throws IOException
    {
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("work",true,false,false,null);

        channel.basicConsume("work",true,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException
            {
                System.out.println("消费者1"+new String(body));
            }
        });
    }
}
