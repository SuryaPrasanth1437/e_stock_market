package com.stock.market.stockprice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
/**
 * @author Ksp This class is used for configuring kafka to consume message
 *
 */
@Configuration
@EnableKafka
public class KafkaConfig {

	   @Bean
	    public ConsumerFactory<String, String>
	    consumerFactory()
	    {
	 
	        // HashMap to store the configurations
	        Map<String, Object> map = new HashMap<>();
	 
	        // put the host IP inn the map
	        map.put(ConsumerConfig
	                    .BOOTSTRAP_SERVERS_CONFIG,
	                "127.0.0.1:9092");
	 
	        // put the group ID in the map
	        map.put(ConsumerConfig
	                    .GROUP_ID_CONFIG,
	                "id");
	        map.put(ConsumerConfig
	                    .KEY_DESERIALIZER_CLASS_CONFIG,
	                StringDeserializer.class);
	        map.put(ConsumerConfig
	                    .VALUE_DESERIALIZER_CLASS_CONFIG,
	                StringDeserializer.class);
	 
	        return new DefaultKafkaConsumerFactory<>(map);
	    }
	 
	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String,
	                                                   String>
	    kafkaListner()
	    {
	        ConcurrentKafkaListenerContainerFactory<String,
	                                                String>
	            obj
	            = new ConcurrentKafkaListenerContainerFactory<>();
	        obj.setConsumerFactory(consumerFactory());
	        return obj;
	    }
}
