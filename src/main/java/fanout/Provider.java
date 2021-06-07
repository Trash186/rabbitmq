package fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import helloworld.utils.RabbitMQUtils;

/**
 * @author 萧润东
 */
public class Provider
{
    public static void main(String[] args) throws  Exception
    {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //1 交换机名称
        //2 交换机类型
        channel.exchangeDeclare("logs","fanout");

        //发送消息
        channel.basicPublish("logs","",null,"fanout type message".getBytes());
        //释放资源
        RabbitMQUtils.closeConnectionAndChanel(channel,connection);
    }
}
