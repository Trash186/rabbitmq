package direct;

import com.rabbitmq.client.*;
import helloworld.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author 萧润东
 */
public class Customer1
{
    public static void main(String[] args) throws  Exception
    {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("logs_direct","direct");

        String queue=channel.queueDeclare().getQueue();//获取临时队列

        channel.queueBind(queue,"logs_direct","error");//绑定队列

        //消费
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException
            {
                System.out.println("消费者1"+new String(body));
            }
        });
    }
}
