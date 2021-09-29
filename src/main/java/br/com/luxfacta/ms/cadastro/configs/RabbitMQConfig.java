package br.com.luxfacta.ms.cadastro.configs;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

	@Value("${spring.rabbitmq.queue}")
	private String QUEUE;
	
	public static final String EXCHANGE = "message_exchange";
	public static final String ROUTING_KEY = "message_routingKey";
	
	@Bean
	public Queue queue() {
		return new Queue(QUEUE, true);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE);
	}
	
	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder
				.bind(queue)
				.to(exchange)
				.with(ROUTING_KEY);
	}
	
	@Bean 
	public Jackson2JsonMessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(messageConverter());
		
		return template;
	}
	
	@Bean
	ApplicationRunner runner(ConnectionFactory cf) {
	    return args -> {
	        boolean open = false;
	        while(!open) {
	            try {
	                cf.createConnection().close();
	                open = true;
	            }
	            catch (Exception e) {
	                Thread.sleep(5000);
	            }
	        }
	    };
	}
}
