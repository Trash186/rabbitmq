package workquene;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import helloworld.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author 萧润东
 */
public class Provider
{
    public static void main(String[] args) throws IOException
    {
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("work",true,false,false,null);

        for (int i = 0; i <10 ; i++)
        {
            channel.basicPublish("","work",null,(i+"hello work queue").getBytes());
        }


        RabbitMQUtils.closeConnectionAndChanel(channel,connection);


    }
}
