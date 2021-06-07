package helloworld;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;

/**
 * @author 萧润东
 */
public class Customer
{
    @Test
    public void test() throws  Exception
    {

    }

    public static void main(String[] args) throws  Exception
    {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("hello",false,false,false,null);
        //autoack 开始消息的自动确认机制
        //消费时的回调接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            //最后一参数，消息队列中取出的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException
            {
                System.out.println(new String(body));
            }
        });

        //一般不关，因为需要监听，然后多次消费
//        channel.close();
//        connection.close();
    }
}
