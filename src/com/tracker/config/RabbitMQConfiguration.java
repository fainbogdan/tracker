package com.tracker.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tracker.integrations.queueListeners.EmailQueueListener;

@Configuration
public class RabbitMQConfiguration {

	@Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
            new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        RabbitAdmin admin=new RabbitAdmin(connectionFactory());
        admin.declareQueue(queue());
        return admin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
    	RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory());
    	rabbitTemplate.setRoutingKey("eventsQueue");
    	rabbitTemplate.setQueue("eventsQueue");
    	return rabbitTemplate;
    }

    @Bean
    public Queue queue() {
       return new Queue("eventsQueue");
    }
    
	@Bean
	@Autowired
	public SimpleMessageListenerContainer messageListenerContainer(EmailQueueListener listener){
		SimpleMessageListenerContainer container=new SimpleMessageListenerContainer(connectionFactory());
		MessageListenerAdapter adapter=new MessageListenerAdapter(listener, "handleMessage");
		container.setMessageListener(adapter);
		container.addQueueNames("eventsQueue");
		return container;
	}
		
}
