package fanout;

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

        //1 交换机名称
        //2 交换机类型
        channel.exchangeDeclare("logs","fanout");

        //获取临时队列
        String queueName = channel.queueDeclare().getQueue();

        //交换机与队列绑定

        channel.queueBind(queueName,"logs","");
        //消费消息
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException
            {
                System.out.println("消费者1:"+new String(body));
            }
        });
    }
}
