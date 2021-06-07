package direct;

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

        //通过通道声明交换机
        //参数1：交换机名称
        //参数2：路由模式
        channel.exchangeDeclare("logs_direct","direct");
        //发送消息
        //定义路由
        String routingkey="info";
        channel.basicPublish("logs_direct",routingkey,null,"这是direct模型".getBytes());

        //关闭资源
        RabbitMQUtils.closeConnectionAndChanel(channel,connection);

    }
}
