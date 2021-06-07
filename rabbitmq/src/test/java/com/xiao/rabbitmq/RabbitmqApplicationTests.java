package com.xiao.rabbitmq;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = RabbitmqApplication.class)
@RunWith(SpringRunner.class)
class RabbitmqApplicationTests
{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test()
    {
        rabbitTemplate.convertAndSend("hello","hello_word") ;
    }

    @Test
    public  void workTest()
    {
        rabbitTemplate.convertAndSend("work","work模型");
    }
    @Test
    public void testFanout()
    {
        rabbitTemplate.convertAndSend("logs","","Fanout模型发送的消息");
    }





}
