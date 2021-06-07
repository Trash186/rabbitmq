package workquene;

import com.rabbitmq.client.*;
import helloworld.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author 萧润东
 */
public class Cosumer2
{
    public static void main(String[] args) throws IOException
    {
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.basicQos(1);//每次只能消费一个消息,不能一次性把消息给消费者
        channel.queueDeclare("work",true,false,false,null);
//参数2：autoAck：消息自动确认，true 消费者自动向rabbitmq确认消息消费
        //false 不会自动确认消息
        channel.basicConsume("work",true,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException
            {
                System.out.println("消费者2"+new String(body));
                //手动确认 ，1：手动确认消息标识，2：每次确认一个
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
