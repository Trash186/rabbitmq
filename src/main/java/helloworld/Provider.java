package helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 萧润东
 */
public class Provider
{
    /**
     * 直连模型，无交换机
     * @throws IOException
     * @throws TimeoutException
     */
    @Test
    public void testSendMessage() throws IOException, TimeoutException
    {

        //1、创建mq的连接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2、设置连接mq的主机
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");

        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");


        //获取连接对象
        Connection connection = connectionFactory.newConnection();

        //获取连接中的通道
        Channel channel1 = connection.createChannel();

        //1、生命队列名称
        //2、durable用来定义队列是否持久化
        //3、exclusive 是否独占队列
        //4、autoDelete 自动删除队列
        //5、额外参数
        channel1.queueDeclare("hello",false,false,false,null);

        //发布消息
        //1、交换机
        //2、队列名
        //属性(消息持久化？) 不开启，重启mq时消息就会丢失
        channel1.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello rabbitMq".getBytes());

        //关闭连接
        channel1.close();
        connection.close();
    }
}
