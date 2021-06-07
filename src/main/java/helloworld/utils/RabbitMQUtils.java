package helloworld.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author 萧润东
 */
public class RabbitMQUtils
{
    //连接工厂是一个重量级资源，并不需要一建立连接就new
    private static ConnectionFactory connectionFactory;

    static
    {
        connectionFactory=new ConnectionFactory();
        //2、设置连接mq的主机
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");

        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
    }

    //提供连接对象
    public static Connection getConnection()
    {
        try
        {
            //获取连接对象
          return  connectionFactory.newConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    //关闭连接和通道
    public static void closeConnectionAndChanel(Channel channel,Connection connection)
    {
        try
        {
            if(channel!=null)
            {
                channel.close();
            }
           if(connection!=null)
           {
               connection.close();
           }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
